package com.crud.tasks.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AttachmentsByTypeDtoTest {

    @Test
    public void AttachmentsByTypeDtoNoArgsConstructorTest() {
        //Given
        AttachmentsByTypeDto attachmentsByTypeDto = new AttachmentsByTypeDto();

        //When

        //Then
        assertEquals(null, attachmentsByTypeDto.getTrello());
    }

    @Test
    public void AttachmentsByTypeDtoAllArgsConstructorTest() {
        //Given
        TrelloDto trelloDto = new TrelloDto(1 ,2);
        AttachmentsByTypeDto attachmentsByTypeDto = new AttachmentsByTypeDto(trelloDto);
        //When

        //Given
        assertEquals(1, attachmentsByTypeDto.getTrello().getBoard());
        assertEquals(2, attachmentsByTypeDto.getTrello().getCard());
    }
}