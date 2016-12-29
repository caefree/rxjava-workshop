package freeletics.com.workoutbuilder.model;

import java.util.HashMap;
import java.util.Map;

import static freeletics.com.workoutbuilder.model.BodyRegion.BACK;
import static freeletics.com.workoutbuilder.model.BodyRegion.BICEPS;
import static freeletics.com.workoutbuilder.model.BodyRegion.CHEST;
import static freeletics.com.workoutbuilder.model.BodyRegion.CORE;
import static freeletics.com.workoutbuilder.model.BodyRegion.LOWER_LEG;
import static freeletics.com.workoutbuilder.model.BodyRegion.TRICEPS;
import static freeletics.com.workoutbuilder.model.BodyRegion.UPPER_LEG;

public class ExerciseDecisions {

    public static final Map<BodyRegion, Iterable<Exercise.ExerciseDef>> limitationsMap = new HashMap<>();
    public static final Map<BodyRegion, Iterable<Exercise.ExerciseDef>> targetMap = new HashMap<>();

    static {
        targetMap.put(BACK, ExerciseConstraints.TARGETING_BACK);
        targetMap.put(BICEPS, ExerciseConstraints.TARGETING_BICEPS);
        targetMap.put(CORE, ExerciseConstraints.TARGETING_CORE);
        targetMap.put(CHEST, ExerciseConstraints.TARGETING_CHEST);
        targetMap.put(LOWER_LEG, ExerciseConstraints.TARGETING_LOWER_LEG);
        targetMap.put(UPPER_LEG, ExerciseConstraints.TARGETING_UPPER_LEG);
        targetMap.put(TRICEPS, ExerciseConstraints.TARGETING_TRICEPS);

        limitationsMap.put(BACK, ExerciseConstraints.HARMFUL_FOR_BACK);
        limitationsMap.put(BICEPS, ExerciseConstraints.HARMFUL_FOR_BICEPS);
        limitationsMap.put(CORE, ExerciseConstraints.HARMFUL_FOR_CORE);
        limitationsMap.put(CHEST, ExerciseConstraints.HARMFUL_FOR_CHEST);
        limitationsMap.put(LOWER_LEG, ExerciseConstraints.HARMFUL_FOR_LOWER_LEG);
        limitationsMap.put(UPPER_LEG, ExerciseConstraints.HARMFUL_FOR_UPPER_LEG);
        limitationsMap.put(TRICEPS, ExerciseConstraints.HARMFUL_FOR_TRICEPS);

    }
}
