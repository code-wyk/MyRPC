/**
 * 客户端根据不同的Service进行动态代理
 * 将不同的Service方法封装成统一的Request对象格式，并且建立与Servier端的通信
 */
package com.wyk.MyRPC.client;

import com.wyk.MyRPC.common.User;
import com.wyk.MyRPC.service.UserService;

public class RPCClient {
    public static void main(String[] args){
        ClientProxy clientProxy=new ClientProxy("127.0.0.1",8899);

        UserService proxy=clientProxy.getProxy(UserService.class);

        //服务的方法1
        User userByUserId=proxy.getUserById(3);
        System.out.println("从服务端得到的user为："+userByUserId);

        //服务的方法2
        User user=User.builder()
                .userName("小明")
                .id(4)
                .sex("男")
                .build();
        Integer integer=proxy.insertUserId(user);
        System.out.println("向服务端插入数据"+integer);

    }
}
