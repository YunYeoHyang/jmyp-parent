package com.czxy.jmyp.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Table(name = "tb_sku_photo")
@Data
public class SkuPhoto {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer id;
  //外键
  @Column(name="sku_id")
  @JsonProperty("sku_id")
  private Integer skuId;
  @Transient
  private Sku sku;

  @Column(name="url")
  @JsonProperty("url")
  private String url;
}
