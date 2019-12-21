package com.example.springbootdemo.utils.http;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class HttpUtils {

    private static Logger LOG = LoggerFactory.getLogger(HttpUtils.class);

    public HttpResponseParam post4Para(HttpRequestParams req) {
        return post2Parameter(req, 5000, 5000);
    }

    public HttpResponseParam post2Parameter(HttpRequestParams req, int connectTimeout, int connectionRequestTimeout) {
        HttpResponseParam res = new HttpResponseParam();
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
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
}