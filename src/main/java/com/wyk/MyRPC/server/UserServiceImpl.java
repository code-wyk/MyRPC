package com.wyk.MyRPC.server;

import com.wyk.MyRPC.common.User;
import com.wyk.MyRPC.jdbc.DbConnect;
import com.wyk.MyRPC.service.UserService;

import java.sql.SQLException;
import java.util.Random;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    DbConnect db=new DbConnect();
    @Override
    public User getUserById(Integer id) {
        System.out.println("客户端查询了id为"+id+"的用户");
//        User user= User.builder().userName(UUID.randomUUID().toString())
//                .id(id)
//                .sex(random.nextBoolean())
//                .build();
        User user= new User(id,getNameById(id),getSexById(id));
        return user;
    }

    @Override
    public String getNameById(Integer id){
        return db.getNameById(id);
    }

    @Override
    public String getSexById(Integer id) {
        if(db.getSexById(id)){
            return "男";
        }else{
            return "女";
        }
    }

    @Override
    public Integer insertUserId(User user) {
        System.out.println("插入数据成功："+user);
        return 1;
    }

}
