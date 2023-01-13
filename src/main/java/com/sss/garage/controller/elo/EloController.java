package com.sss.garage.controller.elo;

import com.sss.garage.controller.SssBaseController;
import com.sss.garage.service.elo.EloService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/elo")
@Api(tags = "Sss Elo")
public class EloController extends SssBaseController {

    private EloService eloService;

    @GetMapping
    public void calculateElo() {
        this.eloService.calculateElo();
    }

    @Autowired
    public void setEloService(final EloService eloService) {
        this.eloService = eloService;
    }

}
