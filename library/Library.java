import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<Book> books;
    private int nextId;
    private static final String DATA_FILE = "library_data.txt";

    public Library() {
        books = new ArrayList<>();
        nextId = 1;
        loadBooks();
        if (books.isEmpty()) {
            initSampleBooks();
        }
    }

    private void initSampleBooks() {
        addBook("红楼梦", "曹雪芹");
        addBook("西游记", "吴承恩");
        addBook("三国演义", "罗贯中");
        addBook("水浒传", "施耐庵");
        saveBooks();
    }

    private void loadBooks() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String title = parts[1];
                    String author = parts[2];
                    boolean isAvailable = Boolean.parseBoolean(parts[3]);
                    books.add(new Book(id, title, author, isAvailable));
                    nextId = Math.max(nextId, id + 1);
                }
            }
        } catch (IOException e) {
            System.out.println("读取数据失败");
        }
    }

    public void saveBooks() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            for (Book book : books) {
                writer.println(book.getId() + "," + book.getTitle() + "," +
                             book.getAuthor() + "," + book.isAvailable());
            }
        } catch (IOException e) {
            System.out.println("保存数据失败");
        }
    }

    public void addBook(String title, String author) {
        books.add(new Book(nextId++, title, author));
        saveBooks();
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
        saveBooks();
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
                    saveBooks();
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
                    saveBooks();
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