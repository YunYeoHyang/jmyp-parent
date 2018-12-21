package com.czxy.jmyp.dao;

import com.czxy.jmyp.pojo.SkuComment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface SkuCommentMapper extends Mapper<SkuComment> {

    @Select("select count(*) from tb_sku_comment where sku_id = #{skuId}")
    Integer findNumBySkuId( @Param("skuId") Integer skuId);
}

