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
@Table(name = "tb_spu")
public class Spu {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long id;

  @Column(name = "spu_name")
  private String spuName;

  @Column(name = "spu_subname")
  private String spuSubname;

  @Column(name = "logo")
  private String logo;

  @Column(name = "cat1_id")
  private long cat1Id;

  @Column(name = "cat2_id")
  private long cat2Id;

  @Column(name = "cat3_id")
  private long cat3Id;

  @Column(name = "brand_id")
  private long brandId;

  @Column(name = "check_time")
  private Date checkTime;

  @Column(name = "check_status")
  private long checkStatus;

  @Column(name = "price")
  private double price;

  @Column(name = "is_on_sale")
  private long isOnSale;

  @Column(name = "on_sale_time")
  private Date onSaleTime;

  @Column(name = "deleted_at")
  private Date deletedAt;

  @Column(name = "weight")
  private double weight;

  @Column(name = "description")
  private String description;

  @Column(name = "packages")
  private String packages;

  @Column(name = "aftersale")
  private String aftersale;

  @Column(name = "spec_list")
  private String specList;

  @Column(name = "created_at")
  private Date createdAt;

  @Column(name = "updated_at")
  private Date updatedAt;

}
