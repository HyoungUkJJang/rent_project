package com.rent.rentshop.member.domain;

import com.rent.rentshop.common.BaseTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

/**
 * 회원의 메인 도메인
 * 정보 - 이메일, 패스워드, 이름, 핸드폰, 생년월일, 계좌번호, 은행, 주소
 */
@Entity
@Getter
@NoArgsConstructor
public class User extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String email;
    private String password;
    private String name;
    private String phone;
    private String birth;
    private String bankName;
    private String account;

    @Embedded
    private Address userAddress;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private UserImage userImage;

    @Builder
    public User(String email, String password, String name, String phone, String birth, String bankName, String account, Address userAddress) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.birth = birth;
        this.bankName = bankName;
        this.account = account;
        this.userAddress = userAddress;
    }

    /**
     * 회원의 정보를 수정합니다.
     * @param phone
     * @param roadAddress
     * @param detailAddress
     */
    public void updateUser(String phone, String roadAddress, String detailAddress) {
        this.phone = phone;
        this.userAddress = new Address(roadAddress, detailAddress);
    }

    /**
     * 회원의 기존 패스워드를 암호화 하여 리턴합니다.
     * @param rawPassword 기존 패스워드
     * @param passwordEncoder 암호화 시킬 인코더
     * @return 패스워드 변경된 유저 객체
     */
    public User createPassword(String rawPassword, PasswordEncoder passwordEncoder) {
        passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(rawPassword);
        return this;
    }

    /**
     * 로그인 시 패스워드가 일치하는지 확인합니다.
     * @param password 비교 할 패스워드
     * @param passwordEncoder 패스워드 비교 할 인코더
     * @return 패스워드 일치시 true | 그렇지 않다면 false
     */
    public boolean passwordCheck(String password,PasswordEncoder passwordEncoder) {
        passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(password, this.password);
    }

}
