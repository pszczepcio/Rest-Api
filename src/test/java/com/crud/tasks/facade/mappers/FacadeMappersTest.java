package com.crud.tasks.facade.mappers;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FacadeMappersTest {
    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void shouldMapToBoards(){
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "task1", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "task2", false);
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(trelloListDto1);
        trelloListsDto.add(trelloListDto2);

        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1", "Task to do", trelloListsDto);
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(trelloBoardDto);

        //When
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardDtoList);
        TrelloBoard trelloBoard = trelloBoardList.get(0);
        TrelloList trelloList1 = trelloBoard.getLists().get(0);
        TrelloList trelloList2 = trelloBoard.getLists().get(1);
        //Then
        assertEquals("1", trelloBoard.getId());
        assertEquals("Task to do", trelloBoard.getName());
        assertEquals("1", trelloList1.getId());
        assertEquals("task1", trelloList1.getName());
        assertEquals(true, trelloList1.isClosed());
        assertEquals("2", trelloList2.getId());
        assertEquals("task2", trelloList2.getName());
        assertEquals(false, trelloList2.isClosed());
    }

    @Test
    public void shouldMapToBoardsDto(){
        //Given
        TrelloList trelloList1 = new TrelloList("1", "task1", true);
        TrelloList trelloList2 = new TrelloList("2", "task2", false);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList1);
        trelloLists.add(trelloList2);

        TrelloBoard trelloBoard = new TrelloBoard("1", "Task to do", trelloLists);
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(trelloBoard);

        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);
        TrelloBoardDto trelloBoardDto = trelloBoardDtoList.get(0);
        TrelloListDto trelloListDto1 = trelloBoardDto.getLists().get(0);
        TrelloListDto trelloListDto2 = trelloBoardDto.getLists().get(1);

        //Then
        assertEquals("1", trelloBoardDto.getId());
        assertEquals("Task to do", trelloBoardDto.getName());
        assertEquals("1", trelloListDto1.getId());
        assertEquals("task1", trelloListDto1.getName());
        assertEquals(true, trelloListDto1.isClosed());
        assertEquals("2", trelloListDto2.getId());
        assertEquals("task2", trelloListDto2.getName());
        assertEquals(false, trelloListDto2.isClosed());
    }

    @Test
    public void shouldMapToList(){
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "task1", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "task2", false);
        TrelloListDto trelloListDto3 = new TrelloListDto("3", "task3", true);

        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(trelloListDto1);
        trelloListDtos.add(trelloListDto2);
        trelloListDtos.add(trelloListDto3);

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtos);
        TrelloList trelloList1 = trelloLists.get(0);
        TrelloList trelloList2 = trelloLists.get(1);
        TrelloList trelloList3 = trelloLists.get(2);

        //Then
        assertEquals("1", trelloList1.getId());
        assertEquals("task1", trelloList1.getName());
        assertEquals(true, trelloList1.isClosed());

        assertEquals("3", trelloList3.getId());
        assertEquals("task3", trelloList3.getName());
        assertEquals(true, trelloList3.isClosed());
    }

    @Test
    public void shouldMapToListDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "task1", true);
        TrelloList trelloList2 = new TrelloList("2", "task2", false);
        TrelloList trelloList3 = new TrelloList("3", "task3", true);

        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList1);
        trelloLists.add(trelloList2);
        trelloLists.add(trelloList3);

        //When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);
        TrelloListDto trelloListDto1 = trelloListDtos.get(0);
        TrelloListDto trelloListDto2 = trelloListDtos.get(1);
        TrelloListDto trelloListDto3 = trelloListDtos.get(2);

        //Then
        assertEquals("1", trelloListDto1.getId());
        assertEquals("task1", trelloListDto1.getName());
        assertEquals(true, trelloListDto1.isClosed());

        assertEquals("3", trelloListDto3.getId());
        assertEquals("task3", trelloListDto3.getName());
        assertEquals(true, trelloListDto3.isClosed());
    }

    @Test
    public void shouldMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Task1", "I want to learn Java", "top", "1");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals("Task1", trelloCardDto.getName());
        assertEquals("I want to learn Java", trelloCardDto.getDescription());
        assertEquals("top", trelloCardDto.getPos());
        assertEquals("1", trelloCardDto.getListId());
    }

    @Test
    public void shouldMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Task1", "I want to learn Java", "top", "1");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals("Task1", trelloCard.getName());
        assertEquals("I want to learn Java", trelloCard.getDescription());
        assertEquals("top", trelloCard.getPos());
        assertEquals("1", trelloCard.getListId());
    }
}
