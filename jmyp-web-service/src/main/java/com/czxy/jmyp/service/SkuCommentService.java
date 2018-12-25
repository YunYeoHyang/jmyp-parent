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

        // 1、 List<Impression> impressions;
        List<Impression> impressionList = impressionMapper.findImpressionsBySpuid(spuid);
        commentResult.setImpressions(impressionList);
        //2、 Map<String,Object> ratio;
        Integer goodCount = skuCommentMapper.findCommentCountByRatio(spuid,0);// 好评
        Integer commonCount = skuCommentMapper.findCommentCountByRatio(spuid,0);// 中评
        Integer badCount = skuCommentMapper.findCommentCountByRatio(spuid,0);// 差评
        Integer totalCount = skuCommentMapper.findCommentCountByRatio(spuid,0);//

        Map<String,Object> ratio = new HashMap<>();
        Integer goods = (goodCount%totalCount)==0? goodCount*100/totalCount :  goodCount*100/totalCount+1;
        ratio.put("goods",goods);
        Integer common = (commonCount%totalCount)==0? commonCount*100/totalCount :  commonCount*100/totalCount+1;
        ratio.put("common",common);
        Integer bad = (badCount%totalCount)==0? badCount*100/totalCount :  badCount*100/totalCount+1;
        ratio.put("bad",bad);
        commentResult.setRatio(ratio);

        // Integer comment_count;
        Integer comment_count = skuCommentMapper.findNumBySpuId(spuid);
        commentResult.setComment_count(comment_count);

        // List<SkuComment> comments;
        List<SkuComment> comments = skuCommentMapper.findCommentsBySpuId(spuid);
        commentResult.setComments(comments);

        return commentResult;
    }
}