package cn.edu.cdtu.sys.mapper;

import cn.edu.cdtu.sys.domain.User;

public interface UserMapper {
    User queryUserByUsernameAndPwd(User user);
}
