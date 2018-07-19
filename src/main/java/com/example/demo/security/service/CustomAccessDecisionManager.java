package com.example.demo.security.service;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author  liuqitian
 * @date : 2018/7/4 15:30
 * @version : V1.0
 * 在这个类中进行用户权限的判定
 */
@Service
public class CustomAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide( Authentication authentication, Object object,
                        Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException{
        if( configAttributes == null ) {
            return ;
        }

        Iterator<ConfigAttribute> ite = configAttributes.iterator();

        while( ite.hasNext()){
            ConfigAttribute ca = ite.next();
            String needRole = ((SecurityConfig)ca).getAttribute();
            //ga 为用户所被赋予的权限。 needRole 为访问相应的资源应该具有的权限。
            for( GrantedAuthority ga: authentication.getAuthorities()){
                if(needRole.trim().equals(ga.getAuthority().trim())){
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足");
    }

    @Override
    public boolean supports( ConfigAttribute attribute ){
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz){
        return true;
    }


}