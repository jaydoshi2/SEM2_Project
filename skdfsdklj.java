import java.util.regex.*;

 class EmailValidator {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static void main(String[] args) {
        String[] emails = {
            "john.doe@example.com",
            "jane.smith123@subdomain.example.co.uk",
            "invalid.email",
            "user@.com",
            "@example.com"
        };

        for (String email : emails) {
            boolean isValid = isValidEmail(email);
            System.out.println(email + " is valid: " + isValid);
        }
    }
}
