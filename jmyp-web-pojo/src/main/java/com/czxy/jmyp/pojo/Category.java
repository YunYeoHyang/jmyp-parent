package com.czxy.jmyp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "tb_category")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Category {

    @Id
    private Integer id;

    @Column(name="cat_name")
    private String catName;

    @Column(name="parent_id")
    private Integer parentId;

    @Column(name="is_parent")
    private Boolean isParent;

    private List<Category> children = new ArrayList<>();
}
