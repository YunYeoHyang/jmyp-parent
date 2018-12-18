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
@Table(name = "tb_sku")
public class Sku {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long id;

  @Column(name = "stock")
  private long stock;

  @Column(name = "spu_id")
  private long spuId;

  @Column(name = "sku_name")
  private String skuName;

  @Column(name = "images")
  private String images;

  @Column(name = "price")
  private double price;

  @Column(name = "spec_list")
  private String specList;

  @Column(name = "spec_list_code")
  private String specListCode;

  @Column(name = "created_at")
  private Date createdAt;

  @Column(name = "updated_at")
  private Date updatedAt;

}
