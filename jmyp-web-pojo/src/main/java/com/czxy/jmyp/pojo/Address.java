package com.czxy.jmyp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "tb_address")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Address {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long id;

  private long userId;

  @Column(name="shr_name")
  private String shrName;

  @Column(name="shr_mobile")
  private String shrMobile;

  @Column(name="shr_province")
  private String shrProvince;

  @Column(name="shr_city")
  private String shrCity;

  @Column(name="shr_area")
  private String shrArea;

  @Column(name="shr_address")
  private String shrAddress;

  @Column(name="isdefault")
  private long isdefault;
}
