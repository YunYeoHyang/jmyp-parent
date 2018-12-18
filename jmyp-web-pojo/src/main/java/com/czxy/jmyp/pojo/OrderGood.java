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
@Table(name = "tb_order_good")
public class OrderGood {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long id;

  @Column(name = "sn")
  private long sn;

  @Column(name = "spu_id")
  private long spuId;

  @Column(name = "sku_id")
  private long skuId;

  @Column(name = "number")
  private long number;

  @Column(name = "spec_list")
  private String specList;

  @Column(name = "sku_name")
  private String skuName;

  @Column(name = "url")
  private String url;

  @Column(name = "price")
  private double price;
}
