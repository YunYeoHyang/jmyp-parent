package com.czxy.jmyp.dao;

import com.czxy.jmyp.pojo.Brand;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface BrandMapper extends Mapper<Brand> {

    @Select("select * from tb_brand where id in (select brand_id from tb_category_brand where category_id = #{catid})")
    @Results({
            @Result(property = "brandName",column = "brand_name")
    })
    List<Brand> findBrandsByCatId(Integer catid);

}
