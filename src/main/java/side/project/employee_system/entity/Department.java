package side.project.employee_system.entity;

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

    private String deptName;

    private String description;

    private Integer empId;
}
