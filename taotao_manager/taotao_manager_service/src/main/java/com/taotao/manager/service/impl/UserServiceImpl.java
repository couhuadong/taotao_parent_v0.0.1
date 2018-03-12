package com.taotao.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.taotao.manager.mapper.UserMapper;
import com.taotao.manager.model.User;
import com.taotao.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Service(interfaceClass = UserService.class)
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {


}
