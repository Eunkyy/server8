package umc.umc7wb.category;

public class Category {
    private Long categoryID;
    private String categoryName;
    public Long getId() {
        return categoryID;
    }
    public void setId(Long categoryID) {
        this.categoryID = categoryID;
    }
    public String getName() {
        return categoryName;
    }
    public void setName(String categoryName) {
        this.categoryName = categoryName;
    }
}
