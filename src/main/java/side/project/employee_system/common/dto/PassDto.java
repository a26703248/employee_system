package side.project.employee_system.common.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PassDto implements Serializable {

  @NotBlank(message="請輸入新密碼")
  private String oldPassword;

  @NotBlank(message="舊密碼未填")
  private String newPassword;
}
