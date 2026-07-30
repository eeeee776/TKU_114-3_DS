import java.util.ArrayList;

public class RegistrationAlgorithms {
    // Merge Sort 依報名編號排序 (升冪)
    public static void sortByIdAsc(Registration[] regs) {
        Registration[] temp = new Registration[regs.length];
        sortByIdAsc(regs, temp, 0, regs.length - 1);
    }

    private static void sortByIdAsc(Registration[] regs, Registration[] temp, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        sortByIdAsc(regs, temp, left, mid);
        sortByIdAsc(regs, temp, mid + 1, right);
        
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (regs[i].getId().compareTo(regs[j].getId()) <= 0) {
                temp[k++] = regs[i++];
            } else {
                temp[k++] = regs[j++];
            }
        }
        while (i <= mid) temp[k++] = regs[i++];
        while (j <= right) temp[k++] = regs[j++];
        for (int idx = left; idx <= right; idx++) regs[idx] = temp[idx];
    }

    // Binary Search 依編號查詢
    public static int binarySearchById(Registration[] regs, String targetId) {
        int low = 0, high = regs.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = targetId.compareTo(regs[mid].getId());
            if (comparison == 0) return mid;
            if (comparison < 0) high = mid - 1;
            else low = mid + 1;
        }
        return -1;
    }

    // Sequential Search 依姓名查詢
    public static ArrayList<Registration> searchByName(ArrayList<Registration> allRegs, String name) {
        ArrayList<Registration> results = new ArrayList<>();
        for (Registration reg : allRegs) {
            if (reg.getName().equalsIgnoreCase(name)) {
                results.add(reg);
            }
        }
        return results;
    }
}