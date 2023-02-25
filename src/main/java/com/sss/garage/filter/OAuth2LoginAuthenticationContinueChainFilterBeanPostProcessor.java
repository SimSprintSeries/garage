package com.sss.garage.filter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Predicate;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;

/**
 * Assign proper Authentication Manager to OAuth2LoginAuthenticationContinueChainFilter
 * Authentication Manager is constructed and injected upon http.build(), so we have to replace it after the bean is created
 */
@Component
public class OAuth2LoginAuthenticationContinueChainFilterBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
        if(bean instanceof SecurityFilterChain) {
            SecurityFilterChain chain = (SecurityFilterChain)bean;
            OAuth2LoginAuthenticationFilter replacedFilter = chain.getFilters().stream()
                    .filter(OAuth2LoginAuthenticationFilter.class::isInstance)
                    .filter(Predicate.not(OAuth2LoginAuthenticationContinueChainFilter.class::isInstance))
                    .map(OAuth2LoginAuthenticationFilter.class::cast)
                    .findFirst().orElseThrow();

            OAuth2LoginAuthenticationContinueChainFilter properFilter = chain.getFilters().stream()
                    .filter(OAuth2LoginAuthenticationContinueChainFilter.class::isInstance)
                    .map(OAuth2LoginAuthenticationContinueChainFilter.class::cast)
                    .findFirst().orElseThrow();

            try {
                Method authenticationManagerField = AbstractAuthenticationProcessingFilter.class.getDeclaredMethod("getAuthenticationManager");
                authenticationManagerField.setAccessible(true);
                AuthenticationManager authManager = (AuthenticationManager) authenticationManagerField.invoke(replacedFilter);

                properFilter.setAuthenticationManager(authManager); // bing bang bong, proper authentication manager provided

            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
