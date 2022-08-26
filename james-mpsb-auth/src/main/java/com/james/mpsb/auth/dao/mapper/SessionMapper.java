package com.james.mpsb.auth.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.james.mpsb.auth.dao.po.Session;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
    Session queryByUserIdAndIp(int userId, String ip);

    List<Session> queryByUserId(int userId);


}

