package Database;

import DietLogs.DietLogEntry;
import DietLogs.DietLogs;
import DietLogs.Ingredient;
import DietLogs.MealIngredients;
import DietLogs.NutrientInfo;
import ExerciseLogs.ExerciseLog;
import ExerciseLogs.Met;
import userData.User;

import java.util.ArrayList;
import java.util.Random;

abstract class Table {
    abstract String getTableName();
    abstract ArrayList<?> getTable();
    abstract Object getById(int id);
    abstract void updateTable(Object object);
    abstract void deleteEntity(Object object);

    abstract void add(Object object);

}
abstract class LogTable extends Table{
    abstract <T> ArrayList<T> getByUserId(int id);

    abstract void BulkCreateforUser(int Id);

}


class UserTable extends Table {
    private final String name="user";
    private static int userId = 1;
    private static boolean bulkcreateUsed = false;
    private static ArrayList<User> userList = new ArrayList<>();

    void add(String name, String password, String sex, int height, float weight, int age) {
        userList.add(new User(name, password, sex, height, weight, age, userId++));
    }
    void bulkCreate(){
        if(!bulkcreateUsed) {

            userList.add(new User("Bob", "Password", "Male",183, 72.3F, 23, userId++));
            userList.add(new User("Lenny", "12345", "Female",156, 82.4F, 26, userId++));
            userList.add(new User("John", "No", "Male",166, 72.1F, 23, userId++));
            userList.add(new User("Todington", "y4gbp87bqt", "Female",201, 7230.45F, 23, userId++));
            bulkcreateUsed = true;
        }else {
            System.out.println("BulkCreate already used");
        }
    }

    /**
     * @return
     */
    @Override
    String getTableName() {
        return name;
    }

    ArrayList<User> getTable() {
        return userList;
    }

    /**
     * @param id
     * @return
     */
    @Override
    Object getById(int id) {
        for (User user : userList) {
            if (user.getID() == id) {
                return user;
            }
        }
        throw new IllegalArgumentException("User not found");

    }

    Object getByName(String name) {
        for (User user : userList) {
            if (user.getName() == name) {
                return user;
            }
        }
        throw new IllegalArgumentException("User not found");

    }

    /**
     * @param object
     */
    @Override
    void updateTable(Object object) {
        User user = (User) object;
        for (User user1 : userList) {
            if (user1.getID() == user.getID()) {
                user1.setAge(user.getAge());
                user1.setHeight(user.getHeight());
                user1.setWeight(user.getWeight());
                user1.setName(user.getName());
                user1.setPassword(user.getPassword());
                user1.setSex(user.getSex());
                return;
            }
        }
        throw new IllegalArgumentException("User not found");


    }

    /**
     * @param object
     */
    @Override
    void deleteEntity(Object object) {
        User user = (User) object;
        for (User user1 : userList) {
            if (user1.getID() == user.getID()) {
                userList.remove(user1);
                return;
            }
        }
        throw new IllegalArgumentException("User not found");

    }


    public <T> ArrayList<T> getByUserId(int id) {
        ArrayList<User> returnList = new ArrayList<>();
        for (User user : userList) {
            if (user.getID() == id) {
                returnList.add(user);
            }
        }
        return (ArrayList<T>) returnList;
    }

    @Override
    void add(Object object) {
        userList.add((User) object);
    }
}

class ExerciseTable extends LogTable {
    private final String name="exercise_log";
    //table starts at 2 to mimic deleted value in database
    private static int exerciseId = 2;
    private static ArrayList<ExerciseLog> exerciseList = new ArrayList<>();

    void add(int userid, int metid, int duration, int intensity) {
        exerciseList.add(new ExerciseLog(exerciseId++, userid, metid, duration, intensity));
    }
    void BulkCreateforUser(int id){
        exerciseList.add(new ExerciseLog(exerciseId++,10000, 50000, 5, id));
        exerciseList.add(new ExerciseLog(exerciseId++, 50000,70000,4, id));
        exerciseList.add(new ExerciseLog(exerciseId++,  100000, 120000, 7,id));
        exerciseList.add(new ExerciseLog(exerciseId++, 150000, 170000, 15, id));
    }

