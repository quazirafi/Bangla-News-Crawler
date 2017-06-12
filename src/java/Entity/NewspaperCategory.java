
package Entity;

public class NewspaperCategory {
   private int newspaperCategoryId;
    private String newspaperName;
    private String categoryName;
    private String title;
    private String date;

    public int getNewspaperCategoryId() {
        return newspaperCategoryId;
    }

    public void setNewspaperCategoryId(int newspaperCategoryId) {
        this.newspaperCategoryId = newspaperCategoryId;
    }

    public String getNewspaperName() {
        return newspaperName;
    }

    public void setNewspaperName(String newspaperName) {
        this.newspaperName = newspaperName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
