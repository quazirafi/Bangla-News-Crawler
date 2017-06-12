
package Entity;

public class Newspaper {
    private int newspaperId;
    private String newspaperName;
    private String newspaperLink;

    public String getNewspaperLink() {
        return newspaperLink;
    }

    public void setNewspaperLink(String newspaperLink) {
        this.newspaperLink = newspaperLink;
    }
    
    public int getNewspaperId() {
        return newspaperId;
    }

    public void setNewspaperId(int newspaperId) {
        this.newspaperId = newspaperId;
    }

    public String getNewspaperName() {
        return newspaperName;
    }

    public void setNewspaperName(String newspaperName) {
        this.newspaperName = newspaperName;
    }
    
}
