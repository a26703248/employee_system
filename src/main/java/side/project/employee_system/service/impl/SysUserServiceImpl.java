package side.project.employee_system.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import side.project.employee_system.entity.SysUser;
import side.project.employee_system.mapper.SysUserMapper;
import side.project.employee_system.service.ISysUserService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author howard
 * @since 2023-01-16
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {


  public SysUser getByUsername(String username) {
    return getOne(new QueryWrapper<SysUser>().eq("username", username));
  }

}
