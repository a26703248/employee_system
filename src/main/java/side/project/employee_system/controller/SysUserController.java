package side.project.employee_system.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.hutool.core.util.StrUtil;
import side.project.employee_system.common.dto.PassDto;
import side.project.employee_system.common.lang.Const;
import side.project.employee_system.entity.SysRole;
import side.project.employee_system.entity.SysUser;
import side.project.employee_system.entity.SysUserRole;
import side.project.employee_system.utils.ResponseHandle;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author howard
 * @since 2023-01-16
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends BaseController {

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @GetMapping("/info/{id}")
  @PreAuthorize("hasAuthority('sys:user:list')")
  public ResponseHandle info(@PathVariable("id") Long id) {
    SysUser user = iSysUserService.getById(id);
    Assert.notNull(user, "查無此帳號");
    List<SysRole> role = iSysRoleService.listRolesById(id);
    user.setRoles(role);
    return ResponseHandle.success(user);
  }

  @GetMapping("/list")
  @PreAuthorize("hasAuthority('sys:user:list')")
  public ResponseHandle list(String username) {
    Page<SysUser> sysUserPage = iSysUserService.page(getPage(),
        new QueryWrapper<SysUser>().like(StrUtil.isNotBlank(username), "username", username));
    sysUserPage.getRecords().forEach(p -> {
      p.setRoles(iSysRoleService.listRolesById(p.getId()));
    });
    return ResponseHandle.success(sysUserPage);
  }

  @PostMapping("/save")
  @PreAuthorize("hasAuthority('sys:user:save')")
  public ResponseHandle save(@Validated @RequestBody SysUser sysUser) {
    SysUser existUser = iSysUserService.getByUsername(sysUser.getUsername());
    if(existUser != null) return ResponseHandle.error("帳號已存在");
    sysUser.setCreated(LocalDateTime.now());
    sysUser.setStatus(Const.STATUS_ON);
    String encodePassword = bCryptPasswordEncoder.encode(Const.DEFAULT_PASSWORD);
    sysUser.setPassword(encodePassword);
    iSysUserService.save(sysUser);
    return ResponseHandle.success(sysUser);
  }

  @Transactional
  @PostMapping("/update")
  @PreAuthorize("hasAuthority('sys:user:update')")
  public ResponseHandle update(@Validated @RequestBody SysUser sysUser) {
    sysUser.setUpdated(LocalDateTime.now());
    iSysUserService.updateById(sysUser);
    return ResponseHandle.success(sysUser);
  }

  @PostMapping("/delete")
  @PreAuthorize("hasAuthority('sys:user:delete')")
  public ResponseHandle delete(@RequestBody Long[] ids) {
    iSysUserService.removeByIds(Arrays.asList(ids));
    iSysUserRoleService.remove(new QueryWrapper<SysUserRole>().in("user_id", ids));
    return ResponseHandle.success(null);
  }

  @Transactional
  @PostMapping("/role/{id}")
  @PreAuthorize("hasAuthority('sys:user:role')")
  public ResponseHandle role(@PathVariable Long id, @RequestBody Long[] roles) {
    List<SysUserRole> roleList = new ArrayList<>();
    Arrays.stream(roles).forEach(r -> {
      SysUserRole sysUserRole = new SysUserRole();
      sysUserRole.setRoleId(r);
      sysUserRole.setUserId(id);
      roleList.add(sysUserRole);
    });
    iSysUserRoleService.remove(new QueryWrapper<SysUserRole>().eq("user_id", id));
    iSysUserRoleService.saveBatch(roleList);
    return ResponseHandle.success(null);
  }

  @PostMapping("/repass")
  @PreAuthorize("hasAuthority('sys:user:repass')")
  public ResponseHandle resetPassword(@RequestBody Long id) {
    // 重設為預設密碼
    SysUser sysUser = iSysUserService.getById(id);
    sysUser.setPassword(bCryptPasswordEncoder.encode(Const.DEFAULT_PASSWORD));
    sysUser.setUpdated(LocalDateTime.now());
    iSysUserService.updateById(sysUser);
    return ResponseHandle.success(sysUser);
  }

  @PostMapping("/updatePass")
  public ResponseHandle updatePass(@Validated @RequestBody PassDto passDto, Principal principal) {

    SysUser sysUser = iSysUserService.getByUsername(principal.getName());
    boolean matches = bCryptPasswordEncoder.matches(passDto.getOldPassword(), sysUser.getPassword());
    if(!matches) return ResponseHandle.error("舊密碼不正確");

    sysUser.setPassword(bCryptPasswordEncoder.encode(passDto.getNewPassword()));
    sysUser.setUpdated(LocalDateTime.now());
    iSysUserService.updateById(sysUser);
    return ResponseHandle.success(sysUser);
  }

}
