package side.project.employee_system.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import side.project.employee_system.entity.EmpLeave;
import side.project.employee_system.mapper.EmpLeaveMapper;
import side.project.employee_system.service.IEmpLeaveService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author howard
 * @since 2023-02-01
 */
@Service
public class EmpLeaveServiceImpl extends ServiceImpl<EmpLeaveMapper, EmpLeave> implements IEmpLeaveService {

}
