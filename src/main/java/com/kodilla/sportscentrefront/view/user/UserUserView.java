package com.kodilla.sportscentrefront.view.user;

import com.kodilla.sportscentrefront.backend.connect.client.UserCardClient;
import com.kodilla.sportscentrefront.backend.connect.domain.AccountOutDto;
import com.kodilla.sportscentrefront.backend.connect.domain.User;
import com.kodilla.sportscentrefront.backend.connect.domain.UserEditDto;
import com.kodilla.sportscentrefront.backend.connect.domain.UserOldNewDto;
import com.kodilla.sportscentrefront.backend.connect.domain.enums.Goals;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("User user")
@CssImport("./styles/views/login/login-view.css")
public class UserUserView extends VerticalLayout {

    private User user;

    private final H1 userDataHeading = new H1("Your Data");

    private final Label nameLabel = new Label();
    private final Label surnameLabel = new Label();
    private final Label birthLabel = new Label();
    private final Label emailLabel = new Label();
    private final Label phoneLabel = new Label();
    private final Label goalLabel = new Label();
    private final Label studentLabel = new Label();
    private final Label swimmingPoolLabel = new Label();
    private final Label gymLabel = new Label();
    private final Label autoExtensionLabel = new Label();

    private final TextField name = new TextField();
    private final TextField surname = new TextField();
    private final DatePicker birth = new DatePicker("Select date of birth:");
    private final TextField email = new TextField();
    private final TextField phone = new TextField();
    private final Select<Goals> goalField = new Select<>();
    private final Checkbox studentCB = new Checkbox("Student?");
    private final Checkbox swimmingPoolCB = new Checkbox("Swimming pool access?");
    private final Checkbox gymCB = new Checkbox("Gym access?");
    private final Checkbox autoExtensionCB = new Checkbox("Auto extension?");

    private final Label validationField = new Label("Please fill all fields to unlock button");
    private final Button regButton = new Button("Edit");

    private UserOldNewDto userOldNewDto;

    private final Button acceptButton = new Button("OK");

    private final H1 h1OldData = new H1("Old Data");
    private final H1 h1NewData = new H1("New Data");

    private FormLayout dataForm;
    private FormLayout editingForm;
    private FormLayout acceptingForm;

