package com.acs.intranetapps.config;

import java.util.Hashtable;

import javax.naming.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

@Configuration
public class LdapConfiguration {

    @Autowired
    Environment env;

    @Bean
    public LdapContextSource contextSource () {
    	
		Hashtable env = new Hashtable();
		env.put(Context.PROVIDER_URL, "ldap://bocaips.acsbnt.ad.net:389/");
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.REFERRAL, "follow");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "B0026150@acsbnt.ad.net");
		env.put(Context.SECURITY_CREDENTIALS, "D@nce!nS3ptember");
		
        LdapContextSource contextSource= new LdapContextSource();
        contextSource.setUrl("ldap://bocaips.acsbnt.ad.net:389/");
        contextSource.setBase("dc=acsbnt,DC=ad,DC=net");
//        contextSource.setUserDn("B0026150");
        contextSource.setBaseEnvironmentProperties(env);        
//        contextSource.setPassword("D@nce!nS3ptember");
        return contextSource;
    }

    @Bean
    public LdapTemplate ldapTemplate() {
        return new LdapTemplate(contextSource());        
    }

}
