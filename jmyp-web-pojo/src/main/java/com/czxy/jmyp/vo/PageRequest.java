package com.czxy.jmyp.vo;

import lombok.Data;

@Data
public class PageRequest {
    private Integer limit;// 限制条数
    private Integer offset;// 起始索引
    private Integer page; // 当前页
    private Integer per_page;// 每页条数
    private String sort_by; // 排序字段
    private String sort_way; //排序方式  asc  升序   desc  降序
}