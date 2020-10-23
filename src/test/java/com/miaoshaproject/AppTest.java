package com.miaoshaproject;

import static org.junit.Assert.assertTrue;

import com.miaoshaproject.service.UserService;
import com.miaoshaproject.service.model.UserModel;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;


import java.io.InputStream;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Autowired
    UserService userService;
    @Test

    public void shouldAnswerWithTrue()
    {

    }
}
