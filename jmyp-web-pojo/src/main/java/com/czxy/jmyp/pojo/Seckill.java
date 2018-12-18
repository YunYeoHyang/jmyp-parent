package com.czxy.jmyp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tb_seckill")
public class Seckill {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long id;

  @Column(name = "seller_id")
  private long sellerId;

  @Column(name = "spu_id")
  private long spuId;

  @Column(name = "sku_id")
  private long skuId;

  @Column(name = "start_time")
  private Date startTime;

  @Column(name = "end_time")
  private Date endTime;

  @Column(name = "stock")
  private long stock;

  @Column(name = "price")
  private double price;

  @Column(name = "check_time")
  private Date checkTime;

  @Column(name = "check_status")
  private long checkStatus;

}
