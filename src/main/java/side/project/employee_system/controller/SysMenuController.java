package side.project.employee_system.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.hutool.core.map.MapUtil;
import side.project.employee_system.common.dto.SysMenuDto;
import side.project.employee_system.entity.SysMenu;
import side.project.employee_system.entity.SysRoleMenu;
import side.project.employee_system.entity.SysUser;
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
@RequestMapping("/sys/menu")
public class SysMenuController extends BaseController {

  /**
   * 獲得當前使用者選單與權限
   * @param principal
   * @return
   */
  @GetMapping("/nav")
  public ResponseHandle nav(Principal principal) {
    SysUser user = iSysUserService.getByUsername(principal.getName());

    // 取得權限資訊
    String userAuthority = iSysUserService.getUserAuthorityInfo(user.getId());
    String[] userAuthorityArray = StringUtils.tokenizeToStringArray(userAuthority, ",");

    // 取得選單
    List<SysMenuDto> nav = iSysMenuService.getCurrentNav();

    return ResponseHandle.success(MapUtil.builder()
        .put("authorities", userAuthority)
        .put("nav", nav).map());
  }

  /**
   * 取得單筆選單資料
   */
  @GetMapping("/info/{id}")
  @PreAuthorize("hasAuthority('sys:menu:list')")
  public ResponseHandle info(@PathVariable("id")Long id) {
    return ResponseHandle.success(iSysMenuService.getById(id));
  }

  @GetMapping("/list")
  @PreAuthorize("hasAuthority('sys:menu:list')")
  public ResponseHandle list() {
    List<SysMenu> list = iSysMenuService.tree();
    return ResponseHandle.success(list);
  }

  @PostMapping("/save")
  @PreAuthorize("hasAuthority('sys:menu:save')")
  public ResponseHandle save(@Validated @RequestBody SysMenu sysMenu) {
    sysMenu.setCreated(LocalDateTime.now());
    iSysMenuService.save(sysMenu);
    return ResponseHandle.success(sysMenu);
  }

  @PostMapping("/update")
  @PreAuthorize("hasAuthority('sys:menu:update')")
  public ResponseHandle update(@Validated @RequestBody SysMenu sysMenu) {
    sysMenu.setUpdated(LocalDateTime.now());
    iSysMenuService.updateById(sysMenu);
    return ResponseHandle.success(sysMenu);
  }

  @PostMapping("/delete/{id}")
  @PreAuthorize("hasAuthority('sys:menu:delete')")
  public ResponseHandle delete(@PathVariable("id")Long id) {
    long count = iSysMenuService.count(new QueryWrapper<SysMenu>().eq("parent_id", id));
    if(count != 0)return ResponseHandle.error("請先清除子選單");
    iSysMenuService.removeById(id);

    // 刪除關聯表資訊
    iSysRoleMenuService.remove(new QueryWrapper<SysRoleMenu>().eq("menu_id", id));
    return ResponseHandle.success("success");
  }

}
