package io.shelves.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.shelves.entity.UserEntity;

import java.util.List;

/**
 * 用户
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:22:06
 */
public interface UserDao extends BaseMapper<UserEntity> {

    /**
     * 查询用户的所有菜单ID
     * @param userId
     * @return
     */
    List<Long> queryAllMenuId(Long userId);
}
