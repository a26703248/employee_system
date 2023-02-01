package side.project.employee_system.service.impl;

import side.project.employee_system.entity.Business;
import side.project.employee_system.mapper.BusinessMapper;
import side.project.employee_system.service.IBusinessService;
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
public class BusinessServiceImpl extends ServiceImpl<BusinessMapper, Business> implements IBusinessService {

}
