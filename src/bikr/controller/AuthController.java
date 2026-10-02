package bikr.controller;

import bikr.model.Racer;
import bikr.model.User;
import bikr.repository.UserRepository;
import bikr.view.AuthView;

/**
 * Handles login. All output goes through AuthView.
 */
public class AuthController {

    private final UserRepository userRepo;
    private final AuthView       view;

    public AuthController(UserRepository userRepo, AuthView view) {
        this.userRepo = userRepo;
        this.view     = view;
    }

    /** Prompts for credentials and returns the logged-in Racer, or null on failure. */
    public Racer login() {
        view.showLoginHeader();
        String email    = view.promptEmail();
        String password = view.promptPassword();

        User user = userRepo.findByEmail(email);
        if (user == null || !(user instanceof Racer)) {
            view.showAccountDoesNotExist();
            view.showLoginFailed();
            return null;
        }
        if (!user.getPassword().equals(password)) {
            view.showWrongCredentials();
            view.showLoginFailed();
            return null;
        }
        view.showWelcome(user.getFullName());
        return (Racer) user;
    }
}