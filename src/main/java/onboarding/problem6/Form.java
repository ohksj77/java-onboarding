package onboarding.problem6;

import java.util.List;
import java.util.Objects;

public class Form implements Comparable<Form> {
    private final String email;
    private final String nickname;

    public Form(final List<String> form) {
        this.email = form.get(0);
        this.nickname = form.get(1);
    }

    public String getNickname() {
        return this.nickname;
    }

    public String getEmail() {
        return this.email;
    }

    @Override
    public int compareTo(final Form o) {
        return email.compareTo(o.email);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Form form = (Form) o;
        return Objects.equals(email, form.email) && Objects.equals(nickname, form.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, nickname);
    }
}
