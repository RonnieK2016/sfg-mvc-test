package com.udemy.sfgmvctest.repositories;

import com.udemy.sfgmvctest.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}