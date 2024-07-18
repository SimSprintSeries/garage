package com.sss.garage.service.image;

import com.sss.garage.model.image.Image;
import com.sss.garage.model.league.League;

public interface ImageService {
    Image getImage(final League league);
}
