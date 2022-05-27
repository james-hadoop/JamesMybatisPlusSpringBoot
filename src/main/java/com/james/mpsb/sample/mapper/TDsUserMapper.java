package com.james.mpsb.sample.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.james.mpsb.sample.entity.TDsUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author james
 * @since 2022-05-20
 */
@Component
@Mapper
public interface TDsUserMapper extends BaseMapper<TDsUser> {

    @Select("select id, user_name, user_password, user_type, email, phone, tenant_id, create_time, update_time, queue, state, time_zone from t_ds_user")
    List<TDsUser> getAllUsers();
}
