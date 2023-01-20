package side.project.employee_system.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

import side.project.employee_system.common.dto.SysMenuDto;
import side.project.employee_system.entity.SysMenu;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author howard
 * @since 2023-01-16
 */
public interface ISysMenuService extends IService<SysMenu> {

  public List<SysMenuDto> getCurrentNav();

  public List<SysMenu> tree();

}
