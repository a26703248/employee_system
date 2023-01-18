package side.project.employee_system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import side.project.employee_system.security.JwtAccessDeniedHandler;
import side.project.employee_system.security.JwtAuthenticationEntryPoint;
import side.project.employee_system.security.JwtAuthenticationFilter;
import side.project.employee_system.security.LoginFailureHandle;
import side.project.employee_system.security.LoginSuccessHandle;
import side.project.employee_system.security.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  private LoginFailureHandle loginFailureHandle;

  @Autowired
  private LoginSuccessHandle loginSuccessHandle ;

  @Autowired
  private JwtAuthenticationEntryPoint authenticationEntryPoint;

  @Autowired
  private JwtAccessDeniedHandler jwtAccessDeniedHandler;

  @Autowired
  private UserDetailServiceImpl userDetailServiceImpl;

  @Bean
  public BCryptPasswordEncoder bcrCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
    return new JwtAuthenticationFilter();
  }

  private static final String[] URL_WHITELIST = {
    "/login",
    "/logout",
    "/favicon.ico"
  };

  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder,
      UserDetailsService userDetailService) throws Exception {
    return http.getSharedObject(AuthenticationManagerBuilder.class)
        .userDetailsService(userDetailServiceImpl)
        .passwordEncoder(passwordEncoder)
        .and()
        .build();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.cors()
      .and()
      .csrf()
      .disable()
      // 登入
      .formLogin()
      .successHandler(loginSuccessHandle)
      .failureHandler(loginFailureHandle)
      .and()
      // 禁用 session
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
      // pattern 政策
      .authorizeHttpRequests()
      .antMatchers(URL_WHITELIST).permitAll()
      .anyRequest().authenticated()
      // 異常處理
      .and()
      .exceptionHandling()
      .authenticationEntryPoint(authenticationEntryPoint)
      .accessDeniedHandler(jwtAccessDeniedHandler)
      // filter 配置
      .and()
      .addFilterBefore(jwtAuthenticationFilter(), BasicAuthenticationFilter.class);
      return http.build();
  }
}
