package onboarding.problem6;

import java.util.List;
import java.util.stream.Collectors;

public class ResultProvider {
    private final FormMapper formMapper;

    public ResultProvider(List<List<String>> forms) {
        validateEmail(forms);
        this.formMapper = new FormMapper(forms);
    }

    private void validateEmail(List<List<String>> forms) {
        final EmailValidator emailValidator = new EmailValidator(getEmailList(forms));
        emailValidator.validate();

    }

    private List<String> getEmailList(final List<List<String>> forms) {
        return forms.stream().map(l -> l.get(0)).collect(Collectors.toList());
    }

    public List<String> getResult() {
        return new DuplicateChecker(formMapper.getForms()).getNoneDuplicateList();
    }
}
