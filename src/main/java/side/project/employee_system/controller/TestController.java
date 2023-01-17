package side.project.employee_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import side.project.employee_system.service.ISysUserService;
import side.project.employee_system.utils.ResponseHandle;

@RestController
public class TestController {

  @Autowired
  private ISysUserService iSysUserService;

  @GetMapping("/test")
  public ResponseHandle test() {
    return ResponseHandle.success(iSysUserService.list());
  }

}
