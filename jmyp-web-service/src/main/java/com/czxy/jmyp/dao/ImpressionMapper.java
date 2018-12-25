package com.czxy.jmyp.dao;

import com.czxy.jmyp.pojo.Impression;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface ImpressionMapper extends Mapper<Impression> {

    @Select("select * from tb_impression where spu_id = #{spuid}")
    List<Impression> findImpressionsBySpuid(Integer spuid);
}