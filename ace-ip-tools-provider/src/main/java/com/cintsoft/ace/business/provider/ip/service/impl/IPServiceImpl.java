package com.cintsoft.ace.business.provider.ip.service.impl;

import com.cintsoft.ace.business.provider.ip.service.IPService;
import com.cintsoft.ace.business.provider.ip.view.IPInfo;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbSearcher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Method;

@Service
public class IPServiceImpl implements IPService {

    @Resource
    private DbSearcher dbSearcher;

    @Override
    public IPInfo getInfo(String ip) {
        try {
            final Method method = dbSearcher.getClass().getMethod("btreeSearch", String.class);
            final DataBlock dataBlock = (DataBlock) method.invoke(dbSearcher, ip);
            if (dataBlock != null) {
                final IPInfo ipInfo = new IPInfo();
                final String[] region = dataBlock.getRegion().split("\\|");
                ipInfo.setCountry(region[0]);
                ipInfo.setProvince(region[2]);
                ipInfo.setCity(region[3]);
                ipInfo.setIsp(region[4]);
                return ipInfo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
