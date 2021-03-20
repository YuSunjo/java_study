package chapter5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

// 실전 문제  5.6
public class Practice {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("alan", "Cambridge");
        Trader brian = new Trader("brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        // 1 - 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리하시오
        transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());
        // 2 - 거래자가 근무하는 모든 도시를 중복 없이 나열하시오 - distinct 위치 조심
        List<String> collect = transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(toList());

        //3 - 케임브릿지에서 근무하는 모든 거래자를찾아서 이름순으로 정렬하시오
        transactions.stream()
                .filter(t -> t.getTrader().getCity() == "Cambridge")
                .sorted(comparing((t -> t.getTrader().getName())))
                .collect(Collectors.toList());

//        transactions.stream()
//                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
//                .distinct()
//                .sorted(comparing(Trader::getName))
//                .collect(Collectors.toList());

        // 4 - 모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오
        transactions.stream()
                .sorted(comparing((t -> t.getTrader().getName())))
                .collect(Collectors.toList());

        transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                // collect(joining())   이것이 더 좋음음
                .reduce("", (n1, n2) -> n1 + n2);


        // 5 - 밀라노에 거래자가 있는가?
        transactions.stream()
                .anyMatch(t -> t.getTrader().getCity().equals("Milan"));

        // 6 - 케임브리지에 거주하는 거래자의 모든 트랜잭션값을 출력하시오
        transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .collect(Collectors.toList());

        // 7 - 전체 트랜잭션 중 최대값은?
        transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);

        // 8 - 전체 트랜잭션 중 최솟값은 ?
        transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min);

        transactions.stream()
                .min(comparing(Transaction::getValue));

    }
}
