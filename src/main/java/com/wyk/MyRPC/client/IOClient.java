/**
 * 底层的通信
 * 这里负责底层与服务端的通信，发送Request，接受Response
 * 客户端发起一起请求调用，Socket建立连接，发起请求Request，得到相应Response
 * 这里的request是封装好的(上层进行封装)，不同的service需要进行不同的封装，客户端只知道service接口，需要一层动态代理根据反射封装不同的service
 */
package com.wyk.MyRPC.client;

import com.wyk.MyRPC.common.RPCRequest;
import com.wyk.MyRPC.common.RPCResponse;
import jdk.nashorn.internal.ir.RuntimeNode;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class IOClient {

    public static RPCResponse sendRequest(String host, int port, RPCRequest request){
        try{
            //根据host和port向服务端发送连接请求
            Socket socket = new Socket(host,port);
            //获取输入输出流
            ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());

            //向流中写入request
            System.out.println(request);
            oos.writeObject(request);
            oos.flush();
            //从流中读取response
            RPCResponse rpcResponse=(RPCResponse) ois.readObject();
            return rpcResponse;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
