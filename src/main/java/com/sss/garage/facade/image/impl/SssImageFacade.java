package com.sss.garage.facade.image.impl;

import com.sss.garage.data.image.ImageData;
import com.sss.garage.facade.SssBaseFacade;
import com.sss.garage.facade.image.ImageFacade;
import com.sss.garage.model.image.Image;
import com.sss.garage.model.league.League;
import com.sss.garage.service.image.ImageService;
import com.sss.garage.service.league.LeagueService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SssImageFacade extends SssBaseFacade implements ImageFacade {
    private LeagueService leagueService;

    private ImageService imageService;

    @Override
    public ImageData getImage(final String leagueId) {
        League league = null;
        if (Strings.isNotEmpty(leagueId)) {
            league = leagueService.getLeague(Long.valueOf(leagueId)).orElseThrow();
        }
        return conversionService.convert(imageService.getImage(league), ImageData.class);
    }

    @Override
    public void createImage(ImageData imageData) {
        imageService.createImage(conversionService.convert(imageData, Image.class));
    }

    @Autowired
    public void setLeagueService(final LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @Autowired
    public void setImageService(final ImageService imageService) {
        this.imageService = imageService;
    }
}
