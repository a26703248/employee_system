package side.project.employee_system.controller;

import java.security.Principal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.hutool.core.util.StrUtil;
import side.project.employee_system.entity.Employee;
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
  @PreAuthorize("hasAuthority('emp:manage:list')")
  public ResponseHandle list(String empName, Principal principal) {
    Page<Employee> page = iEmployeeService.page(getPage(),
        new QueryWrapper<Employee>()
            .like(StrUtil.isNotBlank(empName), "emp_name", empName));

    return ResponseHandle.success(page);
  }

  @GetMapping("/info/{id}")
  @PreAuthorize("hasAuthority('emp:manage:list')")
  public ResponseHandle info(@PathVariable("id") Long id) {
    Employee emp = iEmployeeService.getById(id);
    return ResponseHandle.success(emp);
  }

  @PostMapping("/save")
  @PreAuthorize("hasAuthority('emp:manage:save')")
  public ResponseHandle save(@Validated @RequestBody Employee emp) {
    emp.setEmpSequence(String.valueOf(Instant.now().getEpochSecond()));
    emp.setCreated(LocalDateTime.now());
    boolean res = iEmployeeService.save(emp);
    return ResponseHandle.success(res);
  }

  @Transactional
  @PostMapping("/update")
  @PreAuthorize("hasAuthority('emp:manage:update')")
  public ResponseHandle update(@Validated @RequestBody Employee emp) {
    emp.setUpdated(LocalDateTime.now());
    boolean res = iEmployeeService.updateById(emp);
    return ResponseHandle.success(res);
  }

  @PostMapping("/delete")
  @PreAuthorize("hasAuthority('emp:manage:delete')")
  public ResponseHandle delete(@RequestBody Long[] ids) {
    iEmployeeService.removeByIds(Arrays.asList(ids));
    return ResponseHandle.success(ids);
  }

}
