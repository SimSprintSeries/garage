package com.sss.garage.service.image.impl;

import com.sss.garage.model.image.Image;
import com.sss.garage.model.image.ImageRepository;
import com.sss.garage.model.league.League;
import com.sss.garage.service.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SssImageService implements ImageService {
    private ImageRepository imageRepository;

    @Override
    public Image getImage(final League league) {
        return imageRepository.findByLeague(league).orElseThrow();
    }

    @Override
    public void createImage(Image image) {
        imageRepository.save(image);
    }

    @Autowired
    public void setImageRepository(final ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }
}
