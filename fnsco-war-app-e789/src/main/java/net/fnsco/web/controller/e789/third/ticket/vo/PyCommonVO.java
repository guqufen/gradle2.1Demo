package net.fnsco.web.controller.e789.third.ticket.vo;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;

public class PyCommonVO<T> extends VO {
    public void setList(T vo) {
        String letters = ((SiteVO) vo).getSitePyName().toLowerCase();
        String letter = letters.substring(0, 1);
        if ("a".equals(letter)) {
            List<T> resultList = getA();
            if (null == resultList) {
                resultList = new ArrayList<T>();
            }
            resultList.add(vo);
            setA(resultList);
        } else if ("b".equals(letter)) {
            List<T> resultList = getB();
            if (null == resultList) {
                resultList = new ArrayList<T>();
            }
            resultList.add(vo);
            setB(resultList);
        } else if ("c".equals(letter)) {
            List<T> resultList = getC();
            if (null == resultList) {
                resultList = new ArrayList<T>();
            }
            resultList.add(vo);
            setC(resultList);
        } else if ("d".equals(letter)) {
            List<T> resultList = getD();
            if (null == resultList) {
                resultList = new ArrayList<T>();
            }
            resultList.add(vo);
            setD(resultList);
        } else if ("e".equals(letter)) {
            List<T> resultList = getE();
            if (null == resultList) {
                resultList = new ArrayList<T>();
            }
            resultList.add(vo);
            setE(resultList);
        } else if ("f".equals(letter)) {
            List<T> resultList = getF();
            if (null == resultList) {
                resultList = new ArrayList<T>();
            }
            resultList.add(vo);
            setF(resultList);
        } else if ("g".equals(letter)) {
            List<T> resultList = getG();
            if (null == resultList) {
                resultList = new ArrayList<T>();
            }
            resultList.add(vo);
            setG(resultList);
        } else if ("h".equals(letter)) {
            List<T> resultList = getH();
            if (null == resultList) {
                resultList = new ArrayList<T>();
            }
            resultList.add(vo);
            setH(resultList);
        } else if ("i".equals(letter)) {
            List<T> resultList = getI();
            if (null == resultList) {
                resultList = new ArrayList<T>();
            }
            resultList.add(vo);
            setI(resultList);
        } else if ("j".equals(letter)) {
            List<T> resultList = getJ();
            if (null == resultList) {
                resultList = new ArrayList<T>();
            }
            resultList.add(vo);
            setJ(resultList);
        } else if ("k".equals(letter)) {
            List<T> resultList = getK();
            if (null == resultList) {
                resultList = new ArrayList<T>();
            }
            resultList.add(vo);
            setK(resultList);
        } else if ("l".equals(letter)) {
            List<T> resultList = getL();
            if (null == resultList) {
                resultList = new ArrayList<T>();
            }
            resultList.add(vo);
            setL(resultList);
        } else if ("m".equals(letter)) {
            List<T> resultList = getM();
            if (null == resultList) {
                resultList = new ArrayList<T>();
            }
            resultList.add(vo);
            setM(resultList);
        } else if ("n".equals(letter)) {
            List<T> resultList = getN();
            if (null == resultList) {
                resultList = new ArrayList<T>();
            }
            resultList.add(vo);
            setN(resultList);
        } else if ("l".equals(letter)) {
            List<T> resultList = getL();
            if (null == resultList) {
                resultList = new ArrayList<T>();
            }
            resultList.add(vo);
            setL(resultList);
        } else if ("o".equals(letter)) {
            List<T> resultList = getO();
            if (null == resultList) {
                resultList = new ArrayList<T>();
            }
            resultList.add(vo);
            setO(resultList);
        } else if ("p".equals(letter)) {
            List<T> resultList = getP();
            if (null == resultList) {
                resultList = new ArrayList<T>();
            }
            resultList.add(vo);
            setP(resultList);
        } else if ("q".equals(letter)) {
            List<T> resultList = getQ();
            if (null == resultList) {
                resultList = new ArrayList<T>();
            }
            resultList.add(vo);
            setQ(resultList);
        } else if ("r".equals(letter)) {
            List<T> resultList = getR();
            if (null == resultList) {
                resultList = new ArrayList<T>();
            }
            resultList.add(vo);
            setR(resultList);
        } else if ("s".equals(letter)) {
            List<T> resultList = getS();
            if (null == resultList) {
                resultList = new ArrayList<T>();
            }
            resultList.add(vo);
            setS(resultList);
        } else if ("t".equals(letter)) {
            List<T> resultList = getT();
            if (null == resultList) {
                resultList = new ArrayList<T>();
            }
            resultList.add(vo);
            setT(resultList);
        } else if ("u".equals(letter)) {
            List<T> resultList = getU();
            if (null == resultList) {
                resultList = new ArrayList<T>();
            }
            resultList.add(vo);
            setU(resultList);
        } else if ("v".equals(letter)) {
            List<T> resultList = getV();
            if (null == resultList) {
                resultList = new ArrayList<T>();
            }
            resultList.add(vo);
            setV(resultList);
        } else if ("w".equals(letter)) {
            List<T> resultList = getW();
            if (null == resultList) {
                resultList = new ArrayList<T>();
            }
            resultList.add(vo);
            setW(resultList);
        } else if ("x".equals(letter)) {
            List<T> resultList = getX();
            if (null == resultList) {
                resultList = new ArrayList<T>();
            }
            resultList.add(vo);
            setX(resultList);
        } else if ("y".equals(letter)) {
            List<T> resultList = getY();
            if (null == resultList) {
                resultList = new ArrayList<T>();
            }
            resultList.add(vo);
            setY(resultList);
        } else if ("z".equals(letter)) {
            List<T> resultList = getZ();
            if (null == resultList) {
                resultList = new ArrayList<T>();
            }
            resultList.add(vo);
            setZ(resultList);
        }

    }

