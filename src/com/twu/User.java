package com.twu;
import java.util.*;
public class User extends Person{
    int remainVotes=10;
    public User(String userName){
        super(userName);
    }
    int bAmount=0;

    //投票
    public void vote(LinkedHashMap<String, HotsearchValue> map,String voteName,int voteNumber) {
        if(remainVotes==0) System.out.println("您的票数已用尽");
        else{
            remainVotes=remainVotes-voteNumber;
            if(map.get(voteName).priority==1)
                map.get(voteName).votes+=voteNumber;
            else if(map.get(voteName).priority==2)
                map.get(voteName).votes+=voteNumber*2;
        }
    }

//买热搜
    public void buyHotsearch(LinkedHashMap<String, HotsearchValue> map,String buyName,int buyRank,int buyAmount) {
        if(buyAmount>bAmount) {
            int votes = map.get(buyName).votes;
            map.get(buyName).buyRank=buyRank;
            map.remove(buyName);
            resort(map, buyRank, buyName, votes);
            bAmount=buyAmount;
            map.get(buyName).isPurched=true;
        }
        else System.out.println("购买失败");
    }

//map特定位置插入元素
public static void resort(LinkedHashMap<String, HotsearchValue> input, int index, String key, Integer value) {
        index--;
    if (index >= 0) {
        LinkedHashMap<String, HotsearchValue> output=new LinkedHashMap<String, HotsearchValue>();
        int i = 0;
            for (Map.Entry<String, HotsearchValue> entry : input.entrySet()) {
                //原有热搜购买所得，插入时被替换
                if (entry.getValue().isPurched) {
                    if (i == index) {
                        HotsearchValue item = new HotsearchValue();
                        item.votes = value;
                        item.isPurched = true;
                        item.buyRank=index+1;
                        output.put(key, item);
                    } else output.put(entry.getKey(), entry.getValue());
                    i++;
                }
                //原有热搜投票排名所得，插入时顺延
                else{
                    if (i == index) {
                        HotsearchValue item = new HotsearchValue();
                        item.votes = value;
                        item.isPurched = true;
                        item.buyRank=index+1;
                        output.put(key, item);
                    }
                    output.put(entry.getKey(), entry.getValue());
                    i++;
                }
            }
        input.clear();
        input.putAll(output);
    } else {
        throw new IndexOutOfBoundsException("index越界");
    }
}
}
