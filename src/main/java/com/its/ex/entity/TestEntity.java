package com.its.ex.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name="test_table")
public class TestEntity {
    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment 속성 부여
    @Column(name = "test_id")
    private Long id;

    @Column(name = "test_column1",length = 50)
    private String column1;

    @Column(unique = true)
    private String testColumn2;

}
