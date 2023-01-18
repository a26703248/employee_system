package side.project.employee_system.security;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
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

    return new AccountUser(user.getId(), user.getUsername(), user.getPassword(), false, false, false, false, getUserAuthority(1L));
  }

  /**
   * 獲得帳號權限(角色、選單權限)
   * @param userId
   * @return
   */
  public Set<GrantedAuthority> getUserAuthority(Long userId) {
    return null;
  }

}
