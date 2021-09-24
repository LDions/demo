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
    private static final int APPID = 1400576491;

    // 短信应用SDK AppKey
    private static final String APPKEY = "24ea7d02136ec70ecfbe26043c6a67a4";

    // 短信模板ID，需要在短信应用中申请
    //    private static final int TEMPLATECODE = 1131821;
    private static final int TEMPLATECODE = 1135010;

    // 签名
    private static final String SIGN = "艾东工作室";

    /**
     * 发送验证码
     *
     * @params: params数组元素个数和模板变量的个数一致（当前的是需要填写验证码和有效时间）
     * @phoneNum: 接收短信的电话号码
     */
    public static void sendSms(String[] params, String phoneNum) {
        try {
            SmsSingleSender ssender = new SmsSingleSender(APPID, APPKEY);
            ssender.sendWithParam("86", phoneNum, TEMPLATECODE, params, SIGN, "", "");
        } catch (Exception e) {
            throw new BadRequestAlertException("调用平台发送短信失败", "", "发送验证码失败");
        }
    }
    //    public static void main(String[] args) {
    //        String[] params = {(int) ((Math.random() * 9 + 1) * 100000) + "", "5"};
    //        System.out.println(params[0]);
    //        sendSms(params, "15044042528");
    //    }
}
