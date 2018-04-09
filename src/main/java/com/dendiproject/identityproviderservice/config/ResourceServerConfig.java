package com.dendiproject.identityproviderservice.config;


import com.dendiproject.identityproviderservice.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;

@EnableResourceServer
@Configuration
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {


//   @Autowired
//    private AuthenticationManager authenticationManager;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
   @Autowired
   private UserDetailsService userDetailsService;
    
    @Autowired
    private   AppConfig appConfig;
     
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
               .authorizeRequests()
                .antMatchers("/")
                .permitAll()
                .antMatchers("/private").authenticated();                                           //<--Для теста
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(appConfig.dataSource());//userDetailsService(userDetailsService);
                
    }
    
    
    
}
