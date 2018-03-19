package io.shelves.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.shelves.annotation.Login;
import io.shelves.common.utils.PageUtils;
import io.shelves.common.utils.R;
import io.shelves.common.validator.Assert;
import io.shelves.common.validator.ValidatorUtils;
import io.shelves.entity.UserEntity;
import io.shelves.entity.UserRoleEntity;
import io.shelves.form.RegisterForm;
import io.shelves.interceptor.AuthorizationInterceptor;
import io.shelves.service.UserRoleService;
import io.shelves.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * ${DESCRIPTION}
 *
 * @author five.liu
 * @create 2018/3/9/009
 */
@RestController
@RequestMapping("/api/user")
@Api(tags="用户接口")
public class ApiUserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;


    @Login
    @GetMapping("list")
    @ApiOperation("用户列表")
    public R listUserEntity(@RequestParam(required = false) @ApiParam(value = "用户名") String username,
                            @RequestParam @ApiParam(value = "当前页") int pageNumber,
                            @RequestParam @ApiParam(value = "每页条数") int pageSize){

        EntityWrapper<UserEntity> wrapper = new EntityWrapper<>();
        Long userId = getUser().getUserId();
        wrapper.eq("parent_user_id"
                , userId);
        wrapper.like(StringUtils.isEmpty(username)
                ,"username", username);
        wrapper.orNew("user_id", userId);
        PageUtils page = userService
                .queryPage(new Page(pageNumber, pageSize)
                ,wrapper);

        return R.ok().put("page", page);

    }

    @Login
    @PostMapping("register")
    @ApiOperation("新增用户")
    public R add(@RequestBody RegisterForm form){
        //表单校验
        ValidatorUtils.validateEntity(form);
        //新增用户
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(form, user);
        user.setPassword(DigestUtils.sha256Hex(form.getPassword()));
        user.setCreateTime(new Date());
        userService.insert(user);
        //绑定角色
        UserRoleEntity userRole = new UserRoleEntity();
        userRole.setUserId(user.getUserId());
        userRole.setRoleId(form.getRoleId());
        userRoleService.insert(userRole);

        return R.ok();
    }

    @Login
    @PostMapping("update")
    @ApiOperation("修改用户")
    public R update(@RequestBody RegisterForm form){
        //表单校验
        ValidatorUtils.validateEntity(form);
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(form, user);
        user.setPassword(DigestUtils.sha256Hex(form.getPassword()));

        userService.insertOrUpdate(user);
        return R.ok();
    }


    @Login
    @PostMapping("/password")
    @ApiOperation("修改密码")
    public R password(@RequestParam @ApiParam("原密码") String password,
                      @RequestParam @ApiParam("新密码") String newPassword
                      ){

        Assert.isBlank(newPassword, "新密码不为能空");
        password = DigestUtils.sha256Hex(password);
        newPassword = DigestUtils.sha256Hex(newPassword);

        boolean flag = userService.updatePassword(getUser()
                , password
                , newPassword);
        if (!flag){
            return R.error("原密码不正确");
        }
        return R.ok();
    }


    @Login
    @DeleteMapping("delete")
    @ApiOperation("删除用户")
    public R delete(@RequestParam
                    @ApiParam(value = "用户ID") Integer userId){

        Integer key = (Integer) request
                .getAttribute(AuthorizationInterceptor.USER_KEY);
        if (key.equals(userId)){
            return R.error("不能删除当前登录用户");
        }
        userService.deleteById(userId);

        return R.ok();
    }
}
