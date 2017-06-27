package net.fnsco.freamwork.aop;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 

/**
 * 
 * @author sxf
 *
 */
public class BodyRequestWrapper extends HttpServletRequestWrapper {
    private static Logger logger = LoggerFactory.getLogger(BodyRequestWrapper.class);
    private final byte[] body;

    public BodyRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        body = HttpHelper.getBodyString(request).getBytes(Charset.forName("UTF-8"));
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

        final ByteArrayInputStream bais = new ByteArrayInputStream(body);

        return new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                // TODO Auto-generated method stub
                return true;
            }

            @Override
            public boolean isReady() {
                // TODO Auto-generated method stub
                return true;
            }

            @Override
            public void setReadListener(ReadListener listener) {
                // TODO Auto-generated method stub
                logger.error("sdfd");
            }

           
        };
    }
}
