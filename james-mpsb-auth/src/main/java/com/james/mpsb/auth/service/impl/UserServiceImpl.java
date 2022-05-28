package com.james.mpsb.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.james.mpsb.auth.entity.User;
import com.james.mpsb.auth.enums.UserType;
import com.james.mpsb.auth.mapper.UserMapper;
import com.james.mpsb.auth.service.IUserService;
import com.james.mpsb.common.entity.PageVo;
import com.james.mpsb.common.entity.Query;
import com.james.mpsb.common.entity.QueryCondition;
import com.james.mpsb.common.utils.EncryptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author james
 * @since 2022-05-27
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<User> page = this.page(
                new Query<User>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageVo(page);
    }

    /**
     * query user by id
     *
     * @param id id
     * @return user info
     */
    @Override
    public User queryUser(int id) {
        return userMapper.selectById(id);
    }

    /**
     * get user by user name
     *
     * @param userName user name
     * @return exist user or null
     */
    @Override
    public User getUserByUserName(String userName) {
        return userMapper.queryByUserNameAccurately(userName);
    }

    /***
     * create User for ldap login
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public User createUser(UserType userType, String userId, String email) {
        User user = new User();
        Date now = new Date();

        user.setUserName(userId);
        user.setEmail(email);
        // create general users, administrator users are currently built-in
        user.setUserType(userType);
        user.setCreateTime(now);
        user.setUpdateTime(now);
        user.setQueue("");

        // save user
        userMapper.insert(user);
        return user;
    }

    /**
     * query user
     *
     * @param name     name
     * @param password password
     * @return user info
     */
    @Override
    public User queryUser(String name, String password) {
        String md5 = EncryptionUtils.getMd5(password);
        return userMapper.queryUserByNamePassword(name, md5);
    }
}
