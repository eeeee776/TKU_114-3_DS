public class Rectangle {
    private double width;
    private double height;

    // 接收寬、高的建構子
    public Rectangle(double width, double height) {
        // 使用 setter 確保初始化的值也是合法的
        if (!setWidth(width)) {
            this.width = 1.0; // 預設值
        }
        if (!setHeight(height)) {
            this.height = 1.0; // 預設值
        }
    }

    // getter
    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    // 能驗證正數的 setter
    public boolean setWidth(double width) {
        if (width <= 0) {
            return false;
        }
        this.width = width;
        return true;
    }

    public boolean setHeight(double height) {
        if (height <= 0) {
            return false;
        }
        this.height = height;
        return true;
    }

    public double calculateArea() {
        return width * height;
    }

    public double calculatePerimeter() {
        return 2 * (width + height);
    }

    public boolean isSquare() {
        return Double.compare(width, height) == 0;
    }

    @Override
    public String toString() {
        return "矩形 [寬度=" + width + ", 高度=" + height + "]";
    }
}