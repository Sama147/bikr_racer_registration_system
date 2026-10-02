package bikr.model;

import bikr.model.enums.CategoryLevel;

import java.time.LocalDate;

public class License {
    private int licenseId;
    private int userId;
    private LocalDate expirationDate;
    private CategoryLevel category;

    public License() { }

    public License(int userId, LocalDate expirationDate, CategoryLevel category) {
        this.userId = userId;
        this.expirationDate = expirationDate;
        this.category = category;
    }

    public int getLicenseId() { return licenseId; }
    public void setLicenseId(int licenseId) { this.licenseId = licenseId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public LocalDate getExpirationDate() { return expirationDate; }
    public void setExpirationDate(LocalDate expirationDate) { this.expirationDate = expirationDate; }

    public CategoryLevel getCategory() { return category; }
    public void setCategory(CategoryLevel category) { this.category = category; }
}