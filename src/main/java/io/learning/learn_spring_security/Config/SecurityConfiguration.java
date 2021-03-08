package io.learning.learn_spring_security.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
      //  super.configure(auth);
        //set config on the auth object
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("admin") /*8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918*/
                .roles("ADMIN")
                .and()
                .withUser("jdoe")
                .password("jdoe")
                .roles("USER");





    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
       return NoOpPasswordEncoder.getInstance(); //don't do this in prod. just for tut

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.authorizeRequests()
               /*.antMatchers("/", "static/css", "static/js").permitAll()*/
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USER","ADMIN")
                .antMatchers("/").permitAll()
                .and().formLogin();

    }
}
