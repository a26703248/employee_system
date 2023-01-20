package side.project.employee_system.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.hutool.core.map.MapUtil;
import side.project.employee_system.entity.SysUser;
import side.project.employee_system.utils.ResponseHandle;

@RestController
public class AuthController extends BaseController {

  /**
   * 取得帳號資訊
   * @param principal
   * @return
   */
  @GetMapping("/user/info")
  public ResponseHandle userInfo(Principal principal) {
    SysUser user = iSysUserService.getByUsername(principal.getName());
    return ResponseHandle.success(MapUtil.builder()
        .put("id", user.getId())
        .put("username", user.getUsername())
        .put("avatar", user.getAvatar())
        .put("created", user.getCreated())
        .map());
  }

}
