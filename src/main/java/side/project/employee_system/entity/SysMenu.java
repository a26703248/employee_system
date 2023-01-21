package side.project.employee_system.entity;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
@TableName("sys_menu")
public class SysMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 父選單ID，根選單為 0
     */
    @NotNull(message="父選單未選擇")
    private Long parentId;

    @NotBlank(message="選單名稱為填")
    private String name;

    /**
     * 選單URL
     */
    private String path;

    /**
     * 權限 多個用逗號分割，如: user:list,user:create
     */
    @NotBlank(message="選單權限碼未輸入")
    private String perms;

    private String component;

    /**
     * 類型 0:目錄 1:選單 2:按鈕
     */
    @NotNull(message="選單類型為填寫")
    private Integer type;

    /**
     * 選單圖示
     */
    private String icon;

    private Integer orderNum;

    @TableField(exist = false)
    private List<SysMenu> children = new ArrayList<>();
}
