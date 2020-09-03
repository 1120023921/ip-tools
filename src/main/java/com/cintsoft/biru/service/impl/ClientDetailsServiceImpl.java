package com.cintsoft.biru.service.impl;

import com.cintsoft.biru.model.ClientDetails;
import com.cintsoft.biru.dao.ClientDetailsMapper;
import com.cintsoft.biru.service.ClientDetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * OAuth客户端信息 服务实现类
 * </p>
 *
 * @author 胡昊
 * @since 2020-09-03
 */
@Service
public class ClientDetailsServiceImpl extends ServiceImpl<ClientDetailsMapper, ClientDetails> implements ClientDetailsService {

}
