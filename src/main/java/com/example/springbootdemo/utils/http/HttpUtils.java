package com.example.springbootdemo.utils.http;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.Map;

public class HttpUtils {

    private static Logger LOG = LoggerFactory.getLogger(HttpUtils.class);

    public HttpResponseParam post4Para(HttpRequestParams req) {
        return post2Parameter(req, 5000, 5000);
    }

    public HttpResponseParam post2Parameter(HttpRequestParams req, int connectTimeout, int connectionRequestTimeout) {
        HttpResponseParam res = new HttpResponseParam();
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            //http
            HttpClient h = HttpClients.createDefault();
            //https
            HttpClient h2 = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
            URIBuilder builder = new URIBuilder(req.getUrl());
            Map<String, String> bodys = req.getParams();
            if (bodys != null && bodys.size()  > 0) {
                bodys.forEach((k,v) -> {
                    builder.setParameter(k, v);
                });
            }
            HttpPost httpPost = new HttpPost(builder.build());
            httpPost.setConfig(RequestConfig.custom()
                    .setConnectTimeout(connectTimeout)   //设置连接超时时间
                    .setConnectionRequestTimeout(connectionRequestTimeout) // 设置请求超时时间
                    .setSocketTimeout(5000)
                    .setRedirectsEnabled(true)//默认允许自动重定向
                    .build());

            Map<String, String> heads = req.getHeads();
            if (heads != null && heads.size()  > 0) {
                heads.forEach((k,v) -> {
                    httpPost.setHeader(k, v);
                });
            }
            HttpResponse httpResponse = httpClient.execute(httpPost);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if(HttpStatus.SC_OK != statusCode) {
                throw new HttpException("http请求失败：" + statusCode);
            }
            res.setStatusCode(statusCode);
            res.setBody(EntityUtils.toString(httpResponse.getEntity()));
            LOG.info("API请求: resquest:[{}], response:[{}]", req.toString(), res.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void doGet(String url) throws Exception {
        HttpClient httpClient;
        if (url.startsWith("https")) {
            httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
        } else {
            httpClient = HttpClients.createDefault();
        }
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        InputStream inputStream = entity.getContent();
        List<String> lines = IOUtils.readLines(inputStream);
        for (String line : lines) {
            System.out.println(line);
        }
    }


    public static void main(String[] args) {
        try {
            doGet("https://www.baidu.com");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
