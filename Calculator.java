public class Calculator {

    String password = "admin123"; // Hardcoded password

    public int divide(int a, int b) {
        return a / b; // Division by zero risk
    }

    public String login(String username, String password) {

        if(username.equals("admin") && password.equals("admin")) {
            return "Login Success";
        }

        return null; // Bad practice
    }

    public void printUser(String name) {

        System.out.println(name);

        if(name.equals("admin")) { // Null Pointer Risk
            System.out.println("Welcome Admin");
        }
    }

    public void unusedMethod() {
        int x = 10; // Unused variable
    }
}
