package side.project.employee_system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import side.project.employee_system.common.dto.SysMenuDto;
import side.project.employee_system.entity.SysMenu;
import side.project.employee_system.entity.SysUser;
import side.project.employee_system.mapper.SysMenuMapper;
import side.project.employee_system.mapper.SysUserMapper;
import side.project.employee_system.service.ISysMenuService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author howard
 * @since 2023-01-16
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

  @Autowired
  private SysUserMapper sysUserMapper;

  @Override
  public List<SysMenuDto> getCurrentNav() {
    String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    SysUser user = sysUserMapper.getByUsername(username);
    List<Long> menuIds = sysUserMapper.getNavMenuId(user.getId());
    // 若沒有則返回空
    if (menuIds.size() == 0)
      return new ArrayList<SysMenuDto>();

    List<SysMenu> menus = listByIds(menuIds);
    // 轉換成樹狀結構
    List<SysMenu> menuTree = buildTreeMenu(menus);
    // 將樹狀結構 menu 轉換成 Dto 物件
    return convert(menuTree);
  }

  @Override
  public List<SysMenu> tree() {
    // 取得所有選單
    List<SysMenu> list = list(new QueryWrapper<SysMenu>().orderByAsc("order_num"));
    // 轉換成樹狀結構
    return buildTreeMenu(list);
  }

  // private function
  private List<SysMenu> buildTreeMenu(List<SysMenu> menus) {
    List<SysMenu> finalMenus = new ArrayList<>();

    for (SysMenu menu : menus) {
      for (SysMenu e : menus) {
        if (menu.getId() == e.getParentId()) {
          menu.getChildren().add(e);
        }
      }
      if (menu.getParentId() == 0L) {
        finalMenus.add(menu);
      }
    }

    return finalMenus;
  }

  private List<SysMenuDto> convert(List<SysMenu> menuTree) {
    List<SysMenuDto> menuDtos = new ArrayList<>();
    menuTree.forEach(mt -> {
      SysMenuDto dto = new SysMenuDto();
      dto.setId(mt.getId());
      dto.setName(mt.getPerms());
      dto.setTitle(mt.getName());
      dto.setComponent(mt.getComponent());
      dto.setPath(mt.getPath());
      dto.setIcon(mt.getIcon());
      if (mt.getChildren().size() > 0) {
        dto.setChildren(convert(mt.getChildren()));
      }
      menuDtos.add(dto);
    });
    return menuDtos;
  }

}
