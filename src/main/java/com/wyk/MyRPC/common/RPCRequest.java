/**
 * 定义客户端通用的请求消息的格式
 * 服务器不会只有一个服务一个方法，只传递参数不会直到调用哪个方法
 * 一个RPC请求中，client发送应该需要：调用的Service接口名，方法名，参数，参数类型
 * 服务端能够根据这些信息反射调用的响应的方法
 */
package com.wyk.MyRPC.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class RPCRequest implements Serializable {
    //服务类名，客户端只知道接口名，在服务端中用接口名指向实现类
    private String interfaceName;
    //方法名
    private String methodName;
    //请求的参数列表
    private Object[] params;
    //参数类型
    private Class<?>[] paramsTypes;
}