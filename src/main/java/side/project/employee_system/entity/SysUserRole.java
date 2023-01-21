package side.project.employee_system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("sys_user_role")
public class SysUserRole {


    @TableId(value="id", type = IdType.AUTO)
    private Long id;

    private static final long serialVersionUID = 1L;

    private Long userId;

    private Long roleId;
}
