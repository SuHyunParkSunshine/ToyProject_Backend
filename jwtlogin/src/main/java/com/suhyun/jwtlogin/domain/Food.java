package com.suhyun.jwtlogin.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "food")
public class Food {

    @Id
    private int id;
    private String name;
    private String addr_do;
    private String addr_lot;
    private String contact;
    private String category;
    private String rmenu;
    private String parking;
    private String pet;
    private String englishmenu;
    private String foodimg;
    private String latitude;
    private String longitude;
    private String gu;
    
}
