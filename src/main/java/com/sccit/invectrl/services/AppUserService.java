package com.sccit.invectrl.services;

import com.sccit.invectrl.dbEntitys.AppUserEntity;

import java.util.List;

public interface AppUserService {
    AppUserEntity loginIn(String name, String password);


    List<AppUserEntity> selectAll();
}