    @Autowired
    public UserUserView(UserCardClient userCardClient) {
        setId("login-view");

        user = VaadinSession.getCurrent().getAttribute(AccountOutDto.class).getUser();

        setLabelsAtFirsCard();

        Button editUserButton = new Button("Edit Data");
        editUserButton.addClickListener(event -> {
            dataForm.setVisible(false);
            editingForm.setVisible(true);
            acceptingForm.setVisible(false);

            name.setValue(user.getName());
            surname.setValue(user.getSurname());
            birth.setValue(user.getBirthDate());
            email.setValue(user.getEmail());
            phone.setValue(user.getPhone());
            goalField.setValue(user.getGoal());
            studentCB.setValue(user.getStudent());
            swimmingPoolCB.setValue(user.getSwimmingPool());
            gymCB.setValue(user.getGym());
            autoExtensionCB.setValue(user.getAutoExtension());
                });

        dataForm = new FormLayout();
        dataForm.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1)
        );
        dataForm.add(userDataHeading, nameLabel, surnameLabel, birthLabel, emailLabel, phoneLabel, goalLabel,
                studentLabel, swimmingPoolLabel, gymLabel, autoExtensionLabel, editUserButton);
        dataForm.setWidthFull();
        add(dataForm);

        //----------------------------------------------------------------------

        H1 editingHeading = new H1("Editing user form");
        editingHeading.getStyle().set("text-align", "center");

        name.setLabel("Name:");
        name.addValueChangeListener(event -> checkButton());

        surname.setLabel("Surname:");
        surname.addValueChangeListener(event -> checkButton());

        DatePicker.DatePickerI18n birthPicker = new DatePicker.DatePickerI18n();
        birthPicker.setDateFormat("yyyy-MM-dd");
        birth.setI18n(birthPicker);
        birth.setAllowedCharPattern("yyyy-MM-dd");
        birth.addValueChangeListener(event -> checkButton());

        email.setLabel("Email:");
        email.addValueChangeListener(event -> checkButton());

        phone.setLabel("Phone:");
        phone.addValueChangeListener(event -> checkButton());

        goalField.setItems(Goals.HEALTH, Goals.MUSCULAR, Goals.WEIGHT);
        goalField.setValue(Goals.HEALTH);
        goalField.setLabel("Choose your main goal:");
        goalField.setRequiredIndicatorVisible(true);
        goalField.setErrorMessage("Goal is required!");
        goalField.addValueChangeListener(event -> checkButton());

        regButton.setEnabled(false);
        regButton.addClickListener(event -> {
            dataForm.setVisible(false);
            editingForm.setVisible(false);
            acceptingForm.setVisible(true);

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
                    user.getSubValidity()
            );

            userOldNewDto = userCardClient.editUserWithClone(userEditDto);
            AccountOutDto accountOutDto = VaadinSession.getCurrent().getAttribute(AccountOutDto.class);
            accountOutDto.setUser(userOldNewDto.getNewUser());
            VaadinSession.getCurrent().setAttribute(AccountOutDto.class, accountOutDto);
            user = VaadinSession.getCurrent().getAttribute(AccountOutDto.class).getUser();
            setLabelsAtFirsCard();

            setAcceptingForm();
        });

        editingForm = new FormLayout();
        editingForm.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1)
        );
        editingForm.add(editingHeading,name, surname, birth, email, phone,
                goalField, studentCB, swimmingPoolCB,
                gymCB, autoExtensionCB, regButton);
        editingForm.setWidthFull();
        editingForm.setVisible(false);
        add(editingForm);

        //---------------------------------------------------------

        acceptingForm = new FormLayout();
        acceptingForm.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 2));
        acceptingForm.setVisible(false);
        add(acceptingForm);

        acceptButton.addClickListener(event -> {
            dataForm.setVisible(true);
            editingForm.setVisible(false);
            acceptingForm.setVisible(false);

            userOldNewDto = new UserOldNewDto();
            acceptingForm.removeAll();
        });
    }

    private void setLabelsAtFirsCard() {
        userDataHeading.getStyle().set("text-align", "center");
        nameLabel.setText("Name: " + user.getName());
        nameLabel.getStyle().set("text-align", "center");
        surnameLabel.setText("Surname: " + user.getSurname());
        surnameLabel.getStyle().set("text-align", "center");
        birthLabel.setText("Date of birth: " + user.getBirthDate());
        birthLabel.getStyle().set("text-align", "center");
        emailLabel.setText("Email: " + user.getEmail());
        emailLabel.getStyle().set("text-align", "center");
        phoneLabel.setText("Phone: " + user.getPhone());
        phoneLabel.getStyle().set("text-align", "center");
        goalLabel.setText("Goal " + user.getGoal());
        goalLabel.getStyle().set("text-align", "center");
        studentLabel.setText("Student: " + user.getStudent());
        studentLabel.getStyle().set("text-align", "center");
        swimmingPoolLabel.setText("Swimming pool: " + user.getSwimmingPool());
        swimmingPoolLabel.getStyle().set("text-align", "center");
        gymLabel.setText("Gym: " + user.getGym());
        gymLabel.getStyle().set("text-align", "center");
        autoExtensionLabel.setText("Auto Extension: " + user.getAutoExtension());
        autoExtensionLabel.getStyle().set("text-align", "center");
    }

    private void setAcceptingForm() {
        acceptingForm.add(
                h1OldData, h1NewData,
                new Label(userOldNewDto.getOldUser().getName()),
                new Label(userOldNewDto.getNewUser().getName()),
                new Label(userOldNewDto.getOldUser().getSurname()),
                new Label(userOldNewDto.getNewUser().getSurname()),
                new Label(userOldNewDto.getOldUser().getBirthDate().toString()),
                new Label(userOldNewDto.getNewUser().getBirthDate().toString()),
                new Label(userOldNewDto.getOldUser().getEmail()),
                new Label(userOldNewDto.getNewUser().getEmail()),
                new Label(userOldNewDto.getOldUser().getPhone()),
                new Label(userOldNewDto.getNewUser().getPhone()),
                new Label(userOldNewDto.getOldUser().getGoal().toString()),
                new Label(userOldNewDto.getNewUser().getGoal().toString()),
                new Label(userOldNewDto.getOldUser().getStudent().toString()),
                new Label(userOldNewDto.getNewUser().getStudent().toString()),
                new Label(userOldNewDto.getOldUser().getSwimmingPool().toString()),
                new Label(userOldNewDto.getNewUser().getSwimmingPool().toString()),
                new Label(userOldNewDto.getOldUser().getGym().toString()),
                new Label(userOldNewDto.getNewUser().getGym().toString()),
                new Label(userOldNewDto.getOldUser().getAutoExtension().toString()),
                new Label(userOldNewDto.getNewUser().getAutoExtension().toString()),
                acceptButton
                );
        acceptingForm.setColspan(acceptButton, 2);
    }

    private void checkButton() {
        if (
                (!name.getValue().isEmpty()) &&
                (!surname.getValue().isEmpty()) &&
                (birth.getValue() != null) &&
                (!email.getValue().isEmpty()) &&
                (!phone.getValue().isEmpty()) &&
                (goalField.getValue().equals(Goals.HEALTH) || goalField.getValue().equals(Goals.MUSCULAR) || goalField.getValue().equals(Goals.WEIGHT)))
        {
            validationField.setVisible(false);
            regButton.setEnabled(true);
        } else {
            validationField.setVisible(true);
            regButton.setEnabled(false);
        }
    }
}
