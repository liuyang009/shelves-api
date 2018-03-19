package io.shelves.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.shelves.dao.UserRoleDao;
import io.shelves.entity.UserRoleEntity;
import io.shelves.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * ${DESCRIPTION}
 *
 * @author five.liu
 * @create 2018/3/14/014
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRoleEntity> implements UserRoleService {
}
