package com.james.mpsb.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author james
 * @since 2022-05-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_ds_user")
@ApiModel(value="DsUser对象", description="")
public class DsUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "user id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "user name")
    private String userName;

    @ApiModelProperty(value = "user password")
    private String userPassword;

    @ApiModelProperty(value = "user type, 0:administrator，1:ordinary user")
    private Integer userType;

    @ApiModelProperty(value = "email")
    private String email;

    @ApiModelProperty(value = "phone")
    private String phone;

    @ApiModelProperty(value = "tenant id")
    private Integer tenantId;

    @ApiModelProperty(value = "create time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "update time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "queue")
    private String queue;

    @ApiModelProperty(value = "state 0:disable 1:enable")
    private Integer state;

    @ApiModelProperty(value = "time zone")
    private String timeZone;


}
