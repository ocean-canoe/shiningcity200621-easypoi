package com.shiningcity.testHttpClient;

import java.io.ByteArrayInputStream;

//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.security.KeyManagementException;
//import java.security.KeyStoreException;
//import java.security.NoSuchAlgorithmException;
//import java.security.cert.CertificateException;
//import java.security.cert.X509Certificate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import javax.net.ssl.HostnameVerifier;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.X509TrustManager;
//import org.apache.http.NameValuePair;
//import org.apache.http.ParseException;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.methods.HttpUriRequest;
//import org.apache.http.client.utils.URIBuilder;
//import org.apache.http.config.Registry;
//import org.apache.http.config.RegistryBuilder;
//import org.apache.http.conn.socket.ConnectionSocketFactory;
//import org.apache.http.conn.socket.PlainConnectionSocketFactory;
//import org.apache.http.conn.ssl.NoopHostnameVerifier;
//import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
//import org.apache.http.entity.ContentType;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.entity.mime.MultipartEntityBuilder;
//import org.apache.http.entity.mime.content.StringBody;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.ssl.SSLContextBuilder;
//import org.apache.http.ssl.TrustStrategy;
//import org.apache.http.util.EntityUtils;
//import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;

import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

public class HttpClientUtil {
    
    // 连接池，默认支持http和https
    // 大家都知道http连接是基于tcp的，而tcp创建连接需要三次握手，断开连接四次挥手，如果我们不使用连接池，那么每发出一个请求，就需要三次握手和四次挥手，而三次握手和四次挥手都是耗资源的操作。
    // HttpClients.createDefault();会从默认连接池中获取链接，不会从connectionManager中获取链接。
    private static PoolingHttpClientConnectionManager connectionManager;
    // 从连接池获取连接的超时时间
    private static Integer connectionRequestTimeout = 10000;
    // 客户端和服务器建立连接的超时时间，超时后会ConnectionTimeOutException
    private static Integer connectTimeout = 10000;
    // 客户端从服务器读取数据的超时时间，超出后会抛出SocketTimeOutException
    private static Integer socketTimeout = 180000;
    // 连接池最大连接数，默认20
    private static Integer maxTotal = 100;
    // 连接池每个主机地址的并发数（每路由最大连接数，默认值是2）
    private static Integer defaultMaxPerRoute = 50;
    // 请求设置参数
    private static RequestConfig requestConfig = RequestConfig.custom()
            //.setCookieSpec(CookieSpecs.STANDARD_STRICT)
            //.setExpectContinueEnabled(Boolean.TRUE)
            //.setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM,AuthSchemes.DIGEST))
            //.setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
            //.setProxy(new HttpHost("127.0.0.1",8090))
            .setConnectionRequestTimeout(connectionRequestTimeout)
            .setConnectTimeout(connectTimeout)
            .setSocketTimeout(socketTimeout)
            .build();

//  // 如果交由Spring管理，可以使用 @PostConstruct 代替 static{}
//  @PostConstruct
//  private void init() {}

//    // 方案一：创建连接池，默认支持http和https
//    static {
//        connectionManager = new PoolingHttpClientConnectionManager();
//        connectionManager.setMaxTotal(maxTotal);
//        connectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
//    }
//    
//    // 方案二：创建连接池，请求https请求时，取消检测SSL
//    static {
//        try {
//            // 在调用SSL之前需要重写验证方法，取消检测SSL
//            X509TrustManager trustManager = new X509TrustManager() {
//                @Override 
//                public X509Certificate[] getAcceptedIssuers() {
//                    return null;
//                }
//                @Override
//                public void checkClientTrusted(X509Certificate[] xcs, String str) {}
//                @Override 
//                public void checkServerTrusted(X509Certificate[] xcs, String str) {}
//            };
//            SSLContext sslContext = SSLContext.getInstance(SSLConnectionSocketFactory.TLS);
//            sslContext.init(null, new TrustManager[] { trustManager }, null);
//            connectionManager = createPoolingHttpClientConnectionManager(sslContext);
//            connectionManager.setMaxTotal(maxTotal);
//            connectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
//        } catch (KeyManagementException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//    }

