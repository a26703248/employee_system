package side.project.employee_system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
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
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父選單ID，根選單為 0
     */
    private Long parentId;

    private String name;

    /**
     * 選單URL
     */
    private String path;

    /**
     * 權限 多個用逗號分割，如: user:list,user:create 
     */
    private String perms;

    private String component;

    /**
     * 類型 0:目錄 1:選單 2:按鈕
     */
    private Integer type;

    /**
     * 選單圖示
     */
    private String icon;

    private LocalDateTime created;

    private LocalDateTime updated;

    private Integer status;
}
