package com.rent.rentshop.controller;

import com.rent.rentshop.member.service.LoginService;
import com.rent.rentshop.non_member.domain.NonUser;
import com.rent.rentshop.non_rent.domain.NonUserRent;
import com.rent.rentshop.non_rent.dto.NonUserRentRequest;
import com.rent.rentshop.non_rent.service.NonUserRentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NonUserRentController.class)
@DisplayName("NonUserRentController 클래스")
@MockBean(JpaMetamodelMappingContext.class)
class NonUserRentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private NonUserRentServiceImpl nonUserRentService;
    @MockBean
    private LoginService loginService;

    @Nested
    @DisplayName("createRent 메서드는")
    class Describe_createRent {

        @Nested
        @DisplayName("비회원 사용자가 상품대여를 신청하는 경우에")
        class Context_exist_nonUser_rent {

            Long productId = 1L;
            NonUserRent nonUserRent = createNonUserRent();
            NonUserRentRequest nonUserRentRequest = createNonUserRentRequest();

            @BeforeEach
            void setUp() {
                given(nonUserRentService.createRent(any(NonUserRentRequest.class), anyLong())).willReturn(nonUserRent);
            }

            @Test
            @DisplayName("대여신청 폼과 상품 아이디를 입력받아 신청서 저장 후 200 상태코드를 반환한다.")
            void It_save_nonUserRent_status_200() throws Exception {
                mockMvc.perform(post("/rent/nonuser/borrow")
                                .param("id", String.valueOf(productId))
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{" +
                                        "\"name\":\"김형욱\"," +
                                        "\"phone\":\"010-0000-0000\"," +
                                        "\"rentalDate\":\"2021-12-15\"," +
                                        "\"returnDate\":\"2021-12-25\"" +
                                        "}")
                        ).andExpect(status().isOk());
            }
        }
    }

    /**
     * 비회원 대여신청 테스트 도메인을 생성합니다.
     *
     * @return 대여신청 테스트도메인
     */
    private NonUserRent createNonUserRent() {
        return NonUserRent.builder()
                .nonUser(new NonUser("김형욱", "010-0000-000"))
                .rentalDate(LocalDate.of(2021, 12, 15))
                .returnDate(LocalDate.of(2021, 12, 25))
                .build();
    }

    /**
     * 비회읜 대여신청 폼 테스트 도메인을 생성합니다.
     *
     * @return 대여신청 폼 테스트도메인
     */
    private NonUserRentRequest createNonUserRentRequest() {
        return NonUserRentRequest.builder()
                .name("김형욱")
                .phone("010-0000-0000")
                .rentalDate(LocalDate.of(2021, 12, 15))
                .returnDate(LocalDate.of(2021, 12, 25))
                .build();
    }

}
