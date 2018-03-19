package io.shelves.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.shelves.dao.RoleMenuDao;
import io.shelves.entity.RoleMenuEntity;
import io.shelves.service.RoleMenuService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author five.liu
 * @create 2018/3/14/014
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuDao, RoleMenuEntity> implements RoleMenuService{
    @Override
    public void saveOrUpdate(Long roleId, List<Long> menuIds) {
        //先删除角色与菜单关系
        deleteBatch(new Long[]{roleId});

        if(menuIds.isEmpty()){
            return;
        }

        //保存角色与菜单关系
        List<RoleMenuEntity> list = new ArrayList<>(menuIds.size());

        menuIds.stream().forEach(menuId -> {
            RoleMenuEntity entity = new RoleMenuEntity();
            entity.setRoleId(roleId);
            entity.setMenuId(menuId);
            list.add(entity);
        });

        this.insertBatch(list);
    }

    @Override
    public List<Long> queryMenuIdList(Long roleId) {
        return baseMapper.queryMenuIdList(roleId);
    }

    @Override
    public int deleteBatch(Long[] roleIds) {
        return baseMapper.deleteBatch(roleIds);
    }
}
