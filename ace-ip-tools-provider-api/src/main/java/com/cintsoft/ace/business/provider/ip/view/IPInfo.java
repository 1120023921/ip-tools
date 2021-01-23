package com.cintsoft.ace.business.provider.ip.view;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class IPInfo {

    private String country;
    private String province;
    private String city;
    private String isp;
}
