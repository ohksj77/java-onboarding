package onboarding.problem6;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NicknameCombination {
    private final Map<String, List<String>> combination = new HashMap<>();

    public NicknameCombination(List<String> nicknames) {
        this.generateMap(nicknames);
    }

    private void generateMap(List<String> nicknames) {
        combination.putAll(nicknames.stream()
                .collect(Collectors.toMap(Function.identity(), this::generateCombination)));
    }

    private List<String> generateCombination(String nickname) {
        final int length = nickname.length();
        return IntStream.range(0, length)
                .filter(i -> i < length - 1)
                .mapToObj(i -> nickname.substring(i, i + 2))
                .collect(Collectors.toList());
    }

    public boolean hasDuplicate(String nickname) {
        return this.combination.keySet().stream()
                .anyMatch(k -> combination.get(k).stream().anyMatch(nickname::contains));
    }
}
