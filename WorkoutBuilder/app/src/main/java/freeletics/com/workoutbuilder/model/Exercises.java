package freeletics.com.workoutbuilder.model;

import java.util.List;

import lombok.Value;

@Value
public class Exercises {
    List<Exercise> exercises;

    public List<Exercise> getExercises() {
        return exercises;
    }
}
