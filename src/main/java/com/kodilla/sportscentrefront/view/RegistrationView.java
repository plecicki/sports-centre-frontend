package com.kodilla.sportscentrefront.view;

import com.kodilla.sportscentrefront.backend.connect.domain.enums.Goals;
import com.kodilla.sportscentrefront.backend.connect.domain.enums.Role;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;


@Route("sport/registration")
@PageTitle("Registration")
@CssImport("./styles/views/login/login-view.css")
public class RegistrationView extends VerticalLayout {

    private H1 registrationHeading = new H1("Registration Form");
    private ComboBox<Role> roleField = new ComboBox<>("Choose account role:");
    private TextField adminKey = new TextField();
    private TextField name = new TextField();
    private TextField surname = new TextField();
    private DatePicker.DatePickerI18n birthPicker = new DatePicker.DatePickerI18n();
    private DatePicker birth = new DatePicker("Select date of birth:");
    private TextField email = new TextField();
    private TextField phone = new TextField();
    private ComboBox<Goals> goalField = new ComboBox<>("Choose your main goal:");
    private Checkbox studentCB = new Checkbox("Are you student?");
    private Checkbox swimmingPoolCB = new Checkbox("Do you want swimming pool access?");
    private Checkbox gymCB = new Checkbox("Do you want gym access?");
    private Checkbox autoExtensionCB = new Checkbox("Do you want auto extension of your subscription?");
    private TextField username = new TextField();
    private PasswordField password = new PasswordField();
    private PasswordField passwordCard = new PasswordField();
    private Label validationField = new Label("Please fill all fields to unlock button");
    private Button regButton = new Button("Register");

    @Autowired
    public RegistrationView() {
        setId("login-view");
        add(registrationHeading);

        roleField.setItems(Role.USER, Role.ADMIN);
        roleField.setValue(Role.USER);
        roleField.setAllowCustomValue(false);
        add(roleField);
        roleField.addValueChangeListener(event -> {
            checkButton();
            if (roleField.getValue().equals(Role.ADMIN)) {
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
        goalField.setAllowCustomValue(false);
        add(goalField);
        goalField.addValueChangeListener(event -> checkButton());

        add(studentCB);
        add(swimmingPoolCB);
        add(gymCB);
        add(autoExtensionCB);

        username.setLabel("Username:");
        add(username);
        username.addValueChangeListener(event -> checkButton());

        password.setLabel("Password to account:");
        add(password);
        password.addValueChangeListener(event -> checkButton());

        passwordCard.setLabel("Password to card:");
        add(passwordCard);
        passwordCard.addValueChangeListener(event -> checkButton());

        add(validationField);

        regButton.setEnabled(false);
        add(regButton);
    }

    private void checkButton() {
        if ((roleField.getValue().equals(Role.ADMIN) || roleField.getValue().equals(Role.USER)) &&
            (roleField.getValue().equals(Role.ADMIN) && !adminKey.getValue().isEmpty()) &&
        (!name.getValue().isEmpty()) &&
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
        }
    }
}
