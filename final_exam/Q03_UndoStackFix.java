import java.util.ArrayDeque;
import java.util.Deque;

public class Q03_UndoStackFix {
    
    // 必須包含測試用的 main 方法作為程式進入點
    public static void main(String[] args) {
        Deque<String> history = new ArrayDeque<>();
        history.push("Open file");
        history.push("Type title");
        history.push("Delete line");

        System.out.println("最近操作：" + peekLatest(history));
        System.out.println("復原：" + undo(history));
        System.out.println("復原：" + undo(history));
        System.out.println("剩餘最近操作：" + peekLatest(history));
    }

    // 修正：從 Stack 頂端取出 (Deque 的最前端)
    public static String undo(Deque<String> history) {
        if (history.isEmpty()) {
            return "EMPTY";
        }
        return history.pollFirst(); // 使用 pollFirst() 或 pop()
    }

    // 修正：查看 Stack 頂端但不移除 (Deque 的最前端)
    public static String peekLatest(Deque<String> history) {
        if (history.isEmpty()) {
            return "EMPTY";
        }
        return history.peekFirst(); // 使用 peekFirst() 或 peek()
    }
}