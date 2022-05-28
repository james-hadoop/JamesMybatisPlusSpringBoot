package com.james.mpsb.auth.mapper;

import com.james.mpsb.auth.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author james
 * @since 2022-05-27
 */
@Mapper
public interface DsUserMapper extends BaseMapper<User> {

}
