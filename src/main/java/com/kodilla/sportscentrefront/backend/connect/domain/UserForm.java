package com.kodilla.sportscentrefront.backend.connect.domain;

import com.kodilla.sportscentrefront.backend.connect.client.CardClient;
import com.kodilla.sportscentrefront.backend.connect.client.UserCardClient;
import com.kodilla.sportscentrefront.backend.connect.client.UserClient;
import com.kodilla.sportscentrefront.backend.connect.domain.enums.Goals;
import com.kodilla.sportscentrefront.services.AdminUserService;
import com.kodilla.sportscentrefront.view.admin.AdminUserView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserForm extends FormLayout {

    private TextField name = new TextField("Name:");
    private TextField surname = new TextField("Surname:");
    private DatePicker.DatePickerI18n birthPicker = new DatePicker.DatePickerI18n();
    private DatePicker birth = new DatePicker("Date of birth:");
    private TextField email = new TextField("Email:");
    private TextField phone = new TextField("Phone:");
    private Select<Goals> goalField = new Select<>();
    private Checkbox studentCB = new Checkbox("Student?");
    private Checkbox swimmingPoolCB = new Checkbox("Swimming pool access?");
    private Checkbox gymCB = new Checkbox("Gym access?");
    private Select<Long> cardIds = new Select<>();
    private Checkbox autoExtensionCB = new Checkbox("Auto extension?");
    private DatePicker validation = new DatePicker("Date validate:");

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");

    private Binder<User> binder = new Binder<User>(User.class);

    private AdminUserView adminUserView;

    private AdminUserService adminUserService;

    public UserForm(AdminUserView adminUserView, UserClient userClient, UserCardClient userCardClient, CardClient cardClient) {
        this.adminUserService = AdminUserService.getInstance(userClient, userCardClient);

        birthPicker.setDateFormat("yyyy-MM-dd");
        birth.setI18n(birthPicker);
        birth.setAllowedCharPattern("yyyy-MM-dd");

        goalField.setLabel("Goal:");
        goalField.setItems(Goals.values());

        cardIds.setLabel("Card:");
        List<Long> freeCardsIds = Arrays.asList(cardClient.getCards()).stream()
                        .filter(card -> card.getUser() == null)
                                .map(card -> card.getCardId())
                                        .collect(Collectors.toList());
        cardIds.setItems(freeCardsIds);

        validation.setI18n(birthPicker);
        validation.setAllowedCharPattern("yyyy-MM-dd");

        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(name, surname, birth, email, phone, goalField, studentCB,
                swimmingPoolCB, gymCB, cardIds, autoExtensionCB, validation, buttons);
        this.adminUserView = adminUserView;

        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());

        binder.bindInstanceFields(this);
    }

    private void save() {
        User user = binder.getBean();

        if (user.getUserId() == null) {
            UserCreateDto userCreateDto = new UserCreateDto(
                    user.getName(),
                    user.getSurname(),
                    user.getBirthDate(),
                    user.getEmail(),
                    user.getPhone(),
                    user.getGoal(),
                    user.getStudent(),
                    user.getGym(),
                    user.getSwimmingPool(),
                    user.getCard(),
                    user.getAutoExtension(),
                    user.getSubValidity()
            );
            adminUserService.createUser(userCreateDto);
        } else {
            UserEditDto userEditDto = new UserEditDto(
                    user.getUserId(),
                    user.getName(),
                    user.getSurname(),
                    user.getBirthDate(),
                    user.getEmail(),
                    user.getPhone(),
                    user.getGoal(),
                    user.getStudent(),
                    user.getGym(),
                    user.getSwimmingPool(),
                    user.getCard(),
                    user.getAutoExtension(),
                    user.getSubValidity()
            );
            adminUserService.editUser(userEditDto);
        }

        adminUserView.refresh();
        setUser(null);
    }

    private void delete() {
        User user = binder.getBean();
        adminUserService.delete(user.getUserId());
        adminUserView.refresh();
        setUser(null);
    }

    public void setUser(User user) {
        binder.setBean(user);

        if (user == null) {
            setVisible(false);
        } else {
            setVisible(true);
        }
    }
}
