package com.runmit.uc.core.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by gg on 2014/8/25.
 */
@Component("propertiesConfig")
@Data
public class PropertiesConfig {
    @Value("${memcached.ips}")
    private String ips;
}
