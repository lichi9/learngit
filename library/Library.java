import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<Book> books;
    private int nextId;

    public Library() {
        books = new ArrayList<>();
        nextId = 1;
        initSampleBooks();
    }

    private void initSampleBooks() {
        addBook("红楼梦", "曹雪芹");
        addBook("西游记", "吴承恩");
        addBook("三国演义", "罗贯中");
        addBook("水浒传", "施耐庵");
    }

    public void addBook(String title, String author) {
        books.add(new Book(nextId++, title, author));
    }

    public boolean removeBook(int id) {
        Book bookToRemove = null;
        for (Book book : books) {
            if (book.getId() == id) {
                bookToRemove = book;
                break;
            }
        }

        if (bookToRemove == null) {
            return false;
        }

        if (!bookToRemove.isAvailable()) {
            return false;
        }

        books.remove(bookToRemove);
        return true;
    }

    public void searchBook(String keyword) {
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().contains(keyword) || book.getAuthor().contains(keyword)) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("未找到相关图书");
        }
    }

    public boolean borrowBook(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                if (book.isAvailable()) {
                    book.borrow();
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public boolean returnBook(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                if (!book.isAvailable()) {
                    book.returnBook();
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public void showAllBooks() {
        if (books.isEmpty()) {
            System.out.println("图书馆暂无图书");
            return;
        }
        System.out.println("=== 所有图书 ===");
        for (Book book : books) {
            System.out.println(book);
        }
    }
}