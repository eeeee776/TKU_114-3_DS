public class PlaylistLinkedList {
    private PlaylistNode head;
    private int size;

    public PlaylistLinkedList() {
        head = null;
        size = 0;
    }

    // 依代碼搜尋，檢查是否存在
    public boolean contains(String songId) {
        PlaylistNode current = head;
        while (current != null) {
            if (current.songId.equals(songId)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // 尾端新增 (檢查重複代碼)
    public boolean addLast(String songId, String songName) {
        if (contains(songId)) {
            System.out.println("加入失敗：歌曲代碼 " + songId + " 已存在。");
            return false;
        }

        PlaylistNode newNode = new PlaylistNode(songId, songName);
        if (head == null) {
            head = newNode;
        } else {
            PlaylistNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        return true;
    }

    // 刪除歌曲
    public boolean remove(String songId) {
        if (head == null) return false;

        if (head.songId.equals(songId)) {
            head = head.next;
            size--;
            return true;
        }

        PlaylistNode previous = head;
        PlaylistNode current = head.next;
        while (current != null) {
            if (current.songId.equals(songId)) {
                previous.next = current.next;
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    // 列印完整播放順序
    public void printPlaylist() {
        if (head == null) {
            System.out.println("播放清單是空的。");
            return;
        }
        PlaylistNode current = head;
        System.out.println("--- 目前播放清單 ---");
        while (current != null) {
            System.out.println("[" + current.songId + "] " + current.songName);
            current = current.next;
        }
        System.out.println("總共 " + size + " 首歌\n");
    }
}