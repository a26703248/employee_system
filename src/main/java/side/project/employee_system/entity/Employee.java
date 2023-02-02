package side.project.employee_system.entity;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author howard
 * @since 2023-02-01
 */
@Getter
@Setter
public class Employee extends BaseEntity {

  private static final long serialVersionUID = 1L;

  private String empSequence;

  @NotBlank(message="員工姓名不可為空")
  private String empName;

  @NotBlank(message="職務姓名不可為空")
  private String jobName;

  @Email(message = "Email 格式錯誤")
  private String email;

  @NotBlank(message = "請輸入行動電話")
  private String mobile;

  @TableField(exist = false)
  private List<Department> dept;

}
