package com.james.mpsb.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * james__yqc_爬虫数据
 * </p>
 *
 * @author james
 * @since 2020-02-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class YqcSpiderShanghai implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    private String title;

    /**
     * 标题
     */
    private String url;

    /**
     * 发布时间
     */
    private String pubTime;

    /**
     * 发布机构
     */
    private String pubOrg;

    /**
     * 文号
     */
    private String docId;

    /**
     * 索引号
     */
    private String indexId;

    /**
     * 关键字匹配数
     */
    private Integer keyCnt;

    /**
     * 地区
     */
    private String region;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 内容
     */
    private String cont;


}
