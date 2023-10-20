package Database;

public class IExerciseClientFactory {
    //build a static factory class to return the correct client

    public static IExerciseClient getIExerciseClient() {
        if(TestIdentifier.isTest()) {
            return new ExerciseLogTestClient();
        }
        else {
            return new ExerciseLogClient();
        }
    }
    public static IExerciseClient getIExerciseClient(boolean isTest) {
        if(isTest) {
            TestIdentifier.isTest();
            return new ExerciseLogTestClient();
        }
        else {
            return new ExerciseLogClient();
        }
    }
}
