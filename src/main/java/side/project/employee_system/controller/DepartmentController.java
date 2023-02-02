package side.project.employee_system.controller;

import java.time.Instant;
import java.time.LocalDateTime;

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
@RequestMapping("/dept/manage")
public class DepartmentController extends BaseController {

  @GetMapping("/list")
  public ResponseHandle list(String deptName) {
    Page<Department> page = iDepartmentService.page(
        getPage(),
        new QueryWrapper<Department>()
            .like(StrUtil.isNotBlank(deptName), "dept_name", deptName)
            .eq("status", 1));

    return ResponseHandle.success(page);
  }

  @GetMapping("/info/{id}")
  public ResponseHandle info(@PathVariable Long id) {
    Department dept = iDepartmentService.getById(id);
    return ResponseHandle.success(dept);
  }

  @PostMapping("/save")
  public ResponseHandle save(@Validated @RequestBody Department dept) {
    dept.setDeptSequence(String.valueOf(Instant.now().getEpochSecond()));
    dept.setCreated(LocalDateTime.now());
    boolean res = iDepartmentService.save(dept);
    return ResponseHandle.success(res);
  }

  @PostMapping("/update")
  public ResponseHandle update(@Validated @RequestBody Department dept) {
    dept.setUpdated(LocalDateTime.now());
    boolean res = iDepartmentService.updateById(dept);
    return ResponseHandle.success(res);
  }

}
