package side.project.employee_system.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import side.project.employee_system.utils.ResponseHandle;


@RestController
public class AuthController extends BaseController {

  @GetMapping("/user/info")
  public ResponseHandle userInfo(Principal principal) {
    return ResponseHandle.success(null);
  }

}
