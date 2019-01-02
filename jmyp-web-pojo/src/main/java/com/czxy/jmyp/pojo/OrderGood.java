package com.czxy.jmyp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tb_order_good")
public class OrderGood implements Serializable{

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "sn")
  private String sn;

  @Transient
  private Order order;

  @Column(name = "spu_id")
  private Integer spuId;

  @Column(name = "sku_id")
  private Integer skuId;

  @Transient
  private Sku sku;

  @Column(name = "number")
  private Integer number;

  @Column(name = "spec_list")
  private String specList;

  @Column(name = "sku_name")
  private String skuName;

  @Column(name = "url")
  private String logo;

  @Column(name = "price")
  private double price;
}
