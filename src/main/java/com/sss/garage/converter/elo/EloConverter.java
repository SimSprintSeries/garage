package com.sss.garage.converter.elo;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.driver.DriverData;
import com.sss.garage.data.elo.EloData;
import com.sss.garage.data.game.GameData;
import com.sss.garage.model.elo.Elo;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EloConverter extends BaseConverter implements Converter<Elo, EloData> {

    @Override
    public EloData convert(final Elo source) {
        final EloData eloData = new EloData();

        eloData.setId(source.getId());
        eloData.setValue(source.getValue());
        //eloData.setDriver(getConversionService().convert(source.getDriver(), DriverData.class));
        eloData.setGame(getConversionService().convert(source.getGame(), GameData.class));

        return eloData;
    }

}
