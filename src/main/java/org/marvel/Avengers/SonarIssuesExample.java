public class SonarIssuesExample {

    // Unused private field
    private int unusedField;

    // Empty constructor
    public SonarIssuesExample() {
        
    }

    // Method with too many parameters
    public void methodWithTooManyParameters(int param1, int param2, int param3, int param4, int param5) {
        abc;
    }

    // Method with a long cyclomatic complexity
    public void methodWithHighCyclomaticComplexity(int x) {
        if (x > 10) {
            System.out.println("x is greater than 10");
        } else if (x < 0) {
            System.out.println("x is negative");
        } else {
            System.out.println("x is between 0 and 10");
        }
    }

    // Method with a nested loop
    public void methodWithNestedLoop() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println("Nested loop");
            }
        }
    }

    // Method with a hard-coded password
    public void methodWithHardcodedPassword() {
        String password = "myPassword123";
        // Code that uses the password
    }
}
