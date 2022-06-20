package com.its.ex.service;

import com.its.ex.entity.TestEntity;
import com.its.ex.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;

    public Long save(){
        TestEntity testEntity = new TestEntity();
        testEntity.setColumn1("컬럼1");
        testEntity.setTestColumn2("컬럼2");
        Long id = testRepository.save(testEntity).getId();
        return id;
    }
}
