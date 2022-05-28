package com.james.mpsb.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.james.mpsb.auth.entity.User;
import com.james.mpsb.auth.mapper.DsUserMapper;
import com.james.mpsb.auth.service.IDsUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.james.mpsb.common.entity.PageVo;
import com.james.mpsb.common.entity.Query;
import com.james.mpsb.common.entity.QueryCondition;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author james
 * @since 2022-05-27
 */
@Service("userService")
public class DsUserServiceImpl extends ServiceImpl<DsUserMapper, User> implements IDsUserService {

    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<User> page = this.page(
                new Query<User>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageVo(page);
    }
}
