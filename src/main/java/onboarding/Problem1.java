package onboarding;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.stream.IntStream;

class Problem1 {
    public static int solution(List<Integer> pobi, List<Integer> crong) {
        PageValidator pageValidator = new PageValidator(pobi, crong);
        if (pageValidator.hasInvalidPages()) {
            return -1;
        }

        MaxScoreProvider pobiMaxScoreProvider = new MaxScoreProvider(pobi);
        MaxScoreProvider crongMaxScoreProvider =  new MaxScoreProvider(crong);

        int pobiScore = pobiMaxScoreProvider.getMaxScore();
        int crongScore = crongMaxScoreProvider.getMaxScore();
        ResultProvider resultProvider = new ResultProvider(pobiScore, crongScore);
        return resultProvider.getResult();
    }
}

class PageValidator {
    private final List<Integer> pobi;
    private final List<Integer> crong;

    public PageValidator(List<Integer> pobi, List<Integer> crong) {
        this.pobi = pobi;
        this.crong = crong;
    }

    public boolean hasInvalidPages() {
        return pobi.stream().anyMatch(i -> i == 0 || i == 400)
                || crong.stream().anyMatch(i -> i == 0 || i == 400)
                || pobi.get(0) + 1 != pobi.get(1)
                || crong.get(0) + 1 != crong.get(1);
    }
}

class ResultProvider {
    private final int pobi;
    private final int crong;
    private final Map<BiPredicate<Integer, Integer>, Integer> resultMapper = new HashMap<>();

    public ResultProvider(int pobi, int crong) {
        this.pobi = pobi;
        this.crong = crong;
        initializeMap();
    }

    private void initializeMap() {
        this.resultMapper.put((p, c) -> crong < pobi, 1);
        this.resultMapper.put((p, c) -> crong > pobi, -1);
    }

    public int getResult() {
        return resultMapper.keySet().stream().filter(i -> i.test(pobi, crong)).findAny().map(resultMapper::get).orElse(0);
    }
}

@FunctionalInterface
interface Calculator {
    int calculate(int num);
}

class MaxScoreProvider {
    private final int left;
    private final int right;
    private final Calculator sumCalculator = num -> getIntStreamByNum(num).sum();
    private final Calculator multiplyCalculator = num -> getIntStreamByNum(num).reduce((l, r) -> l * r).orElseThrow(IllegalStateException::new);

    public MaxScoreProvider(List<Integer> bookPages) {
        validateSizeOfList(bookPages);
        this.left = bookPages.get(0);
        this.right = bookPages.get(1);
    }

    private void validateSizeOfList(List<Integer> bookPages) {
        if (bookPages.size() != 2) {
            throw new IllegalArgumentException();
        }
    }

    public int getMaxScore() {
        int sumMax = getMaxScoreOfPages(calculate(left, sumCalculator), calculate(right, multiplyCalculator));
        int multiplyMax = getMaxScoreOfPages(sumCalculator.calculate(left), multiplyCalculator.calculate(right));
        return Math.max(sumMax, multiplyMax);
    }

    private int getMaxScoreOfPages(final int leftPage, final int rightPage) {
        return Math.max(leftPage, rightPage);
    }

    private int calculate(final int num, final Calculator calculator) {
        return calculator.calculate(num);
    }

    private IntStream getIntStreamByNum(final int num) {
        return Arrays.stream(toStringArray(num)).mapToInt(Integer::parseInt);
    }

    private String[] toStringArray(final int num) {
        return Integer.toString(num).split("");
    }
}
