package side.project.employee_system.common.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/*
 {
          title: "系統工具",
          name: "SystemTool",
          icon: "operation",
          component:"",
          path: "",
          children: [],
        },
 */
@Data
public class SysMenuDto implements Serializable {
  private Long id;
  private String name;
  private String title;
  private String icon;
  private String path;
  private String component;
  private List<SysMenuDto> children;

}
