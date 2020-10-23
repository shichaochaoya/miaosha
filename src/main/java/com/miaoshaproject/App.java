package com.miaoshaproject;

import com.miaoshaproject.dao.UserDOMapper;
import com.miaoshaproject.dataobject.UserDO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = {"com.miaoshaproject"})
//@EnableAutoConfiguration//自动化配置的bean,启动springboot
@RestController
@MapperScan("com.miaoshaproject.dao")
public class App 
{
    @Resource
    private UserDOMapper userDOMapper;

    @RequestMapping("/")
    public String home(){
        UserDO userDO = userDOMapper.selectByPrimaryKey(1);
        if (userDO == null) {
            return "用户不存在";
        }else {
            return userDO.getName();
        }
    }

    public static void main( String[] args )
    {
        SpringApplication.run(App.class,args);
        System.out.println( "---------------------------------启动成功--------------------------------" );


    }
}
