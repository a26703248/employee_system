package side.project.employee_system.controller;

import java.security.Principal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

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
import side.project.employee_system.entity.Department;
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
  public ResponseHandle list(String empName, Principal principal) {
    Page<Employee> page = iEmployeeService.page(getPage(),
        new QueryWrapper<Employee>()
            .like(StrUtil.isNotBlank(empName), "emp_name", empName)
            .eq("status", 1));

    // TODO dept list
    return ResponseHandle.success(page);
  }

  @GetMapping("/info/{id}")
  public ResponseHandle info(@PathVariable("id") Long id) {
    Employee emp = iEmployeeService.getById(id);
    List<Department> dept = iDepartmentService.list(
        new QueryWrapper<Department>()
            .eq("emp_id", emp.getId()));
    return ResponseHandle.success(emp);
  }

  @PostMapping("/save")
  public ResponseHandle save(@Validated @RequestBody Employee emp) {
    emp.setEmpSequence(String.valueOf(Instant.now().getEpochSecond()));
    emp.setCreated(LocalDateTime.now());
    boolean res = iEmployeeService.save(emp);
    return ResponseHandle.success(res);
  }

  @PostMapping("/update")
  public ResponseHandle update(@Validated @RequestBody Employee emp) {
    emp.setUpdated(LocalDateTime.now());
    boolean res = iEmployeeService.updateById(emp);
    return ResponseHandle.success(res);
  }

  @PostMapping("/delete")
  public ResponseHandle delete(@RequestBody Long[] ids) {
    iEmployeeService.removeByIds(Arrays.asList(ids));
    return ResponseHandle.success(ids);
  }

}
