package com.czxy.jmyp.service;

import com.czxy.jmyp.repository.SkuRepository;
import com.czxy.jmyp.vo.BaseResult;
import com.czxy.jmyp.vo.ReturnSku;
import com.czxy.jmyp.vo.SearchRequest;
import com.czxy.jmyp.vo.SearchSku;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SkuSearchService {

    @Resource
    private SkuRepository skuRepository;

//    public Object search(SearchRequest req){
//        //1. 数据校验如果三级分类为空
//        if(req.getCat_id()==null){
//            return new BaseResult( 1 , "没有数据");
//        }
//
//        //2 创建查询构建器
//        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
//
//        //2.1 关键字  三级类目    品牌   规格选项过滤
//        //2.1.1  keyword,匹配all
//        if(StringUtils.isNotBlank(req.getKeyword())){
//            queryBuilder.withQuery(QueryBuilders.matchQuery("all",req.getKeyword()));
//        }
//        //2.1.2  catid==3级分类
//        if(req.getCat_id()!=null){
//            queryBuilder.withQuery(QueryBuilders.termQuery("catId",req.getCat_id()));
//        }
//
//        //2.1.3 brandId
//
//
//        //2.1.4 过滤条件构建器
//        BoolQueryBuilder filterQueryBuilder = QueryBuilders.boolQuery();
//
////        if(req.getBrand_id()!=null){
////            filterQueryBuilder.must(QueryBuilders.termQuery("brandId",req.getBrand_id()));
////        }
////        if(req.getSpec_list() != null){
////            if(req.getSpec_list() != null ){
////                for(String key : req.getSpec_list().keySet()){
////                    String value = req.getSpec_list().get(key);
////                    //拼凑se查询key
////                    String searchKey = "specs." + key + ".keyword";
////                    filterQueryBuilder.must( QueryBuilders.termQuery( searchKey , value ) );
////                }
////            }
////        }
//
//        //2.1.5 添加价格过滤
//        if(req.getMin_price()!=null  &&   req.getMax_price()!=null){
//            filterQueryBuilder.must(QueryBuilders.rangeQuery("price").gte(req.getMin_price()).lt(req.getMax_price()));
//        }
//        queryBuilder.withQuery( filterQueryBuilder );
//
//
//        //3  sortBy:zh 综合  xl 销量  xp 新品  jg   pj 评价
//        // 排序
//        if (req.getSort_by()!=null){
//            if(req.getSort_by().equals("xl")&&req.getSort_way().equals("asc")){
//                //销量升序
//                queryBuilder.withSort(SortBuilders.fieldSort("sellerCount").order(SortOrder.ASC));
//            }else  if(req.getSort_by().equals("xl")&&req.getSort_way().equals("desc")) {
//                // 销量降序
//                queryBuilder.withSort(SortBuilders.fieldSort("sellerCount").order(SortOrder.DESC));
//            }else if(req.getSort_by().equals("jg")&&req.getSort_way().equals("asc")){
//                // 价格升序
//                queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));
//            }else  if(req.getSort_by().equals("jg")&&req.getSort_way().equals("desc")) {
//                // 价格降序
//                queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));
//            }else if(req.getSort_by().equals("pl")&&req.getSort_way().equals("asc")){
//                // 评论升序
//                queryBuilder.withSort(SortBuilders.fieldSort("commentCount").order(SortOrder.ASC));
//            }else  if(req.getSort_by().equals("pl")&&req.getSort_way().equals("desc")) {
//                // 评论降序
//                queryBuilder.withSort(SortBuilders.fieldSort("commentCount").order(SortOrder.DESC));
//            }else if(req.getSort_by().equals("sj")&&req.getSort_way().equals("asc")){
//                // 上架时间
//                queryBuilder.withSort(SortBuilders.fieldSort("onSaleTime").order(SortOrder.ASC));
//            }else  if(req.getSort_by().equals("sj")&&req.getSort_way().equals("desc")) {
//                // 上架时间
//                queryBuilder.withSort(SortBuilders.fieldSort("onSaleTime").order(SortOrder.DESC));
//            }
//        }
//
//
//        //4 分页 ， 【注意】 分页从0开始
//        queryBuilder.withPageable(PageRequest.of(req.getPage() - 1 ,req.getPer_page()));
//
//        //5 查询,获取结果
//        // 5.1 查询
//        Page<SearchSku> pageInfo = this.skuRepository.search(queryBuilder.build());
//        // 5.2 总条数
//        long total = pageInfo.getTotalElements();
//        // 5.3 封装
//        BaseResult result = new BaseResult(0, "成功");
//        result.append("count" , total);
//
//
//        // 5.4 获取返回结果 ，组装返回数据
//        List<SearchSku> list = pageInfo.getContent();
//        // 返回的结果
//        List<ReturnSku> returnList = new ArrayList<>();
//        for(SearchSku sku:list){
//            ReturnSku rs = new ReturnSku();
//            rs.setId(sku.getId());
//            rs.setGoodsName(sku.getSkuName());
//            rs.setMidlogo(sku.getLogo());
//            rs.setCommentCount(sku.getCommentCount());
//            rs.setPrice(sku.getPrice());
//
//            returnList.add(rs);
//        }
//
//        result.append("data" ,returnList);
//
//        return result;
//    }

    public BaseResult search(SearchRequest searchRequest ){

        //1. 数据校验如果三级分类为空
        if(searchRequest.getCat_id()==null){
            return new BaseResult( 1 , "没有数据");
        }

        //0 拼凑条件,使用 BoolQueryBuilder 进行条件的追加（and or）
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        //0.1 关键字（暂时不实现）
        //0.2 品牌
        if( searchRequest.getBrand_id() != null ){
            boolQueryBuilder.must(QueryBuilders.termQuery("brandId" , searchRequest.getBrand_id() ) );
        }
        //0.3 规格 : 遍历map依次处理每一个规格，key有要求
        if(searchRequest.getSpec_list() != null ){
            for(String key : searchRequest.getSpec_list().keySet()){
                String value = searchRequest.getSpec_list().get(key);
                //拼凑se查询key
                String searchKey = "specs." + key + ".keyword";
                boolQueryBuilder.must( QueryBuilders.termQuery( searchKey , value ) );
            }
        }

        //0.4 范围
        if(searchRequest.getMin_price() != null){
            boolQueryBuilder.must( QueryBuilders.rangeQuery("price").gte( searchRequest.getMin_price() ) );      //gte 大于等于
        }
        if(searchRequest.getMax_price() != null){
            boolQueryBuilder.must( QueryBuilders.rangeQuery("price").lte( searchRequest.getMax_price() ));       //lte 小于等于
        }



        //1 使用条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        //0.5 排序
        //queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC ));
        if("xl".equals( searchRequest.getSort_by() ) && "desc".equals(searchRequest.getSort_way() )){
            queryBuilder.withSort( SortBuilders.fieldSort("sellerCount").order( SortOrder.DESC));
        } else if("jg".equals( searchRequest.getSort_by() ) && "desc".equals(searchRequest.getSort_way() )){
            queryBuilder.withSort( SortBuilders.fieldSort("price").order( SortOrder.DESC));
        } else if("jg".equals( searchRequest.getSort_by() ) && "asc".equals(searchRequest.getSort_way() )){
            queryBuilder.withSort( SortBuilders.fieldSort("price").order( SortOrder.ASC));
        } else if("pl".equals( searchRequest.getSort_by() ) && "desc".equals(searchRequest.getSort_way() )){
            queryBuilder.withSort( SortBuilders.fieldSort("commentCount").order( SortOrder.DESC));
        } else if("sj".equals( searchRequest.getSort_by() ) && "desc".equals(searchRequest.getSort_way() )){
            queryBuilder.withSort( SortBuilders.fieldSort("onSaleTime").order( SortOrder.DESC));
        }

        // 0.end
        queryBuilder.withQuery(boolQueryBuilder);

        //0.6 分页 进行分页操作 ,【注意：从0开始】
        queryBuilder.withPageable(PageRequest.of( searchRequest.getPage() - 1 , searchRequest.getPer_page() ));

        //3 获得数据 -- SearchSku
        Page<SearchSku> page = this.skuRepository.search(queryBuilder.build());

        System.out.println(page);
        List<ReturnSku> returnList = new ArrayList<>();
        for (SearchSku searchSku : page.getContent()) {
            ReturnSku returnSku = new ReturnSku();

            returnSku.setId( searchSku.getId().intValue() );
            returnSku.setGoodsName( searchSku.getSkuName() );
            returnSku.setPrice( searchSku.getPrice() );
            returnSku.setMidlogo( searchSku.getLogo());
            returnSku.setCommentCount( searchSku.getCommentCount() );

            System.out.println( returnSku );

            returnList.add( returnSku );
        }
        System.out.println(returnList);

        //4 拼凑页面需要数据 -- BaseResult ( ReturnSku)
        BaseResult baseResult = new BaseResult(0 , "成功");
        baseResult.append("count" , page.getTotalElements() );
        baseResult.append("data" ,returnList );

        return baseResult;

    }
}