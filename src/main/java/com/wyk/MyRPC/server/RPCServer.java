package com.wyk.MyRPC.server;

import com.wyk.MyRPC.common.RPCRequest;
import com.wyk.MyRPC.common.RPCResponse;
import com.wyk.MyRPC.common.User;
import com.wyk.MyRPC.service.UserService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class RPCServer {
    public static void main(String[] args){
        UserService userService=new UserServiceImpl();
        try{
            //服务端监听8899
            ServerSocket serverSocket = new ServerSocket(8899);
            System.out.println("服务端启动了");
            //监听socket
            while(true){
                Socket socket=serverSocket.accept();
                //开启一个线程去处理
                new Thread(()->{
                    try {
                        ObjectOutputStream oos= new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());

                        RPCRequest request= (RPCRequest) ois.readObject();
                        //反射调用对应的方法
                        Method method=userService.getClass().getMethod(request.getMethodName(),request.getParamsTypes());
                        Object invoke=method.invoke(userService,request.getParams());
                        //封装，写入response对象
                        oos.writeObject(RPCResponse.success(invoke));
                        oos.flush();
                    } catch (IOException | ClassNotFoundException | NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("启动服务器失败");
        }

    }
}
