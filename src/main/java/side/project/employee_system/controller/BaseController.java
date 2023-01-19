package side.project.employee_system.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import side.project.employee_system.service.ISysMenuService;
import side.project.employee_system.service.ISysRoleMenuService;
import side.project.employee_system.service.ISysRoleService;
import side.project.employee_system.service.ISysUserRoleService;
import side.project.employee_system.service.ISysUserService;

public class BaseController {

  @Autowired
  private HttpServletRequest req;

  @Autowired
  protected  ISysUserService iSysUserService;

  @Autowired
  protected  ISysUserRoleService iSysUserRoleService;

  @Autowired
  protected  ISysRoleService iSysRoleService;

  @Autowired
  protected  ISysMenuService iSysMenuService;

  @Autowired
  protected  ISysRoleMenuService iSysRoleMenuService;
}
