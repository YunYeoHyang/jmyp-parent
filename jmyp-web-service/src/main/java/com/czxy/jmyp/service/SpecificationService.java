package com.czxy.jmyp.service;

import com.czxy.jmyp.dao.SpecificationMapper;
import com.czxy.jmyp.pojo.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SpecificationService {
    @Autowired
    private SpecificationMapper specificationMapper;

    public List<Specification> findSpecificationByCategoryId(Integer categoryId){
        return specificationMapper.findSpecificationByCategoryId(categoryId);
    }
}