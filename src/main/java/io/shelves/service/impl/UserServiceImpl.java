package io.shelves.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.shelves.common.exception.RRException;
import io.shelves.common.utils.PageUtils;
import io.shelves.common.validator.Assert;
import io.shelves.dao.UserDao;
import io.shelves.entity.TokenEntity;
import io.shelves.entity.UserEntity;
import io.shelves.form.LoginForm;
import io.shelves.service.TokenService;
import io.shelves.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {
	@Autowired
	private TokenService tokenService;

	@Override
	public UserEntity queryByMobile(String mobile) {
		UserEntity userEntity = new UserEntity();
		userEntity.setMobile(mobile);
		return baseMapper.selectOne(userEntity);
	}

	@Override
	public Map<String, Object> login(LoginForm form) {
		UserEntity user = queryByMobile(form.getMobile());
		Assert.isNull(user, "手机号或密码错误");

		//密码错误
		if(!user.getPassword().equals(DigestUtils.sha256Hex(form.getPassword()))){
			throw new RRException("手机号或密码错误");
		}

		//获取登录token
		TokenEntity tokenEntity = tokenService.createToken(user.getUserId());

		Map<String, Object> map = new HashMap<>(2);
		map.put("token", tokenEntity.getToken());
		map.put("expire", tokenEntity.getExpireTime().getTime() - System.currentTimeMillis());

		return map;
	}

	@Override
	public PageUtils queryPage(Page pageable, EntityWrapper<UserEntity> entityWrapper) {
		Page page = this.selectPage(pageable, entityWrapper);

		return new PageUtils(page);
	}

	@Override
	public boolean updatePassword(UserEntity entity, String password, String newPassword) {
		if (!entity.getPassword().equals(password)){
			return false;
		}
		entity.setPassword(newPassword);
		insertOrUpdate(entity);
		return true;
	}

	@Override
	public List<Long> queryAllMenuId(Long userId) {
		return baseMapper.queryAllMenuId(userId);
	}


}
