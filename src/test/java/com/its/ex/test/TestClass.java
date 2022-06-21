package com.its.ex.test;

import com.its.ex.dto.TestDTO;
import com.its.ex.service.TestService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class TestClass {
    @Autowired
    private TestService testService;

    // testService의 save() 호출
    // 호출 후 리턴값을 print
    @Test
    @Transactional
    @Rollback(value = true)
    public void saveTest(){
        /**
         * 1. 저장할 TestDTO 객체를 만들고 필드값을 저장.
         * 2. DTO 객체를 서비스의 save 메서드로 전달
         * 3. 전달 후 리턴 값을 받아서(Long)
         * 4. 그 리턴값으로 DB에서 findById를 하고
         * 5. DB에서 조회된 데이터와 1번에서 저장한 데이터가 일치하는지를 판단하여
         * 6. 일치하면 테스트통과, 일치하지 않으면 테스트 실패
         */
        // 1.
        TestDTO testDTO = new TestDTO("테스트데이터999","테스트데이터999");
        // 2,3
        Long saveId = testService.save(testDTO);
        // 4.
        TestDTO findDTO = testService.findById(saveId);
        // 5,6
        assertThat(testDTO.getColumn1()).isEqualTo(findDTO.getColumn1());
    }

    @Test
    @DisplayName("findAll 테스트")
    public void findAllTest(){
        /**
         * 1. 3개의 테스트 데이터 저장
         * 2. findAll 호출
         * 3. 호출한 리스트의 크기가 3인지를 판단
         * 4. 3이면 테스트 통과, 아니면 테스트 실패
         */
        // 1.
//        for(int i=0; i<=2; i++){
//            testService.save(new TestDTO("테스트데이터"+i,"테스트데이터"+i));
//        }
        // 1. 자바 람다식(화살표 함수), IntStream으로 3개의 데이터 저장 / 위의 주석처리한 for문과 같은 내용
        IntStream.rangeClosed(1,3).forEach(i -> {
            testService.save(new TestDTO("테스트데이터"+i,"테스트데이터"+i));
        });
        //2.
        List<TestDTO> testDTOList = testService.findAll();
        //3.
        assertThat(testDTOList.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("삭제 테스트")
    public void deleteTest(){
        /**
         * 1. 삭제할 대상을 저장
         * 2. 저장된 아이디값을 가져오기
         * 3. 삭제
         */
        // 1.
        TestDTO testDTO = new TestDTO("테스트데이터999","테스트데이터999");
        //2
        Long saveId = testService.save(testDTO);
        //3.
        testService.delete(saveId);
        assertThat(testService.findById(saveId)).isNull();
    }

    @Test
    @DisplayName("수정 테스트")
    public void updateTest(){
        /**
         * 수정 테스트를 어떻게 할지 시나리오 쓰기
         * assertThat().isNotEqualTo() 쓰면 됨.
         * 1. 새로운 데이터 저장
         * 2. 저장한 객체를 조회하여 test_column1의 값을 가지고 있음.
         * 3. test_column1의 값을 변경함.
         * 4. 수정처리
         * 5. 수정 후 해당 객체를 조회하여 test_colum1의 값을 가져옴.
         * 6. 2번에서 조회한 값과 5번에서 조회한 값이 같은지를 비교하여
         * 7. 다르면 테스트 성공, 같다면 테스트 실패
         */
        TestDTO testDTO = new TestDTO("테스트데이터999","테스트데이터999");
        Long saveId = testService.save(testDTO);

        TestDTO findDTO = testService.findById(saveId);
        findDTO.setColumn1("수정데이터1");
        findDTO.setTestColumn2("수정데이터2");

        testService.update(findDTO);
        assertThat(testDTO.getColumn1()).isNotEqualTo(findDTO.getColumn1());

    }





}

