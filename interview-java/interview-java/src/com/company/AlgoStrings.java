package com.company;

import java.util.*;

public class AlgoStrings {

    public static void main(String[] args) {
        groupWordsBySymbols();
    }

    /**
     * №1 Сравнение 2-х перемешанных строк
     * 1. Создаем карты из обеих строк (ключ - символ, значение - кол-во вхождений) и сравниваем их
     * 2. Сортируем строки и сравниваем
     */
    private static void equalTwoShuffledStrings() {
        String s1 = "abcdeef";
        var s2 = "dbfeace";

        var map1 = new HashMap<Character, Integer>();
        var map2 = new HashMap<Character, Integer>();

        for (char c : s1.toCharArray()) {
            map1.merge(c, 1, Integer::sum);
        }

        for (char c : s2.toCharArray()) {
            map2.merge(c, 1, Integer::sum);
        }

        System.out.println(map1.equals(map2));

        // 2 вариант
        System.out.println("---------");
        var a1 = s1.toCharArray();
        var a2 = s2.toCharArray();
        Arrays.sort(a1);
        Arrays.sort(a2);
        System.out.println(Arrays.equals(a1, a2));
    }

    /**
     * №2 Найти одинаковые символы в строках
     * 1. Помещаем один массив в множество. Проверяем каждый элемент 2-ого массива в множестве
     * 2. Помещаем оба массива в списки. Через retainAll получаем общие элементы.
     */
    private static void string_intersection() {
        var s1 = "abcdef";
        var s2 = "bbfgh";

        var set = new HashSet<Character>();
        for (char c : s1.toCharArray()) {
            set.add(c);
        }

        StringBuilder result = new StringBuilder();
        for (char c : s2.toCharArray()) {
            if (set.contains(c)) {
                result.append(c);
            }
        }
        System.out.println(result);

        // 2 вариант
        var list1 = new ArrayList<Character>();
        for (char c : s1.toCharArray()) {
            list1.add(c);
        }

        var list2 = new ArrayList<Character>();
        for (char c : s2.toCharArray()) {
            list2.add(c);
        }

        list1.retainAll(list2);
        System.out.println(list1);
    }

    /**
     * №3 Найти количество вхождений символов в строку и напечатать уникальные символы
     * 1. Делаем из строки карту (ключ - символ, значение - кол-во вхождений). Проходимся по карте и выбираем все со значением 1
     */
    private static void unique_char_count() {
        var str = "afsgsfahdgsfagf";
        var map = new HashMap<Character, Integer>();
        for (char c : str.toCharArray()) {
            map.merge(c, 1, Integer::sum);
        }
        System.out.println(map);
        map.forEach((character, integer) -> {
            if (integer == 1) {
                System.out.println(character);
            }
        });
    }

    /**
     * №4 Написать функцию, которая вернёт True, если из первой строки можно получить вторую, совершив не более 1 изменения (== удаление / замена символа).
     * Вычитаем длины строк, если значение больше единицы сразу возвращаем false.
     * Находим строку с меньшим кол-вом символов.
     * Обходим элементы массивов слева, пока не найдем несовпадение. Определяем индекс элемента.
     * Обходим элементы массивов справа, пока не найдем несовпадение. Определяем индекс элемента.
     * Вычитаем индексы, если значение меньше 2, то true.
     */
    private static void string_compare_with_only_one_change() {
        var s1 = "abcdefg".toCharArray();
        var s2 = "abcd-efg".toCharArray();

        if (Math.abs(s1.length - s2.length) > 1) {
            System.out.println("false");
            return;
        }

        var minLength = Math.min(s1.length, s2.length);
        var i = 0;
        while (i < minLength && s1[i] == s2[i]) {
            i++;
        }

        var i1 = s1.length - 1;
        var i2 = s2.length - 1;
        while (i < i1 && i < i2 && s1[i1] == s2[i2]) {
            i1--;
            i2--;
        }
        System.out.println(Math.max(i1, i2) - i < 2);
    }

    /**
     * №5 Подсчитывает количество одинаковых подряд идущих символов.
     * Если символ встречается 1 раз, он остается без изменений, Если символ повторяется более 1 раза, к нему добавляется количество повторений.
     */
    private static void countChars() {
        String text = "AAAABBBCCXYZDDDDEEEFFFAAAAAABBBBBBBBBBBBBBBBBBBBBBBBBBBB";
        StringBuilder result = new StringBuilder();
        char[] chars = text.toCharArray();
        int count = 1;
        for (int i = 0; i < chars.length; i++) {
            char chr = chars[i];
            if (chars.length > (i + 1) && chr == chars[i + 1]) {
                count++;
            } else {
                if (count == 1) {
                    result.append(chr);
                } else {
                    result.append(chr).append(count);
                }
                count = 1;
            }
        }
        System.out.println(result);
    }

    /**
     * №6 Сгруппировать слова по "общим буквам".
     * Создаем карту. Ключ отсортированное значение, значение - список слов массива.
     * Сортируем каждое значение и кидаем в карту
     */
    private static void groupWordsBySymbols() {
        String[] words = {"eat", "tea", "tan", "ate", "nat", "bat"};
        Map<String, List<String>> map = new HashMap<>();
        for (String word : words) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sortedWord = new String(chars);

            List<String> strings = map.get(sortedWord);
            if (strings == null) {
                List<String> list = new ArrayList<>();
                list.add(word);
                map.put(sortedWord, list);
            } else {
                strings.add(word);
                map.put(sortedWord, strings);
            }
        }
        map.forEach((k, v) -> System.out.printf("%s - %s%n", k, v));
    }

    /**
     * №7 Вычислить кол-во операций (добавление, удаление, замена) необходимых, что бы из 1-ой строки получить 2-ую
     */
    private static void editingDistance() {
        String first = "edit";
        String second = "tdi";

        int[] cur = new int[second.length() + 1];
        int[] prev = new int[second.length() + 1];

        for (int n = 0; n <= first.length(); n++) {
            for (int m = 0; m <= second.length(); m++) {
                if (n == 0) {
                    cur[m] = m;
                } else if (m == 0) {
                    cur[m] = n;
                } else {
                    int res1 = cur[m - 1] + 1;
                    int res2 = prev[m] + 1;
                    int res3 = prev[m - 1] + (first.charAt(n - 1) == second.charAt(m - 1) ? 0 : 1);
                    int result = Math.min(Math.min(res1, res2), res3);
                    cur[m] = result;
                }
            }
            int[] temp = cur;
            cur = prev;
            prev = temp;
        }
        System.out.println(prev[second.length()]);
    }

}
