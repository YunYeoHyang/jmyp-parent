package com.czxy.jmyp.vo;

import lombok.Data;

import java.util.Map;

@Data
public class SearchRequest {
    private String keyword;                 // 关键字搜索，预留
    private Integer cat_id;                  // 3 级类目
    private Integer brand_id;                // 品牌
    //    private List spec_list;
    private Map<String,String> spec_list;    // 规格选项列表
    private Double min_price;                //最低价格
    private Double max_price;                //最高价格
    private Integer limit;                  //限制条数
    private Integer page;                   //当前页
    private String sort_by;                  //排序字段
    private String sort_way;                 //排序方式 asc desc
    private Integer per_page;                //每页条数
}