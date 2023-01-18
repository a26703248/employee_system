package side.project.employee_system.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import cn.hutool.json.JSONUtil;
import side.project.employee_system.utils.ResponseHandle;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
      throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ServletOutputStream sos = response.getOutputStream();
        ResponseHandle rh = ResponseHandle.error("請登入後再繼續操作");

        sos.write(JSONUtil.toJsonStr(rh).getBytes("UTF-8"));
        sos.flush();
        sos.close();

  }

}
