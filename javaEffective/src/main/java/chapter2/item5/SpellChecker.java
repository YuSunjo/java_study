package chapter2.item5;

public class SpellChecker {

    /**
     * 자원을 직접 명시한다면 이 하나의 클래스밖에 사용할 수가 없음
     * 테스트에 용이하지가 않다. - 테스트 할 경우 Mock 클래스를 가지고 하는 테스트 해야할 경우가 있는데 하지 못한다.
     */
//    private static final SpellCheckerService spellCheckerService = new SpellCheckerService();
//
//    public SpellChecker() {
//    }

    private final SpellCheckerService spellCheckerService;

    public SpellChecker(SpellCheckerService spellCheckerService) {
        this.spellCheckerService = spellCheckerService;
    }

}
