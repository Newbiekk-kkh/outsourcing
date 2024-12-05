package com.example.outsourcing.menu.repository;

import com.example.outsourcing.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    default Menu findByIdOrElseThrow(Long menuId) {
        return findById(menuId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "메뉴를 찾을 수 없습니다."));
    }
}
