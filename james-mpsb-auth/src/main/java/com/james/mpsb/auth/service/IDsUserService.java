package com.james.mpsb.auth.service;

import com.james.mpsb.auth.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
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
public interface IDsUserService extends IService<User> {
    PageVo queryPage(QueryCondition params);
}
