package com.example.demo.security.config;

import com.example.demo.security.service.CustomUserDetailsService;
import com.example.demo.security.service.MyFilterSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * @Auther: liuqitian
 * @Date: 2018/7/2 10:22
 * @Version: V1.0
 * @Description:
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private MyFilterSecurityInterceptor mySecurityFilter;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    //http://localhost:8080/login 输入正确的用户名密码 并且选中remember-me 则登陆成功，转到 index页面
    //再次访问index页面无需登录直接访问
    //访问http://localhost:8080/home 不拦截，直接访问，
    //访问http://localhost:8080/hello 需要登录验证后，且具备 “ADMIN”权限hasAuthority("ADMIN")才可以访问
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
//                .addFilterBefore(mySecurityFilter, FilterSecurityInterceptor.class)//在正确的位置添加我们自定义的过滤器
                .authorizeRequests()
                .antMatchers("/home").permitAll()//访问：/home 无需登录认证权限
                .anyRequest().authenticated() //其他所有资源都需要认证，登陆后访问
//                .antMatchers("/hello").hasAnyAuthority("ADMIN", "SUPER")//登陆后之后拥有“ADMIN”权限才可以访问/hello方法，否则系统会出现“403”权限不足的提示
//
//                .antMatchers(HttpMethod.GET, "/article/articles").hasAuthority("SUPER")
//                .antMatchers(HttpMethod.POST, "/article/articles").hasAuthority("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")//指定登录页是”/login”
                .permitAll()
                .successHandler(loginSuccessHandler()) //登录成功后可使用loginSuccessHandler()存储用户信息，可选。
                .and()
                .logout()
                .logoutSuccessUrl("/home") //退出登录后的默认网址是”/home”
                .permitAll()
                .invalidateHttpSession(true)
                .and()
                .rememberMe()//登录后记住用户，下次自动登录,数据库中必须存在名为persistent_logins的表
                .tokenValiditySeconds(1209600);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//指定密码加密所使用的加密器为passwordEncoder()
//需要将密码加密后写入数据库
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
        auth.eraseCredentials(false);
//        auth.
//                inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder())
//                .withUser("root").password("root").roles("ADMIN", "USER");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler(){
        return new LoginSuccessHandler();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
