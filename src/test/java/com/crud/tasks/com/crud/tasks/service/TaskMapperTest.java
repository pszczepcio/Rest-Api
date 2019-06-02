package com.crud.tasks.com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
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
public class TaskMapperTest {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void shouldMapToTask() {
        //given
        TaskDto taskDto = new TaskDto(1L, "task", "test_task1");

        //when
        Task testTask = taskMapper.mapToTask(taskDto);

        //then
        assertEquals(1L, testTask.getId().longValue());
        assertEquals("task", testTask.getTitle());
        assertEquals("test_task1", testTask.getContent());
    }

    @Test
    public void shouldMapToTaskDto () {
        //given
        Task task = new Task(1L, "task", "test_task1");

        //when
        TaskDto testTaskDto = taskMapper.mapToTaskDto(task);

        //then
        assertEquals(1L, testTaskDto.getId().longValue());
        assertEquals("task", testTaskDto.getTitle());
        assertEquals("test_task1", testTaskDto.getContent());
    }

    @Test
    public void shouldMapToTaskDtoList() {
        //given
        Task task1 = new Task(1L, "task1", "test_task1");
        Task task2 = new Task(2L, "task2", "test_task2");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);

        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertEquals(2, taskDtoList.size());
    }
}
