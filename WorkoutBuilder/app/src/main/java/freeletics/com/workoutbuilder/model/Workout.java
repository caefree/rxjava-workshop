package freeletics.com.workoutbuilder.model;

import com.google.common.collect.ImmutableList;

import lombok.Builder;
import lombok.Value;


@Value
@Builder(toBuilder = true)
public class Workout {

    private final String name;
    private final ImmutableList<RoundExercise> roundExercises;
    private final Integer roundCount;
}
