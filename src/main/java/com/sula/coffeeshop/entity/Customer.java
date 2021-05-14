package com.sula.coffeeshop.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements SuperEntity {

    @Id
    private String id;
    private String name;
    private String address;

}
