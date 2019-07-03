package com.crud.tasks.scheduler;

import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.UserConfig;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    @Autowired
    private UserConfig userConfig;

    @Autowired
    private SimpleMailService simpleMailService;

    @Autowired
    private TaskRepository taskRepository;

    private static final String SUBJECT = "Tasks: Once a day mail";

    @Scheduled(fixedDelay = 10000000)
    public void sendInformationEmail() {
        long size = taskRepository.count();
        if (size == 1) {
            simpleMailService.sendNumberOfTasks(new Mail(
                    userConfig.getUserMail(),
                    SUBJECT,
                    "Currently in database you got: " + size + " task"
            ));
        } else {
            simpleMailService.sendNumberOfTasks(new Mail(
                    userConfig.getUserMail(),
                    SUBJECT,
                    "Currently in database you got: " + size + " tasks"/*,
                    ""*/));
        }
    }
}
// cron = "0 0 10 * * *"