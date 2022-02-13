package com.rent.rentshop.member.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class UserImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filePath;
    private String originalFileName;
    private String serverFileName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public UserImage(String filePath, String originalFileName, String serverFileName) {
        this.filePath = filePath;
        this.originalFileName = originalFileName;
        this.serverFileName = serverFileName;
    }

}
