package io.shelves.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import io.shelves.common.utils.PageUtils;
import io.shelves.entity.MenuEntity;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author five.liu
 * @create 2018/3/13/013
 */
public interface MenuService extends IService<MenuEntity> {
    /**
     * 查询
     * @param page
     * @param wrapper
     * @return
     */
    PageUtils queryPage(Page page, EntityWrapper<MenuEntity> wrapper);

    /**
     * 用户菜单导航
     * @param userId
     * @return
     */
    List<MenuEntity> getUserMenuList(Long userId);

    /**
     * 查询：根据parentId获取子菜单
     * @param parentId
     * @return
     */
    List<MenuEntity> queryListParentId(Long parentId);

    /**
     * 结构化的菜单
     * @return
     */
    List<MenuEntity> getAllMenuList();
}
