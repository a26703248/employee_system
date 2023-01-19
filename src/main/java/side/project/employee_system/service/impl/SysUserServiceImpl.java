package side.project.employee_system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import side.project.employee_system.entity.SysMenu;
import side.project.employee_system.entity.SysRole;
import side.project.employee_system.entity.SysUser;
import side.project.employee_system.mapper.SysUserMapper;
import side.project.employee_system.service.ISysMenuService;
import side.project.employee_system.service.ISysRoleService;
import side.project.employee_system.service.ISysUserService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author howard
 * @since 2023-01-16
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

  @Autowired
  private ISysRoleService isysRoleService;

  @Autowired
  private SysUserMapper sysUserMapper;

  @Autowired
  private ISysMenuService iSysMenuService;

  public SysUser getByUsername(String username) {
    return getOne(new QueryWrapper<SysUser>().eq("username", username));
  }

  @Override
  public String getUserAuthorityInfo(Long userId) {
    // ROLE_admin,ROLE_normal
    String authority = "";
    String sql = "select role_id from sys_user_role where user_id = " + userId;
    List<SysRole> list = isysRoleService.list();
    List<SysRole> roles = isysRoleService.list(new QueryWrapper<SysRole>().inSql("id", sql));
    if (roles.size() > 0) {
      // 將權限串接並加上 ROLE_
      String roleCode = roles.stream()
          .map(r -> "ROLE_" + r.getCode())
          .collect(Collectors.joining(","));
      authority = roleCode.concat(",");
    }
    List<Long> menuId = sysUserMapper.getNavMenuId(userId);
    if(menuId.size() > 0){
      List<SysMenu> menus = iSysMenuService.listByIds(menuId);
      String menuPerms = menus.stream().map(m -> m.getPerms()).collect(Collectors.joining(","));
      authority = authority.concat(menuPerms);
    }

    return authority;
  }

}
