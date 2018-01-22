package net.fnsco.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.poi.util.IOUtils;

import com.alibaba.fastjson.JSON;

/**
 * @desc 发送http请求工具类
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月9日 下午1:49:25
 */
public class HttpClientUtil {

    /**
     * doGet:(这里用一句话描述这个方法的作用) 发送get请求 可以传递参数
     *
     * @param url
     * @param param
     * @return    设定文件
     * @return String    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public static String doGet(String url, Map<String, String> param) {
        // 创建Httpclient对象  
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri  
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();
            // 创建http GET请求  
            HttpGet httpGet = new HttpGet(uri);
            // 执行请求  
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200  
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * doGet:(这里用一句话描述这个方法的作用) 无参数发送get请求
     *
     * @param url
     * @return    设定文件
     * @return String    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public static String doGet(String url) {
        return doGet(url, null);
    }

    /**
     * doPost:(这里用一句话描述这个方法的作用) 有参数发送POST请求
     * @param url
     * @param param
     * @return    设定文件
     * @author tangliang
     * @date 2017年8月9日 下午1:54:28
     * @return String    DOM对象
     */
    public static String doPost(String url, Map<String, String> param) {
        // 创建Httpclient对象  
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求  
            HttpPost httpPost = new HttpPost(url);
            // 创建参数列表  
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                // 模拟表单  
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            }
            // 执行http请求  
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block  
                e.printStackTrace();
            }
        }

        return resultString;
    }

    /**
     * doPost:(这里用一句话描述这个方法的作用)无参数发送POST请求
     * @param url
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月9日 下午1:55:13
     * @return String    DOM对象
     */
    public static String doPost(String url) {
        return doPost(url, null);
    }

    /**
     * doPostJson:(这里用一句话描述这个方法的作用)发送JSON参数POST请求
     * @param url
     * @param json
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月9日 下午1:55:31
     * @return String    DOM对象
     */
    public static String doPostJson(String url, String json) {
        // 创建Httpclient对象  
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求  
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容  
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求  
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block  
                e.printStackTrace();
            }
        }
        return resultString;
    }

    static PoolingHttpClientConnectionManager cm = null;

    public static void init() {
        LayeredConnectionSocketFactory sslsf = null;
        try {
            sslsf = new SSLConnectionSocketFactory(SSLContext.getDefault());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create().register("https", sslsf).register("http", new PlainConnectionSocketFactory())
            .build();
        cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(20);
    }

    public static CloseableHttpClient getHttpClient() {
        if (cm == null) {
            init();
        }
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();

        /*CloseableHttpClient httpClient = HttpClients.createDefault();//如果不采用连接池就是这种方式获取连接*/
        return httpClient;
    }

    //HaoMaiClient.java中的in.close();作用就是将用完的连接释放，下次请求可以复用，这里特别注意的是，如果不使用in.close();而仅仅使用response.close();结果就是连接会被关闭，并且不能被复用，这样就失去了采用连接池的意义。
    public static <T> T get(String path, Class<T> clazz) {
        CloseableHttpClient httpClient = getHttpClient();
        HttpGet httpget = new HttpGet(path);
        String json = null;
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpget);
            InputStream in = response.getEntity().getContent();
            //json = IOUtils.toString(in);
            in.close();
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return JSON.parseObject(json, clazz);
    }
}
