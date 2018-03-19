package io.shelves.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import io.shelves.common.utils.PageUtils;
import io.shelves.entity.RoleEntity;

/**
 * ${DESCRIPTION}
 *
 * @author five.liu
 * @create 2018/3/12/012
 */
public interface RoleService extends IService<RoleEntity> {
    /**
     * 查询
     * @param page
     * @param wrapper
     * @return
     */
    PageUtils queryPage(Page page, EntityWrapper<RoleEntity> wrapper);
}
