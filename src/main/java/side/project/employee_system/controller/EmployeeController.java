package side.project.employee_system.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.hutool.core.util.StrUtil;
import side.project.employee_system.entity.Employee;
import side.project.employee_system.entity.SysUser;
import side.project.employee_system.utils.ResponseHandle;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author howard
 * @since 2023-02-01
 */
@RestController
@RequestMapping("/emp/manage")
public class EmployeeController extends BaseController {

  @GetMapping("/list")
  public ResponseHandle list(String empName, Principal principal) {
    Page<Employee> page = iEmployeeService.page(getPage(),
        new QueryWrapper<Employee>().eq(StrUtil.isNotBlank(empName), "emp_name", empName));

    List<Long> userIds = page.getRecords()
        .stream()
        .map(e -> e.getUserId())
        .filter(e -> e != null)
        .collect(Collectors.toList());
    List<SysUser> userList = iSysUserService.list(new QueryWrapper<SysUser>().in("id", userIds));
    Map<Long, SysUser> userMap = userList
        .stream()
        .collect(Collectors.toMap(SysUser::getId, Function.identity()));

    page.getRecords().forEach(e -> e.setAccount(userMap.get(e.getUserId())));
    return ResponseHandle.success(page);
  }

  @GetMapping("/info/{id}")
  public ResponseHandle info(@PathVariable("id") Long id) {
    return ResponseHandle.success(null);
  }

  @PostMapping("/update")
  public ResponseHandle update(Employee emp) {
    return ResponseHandle.success(null);
  }

  @PostMapping("/delete")
  public ResponseHandle delete(Long id) {
    return ResponseHandle.success(null);
  }

}
