package io.shelves.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 用户
 *
 * @author five.liu
 * @date 2017-03-23 15:22:06
 */
@TableName("tb_user")
@Data
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@TableId
	private Long userId;
    /**
     * 上级用户
     */
	private Long parentUserId;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 性别
	 */
	private Integer gender;
	/**
	 * 固定电话
	 */
	private String telephone;
	/**
	 * 身份证
	 */
	private String identityCard;
	/**
	 * 出生年月
	 */
	private String birth;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 密码
	 */
	@JSONField(serialize=false)
	private String password;
	/**
	 * 创建时间
	 */
	private Date createTime;
}
