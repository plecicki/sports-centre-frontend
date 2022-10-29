package com.kodilla.sportscentrefront.services;

import com.kodilla.sportscentrefront.backend.connect.client.AccountClient;
import com.kodilla.sportscentrefront.backend.connect.domain.AccountInDto;
import com.kodilla.sportscentrefront.backend.connect.domain.AccountOutDto;
import com.kodilla.sportscentrefront.backend.connect.domain.enums.Role;
import com.kodilla.sportscentrefront.view.admin.*;
import com.kodilla.sportscentrefront.view.AfterLoginView;
import com.kodilla.sportscentrefront.view.LogoutView;
import com.kodilla.sportscentrefront.view.user.UserInvoiceView;
import com.kodilla.sportscentrefront.view.user.UserOrderView;
import com.kodilla.sportscentrefront.view.user.UserUserView;
import com.kodilla.sportscentrefront.view.user.UserView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.VaadinSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AccountClient accountClient;

    public record AuthorizedRoute(String route, String name, Class<? extends Component> view) {

    }

    public String authenticate(String username, String password) {
        Object account = accountClient.login(new AccountInDto(username, password));
        if (account.getClass().equals(AccountOutDto.class)) {
            VaadinSession.getCurrent().setAttribute(AccountOutDto.class, (AccountOutDto) account);
            createRoutes(((AccountOutDto) account).getRole());
        } else {
            return (String) account;
        }
        return "Success logging!";
    }

    private void createRoutes(Role role) {
        getAuthorizedRoutes(role).stream()
                .forEach(route ->
                        RouteConfiguration.forSessionScope().setRoute(
                                route.route, route.view, AfterLoginView.class));
    }

    public List<AuthorizedRoute> getAuthorizedRoutes(Role role) {
        var routes = new ArrayList<AuthorizedRoute>();
        if (role.equals(Role.USER)) {
            routes.add(new AuthorizedRoute("sport/user", "User", UserView.class));
            routes.add(new AuthorizedRoute("sport/user/user", "Your Data Edit", UserUserView.class));
            routes.add(new AuthorizedRoute("sport/user/invoice", "Your Invoices", UserInvoiceView.class));
            routes.add(new AuthorizedRoute("sport/user/order", "Make an Order", UserOrderView.class));

            routes.add(new AuthorizedRoute("sport/logout", "Logout", LogoutView.class));
        } else if (role.equals(Role.ADMIN)) {
            routes.add(new AuthorizedRoute("sport/admin", "Admin", AdminView.class));
            routes.add(new AuthorizedRoute("sport/admin/card", "Cards", AdminCardView.class));
            routes.add(new AuthorizedRoute("sport/admin/user", "Users", AdminUserView.class));
            routes.add(new AuthorizedRoute("sport/admin/invoice", "Invoices", AdminInvoiceView.class));
            routes.add(new AuthorizedRoute("sport/admin/order", "Orders", AdminOrderView.class));

            routes.add(new AuthorizedRoute("sport/logout", "Logout", LogoutView.class));
        }
        return routes;
    }
}
