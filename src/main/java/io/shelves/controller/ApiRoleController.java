package io.shelves.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.shelves.annotation.Login;
import io.shelves.common.utils.PageUtils;
import io.shelves.common.utils.R;
import io.shelves.common.validator.ValidatorUtils;
import io.shelves.entity.RoleEntity;
import io.shelves.form.RoleForm;
import io.shelves.service.RoleMenuService;
import io.shelves.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * ${DESCRIPTION}
 *
 * @author five.liu
 * @create 2018/3/12/012
 */
@RestController
@RequestMapping("/api/role")
@Api(tags="角色接口")
public class ApiRoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Login
    @GetMapping("list")
    @ApiOperation(value = "角色列表", response = PageUtils.class)
    public R list(@RequestParam(required = false)
                  @ApiParam(value = "角色名") String roleName,
                  @RequestParam @ApiParam(value = "当前页") int pageNumber,
                  @RequestParam @ApiParam(value = "每页条数") int pageSize){

        EntityWrapper<RoleEntity> wrapper = new EntityWrapper<>();
        wrapper.like(StringUtils.isEmpty(roleName),
                "role_name", roleName);

        PageUtils page = roleService
                .queryPage(new Page(pageNumber, pageSize)
                        ,wrapper);

        return R.ok().put("page", page);
    }

    @Login
    @PostMapping("register")
    @ApiOperation("新增角色")
    public R add(@RequestBody RoleForm form){
        //验证角色
        ValidatorUtils.validateEntity(form);
        //新增角色
        RoleEntity entity = new RoleEntity();
        BeanUtils.copyProperties(form, entity);
        entity.setCreateTime(new Date());
        roleService.insert(entity);
        //绑定角色对应菜单权限
        roleMenuService.saveOrUpdate(entity.getRoleId()
                ,form.getMenuIds());

        return R.ok();
    }

    @Login
    @PostMapping("update")
    @ApiOperation("更新角色")
    public R update(@RequestBody RoleForm form){
        ValidatorUtils.validateEntity(form);

        RoleEntity entity = new RoleEntity();
        BeanUtils.copyProperties(form, entity);

        roleService.insertOrUpdate(entity);

        return R.ok();
    }

    @Login
    @DeleteMapping("delete")
    @ApiOperation("删除角色")
    public R delete(@RequestParam Long id){
        roleService.deleteById(id);
        return R.ok();
    }

}
