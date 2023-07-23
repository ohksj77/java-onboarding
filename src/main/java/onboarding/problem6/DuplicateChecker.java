package onboarding.problem6;

import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class DuplicateChecker {
    private final Queue<Form> forms;

    public DuplicateChecker(Queue<Form> forms) {
        this.forms = forms;
    }

    public List<String> getNoneDuplicateList() {
        return this.forms.stream()
                .filter(f -> validateNickname(f.getNickname()))
                .map(Form::getEmail)
                .collect(Collectors.toList());
    }

    private boolean validateNickname(String nickname) {
        final List<String> nicknameList = getOthersNicknameList(nickname);
        return new NicknameCombination(nicknameList).hasDuplicate(nickname);
    }

    private List<String> getOthersNicknameList(String nickname) {
        return this.forms.stream()
                .map(Form::getNickname)
                .filter(n -> !n.equals(nickname) && n.length() > 1)
                .collect(Collectors.toList());
    }
}
