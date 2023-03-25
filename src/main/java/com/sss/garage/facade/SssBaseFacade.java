package com.sss.garage.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;

public abstract class SssBaseFacade {

    protected ConversionService conversionService;

    @Autowired
    public void setConversionService(final ConversionService conversionService) {
        this.conversionService = conversionService;
    }
}
