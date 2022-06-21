package com.its.ex.entity;

import com.its.ex.dto.TestDTO;
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

    // TestDTO 객체를 받아서 Entity 객체에 옮겨 담은 후 Entity 객체를 리턴
    // Service 클래스에서 toEntity 메서드를 호출해서 리턴받은 객체를
    // testRepository의 save 메서드로 전달하세요.

    // 정적 팩토리 메서드(다른 클래스에서 객체를 만들지 않고 직접 접근 가능)
    // Entity 객체에 대한 기본 생성자를 외부에서 막을 수 있음.(Entity는 소중해!)
    // 장점 : 내부구조는 노출이 안된 채 매개변수와 리턴만 공개하여 캡슐화 가능
    // 장점 : 원하는 리턴을 줄 수 있음;
    public static TestEntity toEntity(TestDTO testDTO){
        TestEntity testEntity = new TestEntity();
        testEntity.setColumn1(testDTO.getColumn1());
        testEntity.setTestColumn2(testDTO.getTestColumn2());
        return testEntity;
    }

    public static TestEntity toUpdateEntity(TestDTO testDTO) {
        TestEntity testEntity = new TestEntity();
        testEntity.setId(testDTO.getId());
        testEntity.setColumn1(testDTO.getColumn1());
        testEntity.setTestColumn2(testDTO.getTestColumn2());
        return testEntity;
    }
}
