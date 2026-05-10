public class Book {
    private int id;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void borrow() {
        if (this.isAvailable) {
            this.isAvailable = false;
        }
    }

    public void returnBook() {
        if (!this.isAvailable) {
            this.isAvailable = true;
        }
    }

    @Override
    public String toString() {
        return "ID:" + id + " | 书名:" + title + " | 作者:" + author + " | 状态:" + (isAvailable ? "可借" : "已借出");
    }
}