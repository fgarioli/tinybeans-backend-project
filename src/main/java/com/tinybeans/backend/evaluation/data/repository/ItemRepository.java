package com.tinybeans.backend.evaluation.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tinybeans.backend.evaluation.data.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Transactional(readOnly = true)
    public Item findById(Long id);
}
