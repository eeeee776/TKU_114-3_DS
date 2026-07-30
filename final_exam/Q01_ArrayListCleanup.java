public static int removeBelow(ArrayList<Integer> scores, int minimum) {
    int removed = 0;
    // 從陣列尾部往前檢查，避免刪除元素後發生索引錯位
    for (int index = scores.size() - 1; index >= 0; index--) {
        if (scores.get(index) < minimum) {
            scores.remove(index);
            removed++;
        }
    }
    return removed;
}