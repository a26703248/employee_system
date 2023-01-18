package side.project.employee_system.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import cn.hutool.json.JSONUtil;
import side.project.employee_system.utils.ResponseHandle;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        ServletOutputStream sos = response.getOutputStream();
        ResponseHandle rh = ResponseHandle.error(accessDeniedException.getMessage());

        sos.write(JSONUtil.toJsonStr(rh).getBytes("UTF-8"));
        sos.flush();
        sos.close();
  }

}
