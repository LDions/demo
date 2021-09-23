package com.hzy.mydemo.modules.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description: 阿里 发送短信的工具类
 * @author: hzy
 * @time: 2021/9/17 14:53
 */
public class AliSMSUtils {

    private static final String regionId = "cn-hangzhou";

    private static final String accessKeyId = "";

    private static final String accessKeySecret = "";

    /*
     *调用阿里云的sendSms接口，
     * 必传参数 phoneNumbers接收短信的手机号（支持对多个手机号发短信，用英文逗号分隔）
     * signName短信签名名称，在阿里云审核通过的签名
     * templateCode短信模板ID，阿里云审核通过的模板
     */
    public String sendMassage(String phoneNumbers, String signName, String templateCode) throws ClientException, JsonProcessingException {
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysVersion("2017-05-25");
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phoneNumbers); //接收短信的手机号码
        request.putQueryParameter("SignName", signName); //短信签名名称（在阿里云控制台签名管理页面查看签名【必须是已添加并且通过审核的短信签名】）
        request.putQueryParameter("TemplateCode", templateCode); //短信模板id
        request.putQueryParameter("TemplateParam", "\t{\"code\":\"1111\"}"); //短信模板变量对应的实际值，json格式

        CommonResponse response = client.getCommonResponse(request);
        ObjectMapper objectMapper = new ObjectMapper();
        //使用TypeReference可以明确的指定反序列化的类型 https://zhuanlan.zhihu.com/p/262271430
        Map resultMap = objectMapper.readValue(response.getData(), new TypeReference<Map>() {});
        return "ok";
    }
}
