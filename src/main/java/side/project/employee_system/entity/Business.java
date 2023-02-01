package side.project.employee_system.entity;

import java.time.LocalDateTime;

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
public class Business extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String businessSeq;

    private String type;

    private String companyName;

    private String address;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer amount;

    private Integer empId;
}
