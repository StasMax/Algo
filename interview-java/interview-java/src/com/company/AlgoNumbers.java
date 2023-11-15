package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class AlgoNumbers extends Algo {

    public static void main(String[] args) {
        remove_duplications();
    }

    /**
     * №1 Удаление дубликатов из упорядоченного массива
     * 1. Проходимся по списку и сравниваем текущий элемент со следующим, если разные, добавляем в результат. On
     * 2. Создаем LinkedHashSet и копируем массив туда. On + доп память
     */
    private static void remove_duplications() {
        int[] array = {-5, -3, -3, 0, 0, 1, 2, 3, 3, 3, 5, 6, 6};

        var result = new int[array.length];
        int current = array[0];
        int resultIndex = 0;
        result[resultIndex++] = current;
        for (int i = 1; i < array.length; i++) {
            if (array[i] != current) {
                current = array[i];
                result[resultIndex++] = current;
            }
        }
        printArray(result);

        // 2 вариант
        {//use Set
            var set = new LinkedHashSet<Integer>();
            for (int i : array) {
                set.add(i);
            }
            System.out.println(set);
        }
    }

    /**
     * №2 Найти наибольшее вхождение единицы подряд
     * 1. Проходимся по списку и сравниваем текущий элемент со следующим
     * Если 1, то инкрементируем счетчик и проверяем на максимальную последовательность
     * Если не 1, то сбрасываем счетчик
     */
    private static void max_ones_count() {
        int[] array = {0, 1, 1, 0, 1, 0, 1, 1, 1, 1};
        var max = 0;
        var current = 0;
        for (int i : array) {
            if (i == 1) {
                current++;
                max = Math.max(max, current);
            } else {
                current = 0;
            }
        }
        System.out.println(max);
    }

    /**
     * №3 Возведение элементов массива во 2 степень с сортировкой.
     * Умножаем левый и правые элементы сами на себя.
     * Сравниваем, большее значение записываем в результат и увеличиваем или уменьшаем индекс в зависимости от стороны
     */
    private static void squaring_sorted_array() {
        int[] array = {-7, -4, -1, 0, 2, 5, 5};
        int[] output = new int[array.length];

        var outIndex = array.length - 1;
        var leftIndex = 0;
        var rightIndex = array.length - 1;

        while (leftIndex <= rightIndex) {
            var leftSquare = array[leftIndex] * array[leftIndex];
            var rightSquare = array[rightIndex] * array[rightIndex];
            if (leftSquare > rightSquare) {
                output[outIndex--] = leftSquare;
                leftIndex++;
            } else {
                output[outIndex--] = rightSquare;
                rightIndex--;
            }
        }
        printArray(array);
        printArray(output);
    }

    /**
     * №4 Дан неотсортированный список чисел от 0 до N, где одно число пропущено. Найти это число.
     * Находим самый большой и самый маленький элемент в массиве.
     * Подсчитываем сумму арифметической прогрессии по формуле S = (a1+an)*n/2, при расчете для округления в большую сторону добавляем 1 к длине массива.
     * Складываем все элементы массива.
     * Результат равен разнице арифметической прогрессии и сумме всех элементов.
     */
    private static void find_missing_number() {
        //массив чисел от 2 до 7, пропущено чиcло 6
        int[] array = {7, 2, 4, 5, 3};

        int maxValue = array[0];
        int minValue = array[0];

        for (int i : array) {
            maxValue = Math.max(maxValue, i);
            minValue = Math.min(minValue, i);
        }

        //сумма арифметической прогрессии: S = (a1+an)*n/2;
        var expectedSum = (maxValue + minValue) * (array.length + 1) / 2;
        var sum = Arrays.stream(array).sum();
        var missing = expectedSum - sum;

        System.out.printf("Сумма прогрессии от %s до %s: %s%n", minValue, maxValue, expectedSum);
        System.out.printf("Фактическая сумма: %s%n", sum);
        System.out.printf("Пропущенное число: %s%n", missing);
    }

    /**
     * №5 Дан неотсортированный список чисел от 1 до N, где пропущено несколько чисел. Элементы могут повторяться. Найти эти числа.
     * Сортируем массив. Для этого создаем новый и записываем туда в цикле значения. Значение == индексу. Так сортировка пройдет за On.
     * Проходимся по отсортированному массиву. Там где значение 0 - значит отсутствует значение, его индекс и будет решением
     * В этом решении используется дополнительная память
     */
    private static void find_skip_numbers_with_duplicate() {
        int[] array = {9, 4, 2, 1, 2, 4, 5, 9, 7, 9, 4, 5};
        int maxValue = 0;
        int[] sortArray = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            sortArray[array[i]] = array[i];
            maxValue = Math.max(maxValue, array[i]);
        }
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < sortArray.length && i < maxValue; i++) {
            if (sortArray[i] == 0) {
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb);
    }

    /**
     * №6 Дан упорядоченный список чисел от 1 до N, где пропущено несколько чисел. Элементы не могут повторяться. Найти эти числа.
     * Проходимся в цикле по массиву, сравниваем каждый элемент с ожидаемым, если не совпадает, то
     * в цикле проходимся и проверяем нет ли несколько пропущенных чисел подряд
     * В этом решении используется вложенный цикл, но сложность остается On
     */
    private static void find_skip_numbers_filtered_array() {
        int[] array = {2, 4, 5, 7, 9};
        int number = 1;
        var sb = new StringBuilder();

        for (int i = 0; i < array.length; i++) {
            while (array[i] != number) {
                sb.append(number).append(" ");
                number++;
            }
            number++;
        }
        System.out.println(sb);
    }

    /**
     * №7 Даны 3 массива. Необходимо найти общие числа.
     * 1 решение вложенные массивы. 3 вложенных цикла. В худшем случае On^3
     * 2 решение из двух массивов сделать множества, итерируясь по 3-му сравнивать элементы через contains. On + доп память
     * 3 сравнивать поочередно элементы 2-х массивов, если отличаются, увеличивать индекс у меньшего значения, если равны начать сравнивать с 3-им массивом по тому же принципу
     */
    private static void find_common_number() {
        int[] array1 = {1, 2, 4, 5};
        int[] array2 = {3, 3, 4};
        int[] array3 = {2, 3, 4, 5, 6};
        Integer result = null;

        for (int num1 : array1) {
            for (int num2 : array2) {
                if (num1 == num2) {
                    for (int num3 : array3) {
                        if (num3 == num2) {
                            result = num3;
                            break;
                        }
                    }
                }
            }
        }
        System.out.println(result);
    }

    private static void find_common_number1() {
        int[] array1 = {1, 2, 4, 5};
        Integer[] array2 = {3, 3, 4};
        int[] array3 = {2, 3, 4, 5, 6};
        Integer result = null;
        Set<Integer> firstSet = new HashSet<>(Arrays.asList(array2));
        var secondSet = Arrays.stream(array3).boxed().collect(Collectors.toSet());
        for (int number : array1) {
            if (firstSet.contains(number) && secondSet.contains(number)) {
                result = number;
            }
        }

        System.out.println(result);
    }

    private static void find_common_number2() {
//        int[] array1 = {1, 2, 4, 5};
//        int[] array2 = {3, 3, 4};
//        int[] array3 = {2, 3, 4, 5, 6};

        int[] array1 = {1, 2, 4, 4, 5};
        int[] array2 = {3, 3, 4, 5};
        int[] array3 = {2, 3, 3, 5, 6};

//        int[] array1 = {1, 2, 4, 5};
//        int[] array2 = {3, 3, 4, 5};
//        int[] array3 = {2, 3, 3, 6};
        Integer result = null;

        int lastFirstNum = array1[0];
        int lastIndexArray1 = 0;

        int lastSecondNum = array2[0];
        int lastIndexArray2 = 0;

        int lastThirdNum = array3[0];
        int lastIndexArray3 = 0;

        while (true) {
            if (array1.length == lastIndexArray1 || array2.length == lastIndexArray2 || array3.length == lastIndexArray3) {
                break;
            }
            if (lastFirstNum < lastSecondNum) {
                lastIndexArray1++;
                lastFirstNum = array1[lastIndexArray1];
            } else if (lastFirstNum > lastSecondNum) {
                lastIndexArray2++;
                lastSecondNum = array2[lastIndexArray2];
            } else if (lastFirstNum == lastSecondNum) {
                while (array3.length > lastIndexArray3) {
                    if (lastThirdNum == lastFirstNum) {
                        result = lastThirdNum;
                        break;
                    } else if (lastThirdNum > lastFirstNum) {
                        lastIndexArray1++;
                        if (array1.length > lastIndexArray1) {
                            lastFirstNum = array1[lastIndexArray1];
                        }
                        break;
                    }
                    lastIndexArray3++;
                    lastThirdNum = array3[lastIndexArray3];
                }
                if (result != null) {
                    break;
                }
            }
        }
        System.out.println(result);
    }

    /**
     * №8 Дан массив из нулей и единиц. Нужно определить, какой максимальный по длине подинтервал единиц можно получить, удалив ровно один элемент массива.
     * Ищем нуль между единицами. Сохраняем в переменную индекс и сумму единиц слева и справа от нуля. Переходим к следующему нулю, высчитываем и записываем максимальное значение.
     * Сложность On
     */
    private static void oneMaxSequence() {
        int[] array = {0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1};
        int resultSequence = 0;
        Integer resultIndex = null;

        for (int i = 0; i <= array.length - 2; i++) {
            if (array[i] == 1 && array[i + 1] == 0 && array[i + 2] == 1) {
                int leftCount = 0;
                int rightCount = 0;

                int leftIndex = i;
                while (leftIndex >= 0 && array[leftIndex] == 1) {
                    leftIndex--;
                    leftCount++;
                }

                int rightIndex = i + 2;
                while (rightIndex < array.length && array[rightIndex] == 1) {
                    rightIndex++;
                    rightCount++;

                }
                int sum = leftCount + rightCount;
                if (resultSequence < sum) {
                    resultIndex = i + 1;
                    resultSequence = sum;
                }
                resultSequence = Math.max(resultSequence, leftCount + rightCount);
            }
        }
        System.out.printf("Индекс: %s%n", resultIndex);
        System.out.printf("Кол-во единиц: %s", resultSequence);
    }

    /**
     * №8 Дан массив из нулей и единиц. Нужно определить, какой максимальный по длине подинтервал единиц можно получить, удалив ровно один элемент массива.
     * a. Преобразовываем массив к виду списка пар: значение, кол-во повторений подряд
     * b. ищем в списке ноль вокруг которого будут еденицы, проверяем, чтобы это был не первый и не последний элемент в коллекции
     * c. Подсчитываем максимальное значение путем сложения значений левого и правого элемента
     * d. Если результат ноль, значит удаление одного элемента не повлияет на результат и мы ищем просто максимальную последовательность единиц
     * On + доп память
     */
    private static void oneMaxSequence1() {
        int[] array = {0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1};
        // int[] array = {0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1};

        int currentValue = array[0];

        // Группируем массив к виду [[0, 2], [1, 2], [0, 1], [1, 3]]
        List<List<Integer>> sequenceList = new ArrayList<>();
        int count = 1;
        for (int i = 1; i < array.length; i++) {
            if (currentValue == array[i]) {
                count++;
                if (i == array.length - 1) {
                    sequenceList.add(List.of(currentValue, count));
                }
            } else {
                sequenceList.add(List.of(currentValue, count));
                count = 1;
            }
            currentValue = array[i];
        }
        System.out.println(sequenceList);

        int result = 0;
        for (int i = 0; i < sequenceList.size(); i++) {
            if (sequenceList.get(i).get(0) == 0 && sequenceList.get(i).get(1) == 1) {
                if (i != 0 && i != sequenceList.size() - 1) {
                    result = Math.max(result, sequenceList.get(i - 1).get(1) + sequenceList.get(i + 1).get(1));
                }
            }
        }

        if (result == 0) {
            result = sequenceList.stream().filter(a -> a.get(0) == 1).map(a -> a.get(1)).max(Comparator.naturalOrder()).get();
        }
        System.out.println(result);
    }

    /**
     * №9 Дан список интов, повторяющихся элементов в списке нет. Нужно преобразовать это множество в строку, сворачивая соседние по числовому ряду числа в диапазоны.
     * Сортируем массив
     * Проходимся по списку и сравниваем текущий элемент со следующим. Если он увеличился больше чем на единицу, значит отмечаем конец последовательности
     * Делаем дополнительную проверку на последний элемент и добавляем его если нужно
     */
    private static void combineToSequences() {
        int[] array = {1, 4, 5, 2, 3, 9, 8, 11, 0};
        // int[] array = {9, 8, 11};
        Arrays.sort(array);
        int currentValue = array[0];
        int leftValue = array[0];
        int rightValue;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < array.length; i++) {
            if (currentValue + 1 != array[i]) {
                rightValue = currentValue;
                if (leftValue == rightValue) {
                    sb.append(currentValue);
                } else {
                    sb.append(leftValue).append("-").append(rightValue).append(", ");
                }
                leftValue = array[i];
                if (i == array.length - 1) {
                    sb.append(array[i]);
                }
            }
            currentValue = array[i];
        }
        System.out.println(sb);
    }

    /**
     * №10 Найти одинаковые элементы в 2-ух массивах. Элементы могут повторяться
     * 1. Делаем из первого массива карту ключ-значение массив, значение кол-во вхождений в массиве. В цикле сравниваем
     * значения 2-ого массива со значениями в карте, Если в карте есть такой элемент декрементируем кол-во.
     * Первый вариант. On + доп память.
     * <p>
     * 2. Сортируем массивы. Сравниваем поочередно элементы массива, если не равны, инкрементируем индекс массива, где было меньшее значение,
     * если равны, записываем в результат. O log n
     */
    private static void findSimilarElements() {
        int[] firstArray = {1, 2, 3, 2, 0};
        int[] secondArray = {5, 1, 2, 7, 3, 2};
        StringBuilder sb = new StringBuilder();

        Map<Integer, Integer> mapOfFirstArray = new HashMap<>();
        for (int arrayValue : firstArray) {
            mapOfFirstArray.merge(arrayValue, 1, Integer::sum);
        }

        for (int arrayValue : secondArray) {
            Integer mapValue = mapOfFirstArray.get(arrayValue);
            if (mapValue != null && mapValue > 0) {
                sb.append(arrayValue).append(", ");
                mapOfFirstArray.put(arrayValue, mapValue - 1);
            }
        }
        System.out.println(sb);
    }

    /**
     * №10 Найти одинаковые элементы в 2-ух массивах. Элементы могут повторяться.
     * Второй вариант. O log n
     */
    private static void findSimilarElements1() {
        int[] firstArray = {1, 2, 3, 2, 0, 2, 7, 1, 2};
        int[] secondArray = {5, 1, 2, 7, 3, 2};
        Arrays.sort(firstArray);
        Arrays.sort(secondArray);
        StringBuilder sb = new StringBuilder();

        int lastFirstNum;
        int lastIndexArray1 = 0;

        int lastSecondNum;
        int lastIndexArray2 = 0;

        while (lastIndexArray1 < firstArray.length && lastIndexArray2 < secondArray.length) {
            lastFirstNum = firstArray[lastIndexArray1];
            lastSecondNum = secondArray[lastIndexArray2];

            if (lastFirstNum < lastSecondNum) {
                lastIndexArray1++;
            } else if (lastFirstNum > lastSecondNum) {
                lastIndexArray2++;
            } else {
                sb.append(lastFirstNum).append(" ");
                lastIndexArray1++;
                lastIndexArray2++;
            }
        }
        System.out.println(sb);
    }

    /**
     * №11 Слияние отрезков Вход: [1, 3] [100, 200] [2, 4]   * Выход: [1, 4] [100, 200]
     * Сортируем список по первому элементу.
     * Если начало следующего отрезка меньше конца предыдущего, то сливаем в один отрезок.
     * Если под условие не подходит, добавляем текущий отрезок в результат.
     */
    private static void joinSequence() {
        List<List<Integer>> sequences = List.of(List.of(1, 3), List.of(100, 200), List.of(2, 4), List.of(201, 250), List.of(220, 270));
        List<List<Integer>> sortedSequences = sequences.stream()
                .sorted(Comparator.comparingInt(a -> a.get(0)))
                .collect(Collectors.toList());
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentValue = sortedSequences.get(0);
        for (int i = 1; i < sortedSequences.size(); i++) {
            if (currentValue.get(1) >= sortedSequences.get(i).get(0)) {
                currentValue = List.of(currentValue.get(0), sortedSequences.get(i).get(1));
                if (i == sortedSequences.size() - 1) {
                    result.add(currentValue);
                }
            } else {
                result.add(currentValue);
                currentValue = sortedSequences.get(i);
            }
        }
        System.out.println(result);
    }

    /**
     * №12 Поиск двух элементов массива в сумме дающих ответ близкий к заданному.
     * Если массив не отсортирован, то сортируем
     * В цикле складываем первый и последний элемент. Если ответ больше заданного,то сдвигаем индекс правого элемента влево. Если меньше индекс левого вправо.
     * Для того что бы подсчитать ближайшее к заданному, то вычислять будем по формуле: ож.результат минус сумма крайних значений массива
     * При этом сохраняем индексы предыдущих значений массива.
     * Если по формуле мы получаем значение больше предыдущего то, ответом будут предыдущие значения
     */
    private static void findTwoNumbers() {
        int[] array = new int[]{-1, 0, 2, 4, 5, 7, 9};
        int result = 3;

        int rightIndex = array.length - 1;
        int leftIndex = 0;
        int prevRightIndex = rightIndex;
        int prevLeftIndex = leftIndex;

        int prev = Integer.MIN_VALUE;
        while (true) {
            int preResult = result - (array[leftIndex] + array[rightIndex]);
            if (preResult > prev) {
                prevRightIndex = rightIndex;
                prevLeftIndex = leftIndex;
                prev = preResult;
                if ((array[leftIndex] + array[rightIndex]) > result) {
                    rightIndex--;
                } else if ((array[leftIndex] + array[rightIndex]) < result) {
                    leftIndex++;
                }
            } else {
                break;
            }
        }
        System.out.println(array[prevLeftIndex] + " " + array[prevRightIndex]);
    }

    /**
     * №13 Поиск числа, которое встречается в массиве только 1 раз
     * 1 решение: Отсортировать и в цикле пройтись
     * 2 решение: Положить массив в карту и найти со значением 1
     * 3 решение: Используем множество. Если там есть такой элемент, значит удаляем его, если нет добавляем. (только если дубли четные)
     */
    private static void findOnlyOneNumberInArray() {
        int[] array = new int[]{1, 2, 3, 4, 5, 1, 2, 3, 4};

        Arrays.stream(array).boxed().collect(Collectors.groupingBy(obj -> obj, Collectors.counting())).entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .findFirst()
                .map(Map.Entry::getKey)
                .ifPresent(System.out::println);

// 2 решение
        Set<Integer> set = new HashSet<>();
        for (int num : array) {
            if (set.contains(num)) {
                set.remove(num);
            } else {
                set.add(num);
            }
        }
        System.out.println(set);
    }

}
