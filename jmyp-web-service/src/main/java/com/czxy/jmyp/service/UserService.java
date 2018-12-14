package com.czxy.jmyp.service;

import com.czxy.jmyp.dao.UserMapper;
import com.czxy.jmyp.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 注册保存用户信息
     * @param user
     * @return
     */
    public void saveUser(User user){
        userMapper.insert(user);
    }

    public User findByMobile(String mobile) {

        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("mobile" , mobile);
        return this.userMapper.selectOneByExample( example );
    }
}
