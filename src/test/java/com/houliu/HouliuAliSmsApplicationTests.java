package com.houliu;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class HouliuAliSmsApplicationTests {

    @Test
    void contextLoads() {
        //连接阿里云
        DefaultProfile profile = DefaultProfile.getProfile("cn-xiangyang", "LTAI4GAa48jb1SboK89o31To", "TLbplAR8ZA8DBk9sbA8GsLP38hXpQl");
        IAcsClient client = new DefaultAcsClient(profile);

        //构建请求
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");  //不要动
        request.setVersion("2017-05-25");  //不要动
        request.setAction("SendSms");  //事件，不要 动

        //自定义的参数，（手机号，验证码，签名，模板）,不要写错字母，注意大小写
        request.putQueryParameter("PhoneNumbers", "18120273723");
        request.putQueryParameter("SignName", "houliu");
        request.putQueryParameter("TemplateCode", "SMS_196656977");

        //构建一个短信的验证码。，先写死
        Map<String, Object> map = new HashMap<>();
        map.put("code",1125);
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(map));

        try {
            //进行发送，得到响应
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
