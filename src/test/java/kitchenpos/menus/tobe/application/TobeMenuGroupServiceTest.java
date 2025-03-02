package kitchenpos.menus.tobe.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import java.util.UUID;
import kitchenpos.menus.tobe.domain.menugroup.MenuGroup;
import kitchenpos.menus.tobe.dto.menugroup.MenuGroupRequest;
import kitchenpos.menus.tobe.dto.menugroup.MenuGroupResponse;
import kitchenpos.mock.persistence.FakeMenuGroupRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

class TobeMenuGroupServiceTest {

    private final FakeMenuGroupRepository menuGroupRepository = new FakeMenuGroupRepository();
    private final TobeMenuGroupService menuGroupService = new TobeMenuGroupService(menuGroupRepository);

    @DisplayName("정해진 이름으로 메뉴그룹을 생성할 수 있다.")
    @Test
    void create() {
        // given
        UUID id = UUID.randomUUID();
        String name = "추천 메뉴";
        MenuGroupRequest request = new MenuGroupRequest(id, name);

        // when
        MenuGroupResponse menuGroupResponse = menuGroupService.create(request);

        // then
        assertThat(menuGroupResponse).isNotNull();
        assertThat(menuGroupResponse.id()).isEqualTo(request.id());
        assertThat(menuGroupResponse.name()).isEqualTo(request.name());
    }

    @DisplayName("이름 없이 메뉴그룹을 생성 시, 예외가 발생한다.")
    @ParameterizedTest
    @NullSource
    @EmptySource
    void createNameException(String name) {
        // given
        MenuGroupRequest request = new MenuGroupRequest(UUID.randomUUID(), name);

        // when then
        assertThatThrownBy(() -> menuGroupService.create(request))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("저장된 메뉴그룹 목록를 조회할 수 있다.")
    @Test
    void findAll() {
        // given
        List<String> names = List.of("추천 메뉴", "오늘의 메뉴", "할인 메뉴", "세트 메뉴");
        names.forEach(name -> {
            menuGroupService.create(new MenuGroupRequest(UUID.randomUUID(), name));
        });

        // when
        List<MenuGroup> menuGroups = menuGroupService.findAll();

        // then
        assertThat(menuGroups)
            .hasSize(names.size())
            .extracting(MenuGroup::getName)
            .containsExactlyInAnyOrder(names.toArray(String[]::new));
    }
}
