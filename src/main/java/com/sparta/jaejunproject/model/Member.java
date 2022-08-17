package com.sparta.jaejunproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "member")
@Entity
public class Member  {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY) //GenerationType.IDENTITY : ID값이 서로 영향없이 자기만의 테이블 기준으로 올라간다.
   private Long id;
   @Column(nullable = false, unique = true)
   private String userId;
   @JsonIgnore
   @Column(nullable = false)
   private String password;

   @Enumerated(EnumType.STRING)
   private Authority authority;
   @Builder
   public Member(String nickname, String password, Authority authority) {
      this.userId = nickname;
      this.password = password;
      this.authority = authority;
   }
}
