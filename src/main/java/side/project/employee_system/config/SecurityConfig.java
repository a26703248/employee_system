package side.project.employee_system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import side.project.employee_system.security.LoginFailureHandle;
import side.project.employee_system.security.LoginSuccessHandle;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  private LoginFailureHandle loginFailureHandle;

  @Autowired
  private LoginSuccessHandle loginSuccessHandle ;

  private static final String[] URL_WHITELIST = {
    "/login",
    "/logout",
    "/favicon.ico"
  };

  // @Bean
  // public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder,
  //     UserDetailsService userDetailService) throws Exception {
  //   return http.getSharedObject(AuthenticationManagerBuilder.class)
  //       .userDetailsService(userDetailService)
  //       .passwordEncoder(passwordEncoder)
  //       .and()
  //       .build();
  // }

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
      .anyRequest().authenticated();
      return http.build();
  }

}
