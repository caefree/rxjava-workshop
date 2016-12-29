package freeletics.com.workoutbuilder.api;

import freeletics.com.workoutbuilder.model.Exercises;
import rx.Observable;

/**
 * Created by cae on 27/12/16.
 */

public interface ExerciseApi {

    Observable<Exercises> exercises();
}
