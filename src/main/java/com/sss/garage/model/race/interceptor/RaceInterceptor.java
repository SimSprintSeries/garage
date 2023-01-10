package com.sss.garage.model.race.interceptor;

import com.sss.garage.model.race.Race;

import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.stereotype.Component;

/**
 * Nullify event on race in case there exists parent race event, so event has only one race related
 */
@Component
public class RaceInterceptor extends EmptyInterceptor {

    @Override
    public boolean onSave(final Object entity, final Object id, final Object[] state, final String[] propertyNames,
                          final Type[] types) throws CallbackException {
        if(!(entity instanceof Race)) {
            return super.onSave(entity, id, state, propertyNames, types);
        }
        final Race race = (Race) entity;
        if(race.getParentRaceEvent() != null) {
            race.setEvent(null);
        }

        return super.onSave(entity, id, state, propertyNames, types);
    }
}
