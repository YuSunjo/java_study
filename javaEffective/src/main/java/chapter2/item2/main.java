package chapter2.item2;

public class main {

    public static void main(String[] args) {
        //가독성도 좋지 않고 빼먹을 가능성이 있음
//        NutritionFacts nutritionFacts = new NutritionFacts(1,6,10,1);

//        //사용법 1
//        NutritionFacts.Builder builder = new NutritionFacts.Builder(2,2);
//        builder.calories(2);
//        builder.sodium(2);
//        builder.carbohydrate(2);
//
//        //사용법 2
//        NutritionFacts nutritionFacts = new NutritionFacts.Builder(2,2)
//                .calories(2)
//                .sodium(2)
//                .carbohydrate(2)
//                .build();

        //lombok 사용시 1
        NutritionFacts facts = NutritionFacts.builder()
                .calories(20)
                .carbohydrate(20)
                .fat(20)
                .build();

        //lombok 사용시 2
        NutritionFacts.NutritionFactsBuilder builder = NutritionFacts.builder();
        builder.calories(10);
    }
}
