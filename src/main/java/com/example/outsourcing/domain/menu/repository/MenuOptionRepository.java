package com.example.outsourcing.domain.menu.repository;

import com.example.outsourcing.domain.menu.entity.MenuOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuOptionRepository extends JpaRepository<MenuOption, Long> {
}
