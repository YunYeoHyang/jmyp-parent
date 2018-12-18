package com.czxy.jmyp.dao;

import com.czxy.jmyp.pojo.Specification;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface SpecificationMapper extends Mapper<Specification> {

    @Select("select * from tb_specification where category_id = #{categoryId}")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="specName",property="spec_name"),
            @Result(column="categoryId",property="category_id"),
            @Result(column="id",property="options",
                    many=@Many(
                            select="com.czxy.jmyp.dao.SpecificationOptionMapper.findSpecificationOptionBySpecificationId"
                    )
            )
    })
    List<Specification> findSpecificationByCategoryId(Integer categoryId);

}