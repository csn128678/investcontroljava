package com.sccit.invectrl.api;

import com.sccit.invectrl.dbEntitys.AppUserEntity;
import com.sccit.invectrl.services.AppUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Api(tags = "投控平台用户")
@RestController
@RequestMapping("/api/AppUser")
public class AppUserController {

    @Autowired
    AppUserService userService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @ApiOperation("查询所有用户")
    @RequestMapping(value = "/selectAll",method ={RequestMethod.POST})
    public List<AppUserEntity> selectAll(){
        return userService.selectAll();
    }

    @ApiOperation("获取用户AccessToken")
    @RequestMapping(value = "/getAccessToken",method = {RequestMethod.GET,RequestMethod.POST})
    @ApiImplicitParam(paramType="query", name = "userName", value = "用户登录名", required = true, dataType = "String")
    @ResponseBody
    public String getAccessToken(/*@ApiParam(value="用户登录名",required = true)*/ String userName){
        String redisKey = "TokenAccess_"+userName;
        Object result = redisTemplate.opsForValue().get(redisKey);
        if(result==null)
            return null;
        byte[] bytes = (byte[])result;
        String str = UTILS.BYTES.convertString(bytes);
        return str;
    }

    // @Autowired
    // private RedisUtils redisUtils;

    /*@RequestMapping(value = "/hello/{id}")
    public String hello(@PathVariable(value = "id") String id){
       /* //查询缓存中是否存在
        boolean hasKey = redisUtils.exists(id);
        String str = "";
        if(hasKey){
            //获取缓存
            Object object = redisUtils.get(id);
            log.info("从缓存获取的数据"+ object);
            str = object.toString();
        }else{
            //从数据库中获取信息
            log.info("从数据库中获取数据");
            str = testService.test();
            //数据插入缓存（set中的参数含义：key值，user对象，缓存存在时间10（long类型），时间单位）
            redisUtils.set(id,str,10L,TimeUnit.MINUTES);
            log.info("数据插入缓存" + str);
        }
        return str;
        return "";
    }*/
}