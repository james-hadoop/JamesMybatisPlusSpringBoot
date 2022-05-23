package com.james.mpsb.sample.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.james.mpsb.sample.entity.TDsUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author james
 * @since 2022-05-20
 */
public interface ITDsUserService extends IService<TDsUser> {
    List<TDsUser> getAllUsers();
}
