package com.twu;
import java.util.*;

public class Person {
    String name;
    public Person(String userName){
        name=userName;
    }
    public Person(){
    }

    //添加热搜
    public static void additem(LinkedHashMap<String, HotsearchValue> map,String listItem) {
        if (map.containsKey(listItem)) {
            System.out.println("热搜已存在...");
        }
        else{
            map.put(listItem, new HotsearchValue());
            System.out.println("添加成功");
        }
    }

    //按投票数排序
    public static LinkedHashMap<String, HotsearchValue> sortDescend(LinkedHashMap<String, HotsearchValue> map) {
        List<Map.Entry<String, HotsearchValue>> list = new ArrayList<Map.Entry<String, HotsearchValue>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, HotsearchValue>>(){
            @Override
            public int compare(Map.Entry<String, HotsearchValue> o1, Map.Entry<String, HotsearchValue> o2) {
                return o2.getValue().votes - o1.getValue().votes;
            }
        });
        LinkedHashMap<String, HotsearchValue> resultMap = new LinkedHashMap<String, HotsearchValue>();
        for(Map.Entry<String, HotsearchValue> entry:list){
            resultMap.put(entry.getKey(), entry.getValue());
        }
        return resultMap;
    }

        //查看热搜
    public static void checkList(LinkedHashMap<String, HotsearchValue> map) {
        int count=1;
        for (Map.Entry<java.lang.String, HotsearchValue> entry : map.entrySet()) {
            System.out.println(count+"." + entry.getKey()+"  "+entry.getValue().votes);
            count++;
        }
    }
}
