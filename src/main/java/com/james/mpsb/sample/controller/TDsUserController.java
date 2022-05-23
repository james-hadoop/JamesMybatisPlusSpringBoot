package com.james.mpsb.sample.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.james.mpsb.sample.entity.TDsUser;
import com.james.mpsb.sample.service.ITDsUserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author james
 * @since 2022-05-20
 */
@RestController
@RequestMapping("/v1/user")
public class TDsUserController {
    private final static Logger logger = LoggerFactory.getLogger(TDsUserController.class);

    @Autowired
    ITDsUserService userService;

    @ApiOperation("获取所有用户信息")
    @RequestMapping(path = "/get_all_users", method = RequestMethod.GET)
    public List<TDsUser> getAllUsers() {
        return userService.getAllUsers();
    }

}
