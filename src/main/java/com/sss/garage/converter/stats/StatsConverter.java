package com.sss.garage.converter.stats;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.stats.StatsData;
import com.sss.garage.model.stats.Stats;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StatsConverter extends BaseConverter implements Converter<Stats, StatsData> {
    @Override
    public StatsData convert(final Stats source) {
        final StatsData data = new StatsData();

        data.setId(source.getId());
        data.setWins(source.getWins());
        data.setPodiums(source.getPodiums());
        data.setTop10(source.getTop10());
        data.setDnfs(source.getDnfs());
        data.setDsqs(source.getDsqs());
        data.setFastestLaps(source.getFastestLaps());

        return data;
    }
}
