package com.lxcm.sys.service;

import com.lxcm.common.util.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
public class UserServiceImplTest {

    @Autowired
    private IUserService userService;


    @Test
    public void test02() {
        String password = MD5Util.md5_private_salt("123456","98C69A2446384F22B5EFC7874095AC69");
        System.out.println(password);

    }


}