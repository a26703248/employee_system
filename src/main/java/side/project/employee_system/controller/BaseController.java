package side.project.employee_system.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import side.project.employee_system.entity.SysUser;
import side.project.employee_system.service.IBusinessService;
import side.project.employee_system.service.IDepartmentService;
import side.project.employee_system.service.IEmployeeService;
import side.project.employee_system.service.ILeaveService;
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

  @Autowired
  protected  IBusinessService iBusinessService;

  @Autowired
  protected IDepartmentService iDepartmentService;

  @Autowired
  protected  IEmployeeService iEmployeeService;

  @Autowired
  protected  ILeaveService iLeaveService;

  public Page getPage() {
    int current = ServletRequestUtils.getIntParameter(req, "currentPage", 1);
    int size = ServletRequestUtils.getIntParameter(req, "pageSize", 10);
    return new Page(current, size);
  }

  public SysUser getCurrentUserByUsername(String username) {
    return iSysUserService.getByUsername(username);
  }

}
