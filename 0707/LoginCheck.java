public class LoginCheck {
    public static void main(String[] args) {
        String username = "admin";
        String password = "1234";

        String inputUsername = "admin";
        String inputPassword = "1234";
        
        if (username.equals(inputUsername) && password.equals(inputPassword)) {
            System.out.println("登入成功：帳號與密碼正確！");
        } else {
            System.out.println("登入失敗：帳號或密碼錯誤。");
        }
    }
}