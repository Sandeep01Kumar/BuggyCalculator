public class Calculator {

    // SECURITY FIX (CWE-798 Hardcoded Credentials): the hardcoded
    // 'password = "admin123"' field was dead state that exposed a credential in
    // source. It is removed entirely; no method referenced it, so behavior is
    // unchanged.

    public int divide(int a, int b) {
        // ROBUSTNESS FIX (CWE-369 Divide By Zero): guard the divisor and fail
        // fast with a clear, documented error instead of an unchecked
        // ArithmeticException. For every non-zero divisor behavior is identical.
        if (b == 0) {
            throw new IllegalArgumentException("Divisor 'b' must not be zero.");
        }
        return a / b;
    }

    public String login(String username, String password) {
        // SECURITY FIX (CWE-798/CWE-259 Hardcoded Credentials): expected
        // credentials are read from environment variables instead of literal
        // "admin"/"admin" values baked into source. Missing configuration denies
        // access (secure default).
        String expectedUser = System.getenv("APP_ADMIN_USERNAME");
        String expectedPass = System.getenv("APP_ADMIN_PASSWORD");

        // SECURITY/ROBUSTNESS FIX (CWE-476 NPE): compare with the configured
        // (null-checked) value as the receiver so a null username/password can
        // never trigger a NullPointerException.
        if (expectedUser != null && expectedPass != null
                && expectedUser.equals(username) && expectedPass.equals(password)) {
            return "Login Success";
        }

        // FIX (bad practice / CWE-476 for callers): return a non-null failure
        // indicator instead of null so callers never dereference null.
        return "Login Failed";
    }

    public void printUser(String name) {

        System.out.println(name);

        // ROBUSTNESS FIX (CWE-476 NPE): constant-first equals is null-safe and
        // prevents a NullPointerException when 'name' is null.
        if ("admin".equals(name)) {
            System.out.println("Welcome Admin");
        }
    }

    // CODE-QUALITY FIX (dead code / unused variable): the no-op 'unusedMethod'
    // contained only an unused local variable and had no callers; it is removed.
}
