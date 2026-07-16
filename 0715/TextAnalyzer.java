import java.util.Scanner;

public class TextAnalyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. 輸入一行非空白文字 (包含空字串與全空白重新輸入的防呆機制)
        String text = getValidInput(scanner);

        // 2. 顯示原始字元數
        System.out.println("原始字元數: " + text.length());

        // 3. 使用 trim() 後顯示有效字元數
        System.out.println("有效字元數 (trim後): " + countTrimmedCharacters(text));

        // 4. 使用空白切割單字，連續空白也要正確處理
        String[] words = splitIntoWords(text);
        
        // 5. 顯示單字數量
        System.out.println("單字數量: " + words.length);

        // 6. 計算英文字母母音 a、e、i、o、u 的總數
        System.out.println("母音總數: " + countVowels(text));

        // 7. 找出最長單字
        System.out.println("最長單字: " + findLongestWord(words));

        // 8. 輸入關鍵字並顯示出現次數，忽略大小寫
        System.out.print("請輸入要尋找的關鍵字: ");
        String keyword = scanner.nextLine();
        System.out.println("關鍵字「" + keyword + "」出現次數: " + countKeywordOccurrences(words, keyword));

        scanner.close();
    }

    // 自訂方法 1：取得有效輸入（空字串與全空白輸入會要求重新輸入）
    public static String getValidInput(Scanner scanner) {
        String input;
        while (true) {
            System.out.print("請輸入一行非空白文字: ");
            input = scanner.nextLine();
            // 檢查是否為空或是全空白
            if (input != null && !input.trim().isEmpty()) {
                break;
            }
            System.out.println("輸入無效 (不可為空或全空白)，請重新輸入！");
        }
        return input;
    }

    // 自訂方法 2：計算 trim() 後的字元數
    public static int countTrimmedCharacters(String text) {
        return text.trim().length();
    }

    // 自訂方法 3：將字串切割為單字陣列（處理連續空白不會產生錯誤單字數）
    public static String[] splitIntoWords(String text) {
        // 先 trim 避免開頭空白導致切割出空字串，接著用 \\s+ 切割一個以上的空白
        return text.trim().split("\\s+");
    }

    // 自訂方法 4：計算母音總數
    public static int countVowels(String text) {
        int count = 0;
        // 統一轉小寫方便比對
        String lowerText = text.toLowerCase();
        for (int i = 0; i < lowerText.length(); i++) {
            char c = lowerText.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                count++;
            }
        }
        return count;
    }

    // 自訂方法 5：找出最長單字
    public static String findLongestWord(String[] words) {
        String longest = "";
        for (String word : words) {
            if (word.length() > longest.length()) {
                longest = word;
            }
        }
        return longest;
    }

    // 自訂方法 6：計算關鍵字出現次數（忽略大小寫）
    public static int countKeywordOccurrences(String[] words, String keyword) {
        int count = 0;
        for (String word : words) {
            // equalsIgnoreCase 可直接忽略大小寫進行比對
            if (word.equalsIgnoreCase(keyword)) {
                count++;
            }
        }
        return count;
    }
}