package com.sss.garage.converter.image;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.image.ImageData;
import com.sss.garage.model.image.Image;
import com.sss.garage.model.league.League;
import com.sss.garage.util.image.ImageUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ImageReverseConverter extends BaseConverter implements Converter<ImageData, Image> {
    @Override
    public Image convert(ImageData source) {
        final Image target = new Image();

        target.setLeague(getConversionService().convert(source.getLeague(), League.class));
        try {
            target.setBanner(ImageUtils.compressImage(source.getBanner()));
            target.setLogo(ImageUtils.compressImage(source.getLogo()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return target;
    }
}
