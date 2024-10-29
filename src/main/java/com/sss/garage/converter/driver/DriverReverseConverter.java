package com.sss.garage.converter.driver;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.driver.DriverData;
import com.sss.garage.model.driver.Driver;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DriverReverseConverter extends BaseConverter implements Converter<DriverData, Driver> {
    @Override
    public Driver convert(final DriverData source) {
        final Driver target = new Driver();

        target.setId(source.getId());
        target.setName(source.getNickname());
        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        target.setShortName(source.getShortName());

        return target;
    }
}
