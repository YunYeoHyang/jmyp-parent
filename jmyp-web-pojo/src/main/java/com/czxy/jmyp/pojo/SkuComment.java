package com.czxy.jmyp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tb_sku_comment")
public class SkuComment {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long id;

  @Column(name = "created_at")
  private Date createdAt;

  @Column(name = "updated_at")
  private Date updatedAt;

  @Column(name = "user_id")
  private long userId;

  @Column(name = "spu_id")
  private long spuId;

  @Column(name = "sku_id")
  private long skuId;

  @Column(name = "ratio")
  private String ratio;

  @Column(name = "spec_list")
  private String specList;

  @Column(name = "content")
  private String content;

  @Column(name = "star")
  private long star;

  @Column(name = "isshow")
  private long isshow;

  @Column(name = "sn")
  private String sn;

}
