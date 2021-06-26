package com.wyk.MyRPC.service;

import com.wyk.MyRPC.common.User;

public interface UserService {
    User getUserById(Integer id);
    String getNameById(Integer id);
    String getSexById(Integer id);

    Integer insertUserId(User user);
}
