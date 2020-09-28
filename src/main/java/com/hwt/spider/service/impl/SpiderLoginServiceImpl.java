package com.hwt.spider.service.impl;

import com.hwt.spider.component.TokenManager;
import com.hwt.spider.entity.param.LoginParam;
import com.hwt.spider.entity.pojo.SpiderUser;
import com.hwt.spider.exception.BusinessException;
import com.hwt.spider.exception.ErrorCode;
import com.hwt.spider.mapper.SpiderUserMapper;
import com.hwt.spider.service.SpiderLoginService;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author hwt
 * @Date:11:22 2020/9/28
 * @Description
 */
@Service
public class SpiderLoginServiceImpl implements SpiderLoginService {
    @Resource
    private SpiderUserMapper spiderUserMapper;
    @Resource
    private TokenManager tokenManager;
    @Override
    public String login(LoginParam loginParam) {
        Long accoutNumber = loginParam.getAccoutNumber();
        if (accoutNumber == null){
            throw new BusinessException(ErrorCode.USER_ACCOUT_NUMBER_IS_EMPTY);
        }
        SpiderUser userByAccoutNumber = spiderUserMapper.getUserByAccoutNumber(accoutNumber);
        if (userByAccoutNumber == null){
            throw new BusinessException(ErrorCode.USER_IS_NOT_EXIST);
        }
        String passWord = loginParam.getPassWord();
        if (passWord == null){
            throw new BusinessException(ErrorCode.USER_PASS_WORD_IS_EMPTY);
        }
        if (!userByAccoutNumber.getPassWord().equals(passWord)){
            throw new BusinessException(ErrorCode.USER_PASS_WORD_IS_ERROR);
        }
        String token = tokenManager.createToken(userByAccoutNumber.getId());
        return token;
    }
}
