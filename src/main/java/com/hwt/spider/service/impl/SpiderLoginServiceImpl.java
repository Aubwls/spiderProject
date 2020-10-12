package com.hwt.spider.service.impl;

import com.hwt.spider.component.TokenManager;
import com.hwt.spider.entity.param.CodeParam;
import com.hwt.spider.entity.param.LoginParam;
import com.hwt.spider.entity.pojo.SpiderUser;
import com.hwt.spider.exception.BusinessException;
import com.hwt.spider.exception.ErrorCode;
import com.hwt.spider.mapper.SpiderUserMapper;
import com.hwt.spider.service.SpiderLoginService;
import com.hwt.spider.spiderUtils.VerficationCode;
import com.hwt.spider.spiderUtils.sendMail;
import com.sun.org.apache.bcel.internal.classfile.Code;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

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

    @Resource
    private RedisTemplate<String, String> redisTemplate;
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

    @Override
    public void getCode(CodeParam codeParam) {
        String accoutNum = codeParam.getAccoutNum();
        String mail = codeParam.getMail();
        String code = VerficationCode.getCode();
        String key = "verficationCode_"+accoutNum;
        redisTemplate.boundValueOps(key).set(code, 5, TimeUnit.MINUTES);
        sendMail.sendCode(accoutNum, mail, code);
    }
}
