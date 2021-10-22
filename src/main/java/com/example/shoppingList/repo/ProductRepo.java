package com.example.shoppingList.repo;

import com.example.shoppingList.model.entity.ProductEntity;
import com.example.shoppingList.model.entity.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity, Long> {

    @Query("SELECT p FROM ProductEntity p WHERE p.categoryEntity.name = :name")
    List<ProductEntity> findAllByCategoryName(CategoryEnum name);

    Optional<ProductEntity> findByName(String name);

}