    @ApiModelProperty(value = "a", example = "a")
    private List<T> a;
    @ApiModelProperty(value = "b", example = "b")
    private List<T> b;
    @ApiModelProperty(value = "c", example = "c")
    private List<T> c;
    private List<T> d;
    private List<T> e;
    private List<T> f;
    private List<T> g;
    private List<T> h;
    private List<T> i;
    private List<T> j;
    private List<T> k;
    private List<T> l;
    private List<T> m;
    private List<T> n;
    private List<T> o;
    private List<T> p;
    private List<T> q;
    private List<T> r;
    private List<T> s;
    private List<T> t;
    private List<T> u;
    private List<T> v;
    private List<T> w;
    private List<T> x;
    private List<T> y;
    private List<T> z;

    /**
     * d
     *
     * @return  the d
     * @since   CodingExample Ver 1.0
    */

    public List<T> getD() {
        return d;
    }

    /**
     * d
     *
     * @param   d    the d to set
     * @since   CodingExample Ver 1.0
     */

    public void setD(List<T> d) {
        this.d = d;
    }

    /**
     * e
     *
     * @return  the e
     * @since   CodingExample Ver 1.0
    */

    public List<T> getE() {
        return e;
    }

    /**
     * e
     *
     * @param   e    the e to set
     * @since   CodingExample Ver 1.0
     */

    public void setE(List<T> e) {
        this.e = e;
    }

    /**
     * f
     *
     * @return  the f
     * @since   CodingExample Ver 1.0
    */

    public List<T> getF() {
        return f;
    }

    /**
     * f
     *
     * @param   f    the f to set
     * @since   CodingExample Ver 1.0
     */

    public void setF(List<T> f) {
        this.f = f;
    }

    /**
     * g
     *
     * @return  the g
     * @since   CodingExample Ver 1.0
    */

    public List<T> getG() {
        return g;
    }

    /**
     * g
     *
     * @param   g    the g to set
     * @since   CodingExample Ver 1.0
     */

    public void setG(List<T> g) {
        this.g = g;
    }

    /**
     * h
     *
     * @return  the h
     * @since   CodingExample Ver 1.0
    */

    public List<T> getH() {
        return h;
    }

    /**
     * h
     *
     * @param   h    the h to set
     * @since   CodingExample Ver 1.0
     */

    public void setH(List<T> h) {
        this.h = h;
    }

    /**
     * i
     *
     * @return  the i
     * @since   CodingExample Ver 1.0
    */

    public List<T> getI() {
        return i;
    }

    /**
     * i
     *
     * @param   i    the i to set
     * @since   CodingExample Ver 1.0
     */

    public void setI(List<T> i) {
        this.i = i;
    }

    /**
     * j
     *
     * @return  the j
     * @since   CodingExample Ver 1.0
    */

    public List<T> getJ() {
        return j;
    }

    /**
     * j
     *
     * @param   j    the j to set
     * @since   CodingExample Ver 1.0
     */

    public void setJ(List<T> j) {
        this.j = j;
    }

    /**
     * k
     *
     * @return  the k
     * @since   CodingExample Ver 1.0
    */

    public List<T> getK() {
        return k;
    }

    /**
     * k
     *
     * @param   k    the k to set
     * @since   CodingExample Ver 1.0
     */

    public void setK(List<T> k) {
        this.k = k;
    }

    /**
     * l
     *
     * @return  the l
     * @since   CodingExample Ver 1.0
    */

    public List<T> getL() {
        return l;
    }

    /**
     * l
     *
     * @param   l    the l to set
     * @since   CodingExample Ver 1.0
     */

    public void setL(List<T> l) {
        this.l = l;
    }

    /**
     * m
     *
     * @return  the m
     * @since   CodingExample Ver 1.0
    */

    public List<T> getM() {
        return m;
    }

    /**
     * m
     *
     * @param   m    the m to set
     * @since   CodingExample Ver 1.0
     */

    public void setM(List<T> m) {
        this.m = m;
    }

    /**
     * n
     *
     * @return  the n
     * @since   CodingExample Ver 1.0
    */

    public List<T> getN() {
        return n;
    }

