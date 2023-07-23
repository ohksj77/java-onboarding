package onboarding.problem6;

import java.util.List;

public class EmailValidator {
    private final List<String> emails;
    private final String emailEndpoint = "@email.com";

    public EmailValidator(List<String> emails) {
        this.emails = emails;
    }

    public void validate() {
        if (!isValid()) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isValid() {
        return this.emails.stream().allMatch(e -> e.endsWith(emailEndpoint));
    }
}
