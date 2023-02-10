package com.acs.intranetapps.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.ldap.LdapPasswordComparisonAuthenticationManagerFactory;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${ldap.urls}")
    private String ldapUrls;

    @Value("${ldap.base.dn}")
    private String ldapBaseDn;

    @Value("${ldap.username}")
    private String ldapSecurityPrincipal;

    @Value("${ldap.password}")
    private String ldapPrincipalPassword;

    @Value("${ldap.user.dn.pattern}")
    private String ldapUserDnPattern;
	
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
    .authorizeRequests()
    .antMatchers("/login**").anonymous()
    .antMatchers("/resources/**").permitAll()
    .antMatchers("/assets/**").permitAll()
    .antMatchers("/").authenticated()
    .antMatchers("/home").authenticated()
    .and()
    .formLogin()
    .loginPage("/login")
    .permitAll()
    .and()
    .logout()
    .permitAll()
    .and()
    .csrf()
    .disable();
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
	  
	  
	  log.debug("Param: {0}");
    auth
    	
      .ldapAuthentication()
      .contextSource()
      .url(ldapUrls + ldapBaseDn)
      .and()
      .passwordCompare()
      .passwordAttribute("password")
//      .managerDn(ldapSecurityPrincipal)
//      .managerPassword(ldapPrincipalPassword)
          .and()
        .userDnPatterns(ldapUserDnPattern)
//      .userSearchFilter("(sAMAccountName={0})")
//        .userSearchBase("CN=Users,dc=acsbnt,dc=net")

//      .userSearchBase("ou=people")
//      .groupSearchBase("ou=groups")
//      .groupSearchFilter("member={0}")
//        .groupSearchFilter("member={0}")
//        .contextSource()
//      .groupSearchBase("ou=groups")
//          .port(389)
//          
//          .and()
//        .passwordCompare()
//          .passwordEncoder(new LdapShaPasswordEncoder())
//          .passwordAttribute("password")
          ;
    
  }

  @Bean
  AuthenticationManager authenticationManager(BaseLdapPathContextSource contextSource) {
  	LdapPasswordComparisonAuthenticationManagerFactory factory = new LdapPasswordComparisonAuthenticationManagerFactory(
  			contextSource, NoOpPasswordEncoder.getInstance());
  	factory.setUserDnPatterns("uid={0},ou=people");
  	return factory.createAuthenticationManager();
  }
  
  @Bean
  public LdapContextSource getContextSource() {
  	  LdapContextSource contextSource = new LdapContextSource();
      contextSource.setUrl("ldap://bocaips.acsbnt.ad.net:389/");
      contextSource.setBase("dc=acsbnt,DC=ad,DC=net");
      
      //contextSource.setPassword("hisPassword");
      contextSource.afterPropertiesSet(); //needed otherwise you will have a NullPointerException in spring

      return contextSource;
  }
}
