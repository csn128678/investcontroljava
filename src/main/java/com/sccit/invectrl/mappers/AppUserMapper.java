package com.sccit.invectrl.mappers;

import com.sccit.invectrl.dbEntitys.AppUserEntity;

import java.util.List;

public interface AppUserMapper {

    AppUserEntity getInfo(String name, String password);

    List<AppUserEntity> selectAll();
}