package com.sss.garage.facade.user.impl;

import com.sss.garage.data.user.user.DiscordUserData;
import com.sss.garage.facade.SssBaseFacade;
import com.sss.garage.facade.user.UserFacade;
import com.sss.garage.service.auth.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SssUserFacade extends SssBaseFacade implements UserFacade {

    private UserService userService;

    @Override
    public DiscordUserData getCurrentUser() {
        return conversionService.convert(userService.getCurrentUser().orElseThrow(), DiscordUserData.class);
    }

    @Autowired
    public void setUserService(final UserService userService) {
        this.userService = userService;
    }
}
