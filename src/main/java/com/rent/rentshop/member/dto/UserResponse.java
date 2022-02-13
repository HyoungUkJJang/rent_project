package com.rent.rentshop.member.dto;

import com.rent.rentshop.member.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 회원응답 DTO
 */
public class UserResponse {

    /**
     * 회원 상세조회 정적 멤버 클래스
     */
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UserDetailResponse {
        private String email;
        private String name;
        private String phone;
        private String birth;
        private String roadAddress;
        private String detailAddress;
        private String bankName;
        private String account;

        @Builder
        public UserDetailResponse(String email, String name, String phone, String birth, String roadAddress, String detailAddress, String bankName, String account) {
            this.email = email;
            this.name = name;
            this.phone = phone;
            this.birth = birth;
            this.roadAddress = roadAddress;
            this.detailAddress = detailAddress;
            this.bankName = bankName;
            this.account = account;
        }

    }

    /**
     * 회원 상세조회 정적 멤버 클래스로 변환하여 리턴합니다.
     * @param user 유저 엔티티
     * @return 변환된 유저 상세조회 DTO
     */
    public static UserDetailResponse toDetailUser(User user) {
        return UserDetailResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .phone(user.getPhone())
                .birth(user.getBirth())
                .bankName(user.getBankName())
                .account(user.getAccount())
                .roadAddress(user.getUserAddress().getRoadAddress())
                .detailAddress(user.getUserAddress().getDetailAddress())
                .build();
    }

    /**
     * 회원 간단조회 정적 멤버 클래스
     */
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UserSimpleResponse {
        private String email;
        private String image;

        @Builder
        public UserSimpleResponse(String email, String image) {
            this.email = email;
            this.image = image;
        }

    }

}