    /**
     * @param object
     */
    @Override
    void updateTable(Object object) {
        ExerciseLog exerciseLog = (ExerciseLog) object;
        for (ExerciseLog exerciseLog1 : exerciseList) {
            if (exerciseLog1.getId() == exerciseLog.getId()) {

                exerciseLog1.setEndTime(exerciseLog.getEndTime());
                exerciseLog1.setStartTime(exerciseLog.getStartTime());
                exerciseLog1.setDuration(exerciseLog.getDuration());
                exerciseLog1.setMetId(exerciseLog.getMetId());
                exerciseLog1.setUserId(exerciseLog.getUserId());
                return;
            }
        }
        throw new IllegalArgumentException("Exercise not found");

    }

    /**
     * @param object
     */
    @Override
    void deleteEntity(Object object) {
        ExerciseLog exerciseLog = (ExerciseLog) object;
        for (ExerciseLog exerciseLog1 : exerciseList) {
            if (exerciseLog1.getId() == exerciseLog.getId()) {
                exerciseList.remove(exerciseLog1);
                return;
            }
        }
        throw new IllegalArgumentException("Exercise not found");

    }

    /**
     * @return
     */
    @Override
    String getTableName() {
       return name;
    }

    ArrayList<ExerciseLog> getTable() {
        return exerciseList;
    }

    /**
     * @param id
     * @return
     */
    @Override
    Object getById(int id) {
        for (ExerciseLog exerciseLog : exerciseList) {
            if (exerciseLog.getId() == id) {
                return exerciseLog;
            }
        }
        throw new IllegalArgumentException("Exercise not found");
    }


    @Override
    <T> ArrayList<T> getByUserId(int id) {

        ArrayList<ExerciseLog> returnList = new ArrayList<>();
        //print list with user id


        for (ExerciseLog exerciseLog : exerciseList) {
            if (exerciseLog.getUserId() == id) {
                returnList.add(exerciseLog);
            }
        }
        return (ArrayList<T>) returnList;

    }

    @Override
    void add(Object object) {
        ExerciseLog exerciseLog = (ExerciseLog) object;
        ExerciseLog copy= new ExerciseLog(exerciseId++, exerciseLog.getStartTime(),exerciseLog.getEndTime(), exerciseLog.getMetId(),exerciseLog.getUserId());

        exerciseList.add(copy);
    }

}

class MetStaticTable extends Table {
    private final String name="met";
    private static int metId = 1;
    static final ArrayList<Met> metList = new ArrayList<>();

    MetStaticTable() {

        if (metList.isEmpty()) {
            Random r = new Random();
            for (int i = 1; i < 5; i++){
                String Ex =getExerciseFromTable(i);
                for (int j = 1; j < 5; j++){
                    //random float between 1 and 20
                    float randomF = 1 + 19 * r.nextFloat();
                    metList.add(new Met(metId++, Ex, getIntensityFromTable(j), randomF));
                }
            }
        }

    }

    /**
     * @return
     */
    @Override
    String getTableName() {
        return name;
    }

    ArrayList<Met> getTable() {
        return metList;
    }

    /**
     * @param id
     * @return
     */
    @Override
    Object getById(int id) {
        for (Met met : metList) {
            if (met.id() == id) {
                return met;
            }
        }
        throw new IllegalArgumentException("Met not found");
    }

    /**
     * @param object
     */
    @Override
    void updateTable(Object object) {
        throw new IllegalArgumentException("Record Table cant change");
    }

    /**
     * @param object
     */
    @Override
    void deleteEntity(Object object) {
        throw new IllegalArgumentException("Record Table not able to change");
    }

    @Override
    void add(Object object) {
        metList.add((Met) object);
    }

    //only used in test database

    private String getIntensityFromTable(int id) {
        //get intensity from table
        switch (id) {
            case 1:
                return "low";
            case 2:
                return "medium";
            case 3:
                return "high";
            case 4:
                return "very high";
        }
        throw new IllegalArgumentException("Invalid Intensity ID");
    }

    private String getExerciseFromTable(int id) {
        //get exercise from table
        switch (id) {
            case 1:
                return "walking";
            case 2:
                return "running";
            case 3:
                return "swimming";
            case 4:
                return "cycling";
        }
        throw new IllegalArgumentException("Invalid Exercise ID");
    }

}

