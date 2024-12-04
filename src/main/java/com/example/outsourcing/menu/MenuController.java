package com.example.outsourcing.menu;

import com.example.outsourcing.menu.dto.*;
import com.example.outsourcing.response.CommonResponseBody;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping("/{storeId}/menu")
    public CommonResponseBody<CreateMenuResponseDto> createMenu(@PathVariable Long storeId, @RequestBody CreateMenuRequestDto dto) {
        return menuService.createMenu(storeId, dto);
    }

    @GetMapping("/{storeId}")
    public CommonResponseBody<List<GetMenuResponseDto>> getMenu(@PathVariable Long storeId) {
        return menuService.findMenu(storeId);
    }

    @PatchMapping("/{storeId}/menu-update/{menuId}")
    public CommonResponseBody<UpdateMenuResponseDto> updateMenu(@PathVariable Long storeId, @PathVariable Long menuId, @RequestBody UpdateMenuRequestDto dto) {
        return menuService.updateMenu(storeId, menuId, dto);
    }

    @PatchMapping("/{storeId}/menu-delete/{menuId}")
    public CommonResponseBody<DeleteMenuResponseDto> deleteMenu(@PathVariable Long storeId, @PathVariable Long menuId) {
        return menuService.deleteMenu(storeId, menuId);
    }
}