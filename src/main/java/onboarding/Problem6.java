package onboarding;

import onboarding.problem6.ResultProvider;

import java.util.List;

public class Problem6 {
    public static List<String> solution(List<List<String>> forms) {
        return new ResultProvider(forms).getResult();
    }
}
