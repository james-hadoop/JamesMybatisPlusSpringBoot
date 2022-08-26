package com.james.mpsb.auth.controller;


import com.james.mpsb.auth.aspect.AccessLogAnnotation;
import com.james.mpsb.auth.dao.po.User;
import com.james.mpsb.auth.service.UsersService;
import com.james.mpsb.common.Constants;
import com.james.mpsb.common.controller.BaseController;
import com.james.mpsb.common.entity.Result;
import com.james.mpsb.common.enums.Status;
import com.james.mpsb.common.exception.ApiException;
import com.james.mpsb.common.utils.ParameterUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.james.mpsb.common.enums.Status.*;

/**
 * users controller
 *
 * @author james
 * @since 2022-05-27
 */
@Api(tags = "USERS_TAG")
@RestController
@RequestMapping("/users")
public class UsersController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UsersService usersService;

    /**
     * create user
     *
     * @param loginUser login user
     * @param userName user name
     * @param userPassword user password
     * @param email email
     * @param tenantId tenant id
     * @param phone phone
     * @param queue queue
     * @return create result code
     */
    @ApiOperation(value = "createUser", notes = "CREATE_USER_NOTES")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "USER_NAME", required = true, type = "String"),
            @ApiImplicitParam(name = "userPassword", value = "USER_PASSWORD", required = true, type = "String"),
            @ApiImplicitParam(name = "tenantId", value = "TENANT_ID", required = true, dataType = "Int", example = "100"),
            @ApiImplicitParam(name = "queue", value = "QUEUE", dataType = "String"),
            @ApiImplicitParam(name = "email", value = "EMAIL", required = true, dataType = "String"),
            @ApiImplicitParam(name = "phone", value = "PHONE", dataType = "String"),
            @ApiImplicitParam(name = "state", value = "STATE", dataType = "Int", example = "1")
    })
    @PostMapping(value = "/create")
    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    @ApiException(CREATE_USER_ERROR)
    @AccessLogAnnotation(ignoreRequestArgs = {"loginUser", "userPassword"})
    public Result createUser(@ApiIgnore @RequestAttribute(value = Constants.SESSION_USER) User loginUser,
                             @RequestParam(value = "userName") String userName,
                             @RequestParam(value = "userPassword") String userPassword,
                             @RequestParam(value = "tenantId") int tenantId,
                             @RequestParam(value = "queue", required = false, defaultValue = "") String queue,
                             @RequestParam(value = "email") String email,
                             @RequestParam(value = "phone", required = false) String phone,
                             @RequestParam(value = "state", required = false) int state) throws Exception {
        Map<String, Object> result = usersService.createUser(loginUser, userName, userPassword, email, tenantId, phone, queue, state);
        return returnDataList(result);
    }

    /**
     * query user list paging
     *
     * @param loginUser login user
     * @param pageNo page number
     * @param searchVal search avlue
     * @param pageSize page size
     * @return user list page
     */
    @ApiOperation(value = "queryUserList", notes = "QUERY_USER_LIST_NOTES")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "PAGE_NO", required = true, dataType = "Int", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "PAGE_SIZE", required = true, dataType = "Int", example = "10"),
            @ApiImplicitParam(name = "searchVal", value = "SEARCH_VAL", type = "String")
    })
    @GetMapping(value = "/list-paging")
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    @ApiException(QUERY_USER_LIST_PAGING_ERROR)
    @AccessLogAnnotation(ignoreRequestArgs = "loginUser")
    public Result queryUserList(@ApiIgnore @RequestAttribute(value = Constants.SESSION_USER) User loginUser,
                                @RequestParam("pageNo") Integer pageNo,
                                @RequestParam("pageSize") Integer pageSize,
                                @RequestParam(value = "searchVal", required = false) String searchVal) {
        Result result = checkPageParams(pageNo, pageSize);
        if (!result.checkResult()) {
            return result;

        }
        searchVal = ParameterUtils.handleEscapes(searchVal);
        result = usersService.queryUserList(loginUser, searchVal, pageNo, pageSize);
        return result;
    }


    /**
     * update user
     *
     * @param loginUser login user
     * @param id user id
     * @param userName user name
     * @param userPassword user password
     * @param email email
     * @param tenantId tennat id
     * @param phone phone
     * @param queue queue
     * @return update result code
     */
    @ApiOperation(value = "updateUser", notes = "UPDATE_USER_NOTES")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "USER_ID", required = true, dataType = "Int", example = "100"),
            @ApiImplicitParam(name = "userName", value = "USER_NAME", required = true, type = "String"),
            @ApiImplicitParam(name = "userPassword", value = "USER_PASSWORD", required = true, type = "String"),
            @ApiImplicitParam(name = "tenantId", value = "TENANT_ID", required = true, dataType = "Int", example = "100"),
            @ApiImplicitParam(name = "queue", value = "QUEUE", dataType = "String"),
            @ApiImplicitParam(name = "email", value = "EMAIL", required = true, dataType = "String"),
            @ApiImplicitParam(name = "phone", value = "PHONE", dataType = "String"),
            @ApiImplicitParam(name = "state", value = "STATE", dataType = "Int", example = "1")
    })
    @PostMapping(value = "/update")
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    @ApiException(UPDATE_USER_ERROR)
    @AccessLogAnnotation(ignoreRequestArgs = {"loginUser", "userPassword"})
    public Result updateUser(@ApiIgnore @RequestAttribute(value = Constants.SESSION_USER) User loginUser,
                             @RequestParam(value = "id") int id,
                             @RequestParam(value = "userName") String userName,
                             @RequestParam(value = "userPassword") String userPassword,
                             @RequestParam(value = "queue", required = false, defaultValue = "") String queue,
                             @RequestParam(value = "email") String email,
                             @RequestParam(value = "tenantId") int tenantId,
                             @RequestParam(value = "phone", required = false) String phone,
                             @RequestParam(value = "state", required = false) int state,
                             @RequestParam(value = "timeZone", required = false) String timeZone) throws Exception {
        Map<String, Object> result = usersService.updateUser(loginUser, id, userName, userPassword, email, tenantId, phone, queue, state, timeZone);
        return returnDataList(result);
    }

    /**
     * delete user by id
     *
     * @param loginUser login user
     * @param id user id
     * @return delete result code
     */
    @ApiOperation(value = "delUserById", notes = "DELETE_USER_BY_ID_NOTES")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "USER_ID", required = true, dataType = "Int", example = "100")
    })
    @PostMapping(value = "/delete")
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    @ApiException(DELETE_USER_BY_ID_ERROR)
    @AccessLogAnnotation
    public Result delUserById(@ApiIgnore @RequestAttribute(value = Constants.SESSION_USER) User loginUser,
                              @RequestParam(value = "id") int id) throws Exception {
        Map<String, Object> result = usersService.deleteUserById(loginUser, id);
        return returnDataList(result);
    }

    /**
     * grant project
     *
     * @param loginUser login user
     * @param userId user id
     * @param projectIds project id array
     * @return grant result code
     */
    @ApiOperation(value = "grantProject", notes = "GRANT_PROJECT_NOTES")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "USER_ID", required = true, dataType = "Int", example = "100"),
            @ApiImplicitParam(name = "projectIds", value = "PROJECT_IDS", required = true, type = "String")
    })
    @PostMapping(value = "/grant-project")
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    @ApiException(GRANT_PROJECT_ERROR)
    @AccessLogAnnotation
    public Result grantProject(@ApiIgnore @RequestAttribute(value = Constants.SESSION_USER) User loginUser,
                               @RequestParam(value = "userId") int userId,
                               @RequestParam(value = "projectIds") String projectIds) {
        Map<String, Object> result = usersService.grantProject(loginUser, userId, projectIds);
        return returnDataList(result);
    }

    /**
     * grant project by code
     *
     * @param loginUser login user
     * @param userId user id
     * @param projectCode project code
     * @return grant result code
     */
    @ApiOperation(value = "grantProjectByCode", notes = "GRANT_PROJECT_BY_CODE_NOTES")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "USER_ID", required = true, dataType = "Int", example = "100"),
            @ApiImplicitParam(name = "projectCode", value = "PROJECT_CODE", required = true, type = "Long")
    })
    @PostMapping(value = "/grant-project-by-code")
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    @ApiException(GRANT_PROJECT_ERROR)
    @AccessLogAnnotation
    public Result grantProjectByCode(@ApiIgnore @RequestAttribute(value = Constants.SESSION_USER) User loginUser,
                                     @RequestParam(value = "userId") int userId,
                                     @RequestParam(value = "projectCode") long projectCode) {
        Map<String, Object> result = this.usersService.grantProjectByCode(loginUser, userId, projectCode);
        return this.returnDataList(result);
    }

    /**
     * revoke project
     *
     * @param loginUser     login user
     * @param userId        user id
     * @param projectCode   project code
     * @return revoke result code
     */
    @ApiOperation(value = "revokeProject", notes = "REVOKE_PROJECT_NOTES")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "USER_ID", required = true, dataType = "Int", example = "100"),
            @ApiImplicitParam(name = "projectCode", value = "PROJECT_CODE", required = true, type = "Long", example = "100")
    })
    @PostMapping(value = "/revoke-project")
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    @ApiException(REVOKE_PROJECT_ERROR)
    @AccessLogAnnotation
    public Result revokeProject(@ApiIgnore @RequestAttribute(value = Constants.SESSION_USER) User loginUser,
                                @RequestParam(value = "userId") int userId,
                                @RequestParam(value = "projectCode") long projectCode) {
        Map<String, Object> result = this.usersService.revokeProject(loginUser, userId, projectCode);
        return returnDataList(result);
    }

    /**
     * grant resource
     *
     * @param loginUser login user
     * @param userId user id
     * @param resourceIds resource id array
     * @return grant result code
     */
    @ApiOperation(value = "grantResource", notes = "GRANT_RESOURCE_NOTES")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "USER_ID", required = true, dataType = "Int", example = "100"),
            @ApiImplicitParam(name = "resourceIds", value = "RESOURCE_IDS", required = true, type = "String")
    })
    @PostMapping(value = "/grant-file")
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    @ApiException(GRANT_RESOURCE_ERROR)
    @AccessLogAnnotation
    public Result grantResource(@ApiIgnore @RequestAttribute(value = Constants.SESSION_USER) User loginUser,
                                @RequestParam(value = "userId") int userId,
                                @RequestParam(value = "resourceIds") String resourceIds) {
        Map<String, Object> result = usersService.grantResources(loginUser, userId, resourceIds);
        return returnDataList(result);
    }


    /**
     * grant udf function
     *
     * @param loginUser login user
     * @param userId user id
     * @param udfIds udf id array
     * @return grant result code
     */
    @ApiOperation(value = "grantUDFFunc", notes = "GRANT_UDF_FUNC_NOTES")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "USER_ID", required = true, dataType = "Int", example = "100"),
            @ApiImplicitParam(name = "udfIds", value = "UDF_IDS", required = true, type = "String")
    })
    @PostMapping(value = "/grant-udf-func")
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    @ApiException(GRANT_UDF_FUNCTION_ERROR)
    @AccessLogAnnotation
    public Result grantUDFFunc(@ApiIgnore @RequestAttribute(value = Constants.SESSION_USER) User loginUser,
                               @RequestParam(value = "userId") int userId,
                               @RequestParam(value = "udfIds") String udfIds) {
        Map<String, Object> result = usersService.grantUDFFunction(loginUser, userId, udfIds);
        return returnDataList(result);
    }


