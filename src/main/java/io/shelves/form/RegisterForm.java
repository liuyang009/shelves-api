
package io.shelves.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

/**
 * 注册表单
 *
 * @author five.liu
 * @since 3.1.0 2018-01-25
 */
@ApiModel(value = "注册表单")
@Data
public class RegisterForm {

    @ApiModelProperty(value = "用户唯一标识，新增填0")
    private Long userId;

    @ApiModelProperty(value = "手机号")
    @NotBlank(message="手机号不能为空")
    @Pattern(regexp = "(\\+\\d+)?1[3458]\\d{9}$", message = "手机号格式不匹配")
    private String mobile;

    @ApiModelProperty(value = "密码")
    @NotBlank(message="密码不能为空")
    private String password;

    @ApiModelProperty(value = "用户名")
    @NotBlank(message="用户名不能为空")
    private String username;

    @ApiModelProperty(value = "身份证")
    @NotBlank(message="身份证不能为空")
    @Pattern(regexp = "[1-9]\\d{13,16}[a-zA-Z0-9]{1}",message = "身份证格式不匹配")
    private String identityCard;

    @ApiModelProperty(value = "上级用户ID")
    @Min(1)
    private Long parentUserId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别，0保密1男2女")
    private Integer gender;

    @ApiModelProperty(value = "固定电话")
    private String telephone;

    @ApiModelProperty(value = "出生年月")
    private String birth;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "角色ID")
    @Min(value = 1, message = "角色不能为空")
    private Long roleId;

}
