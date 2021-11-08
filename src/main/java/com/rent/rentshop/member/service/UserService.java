package com.rent.rentshop.member.service;

import com.rent.rentshop.member.domain.User;
import com.rent.rentshop.member.dto.UserUpdate;

import java.util.List;

/**
 * 사용자를 저장, 조회, 수정, 삭제 기능 제공하는 서비스
 */
public interface UserService {

    /**
     * 사용자 목록을 조회하여 리턴합니다.
     * @return 사용자 목록
     */
    List<User> getUsers();

    /**
     * 사용자의 상세정보를 조회하여 리턴합니다.
     * @param userEmail 조회할 사용자의 아이디
     * @return 조회된 사용자
     */
    User getUser(String userEmail);

    /**
     * 사용자를 저장소에 저장후 리턴합니다.
     * @param form 저장할 사용자 정보
     * @return 저장된 사용자
     */
    User join(User form);

    /**
     * 사용자 이메일이 중복인지 검증합니다.
     * @param userEmail 검증할 이메일
     * @return true - 이메일 | false - 이메일 사용가능
     */
    boolean userEmailCheck(String userEmail);

    /**
     * 사용자의 정보를 수정합니다.
     * @param userEmail 수정할 사용자의 아이디
     * @param form 수정될 사용자의 정보
     */
    void userUpdate(String userEmail, UserUpdate form);

    /**
     * 사용자를 삭제합니다.
     * @param userEmail 삭제할 사용자 아이디
     */
    void userDelete(String userEmail);

}
