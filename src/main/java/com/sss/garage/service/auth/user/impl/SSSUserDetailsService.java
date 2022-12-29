package com.sss.garage.service.auth.user.impl;

import com.sss.garage.model.user.UserRepository;
import com.sss.garage.model.user.auth.SSSUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//TODO: Not used because of JwtAuthorizationFilter?
@Service
public class SSSUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    private ConversionService conversionService;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(u -> conversionService.convert(u, SSSUserDetails.class))
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }

    @Autowired
    public void setUserRepository(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setConversionService(final ConversionService conversionService) {
        this.conversionService = conversionService;
    }
}
