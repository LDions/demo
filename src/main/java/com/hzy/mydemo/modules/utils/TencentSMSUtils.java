package com.hzy.mydemo.modules.utils;

import com.github.qcloudsms.SmsSingleSender;
import com.hzy.mydemo.modules.common.exception.BadRequestAlertException;

/**
 * @description: 腾讯 短信服务工具类
 * @author: hzy
 * @time: 2021/9/22 14:05
 */
public class TencentSMSUtils {

    // 短信应用SDK AppID
    private static final int APPID = 0;

    // 短信应用SDK AppKey
    private static final String APPKEY = "";

    // 短信模板ID，需要在短信应用中申请
    private static final int TEMPLATECODE = 0;

    // 签名
    private static final String SIGN = "";

    //发送验证码
    public static void sendSms(String[] params, String phoneNum) {
        try {
            SmsSingleSender ssender = new SmsSingleSender(APPID, APPKEY);
            ssender.sendWithParam("86", phoneNum, TEMPLATECODE, params, SIGN, "", "");
        } catch (Exception e) {
            throw new BadRequestAlertException("调用平台发送短信失败", "", "发送验证码失败");
        }
    }
}
