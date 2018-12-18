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
@Table(name = "tb_category_brand")
public class CategoryBrand {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long categoryId;

  @Column(name = "brand_id")
  private long brandId;
}
