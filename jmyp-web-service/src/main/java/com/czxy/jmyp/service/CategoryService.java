package com.czxy.jmyp.service;

import com.czxy.jmyp.dao.CategoryMapper;
import com.czxy.jmyp.pojo.Category;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    public List<Category> findAll(){

        // 查询所有 ， 按照 parent_id 升序排序
        Example example = new Example(Category.class);
        example.setOrderByClause("parent_id asc");
        List<Category> l = this.categoryMapper.selectByExample(example);

        // 记录所有父元素
        ArrayList<Category> list = new ArrayList<>();

        // 子元素寻找对应父元素
        HashMap<Integer, Category> map = new HashMap<>();

        for (Category c :
                l) {
            // 存放所有父元素
            if ( c.getParentId() == 0 ){
               list.add( c );
            }

            // 存储所有元素
            map.put( c.getId() , c );

            // 获得当前元素的父元素 ， 并添加到父元素的children集合中
            Category pCategory = map.get(c.getParentId());
            if ( pCategory != null){
                pCategory.getChildren().add( c );
            }
        }

        return list;
    }

}
