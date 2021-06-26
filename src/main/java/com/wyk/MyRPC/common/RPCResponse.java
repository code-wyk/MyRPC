/**
 * 定义一个通用的Response消息格式
 * 一个应用中不可能只传输一种类型的数据
 * 将传输对象抽象称为Object
 * RPC需要经过网络传输，有可能失败，类似于http，引入状态码和状态信息表示服务调用成功还是失败
 */
package com.wyk.MyRPC.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class RPCResponse implements Serializable {
    //状态信息
    private int code;
    private String message;
    //具体数据
    private Object data;

    public static RPCResponse success(Object data){
        return RPCResponse.builder().code(200).data(data).build();
    }
    public static RPCResponse fail(){
        return RPCResponse.builder().code(500).message("服务器发生错误").build();
    }
}
