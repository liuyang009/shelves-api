package io.shelves.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.shelves.annotation.Login;
import io.shelves.common.utils.PageUtils;
import io.shelves.common.utils.R;
import io.shelves.common.validator.ValidatorUtils;
import io.shelves.entity.MenuEntity;
import io.shelves.form.MenuForm;
import io.shelves.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author five.liu
 * @create 2018/3/13/013
 */
@RestController
@RequestMapping("/api/menu")
@Api(tags="菜单接口")
public class ApiMenuController extends BaseController{

    @Autowired
    private MenuService menuService;

    @Login
    @GetMapping("nav")
    @ApiOperation(value = "导航菜单")
    public R nav(){
        List<MenuEntity> menuList = menuService
                .getUserMenuList(getUser().getUserId());

        return R.ok().put("menuList", menuList);
    }

    @Login
    @GetMapping("tree")
    @ApiOperation(value = "树形菜单")
    public R treeMenus(){
        List<MenuEntity> menuEntities = menuService.getAllMenuList();
        return R.ok().put("menuList",menuEntities);
    }

    @Login
    @GetMapping("list")
    @ApiOperation(value = "菜单列表", response = PageUtils.class)
    public R list(@RequestParam(required = false)
                  @ApiParam(value = "菜单名") String name,
                  @RequestParam @ApiParam(value = "当前页") int pageNumber,
                  @RequestParam @ApiParam(value = "每页条数") int pageSize){

        EntityWrapper<MenuEntity> wrapper = new EntityWrapper<>();
        wrapper.like(StringUtils.isEmpty(name),
                "name", name);

        PageUtils page = menuService
                .queryPage(new Page(pageNumber, pageSize)
                        ,wrapper);

        return R.ok().put("page", page);
    }


    @Login
    @PostMapping("register")
    @ApiOperation(value = "新增菜单")
    public R register(@RequestBody MenuForm form){

        ValidatorUtils.validateEntity(form);

        MenuEntity entity = new MenuEntity();
        BeanUtils.copyProperties(form, entity);
        Date now = new Date();
        entity.setCreateTime(now);
        entity.setUpdateTime(now);

        menuService.insert(entity);
        return R.ok();
    }


    @Login
    @PutMapping("update")
    @ApiOperation(value = "更新菜单")
    public R update(@RequestBody MenuForm form){

        ValidatorUtils.validateEntity(form);

        MenuEntity entity = new MenuEntity();
        BeanUtils.copyProperties(form, entity);
        entity.setUpdateTime(new Date());

        menuService.insertOrUpdate(entity);

        return R.ok();
    }

    @Login
    @DeleteMapping("delete")
    @ApiOperation(value = "删除菜单")
    public R delete(@RequestParam Long id){

        menuService.deleteById(id);

        return R.ok();
    }

}
