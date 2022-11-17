package com.kodilla.sportscentrefront.view;

import com.kodilla.sportscentrefront.backend.connect.domain.AccountOutDto;
import com.kodilla.sportscentrefront.backend.connect.domain.enums.Role;
import com.kodilla.sportscentrefront.services.AuthService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route(value = "/sport/login")
@PageTitle("Login")
@CssImport("./styles/views/login/login-view.css")
public class LoginView extends VerticalLayout {

    public LoginView(AuthService authService) {
        setId("login-view");

        FormLayout loginForm = HomeView.setLoginForm();
        //add(loginForm);

        var username = new TextField("Username");
        var password = new PasswordField("Password");

        Button loginButton = new Button("Login", event -> {
            String auth = authService.authenticate(username.getValue(), password.getValue());
            if (auth.contains("Success")) {
                Role role = VaadinSession.getCurrent().getAttribute(AccountOutDto.class).getRole();
                if (role == Role.USER) UI.getCurrent().navigate("/sport/user");
                if (role == Role.ADMIN) UI.getCurrent().navigate("/sport/admin");
            } else {
                Notification.show(auth);
            }
        });

        loginButton.addClickShortcut(Key.ENTER);

        Button registerButton = new Button("Register", event -> {
            UI.getCurrent().navigate("/sport/registration");
        });

        add(
                new H1("Please Log In to Your account!"),
                loginForm,
                username,
                password,
                loginButton,
                registerButton
        );
    }
}
