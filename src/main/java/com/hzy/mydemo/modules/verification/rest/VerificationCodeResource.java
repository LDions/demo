package com.hzy.mydemo.modules.verification.rest;

import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.RandomUtil;
import com.hzy.mydemo.config.Constants;
import com.hzy.mydemo.modules.utils.TencentSMSUtils;
import com.hzy.mydemo.modules.verification.domain.VerificationCode;
import com.hzy.mydemo.modules.verification.repository.VerificationCodeRepository;
import com.hzy.mydemo.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.time.Instant;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Transactional
@Api(tags = Constants.VERIFICATION)
public class VerificationCodeResource {

    private final VerificationCodeRepository verificationCodeRepository;
    private final RedisUtil redisUtil;

    public VerificationCodeResource(VerificationCodeRepository verificationCodeRepository, RedisUtil redisUtil) {
        this.verificationCodeRepository = verificationCodeRepository;
        this.redisUtil = redisUtil;
    }

    @PostMapping("/verification")
    @ApiOperation(value = "获取验证码")
    public ResponseEntity<String> getVerificationCode(@RequestParam(required = true) String phone) {
        boolean isPhone = Validator.isMobile(phone);
        if (!isPhone) {
            throw new ValidateException("请输入正确的手机号");
        }
        String verificateCode = RandomUtil.randomNumbers(6);
        String[] params = { verificateCode, "5" };
        TencentSMSUtils.sendSms(params, phone);
        Optional<VerificationCode> verification = verificationCodeRepository.findFirstByPhone(phone);
        VerificationCode verificationCode;
        if (verification.isPresent()) {
            verificationCode = verification.get();
            verificationCode.setCode(verificateCode);
            verificationCode.setTime(Instant.now());
            verificationCode.setEnable(true);
        } else {
            verificationCode = new VerificationCode().phone(phone).code(verificateCode).enable(true).time(Instant.now());
        }
        verificationCodeRepository.save(verificationCode);
        //        redisUtil.set(verificationCode.getPhone(), verificationCode.getCode(), 300);
        return ResponseEntity.ok("获取成功");
    }
}
