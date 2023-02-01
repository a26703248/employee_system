package side.project.employee_system.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

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
public class Employee implements Serializable {

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  private static final long serialVersionUID = 1L;

  private String empSequence;

  private String empName;

  private String jobName;

  private Long userId;

  @TableField(exist = false)
  private Department dept;

  @TableField(exist = false)
  private SysUser account;

}
