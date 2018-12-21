package com.czxy.jmyp.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ReturnSku {

    private Integer id;
    @JsonProperty("goods_name")
    private String goodsName;
    private Double price;
    private String midlogo;
    @JsonProperty("comment_count")
    private Integer commentCount;

}