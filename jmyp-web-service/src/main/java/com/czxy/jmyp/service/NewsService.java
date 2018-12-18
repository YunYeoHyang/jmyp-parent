package com.czxy.jmyp.service;

import com.czxy.jmyp.dao.NewsMapper;
import com.czxy.jmyp.pojo.News;
import com.czxy.jmyp.vo.PageRequest;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional
public class NewsService {
    @Autowired
    private NewsMapper newsMapper;

    /**
     * 获取分页数据的代码
     *     private Integer limit;// 限制条数
     *     private Integer offset;// 起始索引
     *     private Integer page; // 当前页
     *     private Integer per_page;// 每页条数
     *     private String sort_by; // 排序字段
     *     private String sort_way; //排序方式  asc  升序   desc  降序
     * @param pageRequest
     * @return
     */
    public List<News> findNewsByPage(PageRequest pageRequest){
        PageHelper.startPage(pageRequest.getPage(),pageRequest.getPer_page());
        Example example = new Example(News.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isBlank(pageRequest.getSort_way()) || pageRequest.getSort_way().equals("desc")){
            example.setOrderByClause("created_at desc");
        }else{
            example.setOrderByClause("created_at asc");
        }

        List<News> list = newsMapper.selectByExample(example);

        return list;
    }

    /**
     * 获取总记录数的代码
     * @return
     */
    public int findTotalCount(){
        return newsMapper.selectAll().size();
    }
}