package net.fnsco.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import net.fnsco.core.base.BaseService;
import net.fnsco.freamwork.business.WebService;
import net.fnsco.freamwork.business.WebUserDTO;

@Service
public class WebUserServiceImpl extends BaseService implements WebService {

    @Override
    public WebUserDTO getCookieUser(HttpServletRequest request) {
        return null;
    }
}
