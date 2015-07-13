package eu.ydp.gwtutil.client.date;

import java.util.Date;

public class DateService {

    public long getTimeMillis() {
        return new Date().getTime();
    }

}
