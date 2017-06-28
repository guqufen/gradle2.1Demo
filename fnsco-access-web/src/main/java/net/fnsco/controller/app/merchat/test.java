package net.fnsco.controller.app.merchat;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class test {

	public static void main(String[] args) {
			Map<String,Emp> map=new HashMap();
			String id="id";
	        Emp emp=new Emp();
	        emp.setE_id("id");
	        emp.setE_name("name");
	        map.put(id, emp);
	        map.get(emp.getE_id());
	        System.out.println(map.get(id).getE_id());
	        System.out.println(map.get(id).getE_name());
//	        Set<String> keys=map.keySet();
//	        System.out.println("遍历集合map：");
//	        System.out.println(map.get("005"));
//	        Iterator<String> it=keys.iterator();
//	        while(it.hasNext())
//	        {
//	            String key=it.next();
//	            System.out.println(key+" "+map.get(key));
//	        }
	      //  System.out.println("移除的是"+map.remove("005"));
		
	}

}



 class Emp {
    private String e_id;
    private String e_name;
//    public Emp(String e_id, String e_name) {
//        super();
//        this.e_id = e_id;
//        this.e_name = e_name;
//    }
    public String getE_id() {
        return e_id;
    }
    public void setE_id(String e_id) {
        this.e_id = e_id;
    }
    public String getE_name() {
        return e_name;
    }
    public void setE_name(String e_name) {
        this.e_name = e_name;
    }
 }
    