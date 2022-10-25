package com.kodilla.sportscentrefront.view;

import com.kodilla.sportscentrefront.backend.connect.client.AccountClient;
import com.kodilla.sportscentrefront.backend.connect.client.CardClient;
import com.kodilla.sportscentrefront.backend.connect.client.UserCardClient;
import com.kodilla.sportscentrefront.backend.connect.domain.*;
import com.kodilla.sportscentrefront.backend.connect.domain.enums.CardStatus;
import com.kodilla.sportscentrefront.backend.connect.domain.enums.Goals;
import com.kodilla.sportscentrefront.backend.connect.domain.enums.Role;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;


@Route("sport/registration")
@PageTitle("Registration")
@CssImport("./styles/views/login/login-view.css")
public class RegistrationView extends VerticalLayout {

    private H1 registrationHeading = new H1("Registration Form");
    private Select<Role> roleField = new Select<>();
    private TextField adminKey = new TextField();
    private TextField name = new TextField();
    private TextField surname = new TextField();
    private DatePicker.DatePickerI18n birthPicker = new DatePicker.DatePickerI18n();
    private DatePicker birth = new DatePicker("Select date of birth:");
    private TextField email = new TextField();
    private TextField phone = new TextField();
    private Select<Goals> goalField = new Select<>();
    private Checkbox studentCB = new Checkbox("Are you student?");
    private Checkbox swimmingPoolCB = new Checkbox("Do you want swimming pool access?");
    private Checkbox gymCB = new Checkbox("Do you want gym access?");
    private Checkbox autoExtensionCB = new Checkbox("Do you want auto extension of your subscription?");
    private TextField username = new TextField();
    private PasswordField password = new PasswordField();
    private PasswordField passwordCard = new PasswordField();
    private Label validationField = new Label("Please fill all fields to unlock button");
    private Button regButton = new Button("Register");

    private Boolean userTaken = true;

    @Autowired
    public RegistrationView(AccountClient accountClient, UserCardClient userCardClient,
                            CardClient cardClient) {
        setId("login-view");
        add(registrationHeading);

        roleField.setItems(Role.USER, Role.ADMIN);
        roleField.setValue(Role.USER);
        roleField.setLabel("Choose account role:");
        roleField.setRequiredIndicatorVisible(true);
        roleField.setErrorMessage("Role is required");
        add(roleField);
        roleField.addValueChangeListener(event -> {
            checkButton();
            if (Role.ADMIN.equals(roleField.getValue())) {
                adminKey.setVisible(true);
            } else {
                adminKey.setVisible(false);
            }
        });

        adminKey.setClearButtonVisible(true);
        adminKey.setLabel("Admin creating key:");
        adminKey.setVisible(false);
        add(adminKey);
        adminKey.addValueChangeListener(event -> checkButton());

        name.setLabel("Name:");
        add(name);
        name.addValueChangeListener(event -> checkButton());

        surname.setLabel("Surname:");
        add(surname);
        surname.addValueChangeListener(event -> checkButton());

        birthPicker.setDateFormat("yyyy-MM-dd");
        birth.setI18n(birthPicker);
        birth.setAllowedCharPattern("yyyy-MM-dd");
        add(birth);
        birth.addValueChangeListener(event -> checkButton());

        email.setLabel("Email:");
        add(email);
        email.addValueChangeListener(event -> checkButton());

        phone.setLabel("Phone:");
        add(phone);
        phone.addValueChangeListener(event -> checkButton());

        goalField.setItems(Goals.HEALTH, Goals.MUSCULAR, Goals.WEIGHT);
        goalField.setLabel("Choose your main goal:");
        goalField.setRequiredIndicatorVisible(true);
        goalField.setErrorMessage("Goal is required!");

        add(goalField);
        goalField.addValueChangeListener(event -> checkButton());

        add(studentCB);
        add(swimmingPoolCB);
        add(gymCB);
        add(autoExtensionCB);

        username.setLabel("Username:");
        add(username);
        username.addValueChangeListener(event -> {
            userTaken = accountClient.checkIfAccountExistsByUsername(username.getValue());
            if (userTaken) {
                username.setHelperText("This username is taken by someone else!");
            } else {
                username.setHelperText("This username is correct :-)");
            }
            checkButton();
        });

        password.setLabel("Password to account:");
        add(password);
        password.addValueChangeListener(event -> checkButton());

        passwordCard.setLabel("Password to card:");
        add(passwordCard);
        passwordCard.addValueChangeListener(event -> checkButton());

        add(validationField);

        regButton.setEnabled(false);
        add(regButton);
        regButton.addClickListener(event -> {
            final String info = buttonEvent(accountClient, userCardClient, cardClient);
            Notification.show(info);
            if (info.contains("Success")) {
                UI.getCurrent().navigate("/sport/login");
            }
        });
    }

    private void checkButton() {
        if ((Role.ADMIN.equals(roleField.getValue()) || Role.USER.equals(roleField.getValue())) &&
        ((Role.ADMIN.equals(roleField.getValue()) && !adminKey.getValue().isEmpty()) || Role.USER.equals(roleField.getValue())) &&
        (!name.getValue().isEmpty()) &&
        (!userTaken) &&
        (!surname.getValue().isEmpty()) &&
        (!birth.getValue().toString().isEmpty()) &&
        (!email.getValue().isEmpty()) &&
        (!phone.getValue().isEmpty()) &&
        (goalField.getValue().equals(Goals.HEALTH) || goalField.getValue().equals(Goals.MUSCULAR) || goalField.getValue().equals(Goals.WEIGHT)) &&
        (!username.getValue().isEmpty()) &&
        (!password.getValue().isEmpty()) &&
        (!passwordCard.getValue().isEmpty()))
        {
            validationField.setVisible(false);
            regButton.setEnabled(true);
        } else {
            validationField.setVisible(true);
            regButton.setEnabled(false);
        }
    }

    private String buttonEvent(AccountClient accountClient, UserCardClient userCardClient,
                             CardClient cardClient) {
        CardCreateDto cardCreateDto = new CardCreateDto(
                null,
                passwordCard.getValue(),
                CardStatus.AVAILABLE
        );
        Card card = cardClient.createCard(cardCreateDto);
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
                card,
                autoExtensionCB.getValue(),
                LocalDate.now().plusMonths(1)
        );
        User user = userCardClient.createUser(userCreateDto);
        AccountCreateDto accountCreateDto = new AccountCreateDto(
                username.getValue(),
                password.getValue(),
                roleField.getValue(),
                user,
                adminKey.getValue()
        );
        String info = accountClient.createAccount(accountCreateDto);
        return info;
    }
}
