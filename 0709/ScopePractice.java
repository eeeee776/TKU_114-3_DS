public class ScopePractice {
    public static void main(String[] args) {
        // 在 main 方法中宣告一個區域變數 name
        String name = "Alice";
        
        // 呼叫 printName 方法，並將變數 name 作為參數傳遞過去
        // 這樣 printName 方法就能接收並使用這筆資料
        printName(name);
    }

    // 定義一個帶有 String 參數的方法：printName
    public static void printName(String name) {
        // 這裡的 name 是透過參數傳進來的，因此可以合法使用並輸出
        System.out.println("Name: " + name);
    }
    
}