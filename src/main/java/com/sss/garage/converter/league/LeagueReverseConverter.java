package com.sss.garage.converter.league;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.league.LeagueData;
import com.sss.garage.model.game.Game;
import com.sss.garage.model.image.Image;
import com.sss.garage.model.image.ImageRepository;
import com.sss.garage.model.league.League;
import com.sss.garage.util.image.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LeagueReverseConverter extends BaseConverter implements Converter<LeagueData, League> {
    private ImageRepository imageRepository;

    @Override
    public League convert(final LeagueData source) {
        final League target = new League();

        target.setGame(getConversionService().convert(source.getGame(), Game.class));
        target.setId(source.getId());
        target.setName(source.getName());
        target.setPlatform(source.getPlatform());
        target.setStartDate(source.getStartDate());
        target.setEventCount(source.getEventCount());

        try {
            imageRepository.save(new Image(target,
                    ImageUtils.compressImage(source.getBannerFile().getBytes()),
                    ImageUtils.compressImage(source.getLogoFile().getBytes())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return target;
    }

    @Autowired
    public void setImageRepository(final ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }
}
