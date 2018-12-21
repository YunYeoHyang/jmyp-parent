package com.czxy.jmyp.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.elasticsearch.index.settings.IndexDynamicSettings;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document( indexName = "skus" , type = "docs")
public class SearchSku {

    /**
     * all：用来进行全文检索的字段，里面包含标题、商品分类信息
     * price：方便根据价格进行筛选过滤
     * specs：所有规格参数的集合。key是参数名，值是参数值。
     * 例如:
     * 当存储到索引库时，elasticsearch会处理为两个字段：
     * specs.内存 ： [4G,6G]
     * specs.颜色：红色
     * 另外， 对于字符串类型，还会额外存储一个字段，这个字段不会分词，用作聚合。
     * specs.颜色.keyword：红色
     */

    @Id
    private Integer id; // skuId

    @Field(type = FieldType.Text)
    private String logo;//图片地址

    @Field(type = FieldType.Text)
    private String skuName;//sku名字

    @Field(type = FieldType.Text ,analyzer = "ik_max_word")
    private String all; // 所有需要被搜索的信息，包含标题，分类，甚至品牌

    @Field(type = FieldType.Date)
    private Date onSaleTime;//商家时间

    //品牌编号
    @Field(type = FieldType.Integer)
    private Integer brandId;

    // 分类id
    @Field(type = FieldType.Integer)
    private Integer catId;

    //规格列表
    @IndexDynamicSettings
    private Map<String, Object> specs;// 可搜索的规格参数，key是参数名，值是参数值

    @Field(type = FieldType.Double)
    private Double price;// 价格

    @Field(type = FieldType.Text)
    private String spuName;

    @Field(type = FieldType.Integer)
    private Integer stock;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Text)
    private String packages;//规格与包装

    @Field(type = FieldType.Text)
    private String aftersale;//售后保障

    private String midlogo;

    //评价数
    @Field(type = FieldType.Integer)
    private Integer commentCount;

    // 销量
    @Field(type = FieldType.Integer)
    private Integer sellerCount;
}

