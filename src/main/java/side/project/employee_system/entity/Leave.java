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
public class Leave extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String type;

    private Integer empId;

    private String reason;

    private Integer amount;
}
