package io.shelves.service;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import io.shelves.common.utils.PageUtils;
import io.shelves.entity.UserEntity;
import io.shelves.form.LoginForm;

import java.util.List;
import java.util.Map;

/**
 * 用户
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:22:06
 */
public interface UserService extends IService<UserEntity> {

	UserEntity queryByMobile(String mobile);

	/**
	 * 用户登录
	 * @param form    登录表单
	 * @return        返回登录信息
	 */
	Map<String, Object> login(LoginForm form);

	/**
	 * 查询：当前用户添加的用户列表
	 * @param pageable
	 * @param entityWrapper
	 * @return
	 */
	PageUtils queryPage(Page pageable, EntityWrapper<UserEntity> entityWrapper);

	/**
	 * 用户：更新密码
	 * @param userEntity
	 * @param password
	 * @param newPassword
	 * @return
	 */
    boolean updatePassword(UserEntity userEntity, String password, String newPassword);

	/**
	 * 查询用户的所有菜单ID
	 * @param userId
	 * @return
	 */
	List<Long> queryAllMenuId(Long userId);
}
