package freeletics.com.workoutbuilder.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class RoundExercise {

    private final Integer roundIndex;
    private final Integer exerciseIndex;
    private final Exercise exercise;
}
