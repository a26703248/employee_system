package side.project.employee_system.service.impl;

import side.project.employee_system.entity.Employee;
import side.project.employee_system.mapper.EmployeeMapper;
import side.project.employee_system.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author howard
 * @since 2023-02-01
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}
