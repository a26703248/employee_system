package side.project.employee_system.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.hutool.core.util.StrUtil;
import side.project.employee_system.entity.Employee;
import side.project.employee_system.mapper.EmployeeMapper;
import side.project.employee_system.service.IEmployeeService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author howard
 * @since 2023-02-01
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {
  public List<Employee> getByEmpName(String empName) {
    List<Employee> employees = list(new QueryWrapper<Employee>().eq(StrUtil.isNotBlank(empName), "emp_name", empName));
    return employees;
  };
}
