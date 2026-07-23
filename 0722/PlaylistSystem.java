public class PlaylistSystem {
    public static void main(String[] args) {
        PlaylistLinkedList playlist = new PlaylistLinkedList();

        System.out.println("測試 1：新增歌曲");
        playlist.addLast("S01", "第一天");
        playlist.addLast("S02", "擁抱");
        playlist.addLast("S03", "稻香");
        playlist.printPlaylist();

        System.out.println("測試 2：測試加入重複代碼");
        playlist.addLast("S02", "倔強"); // 應阻擋

        System.out.println("\n測試 3：刪除第一首 (S01)");
        playlist.remove("S01");
        playlist.printPlaylist();

        System.out.println("測試 4：刪除最後一首 (S03)");
        playlist.remove("S03");
        playlist.printPlaylist();

        System.out.println("測試 5：刪除不存在的歌曲與剩餘唯一節點");
        playlist.remove("S99");
        playlist.remove("S02");
        playlist.printPlaylist(); // 應顯示清單是空的
    }
}