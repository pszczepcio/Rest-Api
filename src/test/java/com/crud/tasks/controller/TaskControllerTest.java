package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private TaskRepository taskRepository;

    @Test
    public void shouldGetEmptyTasksTest() throws Exception {
        //Given
        List<TaskDto> taskDtoList = new ArrayList<>();
        when(taskMapper.mapToTaskDtoList(dbService.getAllTasks())).thenReturn(taskDtoList);

        //When & Then
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldGetTasksTest() throws Exception {
        //given
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(new TaskDto(1L, "task", "content task"));

        when(taskMapper.mapToTaskDtoList(dbService.getAllTasks())).thenReturn(taskDtoList);

        //When & Then
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("task")))
                .andExpect(jsonPath("$[0].content", is("content task")));
    }

    @Test
    public void shouldCreateTaskTest() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(
                2L,
                "task",
                "content task");

        Task taskAfterSave = new Task(
                2L,
                "task",
                "content task");

        when(dbService.saveTask(taskMapper.mapToTask(any(TaskDto.class)))).thenReturn(taskAfterSave);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When &Then
        mockMvc.perform(post("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.title", is("task")))
                .andExpect(jsonPath("$.content", is("content task")));
    }

    @Test
    public void shouldUpdateTaskTest() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(
                1L,
                "task1",
                "content task1");

        TaskDto taskDtoAfterUpdate = new TaskDto(
                1L,
                "task1",
                "content task1");

        when(taskMapper.mapToTaskDto(dbService.saveTask(taskMapper.mapToTask(any(TaskDto.class))))).thenReturn(taskDtoAfterUpdate);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(put("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("task1")))
                .andExpect(jsonPath("$.content", is("content task1")));
    }

    @Test
    public void shouldGetTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(
                1L,
                "task1",
                "content1");

        Task task = new Task(
                1L,
                "task1",
                "content1");

        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);
        when(dbService.getTask(taskDto.getId())).thenReturn(Optional.of(task));

        //When & Then
        mockMvc.perform(get("/v1/tasks/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("task1")))
                .andExpect(jsonPath("$.content", is("content1")));
    }

    @Test
    public void shouldDeleteTaskTest() throws Exception {
        //Given

        //Whem & Then
        mockMvc.perform(delete("/v1/tasks/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
