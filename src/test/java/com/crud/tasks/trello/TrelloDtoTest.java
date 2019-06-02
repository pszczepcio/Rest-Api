package com.crud.tasks.trello;

import com.crud.tasks.domain.TrelloDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloDtoTest {

    @Test
    public void TrelloDtoNoArgsConstructorTest() {
        //Given
        TrelloDto trelloDto = new TrelloDto();

        //When

        //Then
        assertEquals(0, trelloDto.getBoard());
        assertEquals(0, trelloDto.getCard());
    }

    @Test
    public void TrelloDtoAllArgsConstructorTest() {
        //Given
        TrelloDto trelloDto = new TrelloDto( 1, 2);

        //When

        //Then
        assertEquals(1, trelloDto.getBoard());
        assertEquals(2, trelloDto.getCard());
    }
}
