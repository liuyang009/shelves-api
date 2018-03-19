package io.shelves.service;

import com.baomidou.mybatisplus.service.IService;
import io.shelves.entity.RoleMenuEntity;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author five.liu
 * @create 2018/3/14/014
 */
public interface RoleMenuService extends IService<RoleMenuEntity> {
    /**
     *  保存
     * @param roleId
     * @param menuIds
     */
    void saveOrUpdate(Long roleId, List<Long> menuIds);

    /**
     * 根据角色ID，获取菜单ID列表
     * @param roleId
     * @return
     */
    List<Long> queryMenuIdList(Long roleId);

    /**
     * 根据角色ID数组，批量删除
     * @param roleIds
     * @return
     */
    int deleteBatch(Long[] roleIds);
}
