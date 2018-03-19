package io.shelves.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import io.shelves.common.utils.Constant;
import io.shelves.common.utils.PageUtils;
import io.shelves.dao.MenuDao;
import io.shelves.entity.MenuEntity;
import io.shelves.service.MenuService;
import io.shelves.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author five.liu
 * @create 2018/3/13/013
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuDao, MenuEntity> implements MenuService {

    @Autowired
    private UserService userService;

    @Override
    public PageUtils queryPage(Page pageable, EntityWrapper<MenuEntity> wrapper) {

        Page page = this.selectPage(pageable, wrapper);

        return new PageUtils(page);
    }

    @Override
    public List<MenuEntity> getUserMenuList(Long userId) {
        //系统管理员，拥有最高权限
        if (userId == Constant.SUPER_ADMIN){
            return getAllMenuList(null);
        }

        //用户菜单列表
        List<Long> menuIdList = userService.queryAllMenuId(userId);

        return getAllMenuList(menuIdList);
    }

    @Override
    public List<MenuEntity> queryListParentId(Long parentId) {
        return baseMapper.queryListParentId(parentId);
    }

    @Override
    public List<MenuEntity> getAllMenuList() {
        List<MenuEntity> catalogMenus = queryListParentId(0L);
        catalogMenus.stream().forEach(menuEntity -> {
            List<MenuEntity> urlMenus = queryListParentId(menuEntity.getId());
            menuEntity.setList(urlMenus);
            urlMenus.stream().forEach(m -> {
                List<MenuEntity> buttonMenus = queryListParentId(m.getId());
                m.setList(buttonMenus);
            });
        });
        return catalogMenus;
    }

    public List<MenuEntity> queryListParentId(Long parentId, List<Long> menuIdList) {
        List<MenuEntity> menuList = queryListParentId(parentId);
        if(menuIdList == null){
            return menuList;
        }

        List<MenuEntity> userMenuList = new ArrayList<>();
        for(MenuEntity menu : menuList){
            if(menuIdList.contains(menu.getId())){
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    private List<MenuEntity> getAllMenuList(List<Long> menuIdList) {

        //查询根菜单列表
        List<MenuEntity> menuList = queryListParentId(0L, menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);

        return menuList;
    }

    /**
     * 递归
     */
    private List<MenuEntity> getMenuTreeList(List<MenuEntity> menuList, List<Long> menuIdList){
        List<MenuEntity> subMenuList = Lists.newArrayList();

        for(MenuEntity entity : menuList){
            //目录
            if(entity.getType() == Constant.MenuType.CATALOG.getValue()){
                entity.setList(getMenuTreeList(queryListParentId(entity.getId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }

        return subMenuList;
    }
}
