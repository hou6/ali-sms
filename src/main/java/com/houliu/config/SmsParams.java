package com.houliu.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author houliu
 * @created 2020-07-20  0:09
 */

@Data
@ConfigurationProperties(prefix = "ali.sms")
public class SmsParams {

    private String templateCode;
    private String SignName;
    private String regionld;
    private String accessKeyId;
    private String secret;
    private String domain;
    private String version;
    private String action;

}
