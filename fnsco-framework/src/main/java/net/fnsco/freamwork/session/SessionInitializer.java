package net.fnsco.freamwork.session;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

public class SessionInitializer extends AbstractHttpSessionApplicationInitializer {
    public SessionInitializer() {
        super(RedisSessionConfig.class);
    }
}