    /**
     * n
     *
     * @param   n    the n to set
     * @since   CodingExample Ver 1.0
     */

    public void setN(List<T> n) {
        this.n = n;
    }

    /**
     * o
     *
     * @return  the o
     * @since   CodingExample Ver 1.0
    */

    public List<T> getO() {
        return o;
    }

    /**
     * o
     *
     * @param   o    the o to set
     * @since   CodingExample Ver 1.0
     */

    public void setO(List<T> o) {
        this.o = o;
    }

    /**
     * p
     *
     * @return  the p
     * @since   CodingExample Ver 1.0
    */

    public List<T> getP() {
        return p;
    }

    /**
     * p
     *
     * @param   p    the p to set
     * @since   CodingExample Ver 1.0
     */

    public void setP(List<T> p) {
        this.p = p;
    }

    /**
     * q
     *
     * @return  the q
     * @since   CodingExample Ver 1.0
    */

    public List<T> getQ() {
        return q;
    }

    /**
     * q
     *
     * @param   q    the q to set
     * @since   CodingExample Ver 1.0
     */

    public void setQ(List<T> q) {
        this.q = q;
    }

    /**
     * r
     *
     * @return  the r
     * @since   CodingExample Ver 1.0
    */

    public List<T> getR() {
        return r;
    }

    /**
     * r
     *
     * @param   r    the r to set
     * @since   CodingExample Ver 1.0
     */

    public void setR(List<T> r) {
        this.r = r;
    }

    /**
     * s
     *
     * @return  the s
     * @since   CodingExample Ver 1.0
    */

    public List<T> getS() {
        return s;
    }

    /**
     * s
     *
     * @param   s    the s to set
     * @since   CodingExample Ver 1.0
     */

    public void setS(List<T> s) {
        this.s = s;
    }

    /**
     * t
     *
     * @return  the t
     * @since   CodingExample Ver 1.0
    */

    public List<T> getT() {
        return t;
    }

    /**
     * t
     *
     * @param   t    the t to set
     * @since   CodingExample Ver 1.0
     */

    public void setT(List<T> t) {
        this.t = t;
    }

    /**
     * u
     *
     * @return  the u
     * @since   CodingExample Ver 1.0
    */

    public List<T> getU() {
        return u;
    }

    /**
     * u
     *
     * @param   u    the u to set
     * @since   CodingExample Ver 1.0
     */

    public void setU(List<T> u) {
        this.u = u;
    }

    /**
     * v
     *
     * @return  the v
     * @since   CodingExample Ver 1.0
    */

    public List<T> getV() {
        return v;
    }

    /**
     * v
     *
     * @param   v    the v to set
     * @since   CodingExample Ver 1.0
     */

    public void setV(List<T> v) {
        this.v = v;
    }

    /**
     * w
     *
     * @return  the w
     * @since   CodingExample Ver 1.0
    */

    public List<T> getW() {
        return w;
    }

    /**
     * w
     *
     * @param   w    the w to set
     * @since   CodingExample Ver 1.0
     */

    public void setW(List<T> w) {
        this.w = w;
    }

    /**
     * x
     *
     * @return  the x
     * @since   CodingExample Ver 1.0
    */

    public List<T> getX() {
        return x;
    }

    /**
     * x
     *
     * @param   x    the x to set
     * @since   CodingExample Ver 1.0
     */

    public void setX(List<T> x) {
        this.x = x;
    }

    /**
     * y
     *
     * @return  the y
     * @since   CodingExample Ver 1.0
    */

    public List<T> getY() {
        return y;
    }

    /**
     * y
     *
     * @param   y    the y to set
     * @since   CodingExample Ver 1.0
     */

    public void setY(List<T> y) {
        this.y = y;
    }

    /**
     * z
     *
     * @return  the z
     * @since   CodingExample Ver 1.0
    */

    public List<T> getZ() {
        return z;
    }

    /**
     * z
     *
     * @param   z    the z to set
     * @since   CodingExample Ver 1.0
     */

    public void setZ(List<T> z) {
        this.z = z;
    }

    /**
     * a
     *
     * @return  the a
     * @since   CodingExample Ver 1.0
    */

    public List<T> getA() {
        return a;
    }

    /**
     * a
     *
     * @param   a    the a to set
     * @since   CodingExample Ver 1.0
     */

    public void setA(List<T> a) {
        this.a = a;
    }

    /**
     * b
     *
     * @return  the b
     * @since   CodingExample Ver 1.0
    */

    public List<T> getB() {
        return b;
    }

    /**
     * b
     *
     * @param   b    the b to set
     * @since   CodingExample Ver 1.0
     */

    public void setB(List<T> b) {
        this.b = b;
    }

    /**
     * c
     *
     * @return  the c
     * @since   CodingExample Ver 1.0
    */

    public List<T> getC() {
        return c;
    }

    /**
     * c
     *
     * @param   c    the c to set
     * @since   CodingExample Ver 1.0
     */

    public void setC(List<T> c) {
        this.c = c;
    }

}
