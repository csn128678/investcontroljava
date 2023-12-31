package com.sccit.invectrl;

import com.sccit.invectrl.appconfigs.AuthenticationAccessDeniedHandler;
import com.sccit.invectrl.appconfigs.InveCtrlFilterInvocationSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Order(Integer.MAX_VALUE  )
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) //全局
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
/*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll()
                .and().logout().permitAll();
    }*/

   // @Autowired
   // private UserService userService;//实现了UserDetailsService接口
    @Autowired
    private InveCtrlFilterInvocationSecurity inveCtrlFilterInvocationSecurity;//权限过滤器（当前url所需要的访问权限）
   // @Autowired
   // private MyAccessDecisionManager myAccessDecisionManager;//权限决策器
    @Autowired
    private AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;//自定义错误(403)返回数据

    /**
     * 自定义的加密算法
     * @return
     */
   /* @Bean
    public PasswordEncoder myPasswordEncoder() {
        return new MyPasswordEncoder();
    }*/
    /**
     *  配置userDetails的数据源，密码加密格式
     */
   /* @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(myPasswordEncoder());
    }*/
    /**
     * 配置放行的资源
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/index.html", "/static/**","/loginPage","/register")
                // 给 swagger 放行；不需要权限能访问的资源
                .antMatchers(
                        "/api/AppUser/selectAll",
                        "/api/**",
                        "/swagger-ui.html","/swagger-ui/index.html",
                        "/swagger-resources/**", "/images/**", "/webjars/**", "/v2/api-docs", "/configuration/ui",
                        "/configuration/security"
                );
    }

    /**
     * 这段配置，我认为就是配置Security的认证策略, 每个模块配置使用and结尾。
     authorizeRequests()配置路径拦截，表明路径访问所对应的权限，角色，认证信息。
     formLogin()对应表单认证相关的配置
     logout()对应了注销相关的配置
     httpBasic()可以配置basic登录
     */
    /**
     * HttpSecurity包含了原数据（主要是url）
     * 1.通过withObjectPostProcessor将MyFilterInvocationSecurityMetadataSource和MyAccessDecisionManager注入进来
     * 2.此url先被MyFilterInvocationSecurityMetadataSource处理，然后 丢给 MyAccessDecisionManager处理
     * 3.如果不匹配，返回 MyAccessDeniedHandler
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().permitAll().and().logout().permitAll();//配置不需要登录验证
        return;
        // authorizeRequests()配置路径拦截，表明路径访问所对应的权限，角色，认证信息
       /* http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        //o.setSecurityMetadataSource(myFilterInvocationSecurityMetadataSource);
                        //o.setAccessDecisionManager(myAccessDecisionManager);
                        return o;
                    }
                })
                .and()
                // formLogin()对应表单认证相关的配置
                .formLogin()
                .loginPage("/loginPage")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        PrintWriter out = httpServletResponse.getWriter();
                        StringBuffer sb = new StringBuffer();
                        sb.append("{\"status\":\"error\",\"msg\":\"");
                        if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
                            sb.append("用户名或密码输入错误，登录失败!");
                        } else {
                            sb.append("登录失败!");
                        }
                        sb.append("\"}");
                        out.write(sb.toString());
                        out.flush();
                        out.close();
                    }
                }).successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        PrintWriter out = httpServletResponse.getWriter();
                        String s = "{\"status\":\"success\",\"msg\":\"登陆成功\"}";
                        out.write(s);
                        out.flush();
                        out.close();
                    }
                }).and()
                // logout()对应了注销相关的配置
                .logout()
                .permitAll()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .accessDeniedHandler(authenticationAccessDeniedHandler);*/
    }
}
