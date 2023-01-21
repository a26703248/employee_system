package side.project.employee_system.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

import side.project.employee_system.entity.SysRole;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author howard
 * @since 2023-01-16
 */
public interface ISysRoleService extends IService<SysRole> {

  public List<SysRole> listRolesById(Long id);

}
