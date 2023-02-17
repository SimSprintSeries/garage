package com.sss.garage.controller;

import java.util.Collection;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class SssBaseController {
    protected ModelMapper mapper;

    protected <S,D> List<D> mapAsList(final Collection<S> list, Class<D> targetClass) {
        return list.stream()
                .map(o -> this.mapper.map(o, targetClass))
                .toList();
    }

    @Autowired
    protected void setMapper(final ModelMapper mapper) {
        this.mapper = mapper;
    }
}
