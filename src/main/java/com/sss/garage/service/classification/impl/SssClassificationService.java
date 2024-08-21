package com.sss.garage.service.classification.impl;

import com.sss.garage.model.classification.Classification;
import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.driver.DriverRepository;
import com.sss.garage.model.league.League;
import com.sss.garage.model.raceresult.RaceResultRepository;
import com.sss.garage.model.team.Team;
import com.sss.garage.model.team.TeamRepository;
import com.sss.garage.service.classification.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SssClassificationService implements ClassificationService {
    private RaceResultRepository raceResultRepository;

    private DriverRepository driverRepository;

    private TeamRepository teamRepository;

    @Override
    public Page<Classification> getClassification(final League league, final Pageable pageable) {
        return setClassification(league, pageable);
    }

    public Page<Classification> getClassificationForTeams(final League league, final Pageable pageable) {
        return setClassificationForTeams(league, pageable);
    }

    private Page<Classification> setClassification(final League league, final Pageable pageable) {
        List<Classification> classifications = new ArrayList<>();
        for (Driver driver : driverRepository.findDriversByLeague(league)) {
            Classification classification = new Classification();
            classification.setDriver(driver);
            classification.setTeam(raceResultRepository.findLastTeamByDriverAndLeague(driver, league));
            classification.setLeague(league);
            classification.setPoints(raceResultRepository.findPointsByDriverAndLeague(driver, league));
            classifications.add(classification);
        }
        
        final List<Map.Entry<Integer, List<Classification>>> sortedClassifications =
            classifications.stream()
                .collect(Collectors.groupingBy(Classification::getPoints))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey()).toList();
        
        int position = 1;
        for(Map.Entry<Integer, List<Classification>> entry : sortedClassifications) {
            if(entry.getValue().size() == 1) {
                entry.getValue().get(0).setPosition(position++);
            }
            else { // For sure more than 1, never 0
                while (entry.getValue().size() > 1) {
                    Classification winner = findWinnerInDuplicates(entry.getValue());
                    winner.setPosition(position++);
                    entry.getValue().remove(winner);
                }
                entry.getValue().get(0).setPosition(position++);
            }
        }
        
        classifications.sort(Comparator.comparing(Classification::getPosition).reversed());
        
        return new PageImpl<>(classifications, pageable, classifications.size());
    }
    
    private Classification findWinnerInDuplicates(final List<Classification> classifications) {
        if(classifications.size() == 1) {
            return classifications.get(0);
        }
        for(int checkingPosition = 1; checkingPosition <= 20; checkingPosition++) {
            for (Classification classification : classifications) {
                Integer thisPositionCount = raceResultRepository.countFinishPositionByDriverAndLeague(
                    classification.getDriver(), classification.getLeague(), checkingPosition);
                if (thisPositionCount == null) {
                    thisPositionCount = 0;
                } else {
                    ++thisPositionCount;
                }
                
                classification.setPositionCount(classification.getPositionCount() + thisPositionCount);// The sum is not really necessary, but whoever has the highest wins
            }
            
            classifications.sort(Comparator.comparing(Classification::getPositionCount).reversed());
            
            if(classifications.get(0).getPositionCount() > classifications.get(1).getPositionCount()) {
                for(int i = 1; i < classifications.size(); i++) {
                    classifications.get(i).setPositionCount(0);// clear for next round
                }
                return classifications.get(0);
            }
        }
        
        // Almost impossible, drivers have exactly the same number of positions, return first one
        return classifications.get(0);
    }

    private Page<Classification> setClassificationForTeams(final League league, final Pageable pageable) {
        List<Classification> classifications = new ArrayList<>();
        for (Team team : teamRepository.findTeamsByLeague(league)) {
            Classification classification = new Classification();
            classification.setTeam(team);
            classification.setLeague(league);
            classification.setPoints(raceResultRepository.findPointsByTeamAndLeague(team, league));
            classifications.add(classification);
        }

        List<Classification> duplicates = findDuplicates(classifications);

        for (int j = 1; j < 11; j++) {
            if (!duplicates.isEmpty()) {
                List<Classification> filtered = new ArrayList<>();
                for (Classification classification : duplicates) {
                    classification.setPosition(j);
                    classification.setPositionCount(raceResultRepository
                            .countFinishPositionByTeamAndLeague(classification.getTeam(), classification.getLeague(), j));
                    filtered.add(classification);
                }
                duplicates = findDuplicates(filtered);
            }
        }

        sortClassification(classifications);
        return new PageImpl<>(classifications, pageable, classifications.size());
    }

    private void sortClassification(final List<Classification> classifications) {
        classifications.sort(Comparator.comparing(Classification::getPoints)
                .thenComparing(Classification::getPosition, Comparator.reverseOrder())
                .thenComparing(Classification::getPositionCount).reversed());
    }

    private List<Classification> findDuplicates(final List<Classification> classifications) {
        return classifications.stream()
                .collect(Collectors.groupingBy(i -> List.of(i.getPoints(), i.getPosition(), i.getPositionCount())))
                .values().stream()
                .filter(classificationList -> classificationList.size() > 1).flatMap(List::stream).toList();
    }

    @Autowired
    public void setRaceResultRepository(RaceResultRepository raceResultRepository) {
        this.raceResultRepository = raceResultRepository;
    }

    @Autowired
    public void setDriverRepository(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Autowired
    public void setTeamRepository(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
}
