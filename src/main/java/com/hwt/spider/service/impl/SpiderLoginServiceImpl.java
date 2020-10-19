package com.hwt.spider.service.impl;

import com.hwt.spider.component.TokenManager;
import com.hwt.spider.entity.param.CodeParam;
import com.hwt.spider.entity.param.LoginParam;
import com.hwt.spider.entity.pojo.SpiderUser;
import com.hwt.spider.exception.BusinessException;
import com.hwt.spider.exception.ErrorCode;
import com.hwt.spider.mapper.SpiderUserMapper;
import com.hwt.spider.service.SpiderLoginService;
import com.hwt.spider.spiderUtils.Code;
import com.hwt.spider.spiderUtils.sendMail;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
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
        String mail = loginParam.getMail();
        if (StringUtils.isEmpty(mail)){
            throw new BusinessException(ErrorCode.USER_MAIL_IS_EMPTY);
        }
        SpiderUser userByAccoutNumber = spiderUserMapper.getUserByMail(mail);
        if (userByAccoutNumber == null){
            throw new BusinessException(ErrorCode.USER_IS_NOT_EXIST);
        }
        String passWord = loginParam.getPassword();
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
        Long accoutNum = codeParam.getAccoutNum();
        String mail = codeParam.getMail();
        String code = Code.getVerficationCode();
        String key = "verficationCode_"+accoutNum;
        redisTemplate.boundValueOps(key).set(code, 5, TimeUnit.MINUTES);
        sendMail.sendCode(accoutNum, mail, code);
    }

    @Override
    public void register(LoginParam loginParam){
        String mail = loginParam.getMail();
        if (StringUtils.isEmpty(mail)){
            throw new BusinessException(ErrorCode.USER_MAIL_IS_EMPTY);
        }
        String userName = loginParam.getUsername();
        if (StringUtils.isEmpty(userName)){
            throw new BusinessException(ErrorCode.USER_USER_NAME_IS_EMPTY);
        }
        String password = loginParam.getPassword();
        if (StringUtils.isEmpty(password)){
            throw new BusinessException(ErrorCode.USER_PASS_WORD_IS_EMPTY);
        }
        String code = loginParam.getCode();
        if (StringUtils.isEmpty(code)){
            throw new BusinessException(ErrorCode.USER_VERFICATION_CODE_IS_EMPTY);
        }
        String key = "verficationCode_"+mail;
        String verficationCode = redisTemplate.boundValueOps(key).get();
        if (!verficationCode.equals(code)){
            throw new BusinessException(ErrorCode.USER_VERFICATION_CODE_IS_ERROR);
        }
        SpiderUser spiderUser = new SpiderUser();
        spiderUser.setUserName(userName);
        spiderUser.setMail(mail);
        spiderUser.setPassWord(password);
        spiderUser.setCreateTime(new Date());
        spiderUserMapper.insert(spiderUser);
    }

}
