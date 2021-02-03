package com.example.springbootdemo.common.elasticsearch.sdk;


import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.InetAddress;
import java.util.Date;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;


public class ESUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(ESUtil.class);


    public static void main(String[] args) {
        try {
            TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));


            IndexResponse response = client.prepareIndex("twitter", "_doc", "1")
                    .setSource(jsonBuilder()
                            .startObject()
                            .field("user", "kimchy")
                            .field("postDate", new Date())
                            .field("message", "trying out Elasticsearch")
                            .endObject()
                    )
                    .get();

            // Index name
            String _index = response.getIndex();
            // Type name
            String _type = response.getType();
            // Document ID (generated or not)
            String _id = response.getId();
            // Version (if it's the first time you index this document, you will get: 1)
            long _version = response.getVersion();
            // status has stored current instance statement.
            RestStatus status = response.status();
            LOGGER.info(_index);
            LOGGER.info(_type);
            LOGGER.info(_id);
            LOGGER.info("{}",_version);
            LOGGER.info("{}", status);
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
