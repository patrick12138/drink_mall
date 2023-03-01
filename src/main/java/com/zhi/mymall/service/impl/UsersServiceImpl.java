package com.zhi.mymall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhi.mymall.beans.Users;
import com.zhi.mymall.mapper.UsersMapper;
import com.zhi.mymall.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhi.mymall.utils.MD5Utils;
import com.zhi.mymall.vo.ResultVO;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;

/**
 * <p>
 * 用户  服务实现类
 * </p>
 *
 * @author lzp
 * @since 2022-03-12
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {
    @Resource
    private UsersMapper usersMapper;
    @Override
    public ResultVO userResgit(String name, String pwd) {
        synchronized (this) {
            //1.根据用户查询，这个用户是否已经被注册
            QueryWrapper<Users> usersQueryWrapper = new QueryWrapper<>();
            usersQueryWrapper.eq("username",name);
            Users users = usersMapper.selectOne(usersQueryWrapper);
            //2.如果没有被注册则进行保存操作
            if (users == null) {
                String md5Pwd = MD5Utils.md5(pwd);
                Users user = new Users();
                user.setUsername(name);
                user.setPassword(md5Pwd);
                user.setUserImg("default.png");
                int i = usersMapper.insert(user);
                if (i > 0) {
                    return new ResultVO(10000, "注册成功！", user);
                } else {
                    return new ResultVO(10001, "注册失败！", null);
                }
            } else {
                return new ResultVO(10001, "用户名已经被注册！", null);
            }
        }
    }
    @Override
    public ResultVO checkLogin(String name, String pwd) {
        QueryWrapper<Users> usersQueryWrapper = new QueryWrapper<>();
        usersQueryWrapper.eq("username",name);
        Users users = usersMapper.selectOne(usersQueryWrapper);
        if(users== null){
            return new ResultVO(10001,"登录失败，用户名不存在！",null);
        }else{
            String md5Pwd = MD5Utils.md5(pwd);
            if(md5Pwd.equals(users.getPassword())){
                //如果登录验证成功，则需要生成令牌token（token就是按照特定规则生成的字符串）
                //使用jwt规则生成token字符串
                JwtBuilder builder = Jwts.builder();

                HashMap<String,Object> map = new HashMap<>();
                map.put("key1","value1");
                map.put("key2","value2");

                String token = builder.setSubject(name)                     //主题，就是token中携带的数据
                        .setIssuedAt(new Date())                            //设置token的生成时间
                        .setId(users.getUserId() + "")               //设置用户id为token  id
                        .setClaims(map)                                     //map中可以存放用户的角色权限信息
                        .setExpiration(new Date(System.currentTimeMillis() + 24*60*60*1000)) //设置token过期时间
                        .signWith(SignatureAlgorithm.HS256, "lzp1")     //设置加密方式和加密密码
                        .compact();

                return new ResultVO(10000,token,users);
            }else{
                return new ResultVO(10001,"登录失败，密码错误！",null);
            }
        }
    }
    @Override
    public Users findByName(String name){
        QueryWrapper<Users> usersQueryWrapper = new QueryWrapper<>();
        usersQueryWrapper.eq("username",name);
        Users users = usersMapper.selectOne(usersQueryWrapper);
        return users;
    }
}
