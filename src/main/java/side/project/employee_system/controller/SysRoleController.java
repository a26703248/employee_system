package side.project.employee_system.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
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
import side.project.employee_system.common.lang.Const;
import side.project.employee_system.entity.SysRole;
import side.project.employee_system.entity.SysRoleMenu;
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
@RequestMapping("/sys/role")
public class SysRoleController extends BaseController {

  @GetMapping("/info/{id}")
  @PreAuthorize("hasAuthority('sys:role:list')")
  public ResponseHandle info(@PathVariable("id") Long id) {
    SysRole sysRole = iSysRoleService.getById(id);
    List<SysRoleMenu> menus = iSysRoleMenuService.list(new QueryWrapper<SysRoleMenu>().eq("role_id", id));
    List<Long> menuIds = menus.stream().map(p -> p.getMenuId()).collect(Collectors.toList());
    sysRole.setMenuIds(menuIds);
    return ResponseHandle.success(sysRole);
  }

  @GetMapping("/list")
  @PreAuthorize("hasAuthority('sys:role:list')")
  public ResponseHandle list(String name) {
    Page<SysRole> rolePage = iSysRoleService.page(getPage(),
        new QueryWrapper<SysRole>().like(StrUtil.isNotBlank(name), "name", name));
    return ResponseHandle.success(rolePage);
  }

  @PostMapping("/save")
  @PreAuthorize("hasAuthority('sys:role:save')")
  public ResponseHandle save(@Validated @RequestBody SysRole sysRole) {
    sysRole.setCreated(LocalDateTime.now());
    sysRole.setStatus(Const.STATUS_ON);
    iSysRoleService.save(sysRole);
    return ResponseHandle.success(sysRole);
  }


  @PostMapping("/update")
  @PreAuthorize("hasAuthority('sys:role:update')")
  public ResponseHandle update(@Validated @RequestBody SysRole sysRole) {
    sysRole.setUpdated(LocalDateTime.now());
    iSysRoleService.updateById(sysRole);
    return ResponseHandle.success(sysRole);
  }

  @Transactional
  @PostMapping("/delete")
  @PreAuthorize("hasAuthority('sys:role:delete')")
  public ResponseHandle delete(@RequestBody Long[] ids) {
    iSysRoleService.removeByIds(Arrays.asList(ids));
    iSysUserRoleService.remove(new QueryWrapper<SysUserRole>().in("role_id", ids));
    iSysRoleMenuService.remove(new QueryWrapper<SysRoleMenu>().in("role_id", ids));
    return ResponseHandle.success("success");
  }

  @GetMapping("/perm/{roleId}")
  @Transactional
  @PreAuthorize("hasAuthority('sys:role:perm')")
  public ResponseHandle perm(@PathVariable("roleId") Long roleId, @RequestBody Long[] menuIds) {
    List<SysRoleMenu> roleMenuList = new ArrayList<>();
    Arrays.stream(menuIds).forEach( menuId -> {
      SysRoleMenu sysRoleMenu = new SysRoleMenu();
      sysRoleMenu.setMenuId(menuId);
      sysRoleMenu.setRoleId(roleId);
      roleMenuList.add(sysRoleMenu);
    });
    // 清除所有相關關聯
    iSysRoleMenuService.remove(new QueryWrapper<SysRoleMenu>().eq("role_id", roleId));
    // 重新建立關聯
    iSysRoleMenuService.saveBatch(roleMenuList);
    return ResponseHandle.success(roleId);
  }

}
