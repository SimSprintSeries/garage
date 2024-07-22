package com.sss.garage.converter.image;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.image.ImageData;
import com.sss.garage.data.league.LeagueData;
import com.sss.garage.model.image.Image;
import com.sss.garage.util.image.ImageUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.zip.DataFormatException;

@Component
public class ImageConverter extends BaseConverter implements Converter<Image, ImageData> {

    @Override
    public ImageData convert(final Image source) {
        final ImageData data = new ImageData();
        data.setLeague(getConversionService().convert(source.getLeague(), LeagueData.class));
        try {
            data.setBanner(ImageUtils.decompressImage(source.getBanner()));
            data.setLogo(ImageUtils.decompressImage(source.getLogo()));
        } catch (DataFormatException | IOException e) {
            throw new RuntimeException(e);
        }

        return data;
    }
}
