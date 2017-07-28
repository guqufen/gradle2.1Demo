package net.fnsco.core.utils;

/**
 * 示例说明
 * 
 * HelloOSS是OSS Java SDK的示例程序，您可以修改endpoint、accessKeyId、accessKeySecret、bucketName后直接运行。
 * 运行方法请参考README。
 * 
 * 本示例中的并不包括OSS Java SDK的所有功能，详细功能及使用方法，请参看“SDK手册 > Java-SDK”，
 * 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/preface.html?spm=5176.docoss/sdk/java-sdk/。
 * 
 * 调用OSS Java SDK的方法时，抛出异常表示有错误发生；没有抛出异常表示成功执行。
 * 当错误发生时，OSS Java SDK的方法会抛出异常，异常中包括错误码、错误信息，详细请参看“SDK手册 > Java-SDK > 异常处理”，
 * 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/exception.html?spm=5176.docoss/api-reference/error-response。
 * 
 * OSS控制台可以直观的看到您调用OSS Java SDK的结果，OSS控制台地址是：https://oss.console.aliyun.com/index#/。
 * OSS控制台使用方法请参看文档中心的“控制台用户指南”， 指南的来链接地址是：https://help.aliyun.com/document_detail/oss/getting-started/get-started.html?spm=5176.docoss/user_guide。
 * 
 * OSS的文档中心地址是：https://help.aliyun.com/document_detail/oss/user_guide/overview.html。
 * OSS Java SDK的文档地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/install.html?spm=5176.docoss/sdk/java-sdk。
 * 
 */

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
/**
 * @desc 文件上传OSS云服务器
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月4日 下午3:31:10
 *
 */
public class OssUtil {
    static Logger            logger          = LoggerFactory.getLogger(OssUtil.class);

    // endpoint是访问OSS的域名。如果您已经在OSS的控制台上 创建了Bucket，请在控制台上查看域名。
    // 如果您还没有创建Bucket，endpoint选择请参看文档中心的“开发人员指南 > 基本概念 > 访问域名”，
    // 链接地址是：https://help.aliyun.com/document_detail/oss/user_guide/oss_concept/endpoint.html?spm=5176.docoss/user_guide/endpoint_region
    // endpoint的格式形如“http://oss-cn-hangzhou.aliyuncs.com/”，注意http://后不带bucket名称，
    // 比如“http://bucket-name.oss-cn-hangzhou.aliyuncs.com”，是错误的endpoint，请去掉其中的“bucket-name”。
    private static String    endpoint        = "http://oss-cn-hangzhou.aliyuncs.com";

    // accessKeyId和accessKeySecret是OSS的访问密钥，您可以在控制台上创建和查看，
    // 创建和查看访问密钥的链接地址是：https://ak-console.aliyun.com/#/。
    // 注意：accessKeyId和accessKeySecret前后都没有空格，从控制台复制时请检查并去除多余的空格。
    private static String    accessKeyId     = "";
    private static String    accessKeySecret = "";
    private static String    headBucketName  = "";
    private static OSSClient ossClient;

    public static void initOssClient() {
        ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }
    // Bucket用来管理所存储Object的存储空间，详细描述请参看“开发人员指南 > 基本概念 > OSS基本概念介绍”。
    // Bucket命名规范如下：只能包括小写字母，数字和短横线（-），必须以小写字母或者数字开头，长度必须在3-63字节之间。
    //private static String bucketName      = "<YourBucketName>";

    // Object是OSS存储数据的基本单元，称为OSS的对象，也被称为OSS的文件。详细描述请参看“开发人员指南 > 基本概念 > OSS基本概念介绍”。
    // Object命名规范如下：使用UTF-8编码，长度必须在1-1023字节之间，不能以“/”或者“\”字符开头。
    //private static String firstKey        = "my-first-key";

