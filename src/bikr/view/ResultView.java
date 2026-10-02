package bikr.view;

/**
 * View for the post-registration race-day simulation.
 * All messages for ResultController live here.
 */
public class ResultView {

    public void showRaceDayStarted() {
        System.out.println();
        System.out.println("race date came and results have been posted...");
    }

    public void showThirdPlace() {
        System.out.println("you won 3rd place!");
    }

    public void showCategoryUpgraded() {
        System.out.println("Podiums reached 5 — category upgraded!");
    }

    public void showAlreadyAtTop() {
        System.out.println("Already at top category. Podiums reset to 0.");
    }

    public void showPodiumsIncreased(int newPodiums) {
        System.out.println("Podiums increased to " + newPodiums);
    }
}