package com.company;

import com.company.models.Item;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Algo {

    public static void main(String[] args) {
        findCountPeopleInHotel();
    }

    /**
     * №1 Даны даты заезда и отъезда каждого гостя. Для каждого гостя дата заезда строго раньше даты отъезда
     * (то есть каждый гость останавливается хотя бы на одну ночь). В пределах одного дня считается,
     * что сначала старые гости выезжают, а затем въезжают новые. Найти максимальное число постояльцев,
     * которые одновременно проживали в гостинице (считаем, что измерение количества постояльцев происходит в конце дня).
     * <p>
     * 1. Создаем карту ключ-день, значение-кол-во человек проживающих в этот день.
     * Проходимся в цикле по каждому элементу, по каждому прожитому дню и добавляем в карту
     * 2. Проходимся по карте, находим максимальное значение
     */
    private static void findCountPeopleInHotel() {
        List<List<Integer>> guests = List.of(List.of(1, 2), List.of(1, 3), List.of(2, 4), List.of(2, 3));
        int resultCount = 0;
        Map<Integer, Integer> leavingDays = new HashMap<>();
        for (List<Integer> list : guests) {
            for (int i = list.get(0) + 1; i <= list.get(1); i++) {
                leavingDays.merge(i, 1, Integer::sum);
            }
        }
        for (Map.Entry<Integer, Integer> entry : leavingDays.entrySet()) {
            resultCount = Math.max(resultCount, entry.getValue());
        }
        System.out.println(resultCount);
    }

    /**
     * №2 Дан массив точек с целочисленными координатами (x, y). Определить, существует ли вертикальная прямая,
     * делящая точки на 2 симметричных относительно этой прямой множества.
     * 1. Делаем мапу. Ключ - координата 'y', значение - все координаты по этому 'y'
     * 2. Сортируем координаты по 'x'. Берем крайние и вычисляем середину
     * 3. В цикле проходимся по всем координатам и сравниваем значение середины
     */
    private static void existSymmetricalLine() {
        List<List<Integer>> sequences = List.of(List.of(-1, 3), List.of(1, 4), List.of(2, 4), List.of(4, 4), List.of(5, 4), List.of(7, 3));

        Map<Integer, List<List<Integer>>> map = new HashMap<>();

        for (List<Integer> point : sequences) {
            List<List<Integer>> lists = map.get(point.get(1));
            if (lists == null) {
                List<List<Integer>> list = new ArrayList<>();
                list.add(point);
                map.put(point.get(1), list);
            } else {
                lists.add(point);
                map.put(point.get(1), lists);
            }
        }

        boolean result = true;
        Integer firstMiddlePoint = null;

        for (Map.Entry<Integer, List<List<Integer>>> entry : map.entrySet()) {
            if (entry.getValue().size() % 2 != 0) {
                result = false;
                break;
            }
            List<List<Integer>> sortedList = entry.getValue().stream()
                    .sorted(Comparator.comparingInt(a -> a.get(0)))
                    .collect(Collectors.toList());
            int leftIndex = 0;
            int rightIndex = sortedList.size() - 1;

            if (firstMiddlePoint == null) {
                firstMiddlePoint = (sortedList.get(rightIndex).get(0) + sortedList.get(leftIndex).get(0)) / 2;
            }

            while (leftIndex < rightIndex) {
                if (firstMiddlePoint != (sortedList.get(rightIndex).get(0) + sortedList.get(leftIndex).get(0)) / 2) {
                    result = false;
                    break;
                } else {
                    leftIndex++;
                    rightIndex--;
                }
            }
        }
        System.out.println(result);
    }

    /**
     * №3 Скобочные последовательности.
     * Используется рекурсия
     */
    private static void bracketSequence() throws IOException {
        bracket(4, "", 0, 0);
    }

    private static void bracket(int count, String s, int left, int right) {
        if (left == count && right == count) {
            System.out.println(s);
        } else {
            if (left < count) {
                bracket(count, s + "(", left + 1, right);
            }
            if (right < left) {
                bracket(count, s + ")", left, right + 1);
            }
        }
    }

    /**
     * №4 Задача про рюкзак и продукты. Вычислить самые дорогие продукты которые поместятся в рюкзак.
     * Используется жадный алгоритм.
     * Сортируем предметы по стоимости к единице измерения.
     */
    private static double fractionalKnapsack() {
        final Item item1 = new Item(4, 20);
        final Item item2 = new Item(3, 18);
        final Item item3 = new Item(2, 14);

        final Item[] items = {item1, item2, item3};

        Arrays.sort(items, Comparator.comparingDouble(Item::valuePerUnitOfWeight).reversed());
        final int knapsackSpace = 7;

        int filledWeight = 0;
        double valueSoFar = 0;

        for (Item currentItem : items) {
            double emptySpace = knapsackSpace - filledWeight;
            if (emptySpace == 0) {
                break;
            }
            if (currentItem.getWeight() <= emptySpace) {
                filledWeight += currentItem.getWeight();
                valueSoFar += currentItem.getValue();
            } else {
                filledWeight += emptySpace;
                valueSoFar += emptySpace * currentItem.valuePerUnitOfWeight();
            }
        }
        return valueSoFar;
    }

    /**
     * №5 Переводит римские цифры в латинские
     * Введение множителя. Если текущее значение больше предыдущего, значит нужно получить их разницу
     */
    private static void romanToInt() {
        String example1 = "LIV";
        //  String example1 = "LVIII";
        Map<Character, Integer> symbolMap = Map.of('I', 1, 'V', 5, 'X', 10, 'L', 50, 'C', 100, 'D', 500, 'M', 1000);
        int res = 0;
        int prev = 0;
        char[] chars = example1.toCharArray();

        for (char symbol : chars) {
            int value = symbolMap.get(symbol);
            int mult = 1;
            if (value > prev) {
                mult = -1;
            }
            res += prev * mult;
            prev = value;
        }
        System.out.println(res + prev);
    }



    public static void printArray(int[] array) {
        var sb = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        System.out.println(sb);
    }
}
