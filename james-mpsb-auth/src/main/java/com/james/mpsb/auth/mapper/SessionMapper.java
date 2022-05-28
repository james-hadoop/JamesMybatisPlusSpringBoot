package com.james.mpsb.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.james.mpsb.auth.entity.Session;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author james
 * @since 2022-05-28
 */
@Mapper
public interface SessionMapper extends BaseMapper<Session> {

}
