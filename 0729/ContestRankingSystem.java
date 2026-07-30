public class ContestRankingSystem {
    public static void main(String[] args) {
        Contestant[] contestants = {
            new Contestant("A01", "Alice", 95, 120),
            new Contestant("A02", "Bob", 85, 140),
            new Contestant("A03", "Charlie", 95, 110), // 同分，但時間較短
            new Contestant("A04", "David", 85, 150),
            new Contestant("A05", "Eve", 100, 100)
        };

        insertionSortContestants(contestants);

        System.out.println("名次\t編號\t姓名\t分數\t秒數");
        for (int i = 0; i < contestants.length; i++) {
            System.out.println((i + 1) + "\t" + contestants[i]);
        }
    }

    public static void insertionSortContestants(Contestant[] values) {
        for (int index = 1; index < values.length; index++) {
            Contestant key = values[index];
            int position = index - 1;

            while (position >= 0) {
                Contestant current = values[position];
                boolean shouldMove = false;

                // 主要條件：分數由高到低 (降冪)
                if (current.getScore() < key.getScore()) {
                    shouldMove = true;
                } 
                // 次要條件：若分數相同，秒數由少到多 (升冪)
                else if (current.getScore() == key.getScore() && current.getSeconds() > key.getSeconds()) {
                    shouldMove = true;
                }

                if (shouldMove) {
                    values[position + 1] = values[position];
                    position--;
                } else {
                    break; // 已找到正確位置
                }
            }
            values[position + 1] = key;
        }
    }
}