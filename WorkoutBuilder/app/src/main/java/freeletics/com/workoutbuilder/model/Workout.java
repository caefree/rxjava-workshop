package freeletics.com.workoutbuilder.model;

import com.google.common.collect.ImmutableList;

import lombok.Value;


@Value
public class Workout {

    private final String name;
    private final ImmutableList<RoundExercise> roundExercises;
    private final float sumOfTime;
    private final int sumOfReps;

    private Workout(String name, ImmutableList<RoundExercise> roundExercises, float sumOfTime, int sumOfReps) {
        this.name = name;
        this.roundExercises = roundExercises;
        this.sumOfTime = sumOfTime;
        this.sumOfReps = sumOfReps;
    }

    public static WorkoutBuilder builder() {
        return new WorkoutBuilder();
    }

    public WorkoutBuilder toBuilder() {
        return new WorkoutBuilder().name(name)
                .roundExercises(roundExercises)
                .sumOfReps(sumOfReps)
                .sumOfTime(sumOfTime);
    }

    public static class WorkoutBuilder {
        private String name;
        private ImmutableList<RoundExercise> roundExercises;
        private float sumOfTime;
        private int sumOfReps;

        WorkoutBuilder() {
        }

        public Workout.WorkoutBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Workout.WorkoutBuilder roundExercises(ImmutableList<RoundExercise> roundExercises) {
            this.roundExercises = roundExercises;
            return this;
        }

        public Workout.WorkoutBuilder sumOfTime(float sumOfTime) {
            this.sumOfTime = sumOfTime;
            return this;
        }

        public Workout.WorkoutBuilder sumOfReps(int sumOfReps) {
            this.sumOfReps = sumOfReps;
            return this;
        }

        public Workout build() {
            return new Workout(name, roundExercises, sumOfTime, sumOfReps);
        }

        public String toString() {
            return "freeletics.com.workoutbuilder.model.Workout.WorkoutBuilder(name=" + this.name + ", roundExercises=" + this.roundExercises + ", sumOfTime=" + this.sumOfTime + ", sumOfReps=" + this.sumOfReps + ")";
        }
    }
}
