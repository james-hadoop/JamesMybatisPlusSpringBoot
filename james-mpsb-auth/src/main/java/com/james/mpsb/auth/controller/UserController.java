package com.james.mpsb.auth.controller;


import com.james.mpsb.auth.aspect.AccessLogAnnotation;
import com.james.mpsb.auth.service.IUserService;
import com.james.mpsb.common.controller.BaseController;
import com.james.mpsb.common.entity.PageVo;
import com.james.mpsb.common.entity.QueryCondition;
import com.james.mpsb.common.entity.Resp;
import com.james.mpsb.common.entity.Result;
import com.james.mpsb.common.enums.Status;
import com.james.mpsb.common.exception.ApiException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static com.james.mpsb.common.enums.Status.IP_IS_EMPTY;
import static com.james.mpsb.common.enums.Status.USER_LOGIN_FAILURE;

/**
 * 用户列表
 *
 * @author james
 * @since 2022-05-27
 */
@Api(tags = "用户列表")
@RestController
@RequestMapping("/auth/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

//    /**
//     * login
//     *
//     * @param userName user name
//     * @param userPassword user password
//     * @param request request
//     * @param response response
//     * @return login result
//     */
//    @ApiOperation(value = "login", notes = "LOGIN_NOTES")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userName", value = "USER_NAME", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "userPassword", value = "USER_PASSWORD", required = true, dataType = "String")
//    })
//    @PostMapping(value = "/login")
//    @ApiException(USER_LOGIN_FAILURE)
//    @AccessLogAnnotation(ignoreRequestArgs = {"userPassword", "request", "response"})
//    public Resp<Map<String, String>> login(@RequestParam(value = "userName") String userName,
//                                           @RequestParam(value = "userPassword") String userPassword,
//                                           HttpServletRequest request,
//                                           HttpServletResponse response) {
//        //user name check
//        if (StringUtils.isEmpty(userName)) {
//            return Resp.fail(null);
//        }
//
//        // user ip check
//        String ip = getClientIpAddress(request);
//        if (StringUtils.isEmpty(ip)) {
//            return error(IP_IS_EMPTY.getCode(), IP_IS_EMPTY.getMsg());
//        }
//
//        // verify username and password
//        Result<Map<String, String>> result = authenticator.authenticate(userName, userPassword, ip);
//        if (result.getCode() != Status.SUCCESS.getCode()) {
//            return result;
//        }
//
//        response.setStatus(HttpStatus.SC_OK);
//        Map<String, String> cookieMap = result.getData();
//        for (Map.Entry<String, String> cookieEntry : cookieMap.entrySet()) {
//            Cookie cookie = new Cookie(cookieEntry.getKey(), cookieEntry.getValue());
//            cookie.setHttpOnly(true);
//            response.addCookie(cookie);
//        }
//
//
//        return Resp.ok(null);
//    }


    /**
     * 列表
     */
    @ApiOperation("用户列表分页查询(排序)")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('auth:user:list')")
    public Resp<PageVo> list(QueryCondition queryCondition) {
        PageVo page = userService.queryPage(queryCondition);

        return Resp.ok(page);
    }
}
