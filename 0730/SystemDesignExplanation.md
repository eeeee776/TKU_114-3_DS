# 資料結構與演算法選擇說明（以圖書借閱系統為例）

1. **保存所有書籍資料 (ArrayList)**
   - **採用方法**：`ArrayList<Book>`。
   - **原因**：圖書館書籍會動態新增，需要連續記憶體且支援快速走訪 (Iterator) 來進行全面搜尋或匯出成陣列排序。未採用 Array 是因為其長度固定，不利於動態擴充。

2. **書籍排序-依編號 (Merge Sort)**
   - **採用方法**：`BookAlgorithms.sortByIdAsc()`
   - **原因**：館藏資料量大時，`O(n log n)` 效能優於 Selection/Insertion Sort。且其為 Stable Sort，在多重排序情境下能保證資料穩定性。

3. **查詢特定編號書籍 (Binary Search)**
   - **採用方法**：`BookAlgorithms.binarySearchById()`
   - **原因**：編號已排序且具唯一性。Binary Search 的 `O(log n)` 時間複雜度在資料量大時能極速定位，未採用 Sequential Search 是因為後者需耗費 `O(n)`。

4. **查詢特定類別所有書籍 (Sequential Search)**
   - **採用方法**：`BookAlgorithms.searchByCategory()`
   - **原因**：書籍未依「類別」排序，且同一類別可能有多本（非唯一鍵值），必須掃描過每一筆資料才能確保無遺漏，故使用循序搜尋。

5. **維修系統-排隊處理 (Queue)**
   - **採用方法**：`ArrayDeque<RepairTask> waitingQueue`
   - **原因**：工作必須遵守「先到先得」(FIFO) 規則，Queue 的 `offer()` 和 `poll()` 完全吻合此情境。不可用 Stack (LIFO)。

6. **維修系統-復原機制 (Stack)**
   - **採用方法**：`ArrayDeque<RepairTask> completedStack`
   - **原因**：當使用者按「復原」時，必須撤銷「最近一次」的操作。符合 LIFO 結構，使用 `push()` 存入完成工作，`poll()` 取出復原。