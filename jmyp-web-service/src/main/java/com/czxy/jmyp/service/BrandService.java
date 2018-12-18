package com.czxy.jmyp.service;

import com.czxy.jmyp.dao.BrandMapper;
import com.czxy.jmyp.pojo.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BrandService {

    @Autowired
    private BrandMapper brandMapper;

    public List<Brand> findBrandByCatid(Integer catid){
        return brandMapper.findBrandsByCatId(catid);
    }
}