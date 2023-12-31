package Controller.DietLogic;

import DietLogs.DietLogEntry;

import java.util.ArrayList;
import java.util.HashMap;

public interface IDietLogic {


    public ArrayList<DietLogEntry> mealsByDateRange(ArrayList<Integer> startDate, ArrayList<Integer> EndDate, int userId) throws Exception;

    public HashMap<String, Float> AverageDailyNutrientInfo(ArrayList<Integer> startDate, ArrayList<Integer> EndDate, int userId) throws Exception;

    public HashMap<String, Float> PercentagesOfNutrients(ArrayList<Integer> startDate, ArrayList<Integer> EndDate, int userId)throws Exception;

    public HashMap<Integer, String> addMeal(String mealName, String mealType, ArrayList<Integer> dateTime, int userId) throws Exception;

    public void deleteMeal(int mealId) throws Exception;

    public HashMap<String, Float> addIngredient(int mealId, String ingredientName, float quantity) throws Exception;

    public void deleteIngredient(int mealId, String ingredientName) throws Exception;

    public ArrayList<String> getAllIngredientsAvailable() throws Exception;

    public HashMap<String, Float> alignmentWithCanadaFoodGuide(ArrayList<Integer> startDate, ArrayList<Integer> endDate, int userId) throws Exception;

}
