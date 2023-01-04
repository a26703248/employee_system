package side.project.employee_system.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;

@Configuration
@MapperScan("side.project.employee_system.mapper")
public class MybatisPlusConfig {

  @Bean
  public MybatisPlusInterceptor mybatisPlusInterceptor(){
    MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
    // 分頁
    interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
    // 防止全部資料誤更新(where 條件不成立時會更新所有資料)
    interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
    return interceptor;
  }

}
