package io.shelves.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * ${DESCRIPTION}
 *
 * @author five.liu
 * @create 2018/3/14/014
 */
@TableName("tb_user_role")
@Data
public class UserRoleEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 用户主键
     */
    private Long userId;
    /**
     * 角色主键
     */
    private Long roleId;
}
