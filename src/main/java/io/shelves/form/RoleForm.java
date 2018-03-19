package io.shelves.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author five.liu
 * @create 2018/3/12/012
 */
@ApiModel(value = "角色表单")
@Data
public class RoleForm {

    @ApiModelProperty(value = "角色ID，新增填0")
    @Min(0)
    private Long roleId;

    @ApiModelProperty(value = "角色名称")
    @NotBlank(message="角色名称不能为空")
    private String roleName;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "菜单ID列表")
    private List<Long> menuIds;
}
