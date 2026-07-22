import java.util.ArrayList;
import java.util.Scanner;

public class CourseRegistrationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Course> courses = new ArrayList<>();

        while (true) {
            System.out.println("\n=== 選課管理系統 ===");
            System.out.println("1. 新增課程");
            System.out.println("2. 學生選課");
            System.out.println("3. 學生退選");
            System.out.println("4. 刪除課程");
            System.out.println("5. 搜尋課程");
            System.out.println("6. 系統統計報表");
            System.out.println("0. 離開系統");
            System.out.print("請選擇功能：");

            String input = scanner.nextLine().trim();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("錯誤：請輸入有效的數字選項！");
                continue;
            }

            if (choice == 0) {
                System.out.println("系統已離開。");
                break;
            }

            switch (choice) {
                case 1: // Create: 新增課程
                    System.out.print("請輸入課程代碼：");
                    String code = scanner.nextLine().trim();
                    if (code.isEmpty()) {
                        System.out.println("錯誤：代碼不可為空白！");
                        break;
                    }
                    
                    if (findCourseByCode(courses, code) != null) {
                        System.out.println("錯誤：此課程代碼已存在！");
                    } else {
                        System.out.print("請輸入課程名稱：");
                        String name = scanner.nextLine().trim();
                        System.out.print("請輸入課程容量(人數上限)：");
                        int capacity = getValidInteger(scanner);
                        
                        if (capacity > 0) {
                            courses.add(new Course(code, name, capacity));
                            System.out.println("課程新增成功！");
                        } else {
                            System.out.println("錯誤：容量必須大於 0。");
                        }
                    }
                    break;

                case 2: // Update: 選課
                    System.out.print("請輸入要選的課程代碼：");
                    String enrollCode = scanner.nextLine().trim();
                    Course enrollTarget = findCourseByCode(courses, enrollCode);
                    
                    if (enrollTarget != null) {
                        // 讓課程物件自己處理人數遞增與防護
                        if (enrollTarget.enroll()) {
                            System.out.println("選課成功！目前人數：" + enrollTarget.getEnrolled() + "/" + enrollTarget.getCapacity());
                        } else {
                            System.out.println("選課失敗：該課程已額滿！");
                        }
                    } else {
                        System.out.println("錯誤：找不到該課程。");
                    }
                    break;

                case 3: // Update: 退選
                    System.out.print("請輸入要退選的課程代碼：");
                    String dropCode = scanner.nextLine().trim();
                    Course dropTarget = findCourseByCode(courses, dropCode);
                    
                    if (dropTarget != null) {
                        if (dropTarget.drop()) {
                            System.out.println("退選成功！目前人數：" + dropTarget.getEnrolled() + "/" + dropTarget.getCapacity());
                        } else {
                            System.out.println("退選失敗：該課程目前無人選修，無法再退。");
                        }
                    } else {
                        System.out.println("錯誤：找不到該課程。");
                    }
                    break;

                case 4: // Delete: 刪除課程
                    System.out.print("請輸入要刪除的課程代碼：");
                    String deleteCode = scanner.nextLine().trim();
                    Course deleteTarget = findCourseByCode(courses, deleteCode);
                    
                    if (deleteTarget != null) {
                        courses.remove(deleteTarget);
                        System.out.println("課程刪除成功。");
                    } else {
                        System.out.println("刪除失敗：找不到該課程。");
                    }
                    break;

                case 5: // Read: 搜尋單筆
                    System.out.print("請輸入搜尋的課程代碼：");
                    String searchCode = scanner.nextLine().trim();
                    Course searchTarget = findCourseByCode(courses, searchCode);
                    
                    if (searchTarget != null) {
                        System.out.println("搜尋結果：" + searchTarget);
                    } else {
                        System.out.println("找不到課程代碼為「" + searchCode + "」的課程。");
                    }
                    break;

                case 6: // Read: 系統統計 (作業要求)
                    printSystemReport(courses);
                    break;

                default:
                    System.out.println("無效的選項，請重新選擇。");
            }
        }
        scanner.close();
    }

    // ==========================================
    // 自訂 Method 區
    // ==========================================

    /**
     * 核心搜尋方法
     */
    public static Course findCourseByCode(ArrayList<Course> courses, String code) {
        for (Course course : courses) {
            if (course.getCode().equalsIgnoreCase(code)) {
                return course;
            }
        }
        return null;
    }

    /**
     * 列印系統統計報表 (包含總課數、總人次、額滿名單)
     */
    public static void printSystemReport(ArrayList<Course> courses) {
        if (courses.isEmpty()) {
            System.out.println("目前系統中沒有任何課程。");
            return;
        }

        System.out.println("--- 系統統計報表 ---");
        System.out.println("總課程數：" + courses.size() + " 門");
        
        int totalEnrollments = 0;
        ArrayList<Course> fullCourses = new ArrayList<>(); // 用來蒐集額滿的課程

        // 走訪所有課程，進行統計與篩選
        for (Course c : courses) {
            totalEnrollments += c.getEnrolled();
            if (c.isFull()) {
                fullCourses.add(c);
            }
            // 順便印出所有課程狀態
            System.out.println(c); 
        }

        System.out.println("-------------------");
        System.out.println("全校總選課人次：" + totalEnrollments + " 人次");
        
        if (fullCourses.isEmpty()) {
            System.out.println("目前尚無額滿課程。");
        } else {
            System.out.println("【額滿課程名單】：");
            for (Course fc : fullCourses) {
                System.out.println(" - " + fc.getName() + " (" + fc.getCode() + ")");
            }
        }
    }

    /**
     * 輔助方法：安全讀取整數
     */
    private static int getValidInteger(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}