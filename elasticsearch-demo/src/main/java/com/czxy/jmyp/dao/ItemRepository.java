package com.czxy.jmyp.dao;

import com.czxy.jmyp.pojo.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ItemRepository extends ElasticsearchRepository<Item,Long> {

    List<Item> findByCategory(String category);
    List<Item> findByBrand(String brand);
    List<Item> findByPriceBetween(Double d1, double d2);
}