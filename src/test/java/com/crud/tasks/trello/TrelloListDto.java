package com.crud.tasks.trello;

import com.crud.tasks.domain.TrellloListDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TrelloListDto {
    @Test
    public void noArgsconstructorTest(){
        //Given
        TrellloListDto trellloListDto = new TrellloListDto();
        //When

        //Then
        assertEquals(null, trellloListDto.getId());
        assertEquals(null, trellloListDto.getName());
        assertEquals(false, trellloListDto.isClosed());
    }

    @Test
    public void AllArgsConstructorTest(){
        //Given
        TrellloListDto trellloListDto = new TrellloListDto("1", "task", false);

        //When

        //Then
        assertEquals("1", trellloListDto.getId());
        assertEquals("task", trellloListDto.getName());
        assertEquals(false, trellloListDto.isClosed());
    }

}
