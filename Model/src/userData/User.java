package userData;

import DietLogs.DietLogs;
import ExerciseLogs.ExerciseLog;

public class User{
    private String name;
    private String password;
    private String sex;
    private int height;
    private float weight;
    private int age;
    private int id;
    private int bmr;
    private ExerciseLog exerciseLogs;
    private DietLogs dietLogs;

    public User(String name, String password, String sex, int height, float weight, int age, int id){
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.id = id;
        calculateBMR();
    }

    private void calculateBMR(){
        if (this.age < 18){
            //using the Mifflin-St Jeor Equation
            this.bmr = (int) (88.362 + (13.397 * this.weight) + (4.799 * this.height) - (5.677 * this.age));
        }
        else{
            this.bmr = (int) (447.593 + (9.247 * this.weight) + (3.098 * this.height) - (4.330 * this.age));
        }
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }

    public void setSex(String sex){
        this.sex = sex;
    }
    public String getSex(){
        return sex;
    }

    public void setHeight(int height){
        this.height = height;
        //recalculating BMR with the new parameter
        calculateBMR();
    }
    public int getHeight(){
        return height;
    }

    public void setWeight(float weight){
        this.weight = weight;
        //recalculating BMR with the new parameter
        calculateBMR();
    }
    public float getWeight(){
        return weight;
    }

    public void setAge(int age){
        this.age = age;
        //recalculating BMR with the new parameter
        calculateBMR();
    }
    public int getAge(){
        return age;
    }

    public void setID(int id){
        this.id = id;
    }
    public int getID(){
        return id;
    }

    public int getBMR(){
        return bmr;
    }

//    public void addExercise(ExerciseLog exerciseLog){ this.exerciseLogs}
}