//    /**
//     * grant namespace
//     *
//     * @param loginUser login user
//     * @param userId user id
//     * @param namespaceIds namespace id array
//     * @return grant result code
//     */
//    @ApiOperation(value = "grantNamespace", notes = "GRANT_NAMESPACE_NOTES")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userId", value = "USER_ID", required = true, dataType = "Int", example = "100"),
//            @ApiImplicitParam(name = "namespaceIds", value = "NAMESPACE_IDS", required = true, type = "String")
//    })
//    @PostMapping(value = "/grant-namespace")
//    @ResponseStatus(org.springframework.http.HttpStatus.OK)
//    @ApiException(GRANT_K8S_NAMESPACE_ERROR)
//    @AccessLogAnnotation
//    public Result grantNamespace(@ApiIgnore @RequestAttribute(value = Constants.SESSION_USER) User loginUser,
//                                 @RequestParam(value = "userId") int userId,
//                                 @RequestParam(value = "namespaceIds") String namespaceIds) {
//        Map<String, Object> result = usersService.grantNamespaces(loginUser, userId, namespaceIds);
//        return returnDataList(result);
//    }


    /**
     * grant datasource
     *
     * @param loginUser login user
     * @param userId user id
     * @param datasourceIds data source id array
     * @return grant result code
     */
    @ApiOperation(value = "grantDataSource", notes = "GRANT_DATASOURCE_NOTES")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "USER_ID", required = true, dataType = "Int", example = "100"),
            @ApiImplicitParam(name = "datasourceIds", value = "DATASOURCE_IDS", required = true, type = "String")
    })
    @PostMapping(value = "/grant-datasource")
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    @ApiException(GRANT_DATASOURCE_ERROR)
    @AccessLogAnnotation
    public Result grantDataSource(@ApiIgnore @RequestAttribute(value = Constants.SESSION_USER) User loginUser,
                                  @RequestParam(value = "userId") int userId,
                                  @RequestParam(value = "datasourceIds") String datasourceIds) {
        Map<String, Object> result = usersService.grantDataSource(loginUser, userId, datasourceIds);
        return returnDataList(result);
    }


    /**
     * get user info
     *
     * @param loginUser login user
     * @return user info
     */
    @ApiOperation(value = "getUserInfo", notes = "GET_USER_INFO_NOTES")
    @GetMapping(value = "/get-user-info")
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    @ApiException(GET_USER_INFO_ERROR)
    @AccessLogAnnotation
    public Result getUserInfo(@ApiIgnore @RequestAttribute(value = Constants.SESSION_USER) User loginUser) {
        Map<String, Object> result = usersService.getUserInfo(loginUser);
        return returnDataList(result);
    }

    /**
     * user list no paging
     *
     * @param loginUser login user
     * @return user list
     */
    @ApiOperation(value = "listUser", notes = "LIST_USER_NOTES")
    @GetMapping(value = "/list")
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    @ApiException(USER_LIST_ERROR)
    @AccessLogAnnotation
    public Result listUser(@ApiIgnore @RequestAttribute(value = Constants.SESSION_USER) User loginUser) {
        Map<String, Object> result = usersService.queryAllGeneralUsers(loginUser);
        return returnDataList(result);
    }


    /**
     * user list no paging
     *
     * @param loginUser login user
     * @return user list
     */
    @GetMapping(value = "/list-all")
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    @ApiException(USER_LIST_ERROR)
    @AccessLogAnnotation
    public Result listAll(@RequestAttribute(value = Constants.SESSION_USER) User loginUser) {
        Map<String, Object> result = usersService.queryUserList(loginUser);
        return returnDataList(result);
    }


    /**
     * verify username
     *
     * @param loginUser login user
     * @param userName user name
     * @return true if user name not exists, otherwise return false
     */
    @ApiOperation(value = "verifyUserName", notes = "VERIFY_USER_NAME_NOTES")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "USER_NAME", required = true, type = "String")
    })
    @GetMapping(value = "/verify-user-name")
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    @ApiException(VERIFY_USERNAME_ERROR)
    @AccessLogAnnotation
    public Result verifyUserName(@ApiIgnore @RequestAttribute(value = Constants.SESSION_USER) User loginUser,
                                 @RequestParam(value = "userName") String userName
    ) {
        return usersService.verifyUserName(userName);
    }


    /**
     * unauthorized user
     *
     * @param loginUser login user
     * @param alertgroupId alert group id
     * @return unauthorize result code
     */
    @ApiOperation(value = "unauthorizedUser", notes = "UNAUTHORIZED_USER_NOTES")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "alertgroupId", value = "ALERT_GROUP_ID", required = true, type = "String")
    })
    @GetMapping(value = "/unauth-user")
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    @ApiException(UNAUTHORIZED_USER_ERROR)
    @AccessLogAnnotation
    public Result unauthorizedUser(@ApiIgnore @RequestAttribute(value = Constants.SESSION_USER) User loginUser,
                                   @RequestParam("alertgroupId") Integer alertgroupId) {
        Map<String, Object> result = usersService.unauthorizedUser(loginUser, alertgroupId);
        return returnDataList(result);
    }


    /**
     * authorized user
     *
     * @param loginUser login user
     * @param alertgroupId alert group id
     * @return authorized result code
     */
    @ApiOperation(value = "authorizedUser", notes = "AUTHORIZED_USER_NOTES")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "alertgroupId", value = "ALERT_GROUP_ID", required = true, type = "String")
    })
    @GetMapping(value = "/authed-user")
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    @ApiException(AUTHORIZED_USER_ERROR)
    @AccessLogAnnotation
    public Result authorizedUser(@ApiIgnore @RequestAttribute(value = Constants.SESSION_USER) User loginUser,
                                 @RequestParam("alertgroupId") Integer alertgroupId) {
        try {
            Map<String, Object> result = usersService.authorizedUser(loginUser, alertgroupId);
            return returnDataList(result);
        } catch (Exception e) {
            logger.error(Status.AUTHORIZED_USER_ERROR.getMsg(), e);
            return error(Status.AUTHORIZED_USER_ERROR.getCode(), Status.AUTHORIZED_USER_ERROR.getMsg());
        }
    }

    /**
     * user registry
     *
     * @param userName user name
     * @param userPassword user password
     * @param repeatPassword repeat password
     * @param email user email
     */
    @ApiOperation(value = "registerUser", notes = "REGISTER_USER_NOTES")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "USER_NAME", required = true, type = "String"),
            @ApiImplicitParam(name = "userPassword", value = "USER_PASSWORD", required = true, type = "String"),
            @ApiImplicitParam(name = "repeatPassword", value = "REPEAT_PASSWORD", required = true, type = "String"),
            @ApiImplicitParam(name = "email", value = "EMAIL", required = true, type = "String"),
    })
    @PostMapping("/register")
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    @ApiException(CREATE_USER_ERROR)
    @AccessLogAnnotation
    public Result<Object> registerUser(@RequestParam(value = "userName") String userName,
                                       @RequestParam(value = "userPassword") String userPassword,
                                       @RequestParam(value = "repeatPassword") String repeatPassword,
                                       @RequestParam(value = "email") String email) throws Exception {
        userName = ParameterUtils.handleEscapes(userName);
        userPassword = ParameterUtils.handleEscapes(userPassword);
        repeatPassword = ParameterUtils.handleEscapes(repeatPassword);
        email = ParameterUtils.handleEscapes(email);
        Map<String, Object> result = usersService.registerUser(userName, userPassword, repeatPassword, email);
        return returnDataList(result);
    }

    /**
     * user activate
     *
     * @param userName user name
     */
    @ApiOperation(value = "activateUser", notes = "ACTIVATE_USER_NOTES")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "USER_NAME", type = "String"),
    })
    @PostMapping("/activate")
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    @ApiException(UPDATE_USER_ERROR)
    @AccessLogAnnotation
    public Result<Object> activateUser(@ApiIgnore @RequestAttribute(value = Constants.SESSION_USER) User loginUser,
                                       @RequestParam(value = "userName") String userName) {
        userName = ParameterUtils.handleEscapes(userName);
        Map<String, Object> result = usersService.activateUser(loginUser, userName);
        return returnDataList(result);
    }

    /**
     * user batch activate
     *
     * @param userNames user names
     */
    @ApiOperation(value = "batchActivateUser", notes = "BATCH_ACTIVATE_USER_NOTES")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNames", value = "USER_NAMES", required = true, type = "String"),
    })
    @PostMapping("/batch/activate")
    @ResponseStatus(HttpStatus.OK)
    @ApiException(UPDATE_USER_ERROR)
    @AccessLogAnnotation
    public Result<Object> batchActivateUser(@ApiIgnore @RequestAttribute(value = Constants.SESSION_USER) User loginUser,
                                            @RequestBody List<String> userNames) {
        List<String> formatUserNames = userNames.stream().map(ParameterUtils::handleEscapes).collect(Collectors.toList());
        Map<String, Object> result = usersService.batchActivateUser(loginUser, formatUserNames);
        return returnDataList(result);
    }
}