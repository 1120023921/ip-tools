package com.cintsoft.biru.service.impl;

import com.cintsoft.biru.model.Resource;
import com.cintsoft.biru.dao.ResourceMapper;
import com.cintsoft.biru.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资源信息 服务实现类
 * </p>
 *
 * @author 胡昊
 * @since 2020-09-03
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

}
