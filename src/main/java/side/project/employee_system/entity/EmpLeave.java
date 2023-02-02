package side.project.employee_system.entity;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

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
public class EmpLeave extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "請假類型為選擇")
    private String type;

    @NotNull(message = "員工未選擇")
    private Integer empId;

    @TableField(exist = false)
    private String empName;

    @TableField(exist = false)
    private String jobName;

    private String reason;

    private Integer amount;

    @NotNull(message = "開始日期不能為空")
    private LocalDateTime startDate;

    @NotNull(message = "結束日期不能為空")
    private LocalDateTime endDate;

}
