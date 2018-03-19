package io.shelves.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author five.liu
 * @create 2018/3/12/012
 */
@TableName("tb_menu")
@Data
public class MenuEntity {

    @TableId
    private Long id;
    /**
     * 父菜单ID。一级菜单为0
     */
    private Long parentId;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单URL
     */
    private String url;
    /**
     * 授权（多个用逗号分割，如：user:list,user:create）
     */
    private String perms;
    /**
     * 类型 0：目录 1：菜单 2：按钮
     */
    private Integer type;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 排序
     */
    private Integer orderNum;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

    @TableField(exist=false)
    private List<?> list;

}
