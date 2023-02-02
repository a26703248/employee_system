package side.project.employee_system.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
import side.project.employee_system.entity.EmpLeave;
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
@RequestMapping("/emp/leave")
public class EmpLeaveController extends BaseController {

  @GetMapping("/list")
  @PreAuthorize("hasAuthority('emp:leave:list')")
  public ResponseHandle list(String empName, String startDate, String endDate) {
    Page<EmpLeave> page = null;
    if (StrUtil.isNotBlank(empName)) {
      List<Employee> emps = iEmployeeService.getByEmpName(empName);
      List<Long> empIds = emps.stream().map(e -> e.getId()).collect(Collectors.toList());
      page = iEmpLeaveService.page(getPage(),
          new QueryWrapper<EmpLeave>().in("id", empIds)
              .ge(StrUtil.isNotBlank(startDate), "start_date",
                  startDate)
              .le(StrUtil.isNotBlank(endDate), "start_date", endDate));

    } else {
      page = iEmpLeaveService.page(getPage(),
          new QueryWrapper<EmpLeave>()
              .ge(StrUtil.isNotBlank(startDate), "start_date",
                  startDate)
              .le(StrUtil.isNotBlank(endDate), "start_date", endDate));
    }
    page.getRecords()
        .stream()
        .forEach(el -> {
          Employee emp = iEmployeeService.getById(el.getEmpId());
          el.setEmpName(emp.getEmpName());
          el.setJobName(emp.getJobName());
        });

    return ResponseHandle.success(page);
  }

  @GetMapping("/info/{id}")
  @PreAuthorize("hasAuthority('emp:leave:list')")
  public ResponseHandle info(@PathVariable("id") Long id) {
    EmpLeave empLeave = iEmpLeaveService.getById(id);
    Employee emp = iEmployeeService.getById(empLeave.getEmpId());
    empLeave.setEmpName(emp.getEmpName());
    empLeave.setJobName(emp.getJobName());
    return ResponseHandle.success(empLeave);
  }

  @PostMapping("/save")
  @PreAuthorize("hasAuthority('emp:leave:save')")
  public ResponseHandle save(@Validated @RequestBody EmpLeave leave) {
    leave.setCreated(LocalDateTime.now());
    iEmpLeaveService.save(leave);
    return ResponseHandle.success(leave);
  }

  @Transactional
  @PostMapping("/review")
  @PreAuthorize("hasAuthority('emp:leave:review')")
  public ResponseHandle review(@Validated @RequestBody EmpLeave leave) {
    EmpLeave empLeave = iEmpLeaveService.getById(leave.getId());
    empLeave.setStatus(leave.getStatus());
    empLeave.setUpdated(LocalDateTime.now());
    boolean res = iEmpLeaveService.updateById(empLeave);
    return ResponseHandle.success(res);
  }

}
