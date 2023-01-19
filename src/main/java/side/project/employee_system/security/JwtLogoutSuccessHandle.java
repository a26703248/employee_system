package side.project.employee_system.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import cn.hutool.json.JSONUtil;
import side.project.employee_system.utils.JwtUtils;
import side.project.employee_system.utils.ResponseHandle;

@Component
public class JwtLogoutSuccessHandle implements LogoutSuccessHandler {

  @Autowired
  private JwtUtils jwtUtils;

  @Override
  public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException {
        if(authentication != null){
          new SecurityContextLogoutHandler().logout(request, response, authentication);;
        }
        response.setContentType("application/json;charset=UTF-8");

        ServletOutputStream sos = response.getOutputStream();
        // JWT 產生並寫入header中
        response.setHeader(jwtUtils.getHeader(), "");
        ResponseHandle rh = ResponseHandle.success(null);

        sos.write(JSONUtil.toJsonStr(rh).getBytes("UTF-8"));
        sos.flush();
        sos.close();
  }

}
