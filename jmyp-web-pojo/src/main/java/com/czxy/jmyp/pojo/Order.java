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
@Table(name = "tb_order")
public class Order {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long id;

  @Column(name = "created_at")
  private Date createdAt;

  @Column(name = "updated_at")
  private Date updatedAt;

  @Column(name = "sn")
  private String sn;

  @Column(name = "shr_name")
  private String shrName;

  @Column(name = "shr_mobile")
  private String shrMobile;

  @Column(name = "shr_province")
  private String shrProvince;

  @Column(name = "shr_city")
  private String shrCity;

  @Column(name = "shr_area")
  private String shrArea;

  @Column(name = "shr_address")
  private String shrAddress;

  @Column(name = "status")
  private long status;

  @Column(name = "pay_time")
  private Timestamp payTime;

  @Column(name = "post_time")
  private Timestamp postTime;

  @Column(name = "user_id")
  private long userId;

  @Column(name = "total_price")
  private double totalPrice;
}
