package com.sss.garage.facade.image;

import com.sss.garage.data.image.ImageData;

public interface ImageFacade {
    ImageData getImage(final String leagueId);

    void createImage(final ImageData imageData);
}
