package com.sss.garage.configuration.hibernate;

import java.util.Map;

import com.sss.garage.model.race.interceptor.RaceInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;

@Component
public class HibernateInterceptorCustomizer implements HibernatePropertiesCustomizer {
    private RaceInterceptor raceInterceptor;

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put("hibernate.session_factory.interceptor", raceInterceptor);
    }

    @Autowired
    public void setRaceInterceptor(final RaceInterceptor raceInterceptor) {
        this.raceInterceptor = raceInterceptor;
    }
}
