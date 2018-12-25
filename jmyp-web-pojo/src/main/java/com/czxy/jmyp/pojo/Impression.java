package com.czxy.jmyp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tb_impression")
public class Impression {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "title")
  private String title;

  @Column(name = "count")
  private Integer count;

  @Column(name = "spu_id")
  private Integer spuId;

  @Column(name = "sku_id")
  private Integer skuId;
}
