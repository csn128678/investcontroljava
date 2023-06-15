package com.sccit.invectrl.servieImps;

import com.sccit.invectrl.dbEntitys.AppUserEntity;
import com.sccit.invectrl.mappers.AppUserMapper;
import com.sccit.invectrl.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {

    //将DAO注入Service层
    @Autowired
    private AppUserMapper userMapper;

    @Override
    public AppUserEntity loginIn(String name, String password) {
        return userMapper.getInfo(name,password);
    }

    @Override
    public List<AppUserEntity> selectAll(  ) {
        return userMapper.selectAll( );
    }
}