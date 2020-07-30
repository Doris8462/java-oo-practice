package com.twu;
import java.util.*;
public class Main {
    public static void main(String[] args) {
    LinkedHashMap<String, HotsearchValue> map = new LinkedHashMap<>();
        int idType = 0;
        Scanner scanner = new Scanner(System.in);
        while (idType != 3) {
            System.out.println("欢迎来到热搜排行榜，你是？");
            System.out.println("1.用户\n2.管理员\n3.退出");
            idType = scanner.nextInt();
            switch (idType) {
                case 1:
                    int uType=0;
                    System.out.println("请输入您的昵称");
                    scanner.nextLine();
                    String userName = scanner.nextLine();
                    User user = new User(userName);
                    while (uType!=5) {
                        System.out.println("你好，" + userName + "，你可以：");
                        System.out.println("1.查看热搜排行榜\n2.给热搜事件投票\n3.购买热搜\n4.添加热搜\n5.退出");
                        uType = scanner.nextInt();
                        switch (uType) {
                            case 1:
                                if (map.isEmpty()) System.out.println("热搜榜为空");
                                user.checkList(map);
                                break;
                            case 2:
                                System.out.println("请输入你要投票的热搜名称");
                                scanner.nextLine();
                                String voteName = scanner.nextLine();
                                System.out.println("请输入你要投票的热搜票数：（你目前还有" + user.remainVotes + "票)");
                                Integer voteNumber = scanner.nextInt();
                                if (voteNumber > user.remainVotes || voteNumber <= 0)
                                    System.out.println("投票失败");
                                else {
                                    user.vote(map,voteName, voteNumber);
                                    LinkedHashMap<String, HotsearchValue> output=new LinkedHashMap<String, HotsearchValue>();
                                    for(Map.Entry<String, HotsearchValue> entry : map.entrySet()){
                                        if(entry.getValue().isPurched==false){
                                            output.put(entry.getKey(),entry.getValue());
                                        }
                                    }
                                    output=user.sortDescend(output);
                                    for(Map.Entry<String, HotsearchValue> entry : map.entrySet()) {
                                        if(entry.getValue().isPurched==true) {
                                            user.resort(output, entry.getValue().buyRank, entry.getKey(),  entry.getValue().votes);
                                        }
                                    }
                                    map.clear();
                                    map.putAll(output);
                                }
                                break;
                            case 3:
                                System.out.println("请输入你要购买的热搜名称");
                                scanner.nextLine();
                                String buyName = scanner.nextLine();
                                System.out.println("请输入你要购买的热搜排名");
                                int buyRank = scanner.nextInt();
                                System.out.println("请输入你要购买的热搜金额");
                                int buyAmount = scanner.nextInt();
                                user.buyHotsearch(map,buyName, buyRank, buyAmount);
                                break;
                            case 4:
                                System.out.println("请输入你要添加的热搜事件的名字");
                                scanner.nextLine();
                                String hsItem = scanner.nextLine();
                                user.additem(map,hsItem);

                                user.checkList(map);
                                break;
                            case 5:
                                break;
                        }
                    }
                    break;
                case 2:
                    Administrator manager=new Administrator();
                    int adType=0;
                    System.out.println("请输入您管理员的昵称");
                    scanner.nextLine();
                    String adName=scanner.nextLine();
                    System.out.println("请输入您的密码");
                    String password=scanner.nextLine();
                    if(adName.equals(manager.adName)&&password.equals(manager.password)){
                        while (adType!=4){
                            System.out.println("你好，" + adName + "，你可以：");
                            System.out.println("1.查看热搜排行榜\n2.添加热搜\n3.添加超级热搜\n4.退出");
                            adType = scanner.nextInt();
                            switch (adType){
                                case 1:
                                    if (map.isEmpty()) System.out.println("热搜榜为空");
                                    manager.checkList(map);
                                    break;
                                case 2:
                                    System.out.println("请输入你要添加的热搜事件的名字");
                                    scanner.nextLine();
                                    String hsItem = scanner.nextLine();
                                    manager.additem(map,hsItem);
                                    System.out.println("添加成功");
                                    manager.checkList(map);
                                    break;
                                case 3:
                                    System.out.println("请输入你要添加的超级热搜事件的名字");
                                    scanner.nextLine();
                                    String hsSuperItem = scanner.nextLine();
                                    manager.addSuperItem(map,hsSuperItem);
                                    System.out.println("添加成功");
                                    break;
                                case 4:
                                    break;

                            }
                        }
                    }
                    else System.out.println("用户名或密码错误");
                    break;
                case 3:
                    break;
            }
        }
    }
}
