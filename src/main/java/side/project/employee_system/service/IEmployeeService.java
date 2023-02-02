package side.project.employee_system.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

import side.project.employee_system.entity.Employee;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author howard
 * @since 2023-02-01
 */
public interface IEmployeeService extends IService<Employee> {

  public List<Employee> getByEmpName(String empName);

}
