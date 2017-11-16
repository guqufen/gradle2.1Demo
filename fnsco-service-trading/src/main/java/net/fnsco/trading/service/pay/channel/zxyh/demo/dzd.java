package net.fnsco.trading.service.pay.channel.zxyh.demo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;




/**
 * 中信银行杭州分行-移动收单平台 对账单接口
 * @author lilj
 * @version 1.0 20170512
 */
public class dzd {
	
	/**
	 * 默认的http连接超时时间
	 */
	private final static int DEFAULT_CONN_TIMEOUT = 120000;	//10s
	/**
	 * 默认的http read超时时间
	 */
	private final static int DEFAULT_READ_TIMEOUT = 120000;	//120s
	/**
	 * 请求的目标URL
	 * 配置在此处仅为演示方便，正式生产代码中，应该做外置可配置处理
	 */
	private static final String reqUrl = "https://120.27.165.177:8099/MerWeb/StatementQueryApi";//中信对账单下载api地址
	private static final String sPath = "d:\\citicdzd.zip";//下载对账单后本地存放的文件路径及文件名，请自行修改

	
	public static String request(Map<String, String> reqMap){
		
		//将Map转成Json
		Json2 reqJs = Json2.make(reqMap);
		//生成json字符串
		String reqStr = reqJs.toString();
		
		System.out.println("req :"+reqStr);
		//HTTP POST方式发送报文，并获取返回结果
		
		
		String respStr = postReq(reqUrl,reqStr);
		
		return respStr;	
	}
	
	
	/**
	 * http post,有返回String
	 * @param requrl
	 * @param req
	 * @param connTimeOut
	 * @param readTimeOut
	 * @return
	 */
	public static String postReq(String requrl,String req,int connTimeOut,int readTimeOut){
		try {
			HttpURLConnection conn = null;
			try {
				URL url = new URL(requrl);
				conn = (HttpURLConnection)url.openConnection();
				conn.setDoInput(true);
				conn.setDoOutput(true);	//POST
				conn.setRequestMethod("POST");
				conn.setUseCaches(false);
				conn.setConnectTimeout(connTimeOut);
				conn.setReadTimeout(readTimeOut);
				conn.connect();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(),"utf-8");
			out.write(req);
			out.flush();
			out.close();
			
			int respCode=conn.getResponseCode();
			
			System.out.println("respCode:"+respCode);
			if (respCode==200){

				BufferedInputStream in=new BufferedInputStream(conn.getInputStream());
				
		        try {
		         File f = new File(sPath);
		         if (f.exists()) {
		          System.out.println("文件存在");
		         } else {
		          System.out.println("文件不存在，正在创建...");
		          if (f.createNewFile()) {
		           System.out.println("文件创建成功！");
		          } else {
		           System.out.println("文件创建失败！");
		          }
		         }

	                File outFile=new File(sPath);
	                if(!outFile.exists()){
	                    outFile.createNewFile();
	                }
	                BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(new FileOutputStream(sPath));
	                byte[] line=new byte[200];
	                while(true){
	                    int len=in.read(line);
	                    if(len==-1){
	                        break;
	                    }
	                    bufferedOutputStream.write(line,0,len);
	                }
	                bufferedOutputStream.close();

		         return sPath;
		        } catch (Exception e) {
		         e.printStackTrace();
		         return null;
		        }

				
			}else {

				
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
				StringBuilder sb = new StringBuilder();
				char[] buff = new char[2048];
				int cnt = 0;
				while((cnt = in.read(buff))!=-1)
					sb.append(buff,0,cnt);
				in.close();
				String rtStr = sb.toString();
				return rtStr;
			}
		} catch (IOException e) {
			System.out.println(e);
			throw new RuntimeException(e);
		}
	}
	/**
	 * 标准http post,有返回String
	 * @param requrl
	 * @param req
	 * @return
	 */
	public static String postReq(String requrl,String req){
		return postReq(requrl, req,DEFAULT_CONN_TIMEOUT,DEFAULT_READ_TIMEOUT);
	}
	
	
	public static void main(String[] args) {
		//构建演示用报文！！！注意，此为演示用报文，请勿用于生产！！！
		Map<String, String> reqMap = new HashMap<String, String>();
		reqMap.put("userName", "");	 //商户号
		reqMap.put("userPwd", ""); //对账单密钥
		reqMap.put("date", "");//对账单日期
		reqMap.put("statementType", "0");//对账单类型
		//发送获取对账单报文
		String respStr = request(reqMap);
		
		//解析中信返回报文
		System.out.println("resp:"+respStr);
		
		//保存文件
		
	}
}
