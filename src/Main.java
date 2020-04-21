import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String... args) {
        final List<Fruit> fruits = new ArrayList<>();
        fruits.add(new Fruit("Watermelon", 100));
        fruits.add(new Fruit("Pineapple", 200));
        fruits.add(new Fruit("peach", 100));
        fruits.add(new Fruit("Blackberry", 10));
        fruits.add(new Fruit("banana", 200));
        fruits.add(new Fruit("apple", 120));
        fruits.add(new Fruit("Orange", 150));
        fruits.add(new Fruit("cherry", 10));

        System.out.println("Original:");
        System.out.println(fruits);

        final List<Fruit> sorted_fruits = SortHelper.sort(fruits, "calories,ASC", "name,DESC");
        System.out.println("Sorted by calories first then by name in reverse order");
        System.out.println(sorted_fruits);

        final List<Fruit> sorted_fruits2 = SortHelper.sort(fruits, "calories,DESC", null);
        System.out.println("Sorted by calories in reverse");
        System.out.println(sorted_fruits2);

        final List<Fruit> sorted_fruits3 = SortHelper.sort(fruits, null, null);
        System.out.println("No sort");
        System.out.println(sorted_fruits3);
    }

    static class SortHelper {
        private SortHelper() {}

        static List<Fruit> sort(final List<Fruit> fruits, final String sort1, final String sort2) {
            return fruits.stream().sorted((f1, f2) -> {
                final String[] sortAndDir1 = sort1 != null ? sort1.split(",") : null;
                final String field1 = sortAndDir1 != null ? sortAndDir1[0] : null;
                final String dir1 = sortAndDir1 != null ? sortAndDir1[1]: null;

                final String[] sortAndDir2 = sort2 != null ? sort2.split(",") : null;
                final String field2 = sortAndDir2 != null ? sortAndDir2[0] : null;
                final String dir2 = sortAndDir2 != null ? sortAndDir2[1]: null;

                if ("calories".equals(field1)) {
                    if (f1.getCalories() > f2.getCalories()) {
                        return "ASC".equals(dir1) ? 1 : -1;
                    }
                    if (f1.getCalories() < f2.getCalories()) {
                        return "ASC".equals(dir1) ? -1 : 1;
                    }
                    if ("name".equals(field2)) {
                        if ("ASC".equals(dir2)) {
                            return f1.getName().compareToIgnoreCase(f2.getName());
                        }
                        return f2.getName().compareToIgnoreCase(f1.getName());
                    }
                }
                else if ("name".equals(field1)) {
                    if (f1.getName().equalsIgnoreCase(f2.getName())) {
                        if ("calories".equals(field2)) {
                            if (f1.getCalories() > f2.getCalories()) {
                                return "ASC".equals(dir1) ? 1 : -1;
                            }
                            if (f1.getCalories() < f2.getCalories()) {
                                return "ASC".equals(dir1) ? -1 : 1;
                            }
                        }
                    }
                    else {
                        if ("ASC".equals(dir2)) {
                            return f1.getName().compareToIgnoreCase(f2.getName());
                        }
                        return f2.getName().compareToIgnoreCase(f1.getName());
                    }
                }
                return 0;
            }).collect(Collectors.toList());
        }
    }

    static class Fruit {
        private String name;
        private int calories;

        Fruit(final String name, final int calories) {
            this.name = name;
            this.calories = calories;
        }

        public String getName() {
            return name;
        }

        public int getCalories() {
            return calories;
        }

        @Override
        public String toString() {
            return String.format("{Name: %s, Calories: %d}", name, calories);
        }
    }
}
