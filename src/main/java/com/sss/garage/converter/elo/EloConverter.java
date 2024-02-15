package com.sss.garage.converter.elo;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.converter.driver.BasicDriverConverter;
import com.sss.garage.data.driver.DriverData;
import com.sss.garage.data.elo.EloData;
import com.sss.garage.data.game.GameData;
import com.sss.garage.model.elo.Elo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EloConverter extends BaseConverter implements Converter<Elo, EloData> {

    private BasicDriverConverter basicDriverConverter;

    @Override
    public EloData convert(final Elo source) {
        final EloData eloData = new EloData();

        eloData.setId(source.getId());
        eloData.setValue(source.getValue());
        eloData.setDriver(basicDriverConverter.convert(source.getDriver()));
        eloData.setGame(getConversionService().convert(source.getGame(), GameData.class));

        return eloData;
    }

    public BasicDriverConverter getBasicDriverConverter() {
        return basicDriverConverter;
    }

    @Autowired
    public void setBasicDriverConverter(final BasicDriverConverter basicDriverConverter) {
        this.basicDriverConverter = basicDriverConverter;
    }
}
