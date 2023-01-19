package side.project.employee_system.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import cn.hutool.json.JSONUtil;
import side.project.employee_system.utils.ResponseHandle;


@Component
public class LoginFailureHandle implements AuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");

        ServletOutputStream sos = response.getOutputStream();
        ResponseHandle rh = ResponseHandle.error("帳號或密碼錯誤");

        sos.write(JSONUtil.toJsonStr(rh).getBytes("UTF-8"));
        sos.flush();
        sos.close();
  }

}
