package freeletics.com.workoutbuilder.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class RoundExercise {

    private final Integer round;
    private final Exercise exercise;
}
