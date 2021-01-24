package com.cintsoft.ace.business.provider.ip.service;

import com.cintsoft.ace.business.provider.ip.view.IPInfo;

public interface IPService {

    IPInfo getInfo(String ip, String state);
}
