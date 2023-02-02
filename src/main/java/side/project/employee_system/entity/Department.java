package side.project.employee_system.entity;

import javax.validation.constraints.NotBlank;

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
public class Department extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String deptSequence;

    @NotBlank(message = "部門名稱不可為空")
    private String deptName;

    private String description;

    private String tel;
}
