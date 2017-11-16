package net.fnsco.trading.service.pay.channel.zxyh.demo;

import java.net.*;
import java.util.regex.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.io.*;

public class Json2
{
    public static final Factory defaultFactory;
    private static Factory globalFactory;
    private static ThreadLocal<Factory> threadFactory;
    Json2 enclosing;
    static NullJson topnull;
    static Escaper escaper;
    
    static String fetchContent(final URL url) {
        java.io.Reader reader = null;
        try {
            reader = new InputStreamReader((InputStream)url.getContent());
            final StringBuilder content = new StringBuilder();
            final char[] buf = new char[1024];
            for (int n = reader.read(buf); n > -1; n = reader.read(buf)) {
                content.append(buf, 0, n);
            }
            return content.toString();
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (Throwable t) {}
            }
        }
    }
    
    static Json2 resolvePointer(final String pointerRepresentation, final Json2 top) {
        final String[] parts = pointerRepresentation.split("/");
        Json2 result = top;
        for (String p : parts) {
            if (p.length() != 0) {
                p = p.replace("~1", "/").replace("~0", "~");
                if (result.isArray()) {
                    result = result.at(Integer.parseInt(p));
                }
                else {
                    if (!result.isObject()) {
                        throw new RuntimeException("Can't resolve pointer " + pointerRepresentation + " on document " + top.toString(200));
                    }
                    result = result.at(p);
                }
            }
        }
        return result;
    }
    
    static URI makeAbsolute(final URI base, final String ref) throws Exception {
        URI refuri;
        if (base != null && !new URI(ref).isAbsolute()) {
            final StringBuilder sb = new StringBuilder().append(base.getScheme()).append("://").append(base.getHost());
            if (base.getPort() > -1) {
                sb.append(":").append(Integer.toString(base.getPort()));
            }
            if (!ref.startsWith("/")) {
                sb.append(base.getPath()).append(ref.startsWith("#") ? "" : "/");
            }
            refuri = new URI(sb.append(ref).toString());
        }
        else {
            refuri = new URI(ref);
        }
        return refuri;
    }
    
    static Json2 resolveRef(final URI base, Json2 refdoc, URI refuri, final Map<String, Json2> resolved, final Map<Json2, Json2> expanded) throws Exception {
        if (refuri.isAbsolute() && (base == null || !base.isAbsolute() || !base.getScheme().equals(refuri.getScheme()) || !base.getHost().equals(refuri.getHost()) || base.getPort() != refuri.getPort() || !base.getPath().equals(refuri.getPath()))) {
            refuri = refuri.normalize();
            final URI docuri = new URI(refuri.getScheme() + "://" + refuri.getHost() + ((refuri.getPort() > -1) ? (":" + refuri.getPort()) : "") + refuri.getPath());
            refdoc = read(fetchContent(docuri.toURL()));
            refdoc = expandReferences(refdoc, refdoc, docuri, resolved, expanded);
        }
        if (refuri.getFragment() == null) {
            return refdoc;
        }
        return resolvePointer(refuri.getFragment(), refdoc);
    }
    
    static Json2 expandReferences(Json2 json, final Json2 topdoc, URI base, final Map<String, Json2> resolved, final Map<Json2, Json2> expanded) throws Exception {
        if (expanded.containsKey(json)) {
            return json;
        }
        if (json.isObject()) {
            if (json.has("id") && json.at("id").isString()) {
                base = base.resolve(json.at("id").asString());
            }
            if (json.has("$ref")) {
                final URI refuri = base.resolve(json.at("$ref").asString());
                Json2 ref = resolved.get(refuri.toString());
                if (ref == null) {
                    ref = resolveRef(base, topdoc, refuri, resolved, expanded);
                    resolved.put(refuri.toString(), ref);
                    ref = expandReferences(ref, topdoc, base, resolved, expanded);
                    resolved.put(refuri.toString(), ref);
                }
                json = ref;
            }
            else {
                final Json2 O = object();
                for (final Map.Entry<String, Json2> e : json.asJsonMap().entrySet()) {
                    O.set(e.getKey(), expandReferences(e.getValue(), topdoc, base, resolved, expanded));
                }
                json.with(O);
            }
        }
        else if (json.isArray()) {
            for (int i = 0; i < json.asJsonList().size(); ++i) {
                final Json2 el = expandReferences(json.at(i), topdoc, base, resolved, expanded);
                json.set(i, el);
            }
        }
        expanded.put(json, json);
        return json;
    }
    
    public static Schema schema(final Json2 S) {
        return new DefaultSchema(null, S);
    }
    
    public static Schema schema(final URI uri) {
        try {
            return new DefaultSchema(uri, read(fetchContent(uri.toURL())));
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static Schema schema(final Json2 S, final URI uri) {
        return new DefaultSchema(uri, S);
    }
    
    private static Factory factory() {
        final Factory f = Json2.threadFactory.get();
        return (f != null) ? f : Json2.globalFactory;
    }
    
    public static void setGlobalFactory(final Factory factory) {
        Json2.globalFactory = factory;
    }
    
    public static void attachFactory(final Factory factory) {
        Json2.threadFactory.set(factory);
    }
    
    public static void dettachFactory() {
        Json2.threadFactory.remove();
    }
    
    public static Json2 read(final String jsonAsString) {
        return (Json2)new Reader().read(jsonAsString);
    }
    
    public static Json2 read(final URL location) {
        return (Json2)new Reader().read(fetchContent(location));
    }
    
    public static Json2 read(final CharacterIterator it) {
        return (Json2)new Reader().read(it);
    }
    
    public static Json2 nil() {
        return factory().nil();
    }
    
    public static Json2 object() {
        return factory().object();
    }
    
    public static Json2 object(final Object... args) {
        final Json2 j = object();
        if (args.length % 2 != 0) {
            throw new IllegalArgumentException("An even number of arguments is expected.");
        }
        for (int i = 0; i < args.length; ++i) {
            j.set(args[i].toString(), factory().make(args[++i]));
        }
        return j;
    }
    
    public static Json2 array() {
        return factory().array();
    }
    
    public static Json2 array(final Object... args) {
        final Json2 A = array();
        for (final Object x : args) {
            A.add(factory().make(x));
        }
        return A;
    }
    
    public static Json2 make(final Object anything) {
        return factory().make(anything);
    }
    
    protected Json2() {
        this.enclosing = null;
    }
    
    protected Json2(final Json2 enclosing) {
        this.enclosing = null;
        this.enclosing = enclosing;
    }
    
    public String toString(final int maxCharacters) {
        return this.toString();
    }
    
    public void attachTo(final Json2 enclosing) {
        this.enclosing = enclosing;
    }
    
    public final Json2 up() {
        return this.enclosing;
    }
    
    public Json2 dup() {
        return this;
    }
    
    public Json2 at(final int index) {
        throw new UnsupportedOperationException();
    }
    
    public Json2 at(final String property) {
        throw new UnsupportedOperationException();
    }
    
    public final Json2 at(final String property, final Json2 def) {
        final Json2 x = this.at(property);
        if (x == null) {
            this.set(property, def);
            return def;
        }
        return x;
    }
    
    public final Json2 at(final String property, final Object def) {
        return this.at(property, make(def));
    }
    
    public boolean has(final String property) {
        throw new UnsupportedOperationException();
    }
    
    public boolean is(final String property, final Object value) {
        throw new UnsupportedOperationException();
    }
    
    public boolean is(final int index, final Object value) {
        throw new UnsupportedOperationException();
    }
    
    public Json2 add(final Json2 el) {
        throw new UnsupportedOperationException();
    }
    
    public final Json2 add(final Object anything) {
        return this.add(make(anything));
    }
    
    public Json2 atDel(final String property) {
        throw new UnsupportedOperationException();
    }
    
    public Json2 atDel(final int index) {
        throw new UnsupportedOperationException();
    }
    
    public Json2 delAt(final String property) {
        throw new UnsupportedOperationException();
    }
    
    public Json2 delAt(final int index) {
        throw new UnsupportedOperationException();
    }
    
    public Json2 remove(final Json2 el) {
        throw new UnsupportedOperationException();
    }
    
    public final Json2 remove(final Object anything) {
        return this.remove(make(anything));
    }
    
    public Json2 set(final String property, final Json2 value) {
        throw new UnsupportedOperationException();
    }
    
    public final Json2 set(final String property, final Object value) {
        return this.set(property, make(value));
    }
    
    public Json2 set(final int index, final Object value) {
        throw new UnsupportedOperationException();
    }
    
    public Json2 with(final Json2 object) {
        throw new UnsupportedOperationException();
    }
    
    public Object getValue() {
        throw new UnsupportedOperationException();
    }
    
    public boolean asBoolean() {
        throw new UnsupportedOperationException();
    }
    
    public String asString() {
        throw new UnsupportedOperationException();
    }
    
    public int asInteger() {
        throw new UnsupportedOperationException();
    }
    
    public float asFloat() {
        throw new UnsupportedOperationException();
    }
    
    public double asDouble() {
        throw new UnsupportedOperationException();
    }
    
    public long asLong() {
        throw new UnsupportedOperationException();
    }
    
    public short asShort() {
        throw new UnsupportedOperationException();
    }
    
    public byte asByte() {
        throw new UnsupportedOperationException();
    }
    
    public char asChar() {
        throw new UnsupportedOperationException();
    }
    
    public Map<String, Object> asMap() {
        throw new UnsupportedOperationException();
    }
    
    public Map<String, Json2> asJsonMap() {
        throw new UnsupportedOperationException();
    }
    
    public List<Object> asList() {
        throw new UnsupportedOperationException();
    }
    
    public List<Json2> asJsonList() {
        throw new UnsupportedOperationException();
    }
    
    public boolean isNull() {
        return false;
    }
    
    public boolean isString() {
        return false;
    }
    
    public boolean isNumber() {
        return false;
    }
    
    public boolean isBoolean() {
        return false;
    }
    
    public boolean isArray() {
        return false;
    }
    
    public boolean isObject() {
        return false;
    }
    
    public boolean isPrimitive() {
        return this.isString() || this.isNumber() || this.isBoolean();
    }
    
    public String pad(final String callback) {
        return (callback != null && callback.length() > 0) ? (callback + "(" + this.toString() + ");") : this.toString();
    }
    
    static {
        defaultFactory = new DefaultFactory();
        Json2.globalFactory = Json2.defaultFactory;
        Json2.threadFactory = new ThreadLocal<Factory>();
        Json2.topnull = new NullJson();
        Json2.escaper = new Escaper(false);
    }
    
    static class DefaultSchema implements Schema
    {
        static Instruction any;
        int maxchars;
        URI uri;
        Json2 theschema;
        Instruction start;
        
        static Json2 maybeError(final Json2 errors, final Json2 E) {
            return (E == null) ? errors : ((errors == null) ? Json2.array() : errors).with(E);
        }
        
        Instruction compile(final Json2 S, final Map<Json2, Instruction> compiled) {
            Instruction result = compiled.get(S);
            if (result != null) {
                return result;
            }
            final Sequence seq = new Sequence();
            compiled.put(S, seq);
            if (S.has("type") && !S.is("type", "any")) {
                seq.add(new CheckType(S.at("type").isString() ? Json2.array().add(S.at("type")) : S.at("type")));
            }
            if (S.has("enum")) {
                seq.add(new CheckEnum(S.at("enum")));
            }
            if (S.has("allOf")) {
                final Sequence sub = new Sequence();
                for (final Json2 x : S.at("allOf").asJsonList()) {
                    sub.add(this.compile(x, compiled));
                }
                seq.add(sub);
            }
            if (S.has("anyOf")) {
                final CheckAny any = new CheckAny();
                any.schema = S.at("anyOf");
                for (final Json2 x : any.schema.asJsonList()) {
                    any.alternates.add(this.compile(x, compiled));
                }
                seq.add(any);
            }
            if (S.has("oneOf")) {
                final CheckOne any2 = new CheckOne();
                any2.schema = S.at("oneOf");
                for (final Json2 x : any2.schema.asJsonList()) {
                    any2.alternates.add(this.compile(x, compiled));
                }
                seq.add(any2);
            }
            if (S.has("not")) {
                seq.add(new CheckNot(this.compile(S.at("not"), compiled), S.at("not")));
            }
            if (S.has("required")) {
                for (final Json2 p : S.at("required").asJsonList()) {
                    seq.add(new CheckPropertyPresent(p.asString()));
                }
            }
            final CheckObject objectCheck = new CheckObject();
            if (S.has("properties")) {
                for (final Map.Entry<String, Json2> p2 : S.at("properties").asJsonMap().entrySet()) {
                    objectCheck.props.add(objectCheck.new CheckProperty(p2.getKey(), this.compile(p2.getValue(), compiled)));
                }
            }
            if (S.has("patternProperties")) {
                for (final Map.Entry<String, Json2> p2 : S.at("patternProperties").asJsonMap().entrySet()) {
                    objectCheck.patternProps.add(objectCheck.new CheckPatternProperty(p2.getKey(), this.compile(p2.getValue(), compiled)));
                }
            }
            if (S.has("additionalProperties")) {
                if (S.at("additionalProperties").isObject()) {
                    objectCheck.additionalSchema = this.compile(S.at("additionalProperties"), compiled);
                }
                else if (!S.at("additionalProperties").asBoolean()) {
                    objectCheck.additionalSchema = null;
                }
            }
            if (S.has("minProperties")) {
                objectCheck.min = S.at("minProperties").asInteger();
            }
            if (S.has("maxProperties")) {
                objectCheck.max = S.at("maxProperties").asInteger();
            }
            if (!objectCheck.props.isEmpty() || !objectCheck.patternProps.isEmpty() || objectCheck.additionalSchema != DefaultSchema.any || objectCheck.min > 0 || objectCheck.max < Integer.MAX_VALUE) {
                seq.add(objectCheck);
            }
            final CheckArray arrayCheck = new CheckArray();
            if (S.has("items")) {
                if (S.at("items").isObject()) {
                    arrayCheck.schema = this.compile(S.at("items"), compiled);
                }
                else {
                    arrayCheck.schemas = new ArrayList<Instruction>();
                    for (final Json2 s : S.at("items").asJsonList()) {
                        arrayCheck.schemas.add(this.compile(s, compiled));
                    }
                }
            }
            if (S.has("additionalItems")) {
                if (S.at("additionalItems").isObject()) {
                    arrayCheck.additionalSchema = this.compile(S.at("additionalItems"), compiled);
                }
                else if (!S.at("additionalItems").asBoolean()) {
                    arrayCheck.additionalSchema = null;
                }
            }
            if (S.has("uniqueItems")) {
                arrayCheck.uniqueitems = S.at("uniqueItems").asBoolean();
            }
            if (S.has("minItems")) {
                arrayCheck.min = S.at("minItems").asInteger();
            }
            if (S.has("maxItems")) {
                arrayCheck.max = S.at("maxItems").asInteger();
            }
            if (arrayCheck.schema != null || arrayCheck.schemas != null || arrayCheck.additionalSchema != DefaultSchema.any || arrayCheck.uniqueitems != null || arrayCheck.max < Integer.MAX_VALUE || arrayCheck.min > 0) {
                seq.add(arrayCheck);
            }
            final CheckNumber numberCheck = new CheckNumber();
            if (S.has("minimum")) {
                numberCheck.min = S.at("minimum").asDouble();
            }
            if (S.has("maximum")) {
                numberCheck.max = S.at("maximum").asDouble();
            }
            if (S.has("multipleOf")) {
                numberCheck.multipleOf = S.at("multipleOf").asDouble();
            }
            if (S.has("exclusiveMinimum")) {
                numberCheck.exclusiveMin = S.at("exclusiveMinimum").asBoolean();
            }
            if (S.has("exclusiveMaximum")) {
                numberCheck.exclusiveMax = S.at("exclusiveMaximum").asBoolean();
            }
            if (!Double.isNaN(numberCheck.min) || !Double.isNaN(numberCheck.max) || !Double.isNaN(numberCheck.multipleOf)) {
                seq.add(numberCheck);
            }
            final CheckString stringCheck = new CheckString();
            if (S.has("minLength")) {
                stringCheck.min = S.at("minLength").asInteger();
            }
            if (S.has("maxLength")) {
                stringCheck.max = S.at("maxLength").asInteger();
            }
            if (S.has("pattern")) {
                stringCheck.pattern = Pattern.compile(S.at("pattern").asString());
            }
            if (stringCheck.min > 0 || stringCheck.max < Integer.MAX_VALUE || stringCheck.pattern != null) {
                seq.add(stringCheck);
            }
            if (S.has("dependencies")) {
                for (final Map.Entry<String, Json2> e : S.at("dependencies").asJsonMap().entrySet()) {
                    if (e.getValue().isObject()) {
                        seq.add(new CheckSchemaDependency(e.getKey(), this.compile(e.getValue(), compiled)));
                    }
                    else if (e.getValue().isArray()) {
                        seq.add(new CheckPropertyDependency(e.getKey(), e.getValue()));
                    }
                    else {
                        seq.add(new CheckPropertyDependency(e.getKey(), Json2.array(e.getValue())));
                    }
                }
            }
            result = ((seq.seq.size() == 1) ? seq.seq.get(0) : seq);
            compiled.put(S, result);
            return result;
        }
        
        DefaultSchema(final URI uri, final Json2 theschema) {
            this.maxchars = 50;
            try {
                this.uri = ((uri == null) ? new URI("") : uri);
                this.theschema = Json2.expandReferences(theschema, theschema, this.uri, new HashMap<String, Json2>(), new IdentityHashMap<Json2, Json2>());
            }
            catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            this.start = this.compile(this.theschema, new IdentityHashMap<Json2, Instruction>());
        }
        
        public Json2 validate(final Json2 document) {
            final Json2 result = Json2.object("ok", true);
            final Json2 errors = this.start.apply(document);
            return (errors == null) ? result : result.set("errors", errors).set("ok", false);
        }
        
        public Json2 generate(final Json2 options) {
            final Json2 result = Json2.nil();
            return result;
        }
        
        static {
            DefaultSchema.any = new Instruction() {
                public Json2 apply(final Json2 param) {
                    return null;
                }
            };
        }
        
        class IsObject implements Instruction
        {
            public Json2 apply(final Json2 param) {
                return param.isObject() ? null : Json2.make(param.toString(DefaultSchema.this.maxchars));
            }
        }
        
        class IsArray implements Instruction
        {
            public Json2 apply(final Json2 param) {
                return param.isArray() ? null : Json2.make(param.toString(DefaultSchema.this.maxchars));
            }
        }
        
        class IsString implements Instruction
        {
            public Json2 apply(final Json2 param) {
                return param.isString() ? null : Json2.make(param.toString(DefaultSchema.this.maxchars));
            }
        }
        
        class IsBoolean implements Instruction
        {
            public Json2 apply(final Json2 param) {
                return param.isBoolean() ? null : Json2.make(param.toString(DefaultSchema.this.maxchars));
            }
        }
        
        class IsNull implements Instruction
        {
            public Json2 apply(final Json2 param) {
                return param.isNull() ? null : Json2.make(param.toString(DefaultSchema.this.maxchars));
            }
        }
        
        class IsNumber implements Instruction
        {
            public Json2 apply(final Json2 param) {
                return param.isNumber() ? null : Json2.make(param.toString(DefaultSchema.this.maxchars));
            }
        }
        
        class IsInteger implements Instruction
        {
            public Json2 apply(final Json2 param) {
                return (param.isNumber() && ((Number)param.getValue()) instanceof Integer) ? null : Json2.make(param.toString(DefaultSchema.this.maxchars));
            }
        }
        
        class CheckString implements Instruction
        {
            int min;
            int max;
            Pattern pattern;
            
            CheckString() {
                this.min = 0;
                this.max = Integer.MAX_VALUE;
            }
            
            public Json2 apply(final Json2 param) {
                Json2 errors = null;
                if (!param.isString()) {
                    return errors;
                }
                final String s = param.asString();
                final int size = s.codePointCount(0, s.length());
                if (size < this.min || size > this.max) {
                    errors = DefaultSchema.maybeError(errors, Json2.make("String  " + param.toString(DefaultSchema.this.maxchars) + " has length outside of the permitted range [" + this.min + "," + this.max + "]."));
                }
                if (this.pattern != null && !this.pattern.matcher(s).matches()) {
                    errors = DefaultSchema.maybeError(errors, Json2.make("String  " + param.toString(DefaultSchema.this.maxchars) + " does not match regex " + this.pattern.toString()));
                }
                return errors;
            }
        }
        
        class CheckNumber implements Instruction
        {
            double min;
            double max;
            double multipleOf;
            boolean exclusiveMin;
            boolean exclusiveMax;
            
            CheckNumber() {
                this.min = Double.NaN;
                this.max = Double.NaN;
                this.multipleOf = Double.NaN;
                this.exclusiveMin = false;
                this.exclusiveMax = false;
            }
            
            public Json2 apply(final Json2 param) {
                Json2 errors = null;
                if (!param.isNumber()) {
                    return errors;
                }
                final double value = param.asDouble();
                if (!Double.isNaN(this.min) && (value < this.min || (this.exclusiveMin && value == this.min))) {
                    errors = DefaultSchema.maybeError(errors, Json2.make("Number " + param + " is below allowed minimum " + this.min));
                }
                if (!Double.isNaN(this.max) && (value > this.max || (this.exclusiveMax && value == this.max))) {
                    errors = DefaultSchema.maybeError(errors, Json2.make("Number " + param + " is above allowed maximum " + this.max));
                }
                if (!Double.isNaN(this.multipleOf) && value / this.multipleOf % 1.0 != 0.0) {
                    errors = DefaultSchema.maybeError(errors, Json2.make("Number " + param + " is not a multiple of  " + this.multipleOf));
                }
                return errors;
            }
        }
        
        class CheckArray implements Instruction
        {
            int min;
            int max;
            Boolean uniqueitems;
            Instruction additionalSchema;
            Instruction schema;
            ArrayList<Instruction> schemas;
            
            CheckArray() {
                this.min = 0;
                this.max = Integer.MAX_VALUE;
                this.uniqueitems = null;
                this.additionalSchema = DefaultSchema.any;
            }
            
            public Json2 apply(final Json2 param) {
                Json2 errors = null;
                if (!param.isArray()) {
                    return errors;
                }
                if (this.schema == null && this.schemas == null && this.additionalSchema == null) {
                    return errors;
                }
                final int size = param.asJsonList().size();
                for (int i = 0; i < size; ++i) {
                    final Instruction S = (this.schema != null) ? this.schema : ((this.schemas != null && i < this.schemas.size()) ? this.schemas.get(i) : this.additionalSchema);
                    if (S == null) {
                        errors = DefaultSchema.maybeError(errors, Json2.make("Additional items are not permitted: " + param.at(i) + " in " + param.toString(DefaultSchema.this.maxchars)));
                    }
                    else {
                        errors = DefaultSchema.maybeError(errors, S.apply(param.at(i)));
                    }
                    if (this.uniqueitems != null && this.uniqueitems && param.asJsonList().lastIndexOf(param.at(i)) > i) {
                        errors = DefaultSchema.maybeError(errors, Json2.make("Element " + param.at(i) + " is duplicate in array."));
                    }
                }
                if (size < this.min || size > this.max) {
                    errors = DefaultSchema.maybeError(errors, Json2.make("Array  " + param.toString(DefaultSchema.this.maxchars) + " has number of elements outside of the permitted range [" + this.min + "," + this.max + "]."));
                }
                return errors;
            }
        }
        
        class CheckPropertyPresent implements Instruction
        {
            String propname;
            
            public CheckPropertyPresent(final String propname) {
                this.propname = propname;
            }
            
            public Json2 apply(final Json2 param) {
                if (!param.isObject()) {
                    return null;
                }
                if (param.has(this.propname)) {
                    return null;
                }
                return Json2.array().add(Json2.make("Required property " + this.propname + " missing from object " + param.toString(DefaultSchema.this.maxchars)));
            }
        }
        
        class CheckObject implements Instruction
        {
            int min;
            int max;
            HashSet<String> checked;
            Instruction additionalSchema;
            ArrayList<Instruction> props;
            ArrayList<Instruction> patternProps;
            
            CheckObject() {
                this.min = 0;
                this.max = Integer.MAX_VALUE;
                this.checked = new HashSet<String>();
                this.additionalSchema = DefaultSchema.any;
                this.props = new ArrayList<Instruction>();
                this.patternProps = new ArrayList<Instruction>();
            }
            
            public Json2 apply(final Json2 param) {
                Json2 errors = null;
                if (!param.isObject()) {
                    return errors;
                }
                this.checked.clear();
                for (final Instruction I : this.props) {
                    errors = DefaultSchema.maybeError(errors, I.apply(param));
                }
                for (final Instruction I : this.patternProps) {
                    errors = DefaultSchema.maybeError(errors, I.apply(param));
                }
                if (this.additionalSchema != DefaultSchema.any) {
                    for (final Map.Entry<String, Json2> e : param.asJsonMap().entrySet()) {
                        if (!this.checked.contains(e.getKey())) {
                            errors = DefaultSchema.maybeError(errors, (this.additionalSchema == null) ? Json2.make("Extra property '" + e.getKey() + "', schema doesn't allow any properties not explicitly defined:" + param.toString(DefaultSchema.this.maxchars)) : this.additionalSchema.apply(e.getValue()));
                        }
                    }
                }
                if (param.asJsonMap().size() < this.min) {
                    errors = DefaultSchema.maybeError(errors, Json2.make("Object " + param.toString(DefaultSchema.this.maxchars) + " has fewer than the permitted " + this.min + "  number of properties."));
                }
                if (param.asJsonMap().size() > this.max) {
                    errors = DefaultSchema.maybeError(errors, Json2.make("Object " + param.toString(DefaultSchema.this.maxchars) + " has more than the permitted " + this.min + "  number of properties."));
                }
                return errors;
            }
            
            class CheckProperty implements Instruction
            {
                String name;
                Instruction schema;
                
                public CheckProperty(final String name, final Instruction schema) {
                    this.name = name;
                    this.schema = schema;
                }
                
                public Json2 apply(final Json2 param) {
                    final Json2 value = param.at(this.name);
                    if (value == null) {
                        return null;
                    }
                    CheckObject.this.checked.add(this.name);
                    return this.schema.apply(param.at(this.name));
                }
            }
            
            class CheckPatternProperty implements Instruction
            {
                Pattern pattern;
                Instruction schema;
                
                public CheckPatternProperty(final String pattern, final Instruction schema) {
                    this.pattern = Pattern.compile(pattern);
                    this.schema = schema;
                }
                
                public Json2 apply(final Json2 param) {
                    Json2 errors = null;
                    for (final Map.Entry<String, Json2> e : param.asJsonMap().entrySet()) {
                        if (this.pattern.matcher(e.getKey()).find()) {
                            errors = DefaultSchema.maybeError(errors, this.schema.apply(e.getValue()));
                            CheckObject.this.checked.add(e.getKey());
                        }
                    }
                    return errors;
                }
            }
        }
        
        class Sequence implements Instruction
        {
            ArrayList<Instruction> seq;
            
            Sequence() {
                this.seq = new ArrayList<Instruction>();
            }
            
            public Json2 apply(final Json2 param) {
                Json2 errors = null;
                for (final Instruction I : this.seq) {
                    errors = DefaultSchema.maybeError(errors, I.apply(param));
                }
                return errors;
            }
            
            public Sequence add(final Instruction I) {
                this.seq.add(I);
                return this;
            }
        }
        
        class CheckType implements Instruction
        {
            Json2 types;
            
            public CheckType(final Json2 types) {
                this.types = types;
            }
            
            public Json2 apply(final Json2 param) {
                final String ptype = param.isString() ? "string" : (param.isObject() ? "object" : (param.isArray() ? "array" : (param.isNumber() ? "number" : (param.isNull() ? "null" : "boolean"))));
                for (final Json2 type : this.types.asJsonList()) {
                    if (type.asString().equals(ptype)) {
                        return null;
                    }
                    if (type.asString().equals("integer") && param.isNumber() && param.asDouble() % 1.0 == 0.0) {
                        return null;
                    }
                }
                return Json2.array().add(Json2.make("Type mistmatch for " + param.toString(DefaultSchema.this.maxchars) + ", allowed types: " + this.types));
            }
        }
        
        class CheckEnum implements Instruction
        {
            Json2 theenum;
            
            public CheckEnum(final Json2 theenum) {
                this.theenum = theenum;
            }
            
            public Json2 apply(final Json2 param) {
                for (final Json2 option : this.theenum.asJsonList()) {
                    if (param.equals(option)) {
                        return null;
                    }
                }
                return Json2.array().add("Element " + param.toString(DefaultSchema.this.maxchars) + " doesn't match any of enumerated possibilities " + this.theenum);
            }
        }
        
        class CheckAny implements Instruction
        {
            ArrayList<Instruction> alternates;
            Json2 schema;
            
            CheckAny() {
                this.alternates = new ArrayList<Instruction>();
            }
            
            public Json2 apply(final Json2 param) {
                for (final Instruction I : this.alternates) {
                    if (I.apply(param) == null) {
                        return null;
                    }
                }
                return Json2.array().add("Element " + param.toString(DefaultSchema.this.maxchars) + " must conform to at least one of available sub-schemas " + this.schema.toString(DefaultSchema.this.maxchars));
            }
        }
        
        class CheckOne implements Instruction
        {
            ArrayList<Instruction> alternates;
            Json2 schema;
            
            CheckOne() {
                this.alternates = new ArrayList<Instruction>();
            }
            
            public Json2 apply(final Json2 param) {
                int matches = 0;
                for (final Instruction I : this.alternates) {
                    if ((I).apply(param) == null) {
                        ++matches;
                    }
                }
                if (matches != 1) {
                    return Json2.array().add("Element " + param.toString(DefaultSchema.this.maxchars) + " must conform to exactly one of available sub-schemas, but not more " + this.schema.toString(DefaultSchema.this.maxchars));
                }
                return null;
            }
        }
        
        class CheckNot implements Instruction
        {
            Instruction I;
            Json2 schema;
            
            public CheckNot(final Instruction I, final Json2 schema) {
                this.I = I;
                this.schema = schema;
            }
            
            public Json2 apply(final Json2 param) {
                if (this.I.apply(param) != null) {
                    return null;
                }
                return Json2.array().add("Element " + param.toString(DefaultSchema.this.maxchars) + " must NOT conform to the schema " + this.schema.toString(DefaultSchema.this.maxchars));
            }
        }
        
        class CheckSchemaDependency implements Instruction
        {
            Instruction schema;
            String property;
            
            public CheckSchemaDependency(final String property, final Instruction schema) {
                this.property = property;
                this.schema = schema;
            }
            
            public Json2 apply(final Json2 param) {
                if (!param.isObject()) {
                    return null;
                }
                if (!param.has(this.property)) {
                    return null;
                }
                return this.schema.apply(param);
            }
        }
        
        class CheckPropertyDependency implements Instruction
        {
            Json2 required;
            String property;
            
            public CheckPropertyDependency(final String property, final Json2 required) {
                this.property = property;
                this.required = required;
            }
            
            public Json2 apply(final Json2 param) {
                if (!param.isObject()) {
                    return null;
                }
                if (!param.has(this.property)) {
                    return null;
                }
                Json2 errors = null;
                for (final Json2 p : this.required.asJsonList()) {
                    if (!param.has(p.asString())) {
                        errors = DefaultSchema.maybeError(errors, Json2.make("Conditionally required property " + p + " missing from object " + param.toString(DefaultSchema.this.maxchars)));
                    }
                }
                return errors;
            }
        }
        
        interface Instruction extends Function<Json2, Json2>
        {
        }
    }
    
    public static class DefaultFactory implements Factory
    {
        public Json2 nil() {
            return Json2.topnull;
        }
        
        public Json2 bool(final boolean x) {
            return new BooleanJson(x ? Boolean.TRUE : Boolean.FALSE, null);
        }
        
        public Json2 string(final String x) {
            return new StringJson(x, null);
        }
        
        public Json2 number(final Number x) {
            return new NumberJson(x, null);
        }
        
        public Json2 array() {
            return new ArrayJson();
        }
        
        public Json2 object() {
            return new ObjectJson();
        }
        
        public Json2 make(final Object anything) {
            if (anything == null) {
                return Json2.topnull;
            }
            if (anything instanceof Json2) {
                return (Json2)anything;
            }
            if (anything instanceof String) {
                return factory().string((String)anything);
            }
            if (anything instanceof Collection) {
                final Json2 L = this.array();
                for (final Object x : (Collection)anything) {
                    L.add(factory().make(x));
                }
                return L;
            }
            if (anything instanceof Map) {
                final Json2 O = this.object();
                Map tempMap = (Map) anything;
//                for (Map.Entry<Object,Object> x2 : tempMap.entrySet()) {
//                    O.set(x2.getKey().toString(), factory().make(x2.getValue()));
//                }
                return O;
            }
            if (anything instanceof Boolean) {
                return factory().bool((boolean)anything);
            }
            if (anything instanceof Number) {
                return factory().number((Number)anything);
            }
            if (!anything.getClass().isArray()) {
                throw new IllegalArgumentException("Don't know how to convert to Json : " + anything);
            }
            final Class<?> comp = anything.getClass().getComponentType();
            if (!comp.isPrimitive()) {
                return Json2.array((Object[])anything);
            }
            final Json2 A = this.array();
            if (Boolean.TYPE == comp) {
                for (final boolean b : (boolean[])anything) {
                    A.add(b);
                }
            }
            else if (Byte.TYPE == comp) {
                for (final byte b2 : (byte[])anything) {
                    A.add(b2);
                }
            }
            else if (Character.TYPE == comp) {
                for (final char b3 : (char[])anything) {
                    A.add(b3);
                }
            }
            else if (Short.TYPE == comp) {
                for (final short b4 : (short[])anything) {
                    A.add(b4);
                }
            }
            else if (Integer.TYPE == comp) {
                for (final int b5 : (int[])anything) {
                    A.add(b5);
                }
            }
            else if (Long.TYPE == comp) {
                for (final long b6 : (long[])anything) {
                    A.add(b6);
                }
            }
            else if (Float.TYPE == comp) {
                for (final float b7 : (float[])anything) {
                    A.add(b7);
                }
            }
            else if (Double.TYPE == comp) {
                for (final double b8 : (double[])anything) {
                    A.add(b8);
                }
            }
            return A;
        }
    }
    
    static class NullJson extends Json2
    {
        NullJson() {
        }
        
        NullJson(final Json2 e) {
            super(e);
        }
        
        public Object getValue() {
            return null;
        }
        
        public Json2 dup() {
            return new NullJson();
        }
        
        public boolean isNull() {
            return true;
        }
        
        public String toString() {
            return "null";
        }
        
        public List<Object> asList() {
            return Collections.singletonList((Object)null);
        }
        
        public int hashCode() {
            return 0;
        }
        
        public boolean equals(final Object x) {
            return x instanceof NullJson;
        }
    }
    
    static class BooleanJson extends Json2
    {
        boolean val;
        
        BooleanJson() {
        }
        
        BooleanJson(final Json2 e) {
            super(e);
        }
        
        BooleanJson(final Boolean val, final Json2 e) {
            super(e);
            this.val = val;
        }
        
        public Object getValue() {
            return this.val;
        }
        
        public Json2 dup() {
            return new BooleanJson(this.val, null);
        }
        
        public boolean asBoolean() {
            return this.val;
        }
        
        public boolean isBoolean() {
            return true;
        }
        
        public String toString() {
            return this.val ? "true" : "false";
        }
        
        public List<Object> asList() {
            return Collections.singletonList(this.val);
        }
        
        public int hashCode() {
            return this.val ? 1 : 0;
        }
        
        public boolean equals(final Object x) {
            return x instanceof BooleanJson && ((BooleanJson)x).val == this.val;
        }
    }
    
    static class StringJson extends Json2
    {
        String val;
        
        StringJson() {
        }
        
        StringJson(final Json2 e) {
            super(e);
        }
        
        StringJson(final String val, final Json2 e) {
            super(e);
            this.val = val;
        }
        
        public Json2 dup() {
            return new StringJson(this.val, null);
        }
        
        public boolean isString() {
            return true;
        }
        
        public Object getValue() {
            return this.val;
        }
        
        public String asString() {
            return this.val;
        }
        
        public int asInteger() {
            return Integer.parseInt(this.val);
        }
        
        public float asFloat() {
            return Float.parseFloat(this.val);
        }
        
        public double asDouble() {
            return Double.parseDouble(this.val);
        }
        
        public long asLong() {
            return Long.parseLong(this.val);
        }
        
        public short asShort() {
            return Short.parseShort(this.val);
        }
        
        public byte asByte() {
            return Byte.parseByte(this.val);
        }
        
        public char asChar() {
            return this.val.charAt(0);
        }
        
        public List<Object> asList() {
            return Collections.singletonList(this.val);
        }
        
        public String toString() {
            return '\"' + StringJson.escaper.escapeJsonString(this.val) + '\"';
        }
        
        public String toString(final int maxCharacters) {
            if (this.val.length() <= maxCharacters) {
                return this.toString();
            }
            return '\"' + StringJson.escaper.escapeJsonString(this.val.subSequence(0, maxCharacters)) + "...\"";
        }
        
        public int hashCode() {
            return this.val.hashCode();
        }
        
        public boolean equals(final Object x) {
            return x instanceof StringJson && ((StringJson)x).val.equals(this.val);
        }
    }
    
    static class NumberJson extends Json2
    {
        Number val;
        
        NumberJson() {
        }
        
        NumberJson(final Json2 e) {
            super(e);
        }
        
        NumberJson(final Number val, final Json2 e) {
            super(e);
            this.val = val;
        }
        
        public Json2 dup() {
            return new NumberJson(this.val, null);
        }
        
        public boolean isNumber() {
            return true;
        }
        
        public Object getValue() {
            return this.val;
        }
        
        public String asString() {
            return this.val.toString();
        }
        
        public int asInteger() {
            return this.val.intValue();
        }
        
        public float asFloat() {
            return this.val.floatValue();
        }
        
        public double asDouble() {
            return this.val.doubleValue();
        }
        
        public long asLong() {
            return this.val.longValue();
        }
        
        public short asShort() {
            return this.val.shortValue();
        }
        
        public byte asByte() {
            return this.val.byteValue();
        }
        
        public List<Object> asList() {
            return Collections.singletonList(this.val);
        }
        
        public String toString() {
            return this.val.toString();
        }
        
        public int hashCode() {
            return this.val.hashCode();
        }
        
        public boolean equals(final Object x) {
            return x instanceof NumberJson && this.val.doubleValue() == ((NumberJson)x).val.doubleValue();
        }
    }
    
    static class ArrayJson extends Json2
    {
        List<Json2> L;
        
        ArrayJson() {
            this.L = new ArrayList<Json2>();
        }
        
        ArrayJson(final Json2 e) {
            super(e);
            this.L = new ArrayList<Json2>();
        }
        
        public Json2 dup() {
            final ArrayJson j = new ArrayJson();
            for (final Json2 e : this.L) {
                final Json2 v = e.dup();
                v.enclosing = j;
                j.L.add(v);
            }
            return j;
        }
        
        public Json2 set(final int index, final Object value) {
            this.L.set(index, Json2.make(value));
            return this;
        }
        
        public List<Json2> asJsonList() {
            return this.L;
        }
        
        public List<Object> asList() {
            final ArrayList<Object> A = new ArrayList<Object>();
            for (final Json2 x : this.L) {
                A.add(x.getValue());
            }
            return A;
        }
        
        public boolean is(final int index, final Object value) {
            return index >= 0 && index < this.L.size() && this.L.get(index).equals(Json2.make(value));
        }
        
        public Object getValue() {
            return this.asList();
        }
        
        public boolean isArray() {
            return true;
        }
        
        public Json2 at(final int index) {
            return this.L.get(index);
        }
        
        public Json2 add(final Json2 el) {
            this.L.add(el);
            return el.enclosing = this;
        }
        
        public Json2 remove(final Json2 el) {
            this.L.remove(el);
            el.enclosing = null;
            return this;
        }
        
        public Json2 with(final Json2 object) {
            if (object == null) {
                return this;
            }
            if (!object.isArray()) {
                this.add(object);
            }
            else {
                this.L.addAll(((ArrayJson)object).L);
            }
            return this;
        }
        
        public Json2 atDel(final int index) {
            final Json2 el = this.L.remove(index);
            if (el != null) {
                el.enclosing = null;
            }
            return el;
        }
        
        public Json2 delAt(final int index) {
            final Json2 el = this.L.remove(index);
            if (el != null) {
                el.enclosing = null;
            }
            return this;
        }
        
        public String toString() {
            return this.toString(Integer.MAX_VALUE);
        }
        
        public String toString(final int maxCharacters) {
            final StringBuilder sb = new StringBuilder("[");
            final Iterator<Json2> i = this.L.iterator();
            while (i.hasNext()) {
                String s = i.next().toString(maxCharacters);
                if (sb.length() + s.length() > maxCharacters) {
                    s = s.substring(0, Math.max(0, maxCharacters - sb.length()));
                }
                else {
                    sb.append(s);
                }
                if (i.hasNext()) {
                    sb.append(",");
                }
                if (sb.length() >= maxCharacters) {
                    sb.append("...");
                    break;
                }
            }
            sb.append("]");
            return sb.toString();
        }
        
        public int hashCode() {
            return this.L.hashCode();
        }
        
        public boolean equals(final Object x) {
            return x instanceof ArrayJson && ((ArrayJson)x).L.equals(this.L);
        }
    }
    
    static class ObjectJson extends Json2
    {
        Map<String, Json2> object;
        
        ObjectJson() {
            this.object = new HashMap<String, Json2>();
        }
        
        ObjectJson(final Json2 e) {
            super(e);
            this.object = new HashMap<String, Json2>();
        }
        
        public Json2 dup() {
            final ObjectJson j = new ObjectJson();
            for (final Map.Entry<String, Json2> e : this.object.entrySet()) {
                final Json2 v = e.getValue().dup();
                v.enclosing = j;
                j.object.put(e.getKey(), v);
            }
            return j;
        }
        
        public boolean has(final String property) {
            return this.object.containsKey(property);
        }
        
        public boolean is(final String property, final Object value) {
            final Json2 p = this.object.get(property);
            return p != null && p.equals(Json2.make(value));
        }
        
        public Json2 at(final String property) {
            return this.object.get(property);
        }
        
        public Json2 with(final Json2 x) {
            if (x == null) {
                return this;
            }
            if (!x.isObject()) {
                throw new UnsupportedOperationException();
            }
            this.object.putAll(((ObjectJson)x).object);
            return this;
        }
        
        public Json2 set(final String property, final Json2 el) {
            if (property == null) {
                throw new IllegalArgumentException("Null property names are not allowed, value is " + el);
            }
            el.enclosing = this;
            this.object.put(property, el);
            return this;
        }
        
        public Json2 atDel(final String property) {
            final Json2 el = this.object.remove(property);
            if (el != null) {
                el.enclosing = null;
            }
            return el;
        }
        
        public Json2 delAt(final String property) {
            final Json2 el = this.object.remove(property);
            if (el != null) {
                el.enclosing = null;
            }
            return this;
        }
        
        public Object getValue() {
            return this.asMap();
        }
        
        public boolean isObject() {
            return true;
        }
        
        public Map<String, Object> asMap() {
            final HashMap<String, Object> m = new HashMap<String, Object>();
            for (final Map.Entry<String, Json2> e : this.object.entrySet()) {
                m.put(e.getKey(), e.getValue().getValue());
            }
            return m;
        }
        
        public Map<String, Json2> asJsonMap() {
            return this.object;
        }
        
        public String toString() {
            return this.toString(Integer.MAX_VALUE);
        }
        
        public String toString(final int maxCharacters) {
            final StringBuilder sb = new StringBuilder("{");
            final Iterator<Map.Entry<String, Json2>> i = this.object.entrySet().iterator();
            while (i.hasNext()) {
                final Map.Entry<String, Json2> x = i.next();
                sb.append('\"');
                sb.append(ObjectJson.escaper.escapeJsonString(x.getKey()));
                sb.append('\"');
                sb.append(":");
                String s = x.getValue().toString(maxCharacters);
                if (sb.length() + s.length() > maxCharacters) {
                    s = s.substring(0, Math.max(0, maxCharacters - sb.length()));
                }
                sb.append(s);
                if (i.hasNext()) {
                    sb.append(",");
                }
                if (sb.length() >= maxCharacters) {
                    sb.append("...");
                    break;
                }
            }
            sb.append("}");
            return sb.toString();
        }
        
        public int hashCode() {
            return this.object.hashCode();
        }
        
        public boolean equals(final Object x) {
            return x instanceof ObjectJson && ((ObjectJson)x).object.equals(this.object);
        }
    }
    
    static final class Escaper
    {
        private static final char[] HEX_CHARS;
        private static final Set<Character> JS_ESCAPE_CHARS;
        private static final Set<Character> HTML_ESCAPE_CHARS;
        private final boolean escapeHtmlCharacters;
        
        Escaper(final boolean escapeHtmlCharacters) {
            this.escapeHtmlCharacters = escapeHtmlCharacters;
        }
        
        public String escapeJsonString(final CharSequence plainText) {
            final StringBuilder escapedString = new StringBuilder(plainText.length() + 20);
            try {
                this.escapeJsonString(plainText, escapedString);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            return escapedString.toString();
        }
        
        private void escapeJsonString(final CharSequence plainText, final StringBuilder out) throws IOException {
            int pos = 0;
            final int len = plainText.length();
            int charCount;
            for (int i = 0; i < len; i += charCount) {
                final int codePoint = Character.codePointAt(plainText, i);
                charCount = Character.charCount(codePoint);
                if (isControlCharacter(codePoint) || this.mustEscapeCharInJsString(codePoint)) {
                    out.append(plainText, pos, i);
                    pos = i + charCount;
                    switch (codePoint) {
                        case 8: {
                            out.append("\\b");
                            break;
                        }
                        case 9: {
                            out.append("\\t");
                            break;
                        }
                        case 10: {
                            out.append("\\n");
                            break;
                        }
                        case 12: {
                            out.append("\\f");
                            break;
                        }
                        case 13: {
                            out.append("\\r");
                            break;
                        }
                        case 92: {
                            out.append("\\\\");
                            break;
                        }
                        case 47: {
                            out.append("\\/");
                            break;
                        }
                        case 34: {
                            out.append("\\\"");
                            break;
                        }
                        default: {
                            appendHexJavaScriptRepresentation(codePoint, out);
                            break;
                        }
                    }
                }
            }
            out.append(plainText, pos, len);
        }
        
        private boolean mustEscapeCharInJsString(final int codepoint) {
            if (!Character.isSupplementaryCodePoint(codepoint)) {
                final char c = (char)codepoint;
                return Escaper.JS_ESCAPE_CHARS.contains(c) || (this.escapeHtmlCharacters && Escaper.HTML_ESCAPE_CHARS.contains(c));
            }
            return false;
        }
        
        private static boolean isControlCharacter(final int codePoint) {
            return codePoint < 32 || codePoint == 8232 || codePoint == 8233 || (codePoint >= 127 && codePoint <= 159);
        }
        
        private static void appendHexJavaScriptRepresentation(final int codePoint, final Appendable out) throws IOException {
            if (Character.isSupplementaryCodePoint(codePoint)) {
                final char[] surrogates = Character.toChars(codePoint);
                appendHexJavaScriptRepresentation(surrogates[0], out);
                appendHexJavaScriptRepresentation(surrogates[1], out);
                return;
            }
            out.append("\\u").append(Escaper.HEX_CHARS[codePoint >>> 12 & 0xF]).append(Escaper.HEX_CHARS[codePoint >>> 8 & 0xF]).append(Escaper.HEX_CHARS[codePoint >>> 4 & 0xF]).append(Escaper.HEX_CHARS[codePoint & 0xF]);
        }
        
        static {
            HEX_CHARS = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
            final Set<Character> mandatoryEscapeSet = new HashSet<Character>();
            mandatoryEscapeSet.add('\"');
            mandatoryEscapeSet.add('\\');
            JS_ESCAPE_CHARS = Collections.unmodifiableSet((Set<? extends Character>)mandatoryEscapeSet);
            final Set<Character> htmlEscapeSet = new HashSet<Character>();
            htmlEscapeSet.add('<');
            htmlEscapeSet.add('>');
            htmlEscapeSet.add('&');
            htmlEscapeSet.add('=');
            htmlEscapeSet.add('\'');
            HTML_ESCAPE_CHARS = Collections.unmodifiableSet((Set<? extends Character>)htmlEscapeSet);
        }
    }
    
    private static class Reader
    {
        private static final Object OBJECT_END;
        private static final Object ARRAY_END;
        private static final Object COLON;
        private static final Object COMMA;
        public static final int FIRST = 0;
        public static final int CURRENT = 1;
        public static final int NEXT = 2;
        private static Map<Character, Character> escapes;
        private CharacterIterator it;
        private char c;
        private Object token;
        private StringBuffer buf;
        
        private Reader() {
            this.buf = new StringBuffer();
        }
        
        private char next() {
            if (this.it.getIndex() == this.it.getEndIndex()) {
                throw new RuntimeException("Reached end of input at the " + this.it.getIndex() + "th character.");
            }
            return this.c = this.it.next();
        }
        
        private char previous() {
            return this.c = this.it.previous();
        }
        
        private void skipWhiteSpace() {
            do {
                if (Character.isWhitespace(this.c)) {
                    continue;
                }
                if (this.c != '/') {
                    break;
                }
                this.next();
                if (this.c == '*') {
                    while (this.c != '\uffff' && (this.next() != '*' || this.next() != '/')) {}
                    if (this.c == '\uffff') {
                        throw new RuntimeException("Unterminated comment while parsing JSON string.");
                    }
                    continue;
                }
                else {
                    if (this.c != '/') {
                        this.previous();
                        break;
                    }
                    while (this.c != '\n' && this.c != '\uffff') {
                        this.next();
                    }
                }
            } while (this.next() != '\uffff');
        }
        
        public Object read(final CharacterIterator ci, final int start) {
            this.it = ci;
            switch (start) {
                case 0: {
                    this.c = this.it.first();
                    break;
                }
                case 1: {
                    this.c = this.it.current();
                    break;
                }
                case 2: {
                    this.c = this.it.next();
                    break;
                }
            }
            return this.read();
        }
        
        public Object read(final CharacterIterator it) {
            return this.read(it, 2);
        }
        
        public Object read(final String string) {
            return this.read(new StringCharacterIterator(string), 0);
        }
        
        private <T> T read() {
            this.skipWhiteSpace();
            final char ch = this.c;
            this.next();
            switch (ch) {
                case '\"': {
                    this.token = this.readString();
                    break;
                }
                case '[': {
                    this.token = this.readArray();
                    break;
                }
                case ']': {
                    this.token = Reader.ARRAY_END;
                    break;
                }
                case ',': {
                    this.token = Reader.COMMA;
                    break;
                }
                case '{': {
                    this.token = this.readObject();
                    break;
                }
                case '}': {
                    this.token = Reader.OBJECT_END;
                    break;
                }
                case ':': {
                    this.token = Reader.COLON;
                    break;
                }
                case 't': {
                    if (this.c != 'r' || this.next() != 'u' || this.next() != 'e') {
                        throw new RuntimeException("Invalid JSON token: expected 'true' keyword.");
                    }
                    this.next();
                    this.token = factory().bool(Boolean.TRUE);
                    break;
                }
                case 'f': {
                    if (this.c != 'a' || this.next() != 'l' || this.next() != 's' || this.next() != 'e') {
                        throw new RuntimeException("Invalid JSON token: expected 'false' keyword.");
                    }
                    this.next();
                    this.token = factory().bool(Boolean.FALSE);
                    break;
                }
                case 'n': {
                    if (this.c != 'u' || this.next() != 'l' || this.next() != 'l') {
                        throw new RuntimeException("Invalid JSON token: expected 'null' keyword.");
                    }
                    this.next();
                    this.token = Json2.nil();
                    break;
                }
                default: {
                    this.c = this.it.previous();
                    if (Character.isDigit(this.c) || this.c == '-') {
                        this.token = this.readNumber();
                        break;
                    }
                    throw new RuntimeException("Invalid JSON near position: " + this.it.getIndex());
                }
            }
            return (T)this.token;
        }
        
        private String readObjectKey() {
            final Object key = this.read();
            if (key == null) {
                throw new RuntimeException("Missing object key (don't forget to put quotes!).");
            }
            if (key != Reader.OBJECT_END) {
                return ((Json2)key).asString();
            }
            return key.toString();
        }
        
        private Json2 readObject() {
            final Json2 ret = Json2.object();
            String key = this.readObjectKey();
            while (this.token != Reader.OBJECT_END) {
                this.read();
                if (this.token != Reader.OBJECT_END) {
                    final Json2 value = this.read();
                    ret.set(key, value);
                    if (this.read() != Reader.COMMA) {
                        continue;
                    }
                    key = this.readObjectKey();
                }
            }
            return ret;
        }
        
        private Json2 readArray() {
            final Json2 ret = Json2.array();
            Object value = this.read();
            while (this.token != Reader.ARRAY_END) {
                ret.add((Json2)value);
                if (this.read() == Reader.COMMA) {
                    value = this.read();
                }
                else {
                    if (this.token != Reader.ARRAY_END) {
                        throw new RuntimeException("Unexpected token in array " + this.token);
                    }
                    continue;
                }
            }
            return ret;
        }
        
        private Json2 readNumber() {
            int length = 0;
            boolean isFloatingPoint = false;
            this.buf.setLength(0);
            if (this.c == '-') {
                this.add();
            }
            length += this.addDigits();
            if (this.c == '.') {
                this.add();
                length += this.addDigits();
                isFloatingPoint = true;
            }
            if (this.c == 'e' || this.c == 'E') {
                this.add();
                if (this.c == '+' || this.c == '-') {
                    this.add();
                }
                this.addDigits();
                isFloatingPoint = true;
            }
            final String s = this.buf.toString();
            final Number n = isFloatingPoint ? ((length < 17) ? Double.valueOf(s) : new BigDecimal(s)) : ((length < 20) ? Long.valueOf(s) : new BigInteger(s));
            return factory().number(n);
        }
        
        private int addDigits() {
            int ret = 0;
            while (Character.isDigit(this.c)) {
                this.add();
                ++ret;
            }
            return ret;
        }
        
        private Json2 readString() {
            this.buf.setLength(0);
            while (this.c != '\"') {
                if (this.c == '\\') {
                    this.next();
                    if (this.c == 'u') {
                        this.add(this.unicode());
                    }
                    else {
                        final Object value = Reader.escapes.get(new Character(this.c));
                        if (value == null) {
                            continue;
                        }
                        this.add((char)value);
                    }
                }
                else {
                    this.add();
                }
            }
            this.next();
            return factory().string(this.buf.toString());
        }
        
        private void add(final char cc) {
            this.buf.append(cc);
            this.next();
        }
        
        private void add() {
            this.add(this.c);
        }
        
        private char unicode() {
            int value = 0;
            for (int i = 0; i < 4; ++i) {
                switch (this.next()) {
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9': {
                        value = (value << 4) + this.c - 48;
                        break;
                    }
                    case 'a':
                    case 'b':
                    case 'c':
                    case 'd':
                    case 'e':
                    case 'f': {
                        value = (value << 4) + (this.c - 'a') + 10;
                        break;
                    }
                    case 'A':
                    case 'B':
                    case 'C':
                    case 'D':
                    case 'E':
                    case 'F': {
                        value = (value << 4) + (this.c - 'A') + 10;
                        break;
                    }
                }
            }
            return (char)value;
        }
        
        static {
            OBJECT_END = new Object();
            ARRAY_END = new Object();
            COLON = new Object();
            COMMA = new Object();
            (Reader.escapes = new HashMap<Character, Character>()).put(new Character('\"'), new Character('\"'));
            Reader.escapes.put(new Character('\\'), new Character('\\'));
            Reader.escapes.put(new Character('/'), new Character('/'));
            Reader.escapes.put(new Character('b'), new Character('\b'));
            Reader.escapes.put(new Character('f'), new Character('\f'));
            Reader.escapes.put(new Character('n'), new Character('\n'));
            Reader.escapes.put(new Character('r'), new Character('\r'));
            Reader.escapes.put(new Character('t'), new Character('\t'));
        }
    }
    
    public interface Factory
    {
        Json2 nil();
        
        Json2 bool(final boolean p0);
        
        Json2 string(final String p0);
        
        Json2 number(final Number p0);
        
        Json2 object();
        
        Json2 array();
        
        Json2 make(final Object p0);
    }
    
    interface Function<T, R>
    {
        R apply(final T p0);
    }
    
    public interface Schema
    {
        Json2 validate(final Json2 p0);
    }
}
