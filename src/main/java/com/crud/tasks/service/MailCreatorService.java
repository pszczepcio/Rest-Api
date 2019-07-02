package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {
    @Autowired
    private Company company;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://pszczepcio.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("add_task", "Add a new task in Trello");
        context.setVariable("company_name", company.getCompanyName());
        context.setVariable("company_email", company.getCompanyEmail());
        context.setVariable("company_phone", company.getCompanyPhone());
        context.setVariable("goodbye", "Piotr Szczepański");
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
