package side.project.employee_system.service.impl;

import side.project.employee_system.entity.Department;
import side.project.employee_system.mapper.DepartmentMapper;
import side.project.employee_system.service.IDepartmentService;
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
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

}
