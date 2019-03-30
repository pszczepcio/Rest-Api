package com.crud.tasks.controller;

import com.crud.tasks.com.crud.tasks.service.TrelloService;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.mapper.CreatedTrelloCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloService trelloService;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards(){
        return trelloService.fetchTrelloBoards();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoardsKodilla")
    public void getTrelloBoardsKodilla(){
        List<TrelloBoardDto> trelloBoardsKodilla = trelloService.fetchTrelloBoards().stream()
                .filter(trelloBoardDto -> trelloBoardDto.getName().contains("Kodilla") && trelloBoardDto.getName() != null && trelloBoardDto.getId() != null)
                .collect(Collectors.toList());
        trelloBoardsKodilla.forEach(trelloBoardDto -> System.out.println(trelloBoardDto.getId() +" " + trelloBoardDto.getName()));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
    public CreatedTrelloCard createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto){
        return trelloService.createTrelloCard(trelloCardDto);
    }
}
