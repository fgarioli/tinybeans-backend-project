package com.tinybeans.backend.evaluation.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * @author danrodrigues
 * Date: 13/5/22
 */
@Entity @Data @ToString @Builder
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Item extends BaseEntity{

    private String name, description, photoUrl;

    private Double price;

    @JsonIgnore
    @ManyToMany(mappedBy = "items", fetch = FetchType.EAGER)
    private List<Orders> orders;
}
