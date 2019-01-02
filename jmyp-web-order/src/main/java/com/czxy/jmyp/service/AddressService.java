package com.czxy.jmyp.service;

import com.czxy.jmyp.dao.AddressMapper;
import com.czxy.jmyp.pojo.Address;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class AddressService {

    @Resource
    private AddressMapper addressMapper;

    /**
     * 查询所有
     * @param userId
     * @return
     */
    public List<Address> findAllByUserId(Long userId) {
        Example example = new Example(Address.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);

        return this.addressMapper.selectByExample(example);
    }
}