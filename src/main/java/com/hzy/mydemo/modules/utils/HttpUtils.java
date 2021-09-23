package com.hzy.mydemo.modules.utils;

import cn.hutool.http.HttpUtil;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/*
 *调用第三方接口工具类(通过JDK网络类Java.net.HttpURLConnection 比较原始的一种调用做法)
 */
public class HttpUtils {

    protected static final int SOCKET_TIMEOUT = 10000; // 10S
    protected static final String GET = "GET";
    protected static final String POST = "POST";

    public static String post(String host, String body) {
        StringBuilder result = new StringBuilder();
        try {
            // 设置SSLContext  表示安全套接字协议实现
            SSLContext sslcontext = SSLContext.getInstance("TLS");
            //初始化此上下文。 前两个参数中的任何一个都可以为null，在这种情况下，将搜索已安装的安全提供程序以寻找适当工厂的最高优先级实现。 同样，安全随机参数可以为空，在这种情况下将使用默认实现
            sslcontext.init(null, new TrustManager[] { myX509TrustManager }, null);
            //在HTTPS的证书未经权威机构认证的情况下，访问HTTPS站点的两种方法，一种方法是把该证书导入到Java的TrustStore文件中，
            // 另一种是自己实现并覆盖JSSE缺省的证书信任管理器类。两种方法各有优缺点，第一种方法不会影响JSSE的安全性，但需要手工导入证书；第二种方法虽然不用手工导入证书，但需要小心使用，否则会带来一些安全隐患。

            URL uri = new URL(host); // 创建URL对象
            //打开和url之间的连接
            HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
            if (conn instanceof HttpsURLConnection) {
                /*
                 *类HttpsURLConnection似乎并没有提供方法设置信任管理器。
                 * 其实，HttpsURLConnection通过SSLSocket来建立与HTTPS的安全连接，SSLSocket对象是由SSLSocketFactory生成的。
                 * HttpsURLConnection提供了方法setSSLSocketFactory(SSLSocketFactory)设置它使用的SSLSocketFactory对象。
                 * SSLSocketFactory通过SSLContext对象来获得，在初始化SSLContext对象时，可指定信任管理器对象
                 */
                //从上述SSLContext对象中得到SSLSocketFactory对象
                ((HttpsURLConnection) conn).setSSLSocketFactory(sslcontext.getSocketFactory());
                //这样，HttpsURLConnection对象就可以正常连接HTTPS了，无论其证书是否经权威机构的验证，只要实现了接口X509TrustManager的类MyX509TrustManager信任该证书。
            }
            //-----------------------------------------------------------------------------
            conn.setConnectTimeout(SOCKET_TIMEOUT); // 设置相应超时
            conn.setRequestMethod(POST);
            //设置是否向httpUrlConnection输出 发送post请求必须设置这个
            conn.setDoOutput(true);
            // 设置是否从httpUrlConnection读入 发送post请求必须设置这个
            conn.setDoInput(true);
            // 设置是否使用缓存
            conn.setUseCaches(false);
            //设置通用的请求属性 设置参数类型是json格式
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.connect(); //连接

            //-------------调用第三方接口------------------
            //获取URLConnection对象对应的输出流
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), StandardCharsets.UTF_8));
            //发送请求参数即数据
            writer.write(body);
            writer.close();

            //-----------下面的代码相当于，获取调用第三方http接口后返回的结果----------------------------
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                //定义 BufferedReader输入流来读取URL的响应 构造一个字符流缓存
                InputStream inputStream = conn.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = in.readLine()) != null) {
                    result.append(line);
                }
                inputStream.close();
            }
            //断开连接，disconnect是在底层tcp socket链接空闲时才切断，如果正在被其他线程使用就不切断。
            //            conn.disconnect();
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(host + " 网站连接出错。");
        }
    }

    //    public static void main(String[] args) throws Exception {
    //        Map sandboxMap = new HashMap();
    //        sandboxMap.put("mch_id", "1558399921");
    //        sandboxMap.put("nonce_str", WXPayUtil.generateNonceStr());
    //        String body = WXPayUtil.generateSignedXml(sandboxMap, "Tq2VbtZn5URFbxTDIzY1KwYMXqserJIw");
    //        System.out.println(body);
    //        String s = HttpUtil.post("https://api.mch.weixin.qq.com/sandboxnew/pay/getsignkey", body);
    //        System.out.println(s);
    //    }

    public static String postBody(Map<String, String> resMap) {
        String result = "";
        if (resMap == null) return result;
        for (Map.Entry<String, String> entry : resMap.entrySet()) {
            result = result + "\"" + entry.getKey() + "\"" + ":" + "\"" + entry.getValue() + "\"" + ",";
        }
        return "{" + result.substring(0, result.length() - 1) + "}";
    }

    public static String get(String host, Map<String, String> params) {
        try {
            // 设置SSLContext
            SSLContext sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(null, new TrustManager[] { myX509TrustManager }, null);
            String sendUrl = getUrlWithQueryString(host, params);
            //System.out.println("URL:" + sendUrl);
            URL uri = new URL(sendUrl); // 创建URL对象
            HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
            if (conn instanceof HttpsURLConnection) {
                ((HttpsURLConnection) conn).setSSLSocketFactory(sslcontext.getSocketFactory());
            }

            conn.setConnectTimeout(SOCKET_TIMEOUT); // 设置相应超时
            conn.setRequestMethod(GET);
            int statusCode = conn.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException(host + " 网站连接出错。");
            }

            // 读取服务器的数据
            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }

            String text = builder.toString();

            close(br); // 关闭数据流
            close(is); // 关闭数据流
            conn.disconnect(); // 断开连接

            return text;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(host + " 网站连接出错。");
        }
    }

    public static String getUrlWithQueryString(String url, Map<String, String> params) {
        if (params == null) {
            return url;
        }

        StringBuilder builder = new StringBuilder(url);
        if (url.contains("?")) {
            builder.append("&");
        } else {
            builder.append("?");
        }

        int i = 0;
        for (String key : params.keySet()) {
            String value = params.get(key);
            if (value == null) { // 过滤空的key
                continue;
            }

            if (i != 0) {
                builder.append('&');
            }

            builder.append(key);
            builder.append('=');
            builder.append(encode(value));

            i++;
        }

        return builder.toString();
    }

    protected static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 对输入的字符串进行URL编码, 即转换为%20这种形式
     *
     * @param input 原文
     * @return URL编码. 如果编码失败, 则返回原文
     */
    public static String encode(String input) {
        if (input == null) {
            return "";
        }

        try {
            return URLEncoder.encode(input, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return input;
    }

    //https://www.cnblogs.com/gyadmin/p/8084883.html
    private static TrustManager myX509TrustManager = new X509TrustManager() {
        //返回受信任的X509证书数组
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        /*
         *该方法检查服务器的证书，若不信任该证书同样抛出异常。通过自己实现该方法，可以使之信任我们指定的任何证书。在实现该方法时，也可以简单的不做任何处理，即一个空的函数体，由于不会抛出异常，它就会信任任何证书
         */
        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}

        /*
         *该方法检查客户端的证书，若不信任该证书则抛出异常。由于我们不需要对客户端进行认证，因此我们只需要执行默认的信任管理器的这个方法。JSSE中，默认的信任管理器类为TrustManager
         */
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
    };
}
