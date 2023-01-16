package side.project.employee_system;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class CodeGenerator {
  protected static List<String> getTables(String tables) {
    return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
  }

  public static void main(String[] args) {

    String projectPath = System.getProperty("user.dir");
    // sys_menu,sys_role,sys_role_menu,sys_user,sys_user_role
    // data source 資料來原設定
    String url = "jdbc:mysql://192.168.0.200:3306/vueadmin";
    String username = "developer";
    String password = "a0909007892";
    String auth = "howard";
    String groupId = "side.project";
    String moduleName = "employee_system";
    String rootPath = "./";
    String javaPath = rootPath + "src/main/java/";
    String mapperPath = rootPath + "/src/main/resources/mapper";
    FreemarkerTemplateEngine fte = new FreemarkerTemplateEngine();
    FastAutoGenerator.create(url, username, password)
        .globalConfig(builder -> {
          builder.author(auth)
              .fileOverride()
              .outputDir(javaPath);
        })
        .packageConfig(builder -> {
          builder.parent(groupId)
              .moduleName(moduleName)
              .pathInfo(Collections.singletonMap(OutputFile.xml, mapperPath));
        })
        .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("請輸入資料表名稱(多個資料表請用','區分)")))
            .controllerBuilder().enableRestStyle().enableHyphenStyle()
            .entityBuilder().enableLombok()
            .build())
        .templateEngine(fte)
        .execute();
  }

}
