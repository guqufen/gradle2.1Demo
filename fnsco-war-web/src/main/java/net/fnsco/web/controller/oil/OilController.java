/**
 * 
 */
package net.fnsco.web.controller.oil;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.beust.jcommander.internal.Maps;

import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.trading.service.oilcard.OilCardApplyService;
import net.fnsco.trading.service.oilcard.entity.OilCardApplyDO;

/**
 * @author 
 *
 */
@Controller
@RequestMapping("/h5/oil")
public class OilController extends BaseController{
	
	@Autowired
	private OilCardApplyService oilCardApplyService;
	
	
	String url = "http://wx.51ucar.cn/ucariou_new.php?le=apply_verfiy&tkCode=j_500867&pid=02";
	
	@RequestMapping("/getNote")
	@ResponseBody
	public ResultDTO<String> testGetNote(String m,String sj){
		
		Map<String, String> param = Maps.newHashMap();
		param.put("m", m);
		param.put("sj", sj);
		List<Cookie>  cookies = doGet(url,param);
		for (Cookie cookie : cookies) {
			javax.servlet.http.Cookie cookiesPa = new javax.servlet.http.Cookie(cookie.getName(), cookie.getValue());
			response.addCookie(cookiesPa);
		}
		return ResultDTO.success();
	}
	
	@RequestMapping("/next")
	@ResponseBody
	public ResultDTO<JSONObject> testNext(String m,String sj,String v,String innerCode){
		javax.servlet.http.Cookie[] cookies = request.getCookies();
		for (javax.servlet.http.Cookie cookie : cookies) {
			System.out.println("初始cookie: "+cookie.getName()+":"+cookie.getValue());
		}
		Map<String, String> param = Maps.newHashMap();
		param.put("m", m);
		param.put("sj", sj);
		param.put("v", v);
		String  res = doPost(url,param);
		JSONObject json = JSONObject.parseObject(res);
		if("200".equals(json.getString("code"))) {
			OilCardApplyDO oilCardApply = new OilCardApplyDO();
			oilCardApply.setCreateTime(new Date());
			oilCardApply.setInnerCode(innerCode);
			oilCardApply.setMobile(m);
			oilCardApplyService.doAdd(oilCardApply, getUserId());
		}
		System.out.println(json.getString("message")+":"+res);
		return ResultDTO.success(json);
	}
	
	
	@RequestMapping("/index")
	public void testNextIndex(String mobile,String code){
		javax.servlet.http.Cookie[] cookies = request.getCookies();
		for (javax.servlet.http.Cookie cookie : cookies) {
			System.out.println("初始cookie: "+cookie.getName()+":"+cookie.getValue());
		}
		
		String location = "http://wx.51ucar.cn/ucariou_new.php?le=apply_wel&r_mobile="+mobile + "&r_code="+code+"&tkCode=j_500867&pid=02";
		try {
			response.sendRedirect(location);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static String doPost(String url, Map<String, String> param) {  
        // 创建Httpclient对象  
		CookieStore cookieStore = new BasicCookieStore();
		
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();  
        CloseableHttpResponse response = null;  
        String resultString = "";
        List<Cookie> cookies =null;
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
            cookies = cookieStore.getCookies();
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
	
	
	public static List<Cookie> doGet(String url, Map<String, String> param) {  
        // 创建Httpclient对象  
		CookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();  
        String resultString = "";  
        CloseableHttpResponse response = null;  
        List<Cookie> cookies =null;
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
            
             cookies = cookieStore.getCookies();
            
            for (int i = 0; i < cookies.size(); i++) {
                System.out.println("cookie值为"+cookies.get(i).getName()+":"+cookies.get(i).getValue());
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
        return cookies;  
    }  
}
