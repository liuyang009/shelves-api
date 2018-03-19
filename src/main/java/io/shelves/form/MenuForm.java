package io.shelves.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * ${DESCRIPTION}
 *
 * @author five.liu
 * @create 2018/3/13/013
 */
@Data
public class MenuForm {

    @Min(0)
    @ApiModelProperty(value = "用户唯一标识，新增填0")
    private Long id;

    @Min(0)
    @ApiModelProperty(value = "父菜单ID。一级菜单为0")
    private Long parentId;

    @NotBlank(message="菜单名称不能为空")
    @ApiModelProperty(value = "菜单名")
    private String name;

    @ApiModelProperty(value = "菜单URL")
    private String url;

    @ApiModelProperty(value = "授权")
    private String perms;

    @ApiModelProperty(value = "类型 0：目录 1：菜单 2：按钮")
    @NotNull
    private Integer type;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "排序")
    private Integer orderNum;
}
