import java.util.ArrayList;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        ArrayList<Book> library = new ArrayList<>();
        
        // 處理空資料、重複編號
        addBook(library, new Book("B003", "Java 程式設計", "CS", 15));
        addBook(library, new Book("B001", "資料結構", "CS", 40));
        addBook(library, new Book("B005", "歷史漫談", "History", 5));
        addBook(library, new Book("B002", "演算法導論", "CS", 20));
        addBook(library, new Book("B003", "重複的書", "CS", 0)); // 故意重複

        System.out.println("---------------------------------");

        // 依編號升冪排序並印出
        Book[] booksById = library.toArray(new Book[0]);
        BookAlgorithms.sortByIdAsc(booksById);
        System.out.println("【依編號升冪排序】");
        for (Book b : booksById) System.out.println(b);

        System.out.println("---------------------------------");

        // 依借閱次數降冪排序並印出
        Book[] booksByCount = library.toArray(new Book[0]);
        BookAlgorithms.sortByBorrowCountDesc(booksByCount);
        System.out.println("【依借閱次數降冪排序】");
        for (Book b : booksByCount) System.out.println(b);

        System.out.println("---------------------------------");

        // Binary Search 測試
        searchId(booksById, "B002");
        searchId(booksById, "B009"); // 找不到的情況

        System.out.println("---------------------------------");

        // Sequential Search 測試
        System.out.println("【查詢 CS 分類書籍】");
        for (Book b : BookAlgorithms.searchByCategory(library, "CS")) {
            System.out.println(b);
        }
    }

    public static void addBook(ArrayList<Book> library, Book newBook) {
        for (Book b : library) {
            if (b.getId().equals(newBook.getId())) {
                System.out.println("【新增失敗】書籍編號 " + newBook.getId() + " 已存在！");
                return;
            }
        }
        library.add(newBook);
    }

    public static void searchId(Book[] sortedBooks, String id) {
        int index = BookAlgorithms.binarySearchById(sortedBooks, id);
        if (index == -1) {
            System.out.println("【查詢失敗】找不到編號為 " + id + " 的書籍。");
        } else {
            System.out.println("【查詢成功】找到書籍: " + sortedBooks[index]);
        }
    }
}