import java.util.ArrayList;

public class BookAlgorithms {
    
    // 依編號升冪排序
    public static void sortByIdAsc(Book[] books) {
        Book[] temp = new Book[books.length];
        sortByIdAsc(books, temp, 0, books.length - 1);
    }

    private static void sortByIdAsc(Book[] books, Book[] temp, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        sortByIdAsc(books, temp, left, mid);
        sortByIdAsc(books, temp, mid + 1, right);
        
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (books[i].getId().compareTo(books[j].getId()) <= 0) {
                temp[k++] = books[i++];
            } else {
                temp[k++] = books[j++];
            }
        }
        while (i <= mid) temp[k++] = books[i++];
        while (j <= right) temp[k++] = books[j++];
        for (int idx = left; idx <= right; idx++) books[idx] = temp[idx];
    }

    // 依借閱次數降冪排序
    public static void sortByBorrowCountDesc(Book[] books) {
        Book[] temp = new Book[books.length];
        sortByBorrowCountDesc(books, temp, 0, books.length - 1);
    }

    private static void sortByBorrowCountDesc(Book[] books, Book[] temp, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        sortByBorrowCountDesc(books, temp, left, mid);
        sortByBorrowCountDesc(books, temp, mid + 1, right);
        
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (books[i].getBorrowCount() >= books[j].getBorrowCount()) {
                temp[k++] = books[i++];
            } else {
                temp[k++] = books[j++];
            }
        }
        while (i <= mid) temp[k++] = books[i++];
        while (j <= right) temp[k++] = books[j++];
        for (int idx = left; idx <= right; idx++) books[idx] = temp[idx];
    }

    // Binary Search 依排序後編號查詢
    public static int binarySearchById(Book[] books, String targetId) {
        int low = 0, high = books.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = targetId.compareTo(books[mid].getId());
            
            if (comparison == 0) return mid;
            if (comparison < 0) high = mid - 1;
            else low = mid + 1;
        }
        return -1;
    }

    // Sequential Search 依分類找出全部書籍
    public static ArrayList<Book> searchByCategory(ArrayList<Book> books, String category) {
        ArrayList<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.getCategory().equalsIgnoreCase(category)) {
                results.add(book);
            }
        }
        return results;
    }
}