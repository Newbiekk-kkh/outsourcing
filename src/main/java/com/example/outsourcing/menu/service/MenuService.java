package com.example.outsourcing.menu.service;

import com.example.outsourcing.Store.entity.Store;
import com.example.outsourcing.Store.repository.StoreRepository;
import com.example.outsourcing.menu.entity.Menu;
import com.example.outsourcing.eunm.MenuStatus;
import com.example.outsourcing.menu.dto.*;
import com.example.outsourcing.menu.repository.MenuRepository;
import com.example.outsourcing.response.CommonResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;

    // 메뉴 생성
    public CommonResponseBody<CreateMenuResponseDto> createMenu(Long storeId, CreateMenuRequestDto dto) {

        Store store = storeRepository.findById(storeId).orElseThrow(IllegalArgumentException::new);
        Menu menu = menuRepository.save(dto.toEntity(store));

        return new CommonResponseBody<>("메뉴 추가 완료", CreateMenuResponseDto.createMenuResponse(menu));
    }

    // 메뉴 조회 : 삭제되지 않은 메뉴들에 한해 조회
    @Transactional
    public CommonResponseBody<List<GetMenuResponseDto>> findMenu(Long storeId) {

        return new CommonResponseBody<>(
                "메뉴 조회 완료",
                storeRepository.findById(storeId).orElseThrow(IllegalArgumentException::new)
                        .getMenus()
                        .stream()
                        .filter(menu -> menu.getMenuStatus().equals(MenuStatus.NORMAL))
                        .map(GetMenuResponseDto::getMenuResponse)
                        .toList());
    }

    // 메뉴 수정 : 메뉴를 생성한 가게에서만 수정 가능
    @Transactional
    public CommonResponseBody<UpdateMenuResponseDto> updateMenu(Long storeId, Long menuId, UpdateMenuRequestDto dto) {
        Menu menu = menuRepository.findById(menuId).orElseThrow(IllegalArgumentException::new);

        if (menu.getStore().getId() != storeId) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "이 메뉴는 해당 가게에서 수정할 수 없습니다.");
        }

        menu.updateMenu(dto.getMenuName(), dto.getMenuDescription(), dto.getMenuPrice());

        return new CommonResponseBody<>("메뉴 수정 완료", UpdateMenuResponseDto.updateMenuResponse(menu));
    }

    //메뉴 삭제 : 메뉴 삭제 상태로 전환, 메뉴를 생성한 가게에서만 삭제 가능
    @Transactional
    public CommonResponseBody<DeleteMenuResponseDto> deleteMenu(Long storeId, Long menuId) {
        Menu menu = menuRepository.findById(menuId).orElseThrow(IllegalArgumentException::new);

        if (menu.getStore().getId() != storeId) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "이 메뉴는 해당 가게에서 삭제할 수 없습니다.");
        }

        menu.deleteMenu();

        return new CommonResponseBody<>("메뉴 삭제 완료", DeleteMenuResponseDto.deleteMenuResponse(menu));
    }
}
