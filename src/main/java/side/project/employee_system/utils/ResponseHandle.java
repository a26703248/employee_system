package side.project.employee_system.utils;

import java.io.Serializable;

import lombok.Data;

@Data
public class ResponseHandle implements Serializable {
  private Integer code;
  private String msg;
  private Object data;

  public static ResponseHandle success(Object data) {
    return success(200, "success", data);
  }

  public static ResponseHandle success(Integer code, String msg, Object data) {
    ResponseHandle res = new ResponseHandle();
    res.setCode(code);
    res.setMsg(msg);
    res.setData(data);
    return res;
  }

  public static ResponseHandle error(Integer code, String msg, Object data) {
    ResponseHandle res = new ResponseHandle();
    res.setCode(code);
    res.setMsg(msg);
    res.setData(data);
    return res;
  }

  public static ResponseHandle error(String msg) {
    return error(400, msg, null);
  }

}