    // 方案三：创建连接池，请求https请求时，信任所有证书
    static {
        try {
            //使用 loadTrustMaterial() 方法实现一个信任策略，信任所有证书
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                // 信任所有
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            connectionManager = createPoolingHttpClientConnectionManager(sslContext);
            connectionManager.setMaxTotal(maxTotal);
            connectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
    }

    private static PoolingHttpClientConnectionManager createPoolingHttpClientConnectionManager(SSLContext sslContext) {
        //NoopHostnameVerifier类:  作为主机名验证工具，实质上关闭了主机名验证，它接受任何有效的SSL会话并匹配到目标主机。
        HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
        // 创建Registry
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https",socketFactory)
                .build();
        
        // 创建ConnectionManager，添加Connection配置信息
        return new PoolingHttpClientConnectionManager(socketFactoryRegistry);
    }
    
    // 建立连接
    private static CloseableHttpClient getCloseableHttpClient() {
        return HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManagerShared(true)
                .build();
    }
    
    // 构建get请求的请求链接
    private static URI buildUri(String url,Map<String, String> getParam) throws URISyntaxException {
        URIBuilder builder = new URIBuilder(url);
        for (String key : getParam.keySet()) {
            builder.addParameter(key, getParam.get(key));
        }
        return builder.build();
    }

    // 创建get请求HttpGet
    private static HttpGet createHttpGet(String url,Map<String, String> getParam) throws URISyntaxException {
        HttpGet httpGet = null;
        if (getParam != null) {
            httpGet = new HttpGet(buildUri(url,getParam));
        } else {
            httpGet = new HttpGet(url);
        }
        return httpGet;
    }

    // 创建post请求HttpPost
    private static HttpPost createHttpPost(String url,Map<String, String> getParam) throws URISyntaxException {
        HttpPost httpPost = null;
        if (getParam != null) {
            // 创建Http Post请求
            httpPost = new HttpPost(buildUri(url,getParam));
        } else {
            httpPost = new HttpPost(url);
        }
        return httpPost;
    }
    
    // 设置get请求 请求头
    private static HttpGet setHeaderMap(HttpGet httpGet,Map<String, String> headerMap) {
        // 设置请求头
        //httpGet.setHeader("Authorization", "Bearer "+ token);
        if (null != headerMap) {
            for (String key : headerMap.keySet()) {
                httpGet.setHeader(key,headerMap.get(key));
            }
        }
        return httpGet;
    }

    // 设置post请求 请求头
    private static HttpPost setHeaderMap(HttpPost httpPost,Map<String, String> headerMap) {
        // 设置请求头
        //httpPost.addHeader("Content-type", "application/json; charset=utf-8");
        //httpPost.setHeader("Accept", "application/json");
        //httpPost.setHeader("Authorization", "Bearer "+ token);
        if (null != headerMap) {
            for (String key : headerMap.keySet()) {
                httpPost.setHeader(key,headerMap.get(key));
            }
        }
        return httpPost;
    }

    // 设置post请求 表单参数
    private static HttpPost setPostParamByFormUrlencoded(HttpPost httpPost,Map<String, String> postParam) throws UnsupportedEncodingException {
        if (postParam != null) {
            List<NameValuePair> paramList = new ArrayList<>();
            for (String key : postParam.keySet()) {
                paramList.add(new BasicNameValuePair(key, postParam.get(key)));
            }
            // 模拟表单
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                entity.setContentType("application/x-www-form-urlencoded;charset=UTF-8");
                entity.setContentEncoding("utf-8");
            httpPost.setEntity(entity);
        }
        return httpPost;
    }

    // 设置post请求表单参数
    private static HttpPost setPostParamByFormData(HttpPost httpPost,Map<String, String> postParam, Map<String, File> fileParam,Map<String, MultipartFile> multipartFileParam) throws IOException {
        // 请求参数
        //MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        MultipartEntityBuilder builder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.RFC6532 );
        ContentType contentType = ContentType.create("multipart/form-data","UTF-8");
        // 普通参数
        if (postParam != null) {
            for (String key : postParam.keySet()) {
                //builder.addPart(new FormBodyPart(key, new StringBody(paramMap.get(key), contentType))); // 已过时
                //builder.addTextBody(key, paramMap.get(key), contentType);  // 同addPart
                builder.addPart(key, new StringBody(postParam.get(key), contentType));
            }
        }
        // 文件参数
        if (fileParam != null) {
            for (String key : fileParam.keySet()) {
                // 方法一，管用，但会在jar包目录生成临时文件
//                long length = fileParam.get(key).length();
//                if (length < Integer.MAX_VALUE) {
//                    byte[] bytes = new byte[(int) length];
//                    FileInputStream fileInputStream = new FileInputStream(fileParam.get(key));
//                    fileInputStream.read(bytes);
//                    builder.addBinaryBody(key, bytes, ContentType.DEFAULT_BINARY, fileParam.get(key).getName());
//                }
                // 方法二，管用
//                long length = fileParam.get(key).length();
//                if (length < Integer.MAX_VALUE) {
//                    byte[] bytes = new byte[(int) length];
//                    FileInputStream fileInputStream = new FileInputStream(fileParam.get(key));
//                    fileInputStream.read(bytes);
//                    ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
//                    builder.addPart(key, new InputStreamBody(bis, fileParam.get(key).getName()));
//                }
                // 方法三，管用
                //builder.addPart(key, new FileBody(fileParam.get(key), ContentType.DEFAULT_BINARY, fileParam.get(key).getName()));
                //builder.addPart(key, new FileBody(fileParam.get(key), contentType, fileParam.get(key).getName()));
                // 方法四，管用
                //builder.addBinaryBody(key, new FileInputStream(fileParam.get(key)), ContentType.DEFAULT_BINARY, fileParam.get(key).getName());
                builder.addBinaryBody(key, new FileInputStream(fileParam.get(key)), contentType, fileParam.get(key).getName());
                // 方法五，管用
                //builder.addBinaryBody(key, fileParam.get(key), ContentType.DEFAULT_BINARY, fileParam.get(key).getName());
                //builder.addBinaryBody(key, fileParam.get(key), contentType, fileParam.get(key).getName());
            }
        }
        // 文件参数
        if (multipartFileParam != null) {
            for (String key : multipartFileParam.keySet()) {
                //builder.addBinaryBody(key, multipartFileParam.get(key).getInputStream(), ContentType.DEFAULT_BINARY, multipartFileParam.get(key).getName());
                builder.addBinaryBody(key, multipartFileParam.get(key).getInputStream(), contentType, multipartFileParam.get(key).getName());
            }
        }
        httpPost.setEntity(builder.build());
        return httpPost;
    }

    // 设置post请求 请求体 JsonBody
    private static HttpPost setPostParamByJson(HttpPost httpPost,String jsonBody) throws UnsupportedEncodingException   {
        if (jsonBody != null) {
            // 创建请求内容
            StringEntity entity = new StringEntity(jsonBody);
                entity.setContentType("application/json;charset=UTF-8");
                entity.setContentEncoding("utf-8");
            httpPost.setEntity(entity);
        }
        return httpPost;
    }

    //执行请求
    private static String httpClientExecute(HttpUriRequest httpUriRequest) {
        // 创建httpClient
        CloseableHttpClient httpClient = getCloseableHttpClient();
        //CloseableHttpClient httpClient = HttpClients.createDefault(); // 使用HttpClient默认连接池
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpUriRequest);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(),"UTF-8");
            }
        } catch (ClientProtocolException e1) {
            e1.printStackTrace();
        } catch (ParseException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }
    
    //执行带参数的get请求
    public static String doGet(String url, Map<String, String> headerMap, Map<String, String> getParam) {
        HttpGet httpGet = null;
        String resultString = "";
        try {
            httpGet = setHeaderMap(createHttpGet(url, getParam), headerMap);
            resultString = httpClientExecute(httpGet);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return resultString;
    }
    
    //执行不带参数的get请求
    public static String doGet(String url, Map<String, String> headerMap) {
        return doGet(url,headerMap, null);
    }

    //执行带参数的post请求
    public static String doPost(String url, Map<String, String> headerMap, Map<String, String> getParam, Map<String, String> postParam) {
        HttpPost httpPost = null;
        String resultString = "";
        try {
            httpPost = setPostParamByFormUrlencoded(setHeaderMap(createHttpPost(url, getParam), headerMap), postParam);
            resultString = httpClientExecute(httpPost);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return resultString;
    }
    
    //执行带参数的post请求
    public static String doPost(String url, Map<String, String> headerMap, Map<String, String> postParam) {
        return doPost(url,headerMap,null,postParam);
    }
    
    //执行不带参数的post请求
    public static String doPost(String url, Map<String, String> headerMap) {
        return doPost(url,headerMap,null);
    }
    
    //执行参数为json的post请求
    public static String doPostJson(String url, Map<String, String> headerMap, Map<String, String> getParam, String jsonBody) {
        HttpPost httpPost = null;
        String resultString = "";
        try {
            httpPost = setPostParamByJson(setHeaderMap(createHttpPost(url, getParam), headerMap), jsonBody);
            resultString = httpClientExecute(httpPost);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return resultString;
    }

    public static String doPostJson(String url, Map<String, String> headerMap, String jsonBody) {
        return doPostJson(url, headerMap, null, jsonBody);
    }
    
    // File jasperFile = ResourceUtils.getFile("classpath:jasperreport/provinceList2.jasper");
    // 执行带参数的post请求，上传File文件
    private static String doPostFile(String url, Map<String, String> headerMap, Map<String, String> getParam, Map<String, String> postParam, Map<String, File> fileParam,Map<String, MultipartFile> multipartFileParam) {
        HttpPost httpPost = null;
        String resultString = "";
        try {
            httpPost = setPostParamByFormData(setHeaderMap(createHttpPost(url, getParam), headerMap),postParam,fileParam,multipartFileParam);
            resultString = httpClientExecute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return resultString;
    }
    
    // 执行带参数的post请求，上传File文件
    public static String doPostFileWithUrlParam(String url, Map<String, String> headerMap, Map<String, String> getParam, Map<String, String> postParam, Map<String, File> fileParam) {
        return doPostFile(url, headerMap, getParam, postParam, fileParam, null);
    }

    // 执行带参数的post请求，上传File文件
    public static String doPostFileWithUrlParam(String url, Map<String, String> headerMap, Map<String, String> getParam, Map<String, File> fileParam) {
        return doPostFile(url, headerMap, getParam, null, fileParam, null);
    }

    // 执行带参数的post请求，上传File文件
    public static String doPostFile(String url, Map<String, String> headerMap, Map<String, String> postParam, Map<String, File> fileParam) {
        return doPostFile(url, headerMap, null, postParam, fileParam, null);
    }

    // 执行带参数的post请求，上传File文件
    public static String doPostFile(String url, Map<String, String> headerMap, Map<String, File> fileParam) {
        return doPostFile(url, headerMap, null, null, fileParam, null);
    }

    // 执行带参数的post请求，上传File文件
    public static String doPostMultipartFileWithUrlParam(String url, Map<String, String> headerMap, Map<String, String> getParam, Map<String, String> postParam, Map<String, MultipartFile> multipartFileParam) {
        return doPostFile(url, headerMap, getParam, postParam, null, multipartFileParam);
    }

    // 执行带参数的post请求，上传File文件
    public static String doPostMultipartFileWithUrlParam(String url, Map<String, String> headerMap, Map<String, String> getParam, Map<String, MultipartFile> multipartFileParam) {
        return doPostFile(url, headerMap, getParam, null, null, multipartFileParam);
    }

    // 执行带参数的post请求，上传File文件
    public static String doPostMultipartFile(String url, Map<String, String> headerMap, Map<String, String> postParam, Map<String, MultipartFile> multipartFileParam) {
        return doPostFile(url, headerMap, null, postParam, null, multipartFileParam);
    }

    // 执行带参数的post请求，上传File文件
    public static String doPostMultipartFile(String url, Map<String, String> headerMap, Map<String, MultipartFile> multipartFileParam) {
        return doPostFile(url, headerMap, null, null, null, multipartFileParam);
    }

}
