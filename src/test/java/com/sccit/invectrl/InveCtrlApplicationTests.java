package com.sccit.invectrl;

import com.sccit.invectrl.dbEntitys.AppUserEntity;
import com.sccit.invectrl.services.AppUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InveCtrlApplicationTests {
    @Autowired
    AppUserService userService;

    @Test
    public void contextLoads() {
        AppUserEntity userBean = userService.loginIn("whz","e10adc3949ba59abbe56e057f20f883e");
        System.out.println("该用户ID为：");
        System.out.println(userBean.getId());
    }
}
