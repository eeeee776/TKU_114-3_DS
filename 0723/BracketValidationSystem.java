import java.util.ArrayDeque;
import java.util.Deque;

public class BracketValidationSystem {

    /**
     * 核心驗證邏輯：使用 Stack 檢查字串中的括號是否平衡
     */
    public static boolean isValid(String text) {
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            // 1. 遇到左括號：直接 push 進 Stack (代表開啟了一個新的層級)
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } 
            // 2. 遇到右括號：準備進行關閉與配對檢查
            else if (ch == ')' || ch == ']' || ch == '}') {
                
                // 防護：如果此時 Stack 是空的，代表「缺少左括號」來配對這個右括號
                if (stack.isEmpty()) {
                    return false;
                }

                // 取出 Stack 頂端的左括號 (也就是最近一次開啟的括號)
                char top = stack.pop();

                // 檢查左右括號是否為同一種類型 (順序錯誤或類型不符)
                if (!isMatch(top, ch)) {
                    return false;
                }
            }
            // 3. 遇到非括號字元 (例如字母、數字、空白)：直接忽略，什麼都不做
        }

        // 4. 字串讀取完畢後，如果 Stack 內還有剩下的左括號，代表「缺少右括號」來關閉它們
        return stack.isEmpty();
    }

    /**
     * 將配對判斷拆分成獨立的 method，讓主程式邏輯更乾淨
     */
    private static boolean isMatch(char left, char right) {
        return (left == '(' && right == ')') ||
               (left == '[' && right == ']') ||
               (left == '{' && right == '}');
    }

    public static void main(String[] args) {
        System.out.println("=== 括號驗證測試 ===");
        
        // 測試 1：正確的多層巢狀與文字忽略
        System.out.println("1. public void test() { if(true) { a[0] = 1; } } -> " 
                           + isValid("public void test() { if(true) { a[0] = 1; } }")); // true

        // 測試 2：順序錯誤 (交叉交錯)
        System.out.println("2. ([)] -> " + isValid("([)]")); // false

        // 測試 3：缺少左括號 (underflow 防護)
        System.out.println("3. (a + b) ] -> " + isValid("(a + b) ]")); // false

        // 測試 4：缺少右括號 (結束時 Stack 不為空)
        System.out.println("4. ((a + b) -> " + isValid("((a + b)")); // false
        
        // 測試 5：純文字無括號
        System.out.println("5. Hello World -> " + isValid("Hello World")); // true
    }
}