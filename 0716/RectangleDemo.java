public class RectangleDemo {
    public static void main(String[] args) {
        // 建立至少 3 個矩形
        Rectangle r1 = new Rectangle(5.0, 3.0);
        Rectangle r2 = new Rectangle(4.0, 4.0);
        Rectangle r3 = new Rectangle(-2.0, 6.0); // 測試無效的寬度，應會被設為預設值

        printRectangleInfo(r1);
        printRectangleInfo(r2);
        printRectangleInfo(r3);
    }

    public static void printRectangleInfo(Rectangle r) {
        System.out.println(r.toString());
        System.out.println("面積: " + r.calculateArea());
        System.out.println("周長: " + r.calculatePerimeter());
        System.out.println("是否為正方形: " + (r.isSquare() ? "是" : "否"));
        System.out.println("-------------------------");
    }
}