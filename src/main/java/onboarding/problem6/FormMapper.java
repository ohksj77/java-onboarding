package onboarding.problem6;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

public class FormMapper {
    private final Queue<Form> forms = new PriorityQueue<>();

    public FormMapper(final List<List<String>> forms) {
        final List<Form> collect = forms.stream().map(Form::new).collect(Collectors.toList());
        this.forms.addAll(collect);
    }

    public Queue<Form> getForms() {
        return forms;
    }
}
