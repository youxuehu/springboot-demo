package com.example.springbootdemo.common.guava.cache;

/**
 * @author youxuehu
 * @version v1.0
 * @className SlsConfig
 * @date 2021/5/8 10:26 下午
 * @desrription 这是类的描述信息
 */
public class SlsConfig {

    private String accessId;
    private String accessKey;
    private String endpoint;

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
