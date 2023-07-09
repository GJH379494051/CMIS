package cn.edu.cdtu.sys.service;

import cn.edu.cdtu.sys.domain.User;
import cn.edu.cdtu.sys.domain.UserVo;

public interface UserService {
    /**
     * 用户登录
     * @param userVo
     * @return
     */
    User login(UserVo userVo);
}
