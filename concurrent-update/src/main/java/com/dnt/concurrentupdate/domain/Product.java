package com.dnt.concurrentupdate.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Product")
@Table(name = "product")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @Version
    private int version;

}