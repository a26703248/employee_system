package side.project.employee_system.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.web.filter.OncePerRequestFilter;

import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import side.project.employee_system.utils.JwtUtils;

public class JwtAuthenticationFilter  extends OncePerRequestFilter {

  @Autowired
  private JwtUtils jwtUtils;

  @Autowired
  private UserDetailServiceImpl userDetailServiceImpl;

  // @Autowired
  // private ISysUserService isysUserService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException {
        String jwt = request.getHeader(jwtUtils.getHeader());
        if(StrUtil.isBlankOrUndefined(jwt)){
          chain.doFilter(request, response);
          return;
        }

        Claims claimsByToken = jwtUtils.getClaimsByToken(jwt);

        if(claimsByToken == null){
          throw new JwtException("token 錯誤");
        }
        if(jwtUtils.isTokenExpired(claimsByToken)){
          throw new JwtException("token 過期");
        }

        String username = claimsByToken.getSubject();
        // SysUser user = isysUserService.getByUsername(username);

        // 取得權限訊息
        // UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null, userDetailServiceImpl.getUserAuthority(user.getId()));

        // 上下文認證配置
        // SecurityContextHolder.getContext().setAuthentication(token);
        chain.doFilter(request, response);
  }

}
