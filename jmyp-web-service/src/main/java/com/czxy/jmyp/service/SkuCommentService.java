package com.czxy.jmyp.service;

import com.czxy.jmyp.dao.ImpressionMapper;
import com.czxy.jmyp.dao.SkuCommentMapper;
import com.czxy.jmyp.pojo.Impression;
import com.czxy.jmyp.pojo.SkuComment;
import com.czxy.jmyp.vo.CommentResult;
import com.czxy.jmyp.vo.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SkuCommentService {

    @Resource
    private SkuCommentMapper skuCommentMapper;
    @Resource
    private ImpressionMapper impressionMapper;

    /**
     * 根据SpuId查找评论
     * @param spuid
     * @param pageRequest
     * @return
     */
    public CommentResult findComments(Integer spuid, PageRequest pageRequest){

        /**
         * 新建一个 CommentResult 对象
         */
        CommentResult commentResult = new CommentResult();

        /**
         * 1、印象
         * List<Impression> impressions;
        */
        List<Impression> impressionList = impressionMapper.findImpressionsBySpuid(spuid);
        commentResult.setImpressions(impressionList);

        /**
         * 2、商品评论数量
         * Map<String,Object> ratio;
         */
        // 好评
        Integer goodCount = skuCommentMapper.findCommentCountByRatio(spuid,0);
        // 中评
        Integer commonCount = skuCommentMapper.findCommentCountByRatio(spuid,1);
        // 差评
        Integer badCount = skuCommentMapper.findCommentCountByRatio(spuid,2);
        //总评
        Integer totalCount = skuCommentMapper.findNumBySpuId(spuid);

        Map<String,Object> ratio = new HashMap<>();

        /**  String 工具
         *  String.format("%.2f" , goodCount * 100.0 / totalCount);
         */
        if( totalCount > 0 ) {
            ratio.put("goods", String.format("%.1f", goodCount * 100.0 / totalCount));
            ratio.put("common", String.format("%.1f", commonCount * 100.0 / totalCount));
            ratio.put("bad", String.format("%.1f", badCount * 100.0 / totalCount));
        } else {
            // 零为除数优化
            ratio.put("goods", 0);
            ratio.put("common", 0);
            ratio.put("bad", 0);
        }

        commentResult.setRatio(ratio);
        commentResult.setComment_count(totalCount);

        /**
         * 评论详情
         * List<SkuComment> comments;
         */
        List<SkuComment> comments = skuCommentMapper.findCommentsBySpuId(spuid);
        commentResult.setComments(comments);

        return commentResult;
    }
}