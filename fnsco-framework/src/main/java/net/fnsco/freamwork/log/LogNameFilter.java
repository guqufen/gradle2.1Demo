package net.fnsco.freamwork.log;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class LogNameFilter extends Filter {
    String name;

    @Override
    public FilterReply decide(Object eventObject) {
        LoggingEvent event = (LoggingEvent) eventObject;

        if (event.getLoggerName().equals(name)) {
            return FilterReply.ACCEPT;
        } else {
            return FilterReply.DENY;
        }
    }

    /**
     * @param name the name to set
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    public void start() {
        if (this.name != null) {
            super.start();
        }

    }
}
