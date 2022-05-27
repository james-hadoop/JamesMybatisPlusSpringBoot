package com.james.mpsb.auth.controller;


import com.james.mpsb.auth.service.IDsUserService;
import com.james.mpsb.common.entity.PageVo;
import com.james.mpsb.common.entity.QueryCondition;
import com.james.mpsb.common.entity.Resp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * 用户列表
 *
 * @author james
 * @since 2022-05-27
 */
@Api(tags = "用户列表")
@RestController
@RequestMapping("/auth/ds-user")
public class DsUserController {

    @Autowired
    private IDsUserService userService;

    /**
     * 列表
     */
    @ApiOperation("用户列表分页查询(排序)")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('auth:ds-user:list')")
    public Resp<PageVo> list(QueryCondition queryCondition) {
        PageVo page = userService.queryPage(queryCondition);

        System.out.println(page);

        return Resp.ok(page);
    }
}
