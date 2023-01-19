package side.project.employee_system.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.hutool.core.map.MapUtil;
import side.project.employee_system.common.dto.SysMenuDto;
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

}