class DietTable extends LogTable{
    private final String name="diet_log";
    private static int dietId = 4;
    private static ArrayList<DietLogEntry> dietList = new ArrayList<>();


    /**
     * @return
     */
    @Override
    String getTableName() {
       return name;
    }

    @Override
    ArrayList<?> getTable() {
        return dietList;
    }

    /**
     * @param id
     * @return
     */
    @Override
    Object getById(int id) {
        for (DietLogEntry dietLog : dietList) {
            if (dietLog.getDietId() == id) {
                return dietLog;
            }
        }
        throw new IllegalArgumentException("Diet not found");
    }

    @Override
    void add(Object object) {
        dietList.add((DietLogEntry) object);
    }

    @Override
    <T> ArrayList<T> getByUserId(int id) {
        ArrayList<DietLogEntry> returnList = new ArrayList<>();
        for (DietLogEntry dietLog : dietList) {
            if (dietLog.getUserId() == id) {
                returnList.add(dietLog);
            }
        }
        return (ArrayList<T>) returnList;
    }

    @Override
    void BulkCreateforUser(int Id) {

        dietList.add(new DietLogEntry(dietId++, "Apple", "Fruit", 10000, Id));
        dietList.add(new DietLogEntry(dietId++, "Banana", "Fruit", 50000, Id));
        dietList.add(new DietLogEntry(dietId++, "Orange", "Fruit", 100000, Id));
        dietList.add(new DietLogEntry(dietId++, "Pear", "Fruit", 150000, Id));
    }

    /**
     * @param object
     */
    @Override
    void updateTable(Object object) {
        DietLogEntry dietLog = (DietLogEntry) object;
        for (DietLogEntry dietLog1 : dietList) {
            if (dietLog1.getDietId() == dietLog.getDietId()) {
                dietLog1.setDateTime(dietLog.getDateTime());
                dietLog1.setFoodGroup(dietLog.getFoodGroup());
                dietLog1.setName(dietLog.getName());
                dietLog1.setUserId(dietLog.getUserId());
                return;
            }
        }
        throw new IllegalArgumentException("Diet not found");

    }

    /**
     * @param object
     */
    @Override
    void deleteEntity(Object object) {
        DietLogEntry dietLog = (DietLogEntry) object;
        for (DietLogEntry dietLog1 : dietList) {
            if (dietLog1.getDietId() == dietLog.getDietId()) {
                dietList.remove(dietLog1);
                return;
            }
        }
        throw new IllegalArgumentException("Diet not found");

    }

}

class IngredientTable extends Table {

    private final String name="Ingredient";
    //table starts at 2 to mimic deleted value in database
    private static int IngredientId = 5;
    private static ArrayList<Ingredient> IngredientList = new ArrayList<>();


    @Override
    String getTableName() {
        return name;
    }

    @Override
    ArrayList<?> getTable() {
        return IngredientList;
    }

    @Override
    Object getById(int id) {
        for (Ingredient ingredient : IngredientList) {
            if (ingredient.getIngredientId() == id) {
                return ingredient;
            }
        }
        throw new IllegalArgumentException("Ingredient not found");
    }

    @Override
    void updateTable(Object object) {
        Ingredient ingredient = (Ingredient) object;
        for (Ingredient ingredient1 : IngredientList) {
            if (ingredient1.getIngredientId() == ingredient.getIngredientId()) {
                ingredient1.setIngredientName(ingredient.getIngredientName());
                return;
            }
        }

    }

    @Override
    void deleteEntity(Object object) {
        Ingredient ingredient = (Ingredient) object;
        for (Ingredient ingredient1 : IngredientList) {
            if (ingredient1.getIngredientId() == ingredient.getIngredientId()) {
                IngredientList.remove(ingredient1);
                return;
            }
        }
    }

    @Override
    void add(Object object) {
//        Ingredient ingredient = (Ingredient) object;
//        Ingredient copy= new Ingredient(IngredientId++, ingredient.getIngredientName());
//        IngredientList.add(copy);
    }
}

class NutrientInfoTable extends Table {

    private final String name = "nutrient_info";
    //table starts at 2 to mimic deleted value in database
    private static int NutrientId = 5;
    private static int IngredientId = 5;

    private static ArrayList<NutrientInfo> NutrientList = new ArrayList<>();