    public static void getFile(String bucketName, String firstKey) {

        try {
            // 下载文件。详细请参看“SDK手册 > Java-SDK > 下载文件”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/download_object.html?spm=5176.docoss/sdk/java-sdk/manage_object
            OSSObject ossObject = ossClient.getObject(bucketName, firstKey);
            InputStream inputStream = ossObject.getObjectContent();
            StringBuilder objectContent = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                String line = reader.readLine();
                if (line == null)
                    break;
                objectContent.append(line);
            }
            inputStream.close();
            System.out.println("Object：" + firstKey + "的内容是：" + objectContent);
        } catch (OSSException oe) {
            oe.printStackTrace();
        } catch (ClientException ce) {
            ce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
    }
    /**
     * deleteFile:(这里用一句话描述这个方法的作用)删除文件
     *
     * @param bucketName
     * @param fileKey    设定文件
     * @return void    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public static void deleteFile(String bucketName, String fileKey) {
//        String fileKey = "README.md";
        // 删除Object。详细请参看“SDK手册 > Java-SDK > 管理文件”。
        // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_object.html?spm=5176.docoss/sdk/java-sdk/manage_bucket
//        ossClient.deleteObject(bucketName, firstKey);
//        System.out.println("删除Object：" + firstKey + "成功。");
        ossClient.deleteObject(bucketName, fileKey);
        logger.info("删除Object：" + fileKey + "成功。");
    }
    /**
     * uploadFile:(这里用一句话描述这个方法的作用) 上传文件
     *
     * @param filePath    设定文件
     * @return void    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public static void uploadFile(String filePath,String fileKey) {

        // 生成OSSClient，您可以指定一些参数，详见“SDK手册 > Java-SDK > 初始化”，
        // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/init.html?spm=5176.docoss/sdk/java-sdk/get-start
        try {
            // 判断Bucket是否存在。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
            if (ossClient.doesBucketExist(headBucketName)) {
                System.out.println("您已经创建Bucket：" + headBucketName + "。");
            } else {
                System.out.println("您的Bucket不存在，创建Bucket：" + headBucketName + "。");
                // 创建Bucket。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
                // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
                ossClient.createBucket(headBucketName);
            }
            // 文件存储入OSS，Object的名称为fileKey。详细请参看“SDK手册 > Java-SDK > 上传文件”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/upload_object.html?spm=5176.docoss/user_guide/upload_object
//            String fileKey = filePath.substring(filePath.lastIndexOf("/")+1);
            ossClient.putObject(headBucketName, fileKey, new File(filePath));
            logger.info("Object：" + fileKey + "存入OSS成功。");
          //  ossClient.shutdown();
        } catch (OSSException oe) {
            oe.printStackTrace();
        } catch (ClientException ce) {
            ce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        finally {
//            ossClient.shutdown();
//        }
        logger.info("Completed");
    }
    
    public static void main(String[] args) {
//        OssUtil.setEndpoint("http://oss-cn-hangzhou.aliyuncs.com");
//        OssUtil.setAccessKeyId("LTAI8t0z2dOlvLr6");
//        OssUtil.setAccessKeySecret("0jB12XLFoEM4UEauiN8ohp7dXSbE40");
//        OssUtil.setHeadBucketName("bigdata-fns-test");
//        initOssClient();
////        uploadFile("D:\\temp\\2017\\7\\1499145304175.jpg", "2017/1499145304175.jpg");
//        String url = getFileUrl(getHeadBucketName(),"2017/1499145304175.jpg");
//        String url = "http://bigdata-fns.oss-cn-hangzhou-internal.aliyuncs.com/2017/7/14993093838…SAccessKeyId=LTAI8t0z2dOlvLr6&Signature=3BJpI5ffB%2BZ1nrL%2BwS2oCLLZPDA%3D";
//        StringUtils.substringBetween(url, "ads");
//        System.out.println(url.replaceAll("-internal", ""));
    }
    public static void demo(String bucketName, String firstKey) {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        // 把字符串存入OSS，Object的名称为firstKey。详细请参看“SDK手册 > Java-SDK > 上传文件”。
        // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/upload_object.html?spm=5176.docoss/user_guide/upload_object
        InputStream is = new ByteArrayInputStream("Hello OSS".getBytes());
        ossClient.putObject(bucketName, firstKey, is);
        System.out.println("Object：" + firstKey + "存入OSS成功。");

        // 查看Bucket中的Object。详细请参看“SDK手册 > Java-SDK > 管理文件”。
        // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_object.html?spm=5176.docoss/sdk/java-sdk/manage_bucket
        ObjectListing objectListing = ossClient.listObjects(bucketName);
        List<OSSObjectSummary> objectSummary = objectListing.getObjectSummaries();
        System.out.println("您有以下Object：");
        for (OSSObjectSummary object : objectSummary) {
            System.out.println("\t" + object.getKey());
        }
    }
    /**
     * getFileUrl:(这里用一句话描述这个方法的作用)获取查看的URL
     *
     * @param bucketName
     * @param fileKey
     * @return    设定文件
     * @return String    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public static String getFileUrl(String bucketName,String fileKey) {
        // 过期时间8小时
        Date expiration = new Date(new Date().getTime() + 1000 * 60 * 60 * 8);
        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucketName, fileKey, HttpMethod.GET);
        req.setExpiration(expiration);
        URL signedUrl = ossClient.generatePresignedUrl(req);
        logger.info("获取图片地址" + signedUrl.toString());
        return signedUrl.toString().replaceAll("-internal", "");
    }
    /**
     * getForeverFileUrl:(这里用一句话描述这个方法的作用)获取有效期时间为一年的URL地址
     *
     * @param bucketName
     * @param fileKey
     * @return    设定文件
     * @return String    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public static String getForeverFileUrl(String bucketName,String fileKey){
     // 过期时间10年
        Date expiration = new Date(new Date().getTime() + 1000 * 60 * 60 * 24 * 365 * 10);
        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucketName, fileKey, HttpMethod.GET);
        req.setExpiration(expiration);
        URL signedUrl = ossClient.generatePresignedUrl(req);
        logger.info("获取图片地址" + signedUrl.toString());
        return signedUrl.toString().replaceAll("-internal", "");
    }
    
    /**
     * endpoint
     *
     * @param   endpoint    the endpoint to set
     * @since   CodingExample Ver 1.0
     */

    public static void setEndpoint(String endpoint) {
        OssUtil.endpoint = endpoint;
    }

    /**
     * headBucketName
     *
     * @param   headBucketName    the headBucketName to set
     * @since   CodingExample Ver 1.0
     */

    public static void setHeadBucketName(String headBucketName) {
        OssUtil.headBucketName = headBucketName;
    }
    /**
     * headBucketName
     *
     * @return  the headBucketName
     * @since   CodingExample Ver 1.0
    */
    
    public static String getHeadBucketName() {
        return OssUtil.headBucketName;
    }

    /**
     * accessKeyId
     *
     * @param   accessKeyId    the accessKeyId to set
     * @since   CodingExample Ver 1.0
     */

    public static void setAccessKeyId(String accessKeyId) {
        OssUtil.accessKeyId = accessKeyId;
    }

    /**
     * accessKeySecret
     *
     * @param   accessKeySecret    the accessKeySecret to set
     * @since   CodingExample Ver 1.0
     */

    public static void setAccessKeySecret(String accessKeySecret) {
        OssUtil.accessKeySecret = accessKeySecret;
    }

}
