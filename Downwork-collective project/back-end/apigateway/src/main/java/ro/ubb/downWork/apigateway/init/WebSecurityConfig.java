package ro.ubb.downWork.apigateway.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

import ro.ubb.downWork.apigateway.security.CorsFilter;
import ro.ubb.downWork.apigateway.security.RestAuthenticationEntryPoint;
import ro.ubb.downWork.apigateway.security.RestAuthenticationFailureHandler;
import ro.ubb.downWork.apigateway.security.RestAuthenticationSuccessHandler;

/**
 * Created by langchristian96 and CristianCosmin on 20.10.2017.
 */
@Configuration
@EnableWebSecurity
@ComponentScan("ro.ubb.downWork.apigateway.security")
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private RestAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private RestAuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private RestAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private UserDetailsService apiGatewayUserDetailsService;

    @Bean
    CorsFilter corsFilter() {
        CorsFilter filter = new CorsFilter();
        return filter;
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return apiGatewayUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().addFilterBefore(corsFilter(), ChannelProcessingFilter.class) // adds your custom CorsFilter
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                //TODO: REMOVE UNNECESSARY PERMITS
                .and().authorizeRequests().antMatchers("/api/person/create").permitAll()
                .antMatchers("/api/person/reset/*").permitAll()
                .antMatchers("/api/person/getreviewsbyusername/*").permitAll()
                .antMatchers("/api/person/addreview/*").permitAll()
                .antMatchers("/api/person/getnotifications/*").permitAll()
                .antMatchers("/api/person/collaborated/**").permitAll()
                .antMatchers("/api/job/gettoacceptjobs/1").permitAll()
                .antMatchers("/api/job/getcompletedjobs/1").permitAll()
                .antMatchers("/api/job/addcandidate/6/4").permitAll()
                .antMatchers("/api/job/hirecandidate/6/4").permitAll()
                .antMatchers("/api/job/acceptjob/1/1").permitAll()
                .antMatchers("/api/person/changepassword/*").permitAll()
                .antMatchers("/api/person/*").hasAnyRole("ADMIN")
                .antMatchers("/api/jobtype/*").hasAnyRole("ADMIN")
                .antMatchers("/api/job/*").hasAnyRole("ADMIN")
//                .antMatchers("/api/job/*/**").hasAnyRole("ADMIN")
//                .antMatchers("/rest/team/getallbyteamlead/*").hasRole(UserType.TeamLead.toString().toUpperCase())
//                .antMatchers("/rest/user/getbyusername/*").permitAll().antMatchers("/rest/employee/getbyusername/*")
//                .permitAll().antMatchers("/rest/agenda/getall/").permitAll()
//                .antMatchers("/rest/teamcoordinator/getbyusername/*").permitAll().antMatchers("/rest/team/getallbyteamlead/*")
//                .permitAll().antMatchers("/rest/teamlead/getbyusername/*").permitAll().antMatchers("/rest/employee/**/*")
//                .hasAnyRole(UserType.Admin.toString().toUpperCase(), UserType.Employee.toString().toUpperCase())
//                .antMatchers("/rest/teamcoordinator/**/*")
//                .hasAnyRole(UserType.Admin.toString().toUpperCase(), UserType.TeamCoordinator.toString().toUpperCase())
//                .antMatchers("/rest/teamlead/**/*")
//                .hasAnyRole(UserType.Admin.toString().toUpperCase(), UserType.TeamLead.toString().toUpperCase())
//                .antMatchers("/rest/user/getall").permitAll().antMatchers("/rest/user/update/**/*").permitAll()
//                .antMatchers("/rest/team/getbyname/*").permitAll().antMatchers("/rest/user/**/*")
//                .hasAnyRole(UserType.Admin.toString().toUpperCase()).antMatchers("/rest/team/**/*")
//                .antMatchers("*").permitAll()
                .and().formLogin()
                .loginPage("/login").successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler)
                .and().logout().logoutSuccessUrl("/login");
    }

//     @Override
//     protected void configure(AuthenticationManagerBuilder builder) throws Exception {
//     builder.inMemoryAuthentication().withUser("user").password("user").roles("USER").and().withUser("cpruteanu")
//     .password("1234").roles("ADMIN");
//     }
}
