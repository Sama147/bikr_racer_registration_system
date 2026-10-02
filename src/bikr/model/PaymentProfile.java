package bikr.model;

public class PaymentProfile {
    private int profileId;
    private int userId;
    private String cardNumber;
    private String cardExpiration;

    //default constructor
    public PaymentProfile() { }

    //constructor
    public PaymentProfile(int userId, String cardNumber, String cardExpiration) {
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.cardExpiration = cardExpiration;
    }

    //setters and getters
    public int getProfileId() { return profileId; }
    public void setProfileId(int profileId) { this.profileId = profileId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public String getCardExpiration() { return cardExpiration; }
    public void setCardExpiration(String cardExpiration) { this.cardExpiration = cardExpiration; }
}