package com.cintsoft.biru.service.impl;

import com.cintsoft.biru.model.User;
import com.cintsoft.biru.dao.UserMapper;
import com.cintsoft.biru.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author 胡昊
 * @since 2020-09-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
