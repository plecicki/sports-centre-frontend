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
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    private List<Long> nonUsedCards = new ArrayList<>();

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");

    private Binder<User> binder = new BeanValidationBinder<User>(User.class);

    private AdminUserView adminUserView;

    private AdminUserService adminUserService;

    public UserForm(AdminUserView adminUserView, UserClient userClient, UserCardClient userCardClient, CardClient cardClient) {
        this.adminUserService = AdminUserService.getInstance(userClient, userCardClient);

        name.setRequired(true);
        surname.setRequired(true);
        birth.setRequired(true);
        email.setRequired(true);
        phone.setRequired(true);
        goalField.setRequiredIndicatorVisible(true);
        cardIds.setRequiredIndicatorVisible(true);
        validation.setRequired(true);

        birthPicker.setDateFormat("yyyy-MM-dd");
        birth.setI18n(birthPicker);
        birth.setAllowedCharPattern("yyyy-MM-dd");

        goalField.setLabel("Goal:");
        goalField.setItems(Goals.values());
        goalField.setEmptySelectionAllowed(true);

        cardIds.setLabel("Card:");
        cardIds.setEmptySelectionAllowed(true);

        nonUsedCards = setSelectWithEmptyCardIds(cardClient, userClient);
        cardIds.setItems(nonUsedCards);

        validation.setI18n(birthPicker);
        validation.setAllowedCharPattern("yyyy-MM-dd");

        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(name, surname, birth, email, phone, goalField, studentCB,
                swimmingPoolCB, gymCB, cardIds, autoExtensionCB, validation, buttons);
        this.adminUserView = adminUserView;

        save.addClickListener(event -> save(userClient, cardClient));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        delete.addClickListener(event -> delete(userClient, cardClient));

        binder.bindInstanceFields(this);
    }

    private void save(UserClient userClient, CardClient cardClient) {
        User user = binder.getBean();

        if (user.getUserId() == null) {
            user.setCard(new Card(cardIds.getValue()));
            UserCreateDto userCreateDto = new UserCreateDto(
                    name.getValue(),
                    surname.getValue(),
                    birth.getValue(),
                    email.getValue(),
                    phone.getValue(),
                    goalField.getValue(),
                    studentCB.getValue(),
                    gymCB.getValue(),
                    swimmingPoolCB.getValue(),
                    user.getCard(),
                    autoExtensionCB.getValue(),
                    validation.getValue()
            );
            adminUserService.createUser(userCreateDto);
        } else {
            user.setCard(new Card(cardIds.getValue()));
            UserEditDto userEditDto = new UserEditDto(
                    user.getUserId(),
                    name.getValue(),
                    surname.getValue(),
                    birth.getValue(),
                    email.getValue(),
                    phone.getValue(),
                    goalField.getValue(),
                    studentCB.getValue(),
                    gymCB.getValue(),
                    swimmingPoolCB.getValue(),
                    user.getCard(),
                    autoExtensionCB.getValue(),
                    validation.getValue()
            );
            adminUserService.editUser(userEditDto);
        }

        adminUserView.refresh();
        setUser(null, false, userClient, cardClient);
    }

    private void delete(UserClient userClient, CardClient cardClient) {
        User user = binder.getBean();
        adminUserService.delete(user.getUserId());
        adminUserView.refresh();
        setUser(null, false, userClient, cardClient);
    }

    public void setUser(User user, Boolean createUser, UserClient userClient, CardClient cardClient) {

        binder.setBean(user);

        if (user == null) {
            setVisible(false);
        } else {
            setVisible(true);

            if (!createUser) {
                birth.setValue(user.getBirthDate());
                goalField.setValue(user.getGoal());
                studentCB.setValue(user.getStudent());
                swimmingPoolCB.setValue(user.getSwimmingPool());
                gymCB.setValue(user.getGym());
                if (user.getCard() != null) {
                    List<Long> nonUsedCardsIds = setSelectWithEmptyCardIds(cardClient, userClient);
                    List<Long> nonUsedPlusActual = new ArrayList<>();
                    for (Long nonUsed: nonUsedCardsIds) {
                        nonUsedPlusActual.add(nonUsed);
                    }
                    nonUsedPlusActual.add(user.getCard().getCardId());
                    cardIds.setItems(nonUsedPlusActual);
                    cardIds.setValue(user.getCard().getCardId());
                } else {
                    List<Long> nonUsedCardsIds = setSelectWithEmptyCardIds(cardClient, userClient);
                    cardIds.setItems(nonUsedCardsIds);
                    cardIds.setValue(null);
                }
                autoExtensionCB.setValue(user.getAutoExtension());
                validation.setValue(user.getSubValidity());
            } else {
                birth.setValue(null);
                goalField.setValue(null);
                studentCB.setValue(false);
                swimmingPoolCB.setValue(false);
                gymCB.setValue(false);

                List<Long> nonUsedCardsIds = setSelectWithEmptyCardIds(cardClient, userClient);
                cardIds.setItems(nonUsedCardsIds);
                cardIds.setValue(null);

                autoExtensionCB.setValue(false);
                validation.setValue(null);
            }
        }
    }

    private List<Long> setSelectWithEmptyCardIds(CardClient cardClient, UserClient userClient) {
        List<Long> allCardsIds = Arrays.asList(cardClient.getCards()).stream()
                .map(card -> card.getCardId())
                .toList();
        List<Long> usedCards = Arrays.asList(userClient.getUsers()).stream()
                .map(user -> {
                    if (user.getCard() != null) {
                        return user.getCard().getCardId();
                    } else {
                        return 0L;
                    }
                })
                .toList();
        return allCardsIds.stream()
                .filter(id -> !usedCards.contains(id))
                .toList();
    }
}
