package side.project.employee_system.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author howard
 * @since 2023-01-16
 */
@Data
@TableName("sys_user")
public class SysUser extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @NotBlank(message = "請輸入帳號")
  private String username;

  private String password;

  private String avatar;

  private LocalDateTime lastLogin;

  @TableField(exist = false)
  private List<SysRole> roles = new ArrayList<>();

}
