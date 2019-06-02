package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {
    @Autowired
    private TrelloFacade trelloFacade;

    @RequestMapping(method = RequestMethod.POST, value = "/createTrelloCard")
    public CreatedTrelloCardDto createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto){
        return trelloFacade.createCard(trelloCardDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloFacade.fetchTrelloBoards();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getTrelloBoardsKodilla")
    public void getTrelloBoardsKodilla(){
        List<TrelloBoardDto> trelloBoardsKodilla = trelloFacade.fetchTrelloBoards().stream()
                .filter(trelloBoardDto -> trelloBoardDto.getName().contains("Kodilla") && trelloBoardDto.getName() != null && trelloBoardDto.getId() != null)
                .collect(Collectors.toList());
        trelloBoardsKodilla.forEach(trelloBoardDto -> System.out.println(trelloBoardDto.getId() +" " + trelloBoardDto.getName()));
    }
}
