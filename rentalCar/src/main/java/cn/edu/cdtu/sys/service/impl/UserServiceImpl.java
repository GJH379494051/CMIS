package cn.edu.cdtu.sys.service.impl;

import cn.edu.cdtu.sys.domain.User;
import cn.edu.cdtu.sys.domain.UserVo;
import cn.edu.cdtu.sys.mapper.UserMapper;
import cn.edu.cdtu.sys.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(UserVo userVo) {
        //明文转换密文
        String md5Pwd = DigestUtils.md5DigestAsHex(userVo.getPwd().getBytes());
        userVo.setPwd(md5Pwd);
        return userMapper.queryUserByUsernameAndPwd(userVo);
    }
}
