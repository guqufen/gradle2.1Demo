package net.fnsco.service.modules.appuser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class test {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("abc");
        list.add("def");
        list.add("ghi");
        Iterator it = list.iterator();
        while(it.hasNext()) {
          System.out.println(it.next());
        }
        
    }

}
