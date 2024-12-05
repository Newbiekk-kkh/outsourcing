package com.example.outsourcing.menu.controller;

import com.example.outsourcing.menu.dto.*;
import com.example.outsourcing.menu.service.MenuService;
import com.example.outsourcing.response.CommonResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/stores/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponseBody<CreateMenuResponseDto> createMenu(@RequestParam Long storeId, @RequestBody CreateMenuRequestDto dto) {
        return menuService.createMenu(storeId, dto);
    }

    @GetMapping
    public CommonResponseBody<List<GetMenuResponseDto>> getMenu(@RequestParam Long storeId) {
        return menuService.findMenu(storeId);
    }

    @PatchMapping("/update/{menuId}")
    public CommonResponseBody<UpdateMenuResponseDto> updateMenu(@RequestParam Long storeId, @PathVariable Long menuId, @RequestBody UpdateMenuRequestDto dto) {
        return menuService.updateMenu(storeId, menuId, dto);
    }

    @PatchMapping("/delete/{menuId}")
    public CommonResponseBody<DeleteMenuResponseDto> deleteMenu(@RequestParam Long storeId, @PathVariable Long menuId) {
        return menuService.deleteMenu(storeId, menuId);
    }
}
