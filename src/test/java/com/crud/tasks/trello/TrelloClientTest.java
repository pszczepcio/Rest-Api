package com.crud.tasks.trello;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;

import com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.trello.config.TrelloConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloClientTest {

    @InjectMocks
    private TrelloClient trelloClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TrelloConfig trelloConfig;

    @Test
    public void shouldFetchTrelloBoards() throws URISyntaxException {
        //Given
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        when(trelloConfig.getTrelloUserName()).thenReturn("kodillauser");
        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        when(trelloConfig.getTrelloToken()).thenReturn("test");

        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];
        trelloBoards[0] = new TrelloBoardDto("test_id", "test_board", new ArrayList<>());

        URI uri = new URI("http://test.com/members/kodillauser/boards?key=test&token=test&fields=name,id&lists=all");

        System.out.println(uri);
        when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(trelloBoards);

        //When
        List<TrelloBoardDto> fetchedTrelloBoards = trelloClient.getTrelloBoards();
        //Then
        Assert.assertEquals(1, fetchedTrelloBoards.size());
        Assert.assertEquals("test_id", fetchedTrelloBoards.get(0).getId());
        Assert.assertEquals("test_board", fetchedTrelloBoards.get(0).getName());
        Assert.assertEquals(new ArrayList<>(), fetchedTrelloBoards.get(0).getLists());
    }

    @Test
    public void shouldCreateCard() throws  URISyntaxException {
        //given
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        when(trelloConfig.getTrelloUserName()).thenReturn("kodillauser");
        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        when(trelloConfig.getTrelloToken()).thenReturn("test");
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "Test task",
                "Test Description",
                "top",
                "test_id"
        );

        URI uri = new URI("http://test.com/cards?key=test&token=test&name=Test%20task&desc=Test%20Description&pos=top&idList=test_id");

        CreatedTrelloCardDto createdTrelloCard = new CreatedTrelloCardDto(
                "1",
                "Test task",
                "http://test.com"
        );

        when(restTemplate.postForObject(uri, null , CreatedTrelloCardDto.class)).thenReturn(createdTrelloCard);
        //When
        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);

        //Then
        Assert.assertEquals("1", newCard.getId());
        Assert.assertEquals("Test task", newCard.getName());
        Assert.assertEquals("http://test.com", newCard.getShortUrl());
    }

    @Test
    public void shouldReturnEmptyList() throws URISyntaxException {
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        when(trelloConfig.getTrelloUserName()).thenReturn("kodillauser");
        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        when(trelloConfig.getTrelloToken()).thenReturn("test");

        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];
        trelloBoards[0] = new TrelloBoardDto("test_id", "test_board", new ArrayList<>());

        URI uri = new URI("http://test.com/members/kodillauser/boards?key=test&token=test&fields=name,id&lists=all");

        System.out.println(uri);
        when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(null);

        //When
        List<TrelloBoardDto> emptyTrelloBoards = trelloClient.getTrelloBoards();
        System.out.println(emptyTrelloBoards.size());
        //Then
        Assert.assertEquals(0, emptyTrelloBoards.size());
        Assert.assertEquals(new ArrayList<>(), emptyTrelloBoards);
        System.out.println(emptyTrelloBoards);
    }
}