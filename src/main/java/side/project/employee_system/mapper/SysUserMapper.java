package side.project.employee_system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import side.project.employee_system.entity.SysUser;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author howard
 * @since 2023-01-16
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
  public List<Long> getNavMenuId(@Param("userId")Long userId);
}