    @Override
    String getTableName() {
        return name;
    }

    @Override
    ArrayList<?> getTable() {
        return NutrientList;
    }

    @Override
    Object getById(int id) {
        for (NutrientInfo nutrientInfo : NutrientList) {
            if (nutrientInfo.getNutrientId() == id) {
                return nutrientInfo;
            }
        }
        throw new IllegalArgumentException("Nutrient not found");
    }

    Object getByIngredientId(int id) {
        for (NutrientInfo nutrientInfo : NutrientList) {
            if (nutrientInfo.getIngredientId() == id) {
                return nutrientInfo;
            }
        }
        throw new IllegalArgumentException("Nutrient not found");
    }

    @Override
    void updateTable(Object object) {
        NutrientInfo nutrientInfo = (NutrientInfo) object;
        for (NutrientInfo nutrientInfo1 : NutrientList) {
            if (nutrientInfo1.getNutrientId() == nutrientInfo.getNutrientId()) {
                nutrientInfo1.setIngredientId(nutrientInfo.getIngredientId());
                nutrientInfo1.setNutrientName(nutrientInfo.getNutrientName());
                nutrientInfo1.setNutrientValue(nutrientInfo.getNutrientValue());
                return;
            }
        }
    }

    @Override
    void deleteEntity(Object object) {
        NutrientInfo nutrientInfo = (NutrientInfo) object;
        for (NutrientInfo nutrientInfo1 : NutrientList) {
            if (nutrientInfo1.getNutrientId() == nutrientInfo.getNutrientId()) {
                NutrientList.remove(nutrientInfo1);
                return;
            }
        }

    }

    @Override
    void add(Object object) {
        NutrientInfo nutrientInfo = (NutrientInfo) object;
        NutrientInfo copy= new NutrientInfo(NutrientId++, nutrientInfo.getIngredientId(), nutrientInfo.getNutrientName(), nutrientInfo.getNutrientValue());
        NutrientList.add(copy);
    }
}

class MaelIngredientTable extends Table {

    private final String name = "MealIngredient";
    //table starts at 2 to mimic deleted value in database
    private static int MealId = 5;
    private static int IngredientId = 7;

    private static ArrayList<MealIngredients> MealIngredientList = new ArrayList<>();

    @Override
    String getTableName() {
        return name;
    }

    @Override
    ArrayList<?> getTable() {
        return MealIngredientList;
    }

    @Override
    Object getById(int id) {
        for (MealIngredients mealIngredients : MealIngredientList) {
            if (mealIngredients.getMealId() == id ) {
                return mealIngredients;
            }
        }
        throw new IllegalArgumentException("MealIngredient not found");
    }

    Object getByIngredientId(int id) {
        for (MealIngredients mealIngredients : MealIngredientList) {
            if (mealIngredients.getIngredientId() == id) {
                return mealIngredients;
            }
        }
        throw new IllegalArgumentException("MealIngredient not found");
    }

    @Override
    void updateTable(Object object) {
        MealIngredients mealIngredients = (MealIngredients) object;
        for (MealIngredients mealIngredients1 : MealIngredientList) {
            if (mealIngredients1.getMealId() == mealIngredients.getMealId() && mealIngredients1.getIngredientId() == mealIngredients.getIngredientId()) {
                mealIngredients1.setIngredientId(mealIngredients.getIngredientId());
                mealIngredients1.setMealId(mealIngredients.getMealId());
                mealIngredients1.setQuantityValue(mealIngredients.getQuantityValue());
                return;
            }
        }

    }

    @Override
    void deleteEntity(Object object) {

        MealIngredients mealIngredients = (MealIngredients) object;
        for (MealIngredients mealIngredients1 : MealIngredientList) {
            if (mealIngredients1.getMealId() == mealIngredients.getMealId() && mealIngredients1.getIngredientId() == mealIngredients.getIngredientId() ) {
                MealIngredientList.remove(mealIngredients1);
                return;
            }
        }

    }

    @Override
    void add(Object object) {
        MealIngredients mealIngredients = (MealIngredients) object;
        MealIngredients copy= new MealIngredients(MealId++, mealIngredients.getIngredientId(), mealIngredients.getQuantityValue());
        MealIngredientList.add(copy);
    }
}