package com.kodilla.sportscentrefront.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "/sport/login")
@PageTitle("SportLogin")
@CssImport("./styles/views/login/login-view.css")
public class LoginView extends Div {

    public LoginView() {
        setId("login-view");
        var username = new TextField("Username");
        var password = new PasswordField("Password");
        add(
                new H1("Please Log In to Your account!"),
                username,
                password,
                new Button("Login")
        );
    }
}
