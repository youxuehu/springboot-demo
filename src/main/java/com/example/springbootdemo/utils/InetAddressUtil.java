package com.example.springbootdemo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(InetAddressUtil.class);

    public static String getLocalHost() {
        try {
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            return hostAddress;
        } catch (UnknownHostException e) {
            LOGGER.error("get local host address error", e);
        }
        return null;
    }
}
