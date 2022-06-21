package com.its.ex.service;

import com.its.ex.dto.TestDTO;
import com.its.ex.entity.TestEntity;
import com.its.ex.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;

    public Long save(TestDTO testDTO){
        //TestDTO 객체에 담긴 값을  TestEntity 객체에 옮겨담기
//        TestEntity testEntity = new TestEntity();
//        testEntity.setColumn1(testDTO.getColumn1());
//        testEntity.setTestColumn2(testDTO.getTestColumn2());
        TestEntity testEntity = TestEntity.toEntity(testDTO);
        Long id = testRepository.save(testEntity).getId();
        return id;
    }

    public TestDTO findById(Long id){
        Optional<TestEntity> optionalTestEntity = testRepository.findById(id);
        if(optionalTestEntity.isPresent()){
            // 조회된 결과가 있다.
            TestEntity testEntity = optionalTestEntity.get();
            TestDTO testDTO = TestDTO.toDTO(testEntity);
            return testDTO;
        }else{
            // 조회된 결과가 없다.
            return null;
        }
    }

    public List<TestDTO> findAll() {
        List<TestEntity> entityList = testRepository.findAll();
        List<TestDTO> testDTOList = new ArrayList<>();
        for(TestEntity testEntity : entityList){
            TestDTO testDTO = TestDTO.toDTO(testEntity);
            testDTOList.add(testDTO);
        }
        return testDTOList;
    }

    public void delete(Long id) {
        testRepository.deleteById(id);
    }

    public Long update(TestDTO testDTO) {
        // save 메서드 호출로 update 쿼리 가능(단,id가 같이 가야함.)
        TestEntity testEntity = TestEntity.toUpdateEntity(testDTO);
        Long id = testRepository.save(testEntity).getId();
        return id;
    }
}
