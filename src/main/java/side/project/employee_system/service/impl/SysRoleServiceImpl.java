package side.project.employee_system.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import side.project.employee_system.entity.SysRole;
import side.project.employee_system.mapper.SysRoleMapper;
import side.project.employee_system.service.ISysRoleService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author howard
 * @since 2023-01-16
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

  @Override
  public List<SysRole> listRolesById(Long id) {
    List<SysRole> roles = list(
        new QueryWrapper<SysRole>().inSql("id", "select role_id from sys_user_role where user_id = " + id));
    return roles;
  }

}
