package com.james.mpsb.sample.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.james.mpsb.sample.entity.TDsUser;
import com.james.mpsb.sample.mapper.TDsUserMapper;
import com.james.mpsb.sample.service.ITDsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author james
 * @since 2022-05-20
 */
@Service
@Primary
public class TDsUserServiceImpl extends ServiceImpl<TDsUserMapper, TDsUser> implements ITDsUserService {
    @Autowired
    TDsUserMapper userMapper;

    @Override
    public List<TDsUser> getAllUsers() {
        Page<TDsUser> page = new Page<>(1, 10);

//        QueryWrapper wrapper = new QueryWrapper<TDsUser>();
//        wrapper.eq("id", 1);

//        IPage<TDsUser> userIPage = userMapper.queryUserPaging(page);

        List<TDsUser> users  = userMapper.getAllUsers();

        return users;
    }
}
