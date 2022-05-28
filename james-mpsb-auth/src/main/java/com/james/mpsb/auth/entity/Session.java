package com.james.mpsb.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author james
 * @since 2022-05-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_ds_session")
@ApiModel(value="Session对象", description="")
public class Session implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "key")
    private String id;

    @ApiModelProperty(value = "user id")
    private Integer userId;

    @ApiModelProperty(value = "ip")
    private String ip;

    @ApiModelProperty(value = "last login time")
    private LocalDateTime lastLoginTime;


}
