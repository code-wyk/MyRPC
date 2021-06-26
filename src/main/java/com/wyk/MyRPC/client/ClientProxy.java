/**
 * 动态代理类的调用处理程序
 */
package com.wyk.MyRPC.client;

import com.wyk.MyRPC.common.RPCRequest;
import com.wyk.MyRPC.common.RPCResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@AllArgsConstructor
public class ClientProxy implements InvocationHandler {
    //传入参数Service接口的class对象，反射封装成一个request
    private String host;
    private int port;

    //jdk动态代理，每一次代理对象调用方法，都会经过此方法增强
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //使用lombok中的builder构建request
        RPCRequest request=RPCRequest.builder().interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .params(args)
                .paramsTypes(method.getParameterTypes())
                .build();
        //数据传输
        RPCResponse response=IOClient.sendRequest(host,port,request);
        return response.getData();
    }

    /**
     *
     * <T>T表示的是返回值T是泛型，传递啥类型就返回啥类型
     * T是一个占位符，告诉编译器，这个东西先给我留着，等我编译的时候告诉你
     * 生成代理类，返回代理类
     */
    <T>T getProxy(Class<T> clazz){
        Object o= Proxy.newProxyInstance(clazz.getClassLoader(),new Class[]{clazz},this);
        return (T)o;
    }
}
