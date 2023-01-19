package side.project.employee_system.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import side.project.employee_system.entity.SysUser;
import side.project.employee_system.service.ISysUserService;
import side.project.employee_system.utils.ResponseHandle;

@RestController
public class TestController {

  @Autowired
  private ISysUserService iSysUserService;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;


  @PreAuthorize("hasRole('admin')")
  @GetMapping("/test")
  public ResponseHandle test() {
    return ResponseHandle.success(iSysUserService.list());
  }

  @PreAuthorize("hasAuthority('sys:user:list')")
  @GetMapping("/test/pass")
  public ResponseHandle password() {
    String encode = bCryptPasswordEncoder.encode("root");
    boolean matches = bCryptPasswordEncoder.matches("root", encode);
    return ResponseHandle.success(encode);
  }

  // create demo 資料
  @GetMapping("/create/user")
  public void createDemoUser() {
    List<SysUser> users = new ArrayList<>();
    if(iSysUserService.getByUsername("admin") == null){
      SysUser admin = new SysUser();
      admin.setUsername("admin");
      admin.setPassword(bCryptPasswordEncoder.encode("root"));
      admin.setEmail("XXX@gmail.com");
      admin.setCreated(LocalDateTime.now());
      users.add(admin);
    }

    if(iSysUserService.getByUsername("test") == null){
      SysUser test = new SysUser();
      test.setUsername("test");
      test.setPassword(bCryptPasswordEncoder.encode("1234"));
      test.setEmail("OOO@gmail.com");
      test.setCreated(LocalDateTime.now());
      users.add(test);
    }

    if(iSysUserService.getByUsername("test2") == null){
      SysUser test2 = new SysUser();
      test2.setUsername("test2");
      test2.setPassword(bCryptPasswordEncoder.encode("1234"));
      test2.setEmail("OOO@gmail.com");
      test2.setCreated(LocalDateTime.now());
      users.add(test2);
    }

    if(users.size() != 0){
      boolean res = iSysUserService.saveBatch(users);
      System.out.println(res);
    }else{
      System.out.println("is exist");
    }

  }

}
