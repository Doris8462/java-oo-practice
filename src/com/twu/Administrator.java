package com.twu;
import java.util.*;

public class Administrator extends Person{
    String adName="admin";
    String password="123456";

    //添加超级热搜
    public static void addSuperItem(LinkedHashMap<String, HotsearchValue> map,String listItem) {
        if (map.containsKey(listItem)) {
            System.out.println("热搜已存在...");
        }
        else{
            HotsearchValue item=new HotsearchValue();
            item.priority=2;
            map.put(listItem, item);
        }
    }
}
