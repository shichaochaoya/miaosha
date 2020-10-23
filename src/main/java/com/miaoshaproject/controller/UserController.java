package com.miaoshaproject.controller;

import com.alibaba.druid.util.StringUtils;
import com.miaoshaproject.controller.viewObject.UserVO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.UserService;
import com.miaoshaproject.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;


import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * 用户请求
 *
 * @author Administrator
 */
@Controller("user")
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;


    /**
     * 用户登录请求
     *
     * @param telephone
     * @param password
     * @return
     * @throws BusinessException
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping(value = "/login", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FROMED})
    @ResponseBody
    public CommonReturnType login(@RequestParam(name = "telephone") String telephone,
                                  @RequestParam(name = "password") String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //入参检验
        if (org.apache.commons.lang3.StringUtils.isEmpty(telephone) || org.apache.commons.lang3.StringUtils.isEmpty(password)) {
            throw new BusinessException(EmBusinessError.PARAMWTER_VALIDATION_ERROR);
        }
        //用户登录服务，用来校验用户登录是否合法
        UserModel userModel = userService.validateLogin(telephone, this.encodeByMD5(password));

        //将登录凭证加入到用户登录成功的session
        this.httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        this.httpServletRequest.getSession().setAttribute("LOGIN_USER", userModel);
        return CommonReturnType.create(null);
    }

    /**
     * 用户注册接口
     *
     * @param telephone
     * @param otpCode
     * @param name
     * @param gender
     * @param age
     * @param password
     * @return
     * @throws BusinessException
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping(value = "/register", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FROMED})
    @ResponseBody
    public CommonReturnType register(@RequestParam(name = "telephone") String telephone,
                                     @RequestParam(name = "otpCode") String otpCode,
                                     @RequestParam(name = "name") String name,
                                     @RequestParam(name = "gender") Integer gender,
                                     @RequestParam(name = "age") Integer age,
                                     @RequestParam(name = "password") String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //验证手机号和otpCode符合
        String inSessionOtpCode = (String) this.httpServletRequest.getSession().getAttribute(telephone);
        if (!StringUtils.equals(otpCode, inSessionOtpCode)) {
            throw new BusinessException("短信验证码不符合", EmBusinessError.PARAMWTER_VALIDATION_ERROR);
        }
        //用户注册流程
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setGender(new Byte(String.valueOf(gender.intValue())));
        userModel.setAge(age);
        userModel.setTelephone(telephone);
        userModel.setRegisterMode("byPhone");
        userModel.setEncrptPassword(this.encodeByMD5(password));

        userService.register(userModel);
        return CommonReturnType.create(null);

    }

    /**
     * 加密方法
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public String encodeByMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        //加密字符串
        String newstr = base64Encoder.encode(md.digest(str.getBytes("utf-8")));
        return newstr;
    }

    /**
     * 获取短信验证码接口
     * @param telephone
     * @return
     */
    @RequestMapping(value = "/getotp", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FROMED})
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name = "telephone") String telephone) {
        //按照一定规则生成OTP验证码
        Random random = new Random();
        int rabdomInt = random.nextInt(99999);
        rabdomInt += 10000;
        String otpCode = String.valueOf(rabdomInt);
        //将OTP验证码同对应用户手机号关联,使用HTTPsession的方式绑定手机号和Code
        httpServletRequest.getSession().setAttribute(telephone, otpCode);
        //将OTP验证码通过短信发送给用户（暂时省略，需要第三方接口）
        System.out.println("telephone = " + telephone + "& otpCode = " + otpCode + httpServletRequest.getSession().getAttribute(otpCode));
        return CommonReturnType.create(null);
    }

    /**
     * 获取用户信息
     * @param id
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name = "id") Integer id) throws BusinessException {
        //调用用户名返回给前端
        UserModel userModel = userService.getUserById(id);

        //若获取的用户信息不存在
        if (userModel == null) {
            throw new BusinessException(EmBusinessError.USER_NOT_EXIT);
        }

        //将核心领域模型用户对象转换为可供ui使用的前端对象
        UserVO userVO = convertFromModel(userModel);
        //返回通用对象
        return CommonReturnType.create(userVO);
    }

    /**
     * 用户模型转前端模型
     * @param userModel
     * @return
     */
    private UserVO convertFromModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }


}
