package com.houliu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.houliu.config.SmsParams;
import com.houliu.service.SendSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author houliu
 * @created 2020-07-17  0:24
 */

@Service
@EnableConfigurationProperties(SmsParams.class)
public class SendSmsServiceImpl implements SendSmsService {

//    private static final String templateCode = "SMS_196656977";
//    private static final String SignName = "houliu";
    @Autowired
    private SmsParams smsParams;

    @Override
    public boolean sendSms(String phoneNumber) {
        //连接阿里云
        DefaultProfile profile = DefaultProfile.getProfile(smsParams.getRegionld(), smsParams.getAccessKeyId(), smsParams.getSecret());
        IAcsClient client = new DefaultAcsClient(profile);

        //构建请求
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(smsParams.getDomain());  //不要动
        request.setVersion(smsParams.getVersion());  //不要动
        request.setAction(smsParams.getAction());  //事件，可以动

        //自定义的参数，（手机号，验证码，签名，模板）,不要写错字母，注意大小写
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("SignName", smsParams.getSignName()); //模板在实际工作中一般不会随便变动
        request.putQueryParameter("TemplateCode", smsParams.getTemplateCode());

        //构建一个短信的验证码
        Map<String, Object> map = new HashMap<>();
        map.put("code",UUID.randomUUID().toString().substring(0,6));
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(map));

        try {
            //进行发送，得到响应
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return response.getHttpResponse().isSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;

    }
}
