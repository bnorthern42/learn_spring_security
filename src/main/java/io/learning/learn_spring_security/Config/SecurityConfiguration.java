package io.learning.learn_spring_security.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter{

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
      //  super.configure(auth);
        //set config on the auth object //now in data.sql
      /*  auth.inMemoryAuthentication()
                .withUser("admin")
                .password("admin") *//*8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918*//*
                .roles("ADMIN")
                .and()
                .withUser("jdoe")
                .password("jdoe")
                .roles("USER");
*/

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(
                        "SELECT username, password, enabled "
                                + "FROM my_users "
                                + "WHERE username=?")
                .authoritiesByUsernameQuery(
                        "SELECT username, authority"
                               + " FROM users "
                               + "WHERE username=?");;
        /*    .withDefaultSchema()
                .withUser(
                        User.withUsername("user")
                        .password("pass")
                        .roles("USER")
                )
                .withUser(
                        User.withUsername("admin")
                        .password("admin")
                        .roles("ADMIN")
                );*/



    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       // super.configure(http);
        http.authorizeRequests()
                .antMatchers("/", "static/css", "static/js").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USER","ADMIN")
                .antMatchers("/").permitAll()
                .and().formLogin();



    }
      @Bean
    public PasswordEncoder getPasswordEncoder(){
       return NoOpPasswordEncoder.getInstance(); //don't do this in prod. just for tut

    }
 /*


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.authorizeRequests()
               .antMatchers("/", "static/css", "static/js").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USER","ADMIN")
                .antMatchers("/").permitAll()
                .and().formLogin();

    }*/
}
