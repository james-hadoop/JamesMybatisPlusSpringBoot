package com.james.mpsb.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.james.mpsb.auth.entity.User;
import com.james.mpsb.auth.enums.UserType;
import com.james.mpsb.common.entity.PageVo;
import com.james.mpsb.common.entity.QueryCondition;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author james
 * @since 2022-05-27
 */
public interface IUserService extends IService<User> {
    PageVo queryPage(QueryCondition params);

    /**
     * query user by id
     *
     * @param id id
     * @return user info
     */
    User queryUser(int id);

    /**
     * get user by user name
     *
     * @param userName user name
     * @return exist user or null
     */
    User getUserByUserName(String userName);

    /***
     * create User for ldap login
     */
    User createUser(UserType userType, String userId, String email);

    /**
     * query user
     *
     * @param name name
     * @param password password
     * @return user info
     */
    User queryUser(String name, String password);

}
