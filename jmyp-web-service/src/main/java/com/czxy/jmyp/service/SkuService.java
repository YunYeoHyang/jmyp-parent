package com.czxy.jmyp.service;

import com.alibaba.fastjson.JSON;
import com.czxy.jmyp.dao.SkuCommentMapper;
import com.czxy.jmyp.dao.SkuMapper;
import com.czxy.jmyp.pojo.Sku;
import com.czxy.jmyp.vo.ESData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SkuService {
    @Resource
    private SkuMapper skuMapper;

    @Resource
    private SkuCommentMapper skuCommentMapper;

    /**
     * @return
     */
    public List<ESData> findESData(){

        //1 查询所有详情sku
        List<Sku> skulist = skuMapper.findAllSkus();

        //2 将SKU 转换成 ESData
        List<ESData> esDataList = new ArrayList<>();

        for (Sku sku:skulist){
            ESData esData = new ESData();

            System.out.println( sku.getSkuName()+"   " + sku.getSpecInfoIdTxt() + "   " +sku.getSpu().getBrand().getBrandName() );
            // id
            esData.setId(sku.getId());
            // logo
            esData.setLogo(sku.getImages());
            // sku_name
            esData.setSkuName(sku.getSkuName());
            // all  “华为xx {"机身颜色":"白色","内存":"3GB","机身存储":"16GB"} 荣耀 ”
            esData.setAll(sku.getSkuName()+"   " + sku.getSpecInfoIdTxt() + "   " +sku.getSpu().getBrand().getBrandName());
            // on_sale_time
            esData.setOnSaleTime(sku.getSpu().getOnSaleTime());
            // brand_id
            esData.setBrandId(sku.getSpu().getBrandId());
            // cat_id
            esData.setCatId(sku.getSpu().getCat3Id());
            //  Map<String, Object> specs;// 可搜索的规格参数，key是参数名，值是参数值
            Map specs = JSON.parseObject(sku.getSpecInfoIdTxt(), Map.class);
            esData.setSpecs(specs);
            // price 价格
            esData.setPrice(sku.getPrice());
            // spu_name
            esData.setSpuName(sku.getSpu().getSpuName());
            // stock 库存
            esData.setStock(sku.getStock());
            // description
            esData.setDescription(sku.getSpu().getDescription());
            // packages;//规格与包装
            esData.setPackages(sku.getSpu().getPackages());
            // aftersale;//售后保障
            esData.setAftersale(sku.getSpu().getAftersale());
            // midlogo;
            esData.setMidlogo(sku.getSpu().getLogo());
            // comment_count; 评价数
            Integer comment_count = skuCommentMapper.findNumBySkuId(sku.getId());
            esData.setCommentCount(comment_count);

            //销售量
            esData.setSellerCount(10);

            esDataList.add(esData);
        }

        return esDataList;
    }

}
