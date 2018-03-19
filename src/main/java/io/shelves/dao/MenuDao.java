package io.shelves.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.shelves.entity.MenuEntity;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author five.liu
 * @create 2018/3/13/013
 */
public interface MenuDao extends BaseMapper<MenuEntity> {
    /**
     * 菜单
     * @param parentId
     * @return
     */
    List<MenuEntity> queryListParentId(Long parentId);
}
