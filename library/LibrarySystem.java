import java.util.Scanner;

public class LibrarySystem {
    private Library library;
    private Scanner scanner;

    public LibrarySystem() {
        library = new Library();
        scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    removeBook();
                    break;
                case 3:
                    searchBook();
                    break;
                case 4:
                    borrowBook();
                    break;
                case 5:
                    returnBook();
                    break;
                case 6:
                    library.showAllBooks();
                    break;
                case 0:
                    System.out.println("退出系统");
                    return;
                default:
                    System.out.println("无效选择，请重新输入");
            }
        }
    }

    private void showMenu() {
        System.out.println("=== 图书馆管理系统 ===");
        System.out.println("1. 添加图书");
        System.out.println("2. 删除图书");
        System.out.println("3. 查找图书");
        System.out.println("4. 借阅图书");
        System.out.println("5. 归还图书");
        System.out.println("6. 显示所有图书");
        System.out.println("0. 退出");
        System.out.print("请选择功能: ");
    }

    private void addBook() {
        System.out.print("请输入书名: ");
        String title = scanner.nextLine();
        System.out.print("请输入作者: ");
        String author = scanner.nextLine();
        library.addBook(title, author);
        System.out.println("图书添加成功");
    }

    private void removeBook() {
        System.out.print("请输入要删除的图书ID: ");
        int id = scanner.nextInt();
        if (library.removeBook(id)) {
            System.out.println("删除成功");
        } else {
            System.out.println("未找到该图书或图书已借出");
        }
    }

    private void searchBook() {
        System.out.print("请输入要查找的书名或作者: ");
        String keyword = scanner.nextLine();
        library.searchBook(keyword);
    }

    private void borrowBook() {
        System.out.print("请输入要借阅的图书ID: ");
        int id = scanner.nextInt();
        if (library.borrowBook(id)) {
            System.out.println("借阅成功");
        } else {
            System.out.println("图书不存在或已被借出");
        }
    }

    private void returnBook() {
        System.out.print("请输入要归还的图书ID: ");
        int id = scanner.nextInt();
        if (library.returnBook(id)) {
            System.out.println("归还成功");
        } else {
            System.out.println("图书不存在或未被借出");
        }
    }

    public static void main(String[] args) {
        LibrarySystem system = new LibrarySystem();
        system.run();
    }
}