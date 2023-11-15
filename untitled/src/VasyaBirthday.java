import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class VasyaBirthday {

    public static void main(String[] args) throws IOException {
        Map<String, Integer> dishesCountMap = new HashMap<>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("src/files/birthday/input.txt")));
        int dishesCount = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < dishesCount; i++) {
            String dish = bufferedReader.readLine();
            String[]dishArray = dish.split(" ");
            dishesCountMap.put(dishArray[0], Integer.parseInt(dishArray[1]));
            int ingredientsCount = Integer.parseInt(dishArray[2]);
            for (int j = 0; j < ingredientsCount; j++) {
                String ingredient = bufferedReader.readLine();
                String[]ingredientArray = ingredient.split(" ");

            }
        }
    }

static class Ingredient {
        private String name;
        private long cnt;
}

    static class IngredientBuyInfo {
        long cost;
        long cnt;
        long buyCnt;

    }
}
