package side.project.employee_system.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import cn.hutool.json.JSONUtil;
import side.project.employee_system.utils.JwtUtils;
import side.project.employee_system.utils.ResponseHandle;

@Component
public class LoginSuccessHandle implements AuthenticationSuccessHandler {

  @Autowired
  private JwtUtils jwtUtils;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");

        ServletOutputStream sos = response.getOutputStream();
        // JWT 產生並寫入header中
        String jwt = jwtUtils.generatorJwtToken(authentication.getName());
        response.setHeader(jwtUtils.getHeader(), jwt);
        Map<String, Object> data = new HashMap<>();
        data.put("token", jwt);
        ResponseHandle rh = ResponseHandle.success(data);

        sos.write(JSONUtil.toJsonStr(rh).getBytes("UTF-8"));
        sos.flush();
        sos.close();
  }

}
