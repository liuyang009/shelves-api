package io.shelves.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.shelves.common.utils.PageUtils;
import io.shelves.dao.RoleDao;
import io.shelves.entity.RoleEntity;
import io.shelves.service.RoleService;
import org.springframework.stereotype.Service;


/**
 * @author Administrator
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, RoleEntity> implements RoleService {

    @Override
    public PageUtils queryPage(Page pageable, EntityWrapper<RoleEntity> wrapper) {

        Page page = this.selectPage(pageable, wrapper);

        return new PageUtils(page);
    }
}
