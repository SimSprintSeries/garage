package com.sss.garage.facade.classification.impl;

import com.sss.garage.data.classification.ClassificationData;
import com.sss.garage.data.driver.DriverData;
import com.sss.garage.data.league.LeagueData;
import com.sss.garage.facade.SssBaseFacade;
import com.sss.garage.facade.classification.ClassificationFacade;
import com.sss.garage.model.classification.Classification;
import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.league.League;
import com.sss.garage.service.classification.ClassificationService;
import com.sss.garage.service.driver.DriverService;
import com.sss.garage.service.league.LeagueService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SssClassificationFacade extends SssBaseFacade implements ClassificationFacade {
    private ClassificationService classificationService;

    private LeagueService leagueService;

    public Page<ClassificationData> getClassification(final String leagueId, final Pageable pageable) {
        League league = null;
        if(Strings.isNotEmpty(leagueId)) {
            league = leagueService.getLeague(Long.valueOf(leagueId)).orElseThrow();
        }
        Page<Classification> classification = classificationService.getClassification(league, pageable);
        return classification.map(c -> conversionService.convert(c, ClassificationData.class));
    }

    @Autowired
    public void setClassificationService(ClassificationService classificationService) {
        this.classificationService = classificationService;
    }

    @Autowired
    public void setLeagueService(LeagueService leagueService) {
        this.leagueService = leagueService;
    }
}
