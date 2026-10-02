package bikr.view;

import bikr.model.Race;

import java.util.List;
import java.util.Scanner;

/**
 * View for the race selection and registration flow.
 * All prompts and messages for RaceRegistrationController live here.
 */
public class RaceRegistrationView {

    private final Scanner sc;

    public RaceRegistrationView(Scanner sc) {
        this.sc = sc;
    }

    /** Prints the race menu and returns the user's numeric choice. */
    public int showRaceMenu(List<Race> races) {
        System.out.println("\nChoose one of the following races:");
        for (int i = 0; i < races.size(); i++) {
            Race r = races.get(i);
            String type = r.isRaceOfficiality() ? "official" : "Unofficial";
            System.out.println((i + 1) + "-" + r.getRaceName() + " - " + type);
        }
        System.out.println("0- exit");
        System.out.println("5- sign out");
        System.out.print("> ");
        return readInt();
    }

    public void showInvalidChoice() {
        System.out.println("invalid choice");
    }

    public void showRegistrationClosed() {
        System.out.println("Race registration is closed");
    }

    public boolean promptPurchaseLicense() {
        System.out.print("you have no license, want to purchase license using current card info? yes or no: ");
        return sc.nextLine().trim().equalsIgnoreCase("yes");
    }

    public void showLicensePurchased() {
        System.out.println("License purchased, podiums set to 0 with Category level CAT-5");
    }

    public boolean promptRenewLicense() {
        System.out.print("you have no valid license, want renew it using current card info? yes or no: ");
        return sc.nextLine().trim().equalsIgnoreCase("yes");
    }

    public void showLicenseRenewed() {
        System.out.println("License renewed");
    }

    public void showCantRegister() {
        System.out.println("can't register race");
    }

    public void showCategoryFull() {
        System.out.println("can't register race, no available seats for your category");
    }

    public void showRegistrationSuccess() {
        System.out.println("race registered successfully");
    }

    public void showSignedOut() {
        System.out.println("\nsigned out");
    }

    public void showBye() {
        System.out.println("byeee");
    }

    private int readInt() {
        while (true) {
            try {
                String s = sc.nextLine().trim();
                if (s.isEmpty()) continue;
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.print("please enter a number: ");
            }
        }
    }
}