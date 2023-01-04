package com.sss.garage.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class SssBaseController {
    protected ModelMapper mapper;

    @Autowired
    protected void setMapper(final ModelMapper mapper) {
        this.mapper = mapper;
    }
}
