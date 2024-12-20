package com.example.outsourcing.domain.menu.service;

import com.example.outsourcing.domain.member.entity.Member;
import com.example.outsourcing.domain.member.repository.MemberRepository;
import com.example.outsourcing.domain.menu.entity.MenuOption;
import com.example.outsourcing.domain.menu.repository.MenuOptionRepository;
import com.example.outsourcing.domain.store.entity.Store;
import com.example.outsourcing.domain.store.repository.StoreRepository;
import com.example.outsourcing.domain.menu.dto.*;
import com.example.outsourcing.domain.menu.entity.Menu;
import com.example.outsourcing.global.enums.MenuOptionStatus;
import com.example.outsourcing.global.enums.MenuStatus;
import com.example.outsourcing.domain.menu.repository.MenuRepository;
import com.example.outsourcing.global.common.CommonResponseBody;
import com.example.outsourcing.global.enums.UserAccess;
import com.example.outsourcing.global.sesstionUtils.SessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MemberRepository memberRepository;
    private final MenuRepository menuRepository;
    private final MenuOptionRepository menuOptionRepository;
    private final StoreRepository storeRepository;
    private final SessionUtils sessionUtils;

    // 메뉴 생성
    @Transactional
    public CommonResponseBody<CreateMenuResponseDto> createMenu(Long storeId, CreateMenuRequestDto dto) {
        Store store = CheckValidMemberAndStoreId(storeId, "메뉴 추가");

        Menu menu = menuRepository.save(dto.toEntity(store));

        return new CommonResponseBody<>("메뉴 추가 완료", CreateMenuResponseDto.createMenuResponse(menu));
    }

    // 메뉴 조회 : 삭제되지 않은 메뉴들에 한해 조회
    @Transactional
    public CommonResponseBody<List<GetMenuResponseDto>> findMenu(Long storeId) {
        return new CommonResponseBody<>(
                "메뉴 조회 완료",
                storeRepository.findById(storeId).orElseThrow(() -> new IllegalArgumentException("해당 가게가 존재하지 않습니다."))
                        .getMenus()
                        .stream()
                        .filter(menu -> menu.getMenuStatus().equals(MenuStatus.NORMAL))
                        .map(GetMenuResponseDto::getMenuResponse)
                        .toList());
    }

    // 메뉴 수정 : 메뉴를 생성한 가게에서만 수정 가능
    @Transactional
    public CommonResponseBody<UpdateMenuResponseDto> updateMenu(Long storeId, Long menuId, UpdateMenuRequestDto dto) {

        CheckValidMemberAndStoreId(storeId, "메뉴 수정");
        Menu menu = menuRepository.findById(menuId).orElseThrow(() -> new IllegalArgumentException("해당 메뉴가 존재하지 않습니다."));

        if (menu.getStore().getId() != storeId) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "이 메뉴는 해당 가게에서 수정할 수 없습니다.");
        }

        menu.updateMenu(dto.getMenuName(), dto.getMenuDescription(), dto.getMenuPrice());

        return new CommonResponseBody<>("메뉴 수정 완료", UpdateMenuResponseDto.updateMenuResponse(menu));
    }

    //메뉴 삭제 : 메뉴 삭제 상태로 전환, 메뉴를 생성한 가게에서만 삭제 가능
    @Transactional
    public CommonResponseBody<DeleteMenuResponseDto> deleteMenu(Long storeId, Long menuId) {

        CheckValidMemberAndStoreId(storeId, "메뉴 삭제");
        Menu menu = menuRepository.findById(menuId).orElseThrow(() -> new IllegalArgumentException("해당 메뉴가 존재하지 않습니다."));

        if (menu.getStore().getId() != storeId) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "이 메뉴는 해당 가게에서 삭제할 수 없습니다.");
        }

        menu.deleteMenu();

        return new CommonResponseBody<>("메뉴 삭제 완료", DeleteMenuResponseDto.deleteMenuResponse(menu));
    }

    @Transactional
    public CommonResponseBody<CreateMenuOptionResponseDto> createMenuOption(Long storeId, Long menuId, CreateMenuOptionRequestDto dto) {
        CheckValidMemberAndStoreId(storeId, "메뉴 옵션 추가");
        Menu menu = menuRepository.findById(menuId).orElseThrow(() -> new IllegalArgumentException("해당 메뉴가 존재하지 않습니다."));

        if (menu.getStore().getId() != storeId) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "이 메뉴는 해당 가게에서 수정할 수 없습니다.");
        }

        MenuOption menuOption = menuOptionRepository.save(dto.toEntity(menu));

        return new CommonResponseBody<>("메뉴 옵션 추가 완료", CreateMenuOptionResponseDto.createMenuOptionResponse(menuOption));
    }

    @Transactional
    public CommonResponseBody<DeleteMenuOptionResponseDto> deleteMenuOption(Long storeId, Long menuId, Long menuOptionId) {
        CheckValidMemberAndStoreId(storeId, "메뉴 옵션 삭제");
        Menu menu = menuRepository.findById(menuId).orElseThrow(() -> new IllegalArgumentException("해당 메뉴가 존재하지 않습니다."));

        if (menu.getStore().getId() != storeId) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "이 메뉴는 해당 가게에서 수정할 수 없습니다.");
        }
        MenuOption menuOption = menuOptionRepository.findById(menuOptionId).orElseThrow(() -> new IllegalArgumentException("해당 메뉴 옵션이 존재하지 않습니다."));
        menuOptionRepository.deleteById(menuOptionId);

        return new CommonResponseBody<>("메뉴 옵션 삭제 완료", DeleteMenuOptionResponseDto.deleteMenuOptionResponse(menuOption));
    }

    public Store CheckValidMemberAndStoreId(Long storeId, String messsage) {
        Member loginMenber = memberRepository.findById(sessionUtils.getLoggedInUserId()).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        if(!loginMenber.getUserAccess().equals(UserAccess.MANAGER)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, messsage+" 기능은 사장님만 수행할 수 없습니다.");
        }

        Store store = storeRepository.findById(storeId).orElseThrow(() -> new IllegalArgumentException("해당 가게가 존재하지 않습니다."));

        if(!loginMenber.getStores().contains(store)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, messsage+" 기능은 해당 가게에서 수행할 수 없습니다.");
        }

        return store;
    }
}
