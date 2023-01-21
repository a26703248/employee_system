package side.project.employee_system.entity;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("sys_role")
public class SysRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotBlank(message="請輸入權限名稱")
    private String name;

    @NotBlank(message="請輸入權限代號")
    private String code;

    /**
     * 備註
     */
    private String description;

    @TableField(exist = false)
    private List<Long> menuIds;
}
