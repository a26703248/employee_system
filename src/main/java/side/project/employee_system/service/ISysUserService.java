package side.project.employee_system.service;

import com.baomidou.mybatisplus.extension.service.IService;

import side.project.employee_system.entity.SysUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author howard
 * @since 2023-01-16
 */
public interface ISysUserService extends IService<SysUser> {

  public SysUser getByUsername(String username);

  public String getUserAuthorityInfo(Long userId);

}
