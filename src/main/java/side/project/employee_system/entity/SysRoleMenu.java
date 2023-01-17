package side.project.employee_system.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author howard
 * @since 2023-01-16
 */
@Getter
@Setter
@TableName("sys_role_menu")
public class SysRoleMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long roleId;

    private Long menuId;
}
