package side.project.employee_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import side.project.employee_system.service.ISysUserService;
import side.project.employee_system.utils.ResponseHandle;

@RestController
public class TestController {

  @Autowired
  private ISysUserService iSysUserService;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @GetMapping("/test")
  public ResponseHandle test() {
    return ResponseHandle.success(iSysUserService.list());
  }

  @GetMapping("/test/pass")
  public ResponseHandle password() {
    String encode = bCryptPasswordEncoder.encode("root");
    boolean matches = bCryptPasswordEncoder.matches("root", encode);
    System.out.println("matches = " + matches);
    return ResponseHandle.success(encode);
  }

  // create demo 資料

}
