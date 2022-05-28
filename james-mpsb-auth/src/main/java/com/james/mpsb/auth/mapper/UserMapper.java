package com.james.mpsb.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.james.mpsb.auth.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author james
 * @since 2022-05-27
 */
@Mapper
//@CacheConfig(cacheNames = "user", keyGenerator = "cacheKeyGenerator")
public interface UserMapper extends BaseMapper<User> {
    /**
     * query user by name
     *
     * @param userName userName
     * @return user
     */
    User queryByUserNameAccurately(@Param("userName") String userName);

    /**
     * select by user id
     */
    @Cacheable(sync = true)
    User selectById(int id);

    /**
     * query user by userName and password
     *
     * @param userName userName
     * @param password password
     * @return user
     */
    User queryUserByNamePassword(@Param("userName") String userName, @Param("password") String password);


}
