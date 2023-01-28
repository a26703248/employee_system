package side.project.employee_system.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import side.project.employee_system.entity.SysMenu;

@SpringBootTest
public class SysMenuMapperTest {

  @Autowired
  private SysMenuMapper mapper;

  private static Long testEntityId;

  private static final Logger logger = LoggerFactory.getLogger(SysMenuMapperTest.class);

  @Test
  public void insertTest() {
    logger.info("Inserting test");
    SysMenu sysMenu = new SysMenu();
    sysMenu.setName("測試選單");
    sysMenu.setType(0);
    sysMenu.setOrderNum(18);
    sysMenu.setComponent("sys/User");
    sysMenu.setStatus(1);
    sysMenu.setPerms("sys:test:list");
    sysMenu.setCreated(LocalDateTime.now());
    sysMenu.setUpdated(LocalDateTime.now());
    mapper.insert(sysMenu);
    logger.info(sysMenu.toString());
    assertNotNull(sysMenu.getId());
    testEntityId = sysMenu.getId();
    logger.info("Inserting test end");
  }

  @Test
  public void selectTest() {
    logger.info("Selecting test");
    SysMenu selectById = mapper.selectById(testEntityId);
    assertNotNull(selectById);
    QueryWrapper<SysMenu> qw = new QueryWrapper<SysMenu>().eq("id", testEntityId);
    List<SysMenu> selectList = mapper.selectList(qw);
    assertNotNull(selectList);
    Page<SysMenu> pg = new Page<>(1, 3);
    Page<SysMenu> selectPage = mapper.selectPage(pg, qw);
    List<SysMenu> records = selectPage.getRecords();
    assertNotNull(records);
    logger.info("Selecting test end");
  }

  @Test
  public void updateTest() {
    logger.info("Updating test");
    SysMenu sysMenu = new SysMenu();
    sysMenu.setId(testEntityId);
    sysMenu.setComponent("sys/Roles");
    mapper.updateById(sysMenu);
    SysMenu selectById = mapper.selectById(testEntityId);
    assertTrue(selectById.getComponent() == sysMenu.getComponent());
    logger.info("Updating test end");
  }

  @Test
  public void deleteTest() {
    logger.info("Deleting test");
    mapper.deleteById(testEntityId);
    logger.info("Deleting test end");
  }

}
