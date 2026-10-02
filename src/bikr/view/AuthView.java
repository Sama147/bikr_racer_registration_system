package bikr.view;

import java.util.Scanner;

/**
 * View for the login flow.
 * All input prompts and output messages for AuthController live here.
 */
public class AuthView {

    private final Scanner sc;

    public AuthView(Scanner sc) {
        this.sc = sc;
    }

    public void showLoginHeader() {
        System.out.println("WELCOME TO BIKR, RACER. Log in to register a race");
    }

    public String promptEmail() {
        System.out.print("email: ");
        return sc.nextLine().trim();
    }

    public String promptPassword() {
        System.out.print("password: ");
        return sc.nextLine().trim();
    }

    public void showAccountDoesNotExist() {
        System.out.println("account does not exist");
    }

    public void showWrongCredentials() {
        System.out.println("wrong credentials");
    }

    public void showWelcome(String fullName) {
        System.out.println("\nWelcome " + fullName + "!");
    }

    public void showLoginFailed() {
        System.out.println("login failed, try again\n");
    }
}