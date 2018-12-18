package com.czxy.jmyp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tb_specification")
public class Specification {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long id;

  @Column(name = "spec_name")
  private String specName;

  @Column(name = "category_id")
  private long categoryId;

  @Transient
  private Category category;

  @Transient
  private List<SpecificationOption> options;

}
