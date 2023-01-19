package side.project.employee_system.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import side.project.employee_system.entity.SysUser;
import side.project.employee_system.service.ISysUserService;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

  @Autowired
  private ISysUserService iSysUserService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    SysUser user = iSysUserService.getByUsername(username);
    if(user == null){
      throw new UsernameNotFoundException("帳號不存在");
    }
    String flag = "";

    return new AccountUser(user.getId(), user.getUsername(), user.getPassword(), true, true, true, true, getUserAuthority(8L));
  }

  /**
   * 獲得帳號權限(角色、選單權限)
   * @param userId
   * @return
   */
  public List<GrantedAuthority> getUserAuthority(Long userId) {
    // 取得角色
    String authority = iSysUserService.getUserAuthorityInfo(userId);
    // 取得是否有操作權限
    return AuthorityUtils.commaSeparatedStringToAuthorityList(authority);
  }

}
