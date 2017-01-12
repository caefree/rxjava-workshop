package freeletics.com.workoutbuilder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.github.dkharrat.nexusdialog.FormController;
import com.github.dkharrat.nexusdialog.FormModel;
import com.github.dkharrat.nexusdialog.controllers.FormSectionController;
import com.github.dkharrat.nexusdialog.controllers.SelectionController;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import freeletics.com.workoutbuilder.api.ExerciseApi;
import freeletics.com.workoutbuilder.api.JsonExerciseApi;
import freeletics.com.workoutbuilder.model.Exercise;
import freeletics.com.workoutbuilder.model.Exercises;
import freeletics.com.workoutbuilder.model.RoundExercise;
import freeletics.com.workoutbuilder.model.Workout;
import freeletics.com.workoutbuilder.ui.ExerciseAdapter;
import ix.Ix;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    public static final String FILTER_EXERCISE_1 = "filterExercise1";
    public static final String FILTER_EXERCISE_2 = "filterExercise2";
    public static final String FILTER_EXERCISE_3 = "filterExercise3";
    public static final String FILTER_ROUND_COUNT = "filterRoundCount";
    public static final String FILTER_INIT_REP = "filterInitRep";
    public static final String FILTER_DECREMENT = "filterDecrement";

    @BindView(R.id.exercise_list)
    RecyclerView recyclerView;

    @BindView(R.id.form_elements_container)
    LinearLayout form;

    FormController setUpForm() {
        FormController formController = new FormController(this);
        FormSectionController section = new FormSectionController(this, "Workout Builder");

        SelectionController exerciseSelector1 = new SelectionController(this, FILTER_EXERCISE_1, "Exercise", true, "Select One",
                Ix.fromArray(Exercise.ExerciseDef.values()).map(Enum::name).toList(), true);

        SelectionController exerciseSelector2 = new SelectionController(this, FILTER_EXERCISE_2, "Exercise", true, "Select Two",
                Ix.fromArray(Exercise.ExerciseDef.values()).map(Enum::name).toList(), true);

        SelectionController exerciseSelector3 = new SelectionController(this, FILTER_EXERCISE_3, "Exercise", true, "Select Three",
                Ix.fromArray(Exercise.ExerciseDef.values()).map(Enum::name).toList(), true);

        SelectionController roundSelector = new SelectionController(this, FILTER_ROUND_COUNT, "Number of Rounds", true, "0",
                Ix.range(1,5).map(String::valueOf).toList(), true);


        SelectionController repNumberEditText = new SelectionController(this, FILTER_INIT_REP, "Initial Rep Count", true, "50",
                Ix.fromArray(25, 50).map(String::valueOf).toList(), true);


        SelectionController decrementNumberEditTExt = new SelectionController(this, FILTER_DECREMENT, "Decrement", true, "10",
                Ix.fromArray(5, 10).map(String::valueOf).toList(), true);


        section.addElement(exerciseSelector1);
        section.addElement(exerciseSelector2);
        section.addElement(exerciseSelector3);
        section.addElement(roundSelector);
        section.addElement(repNumberEditText);
        section.addElement(decrementNumberEditTExt);

        formController.addSection(section);
        formController.recreateViews(form);

        return formController;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JsonExerciseApi api = new JsonExerciseApi(this);

        ButterKnife.setDebug(true);
        ButterKnife.bind(this);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                        WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        final FormController formController = setUpForm();

        formController.getModel().setValue(FILTER_EXERCISE_1, Exercise.ExerciseDef.Burpees.name());
        formController.getModel().setValue(FILTER_EXERCISE_2, Exercise.ExerciseDef.Squats.name());
        formController.getModel().setValue(FILTER_EXERCISE_3, Exercise.ExerciseDef.Situps.name());
        formController.getModel().setValue(FILTER_ROUND_COUNT, "5");
        formController.getModel().setValue(FILTER_INIT_REP, "25");
        formController.getModel().setValue(FILTER_DECREMENT, "5");

        final String workoutName = "Athena";

        final Workout.WorkoutBuilder workoutBuilder =
                Workout.builder().sumOfReps(0).sumOfTime(0).name(workoutName).roundExercises(ImmutableList.of());

        buildPropertyChangeListener(formController)
                .flatMap(model -> {
                    final Exercise.ExerciseDef exerciseOne = Exercise.ExerciseDef.valueOf(model.getValue(FILTER_EXERCISE_1).toString());
                    final Exercise.ExerciseDef exerciseTwo = Exercise.ExerciseDef.valueOf(model.getValue(FILTER_EXERCISE_2).toString());
                    final Exercise.ExerciseDef exerciseThree = Exercise.ExerciseDef.valueOf(model.getValue(FILTER_EXERCISE_3).toString());

                    final Integer roundCount = Integer.valueOf(model.getValue(FILTER_ROUND_COUNT).toString());
                    final Integer repCount = Integer.valueOf(model.getValue(FILTER_INIT_REP).toString());
                    final Integer repDecrement = Integer.valueOf(model.getValue(FILTER_DECREMENT).toString());

                    ImmutableList<Exercise.ExerciseDef> exerciseDefList = ImmutableList.of(exerciseOne, exerciseTwo, exerciseThree);

                    return getFullExerciseList(api)
                            .filter(withExercises(exerciseDefList))
                            .flatMap((exercise ->
                                    Observable.range(1, roundCount)
                                            .map(round -> {
                                                final int trainingVolume = repCount - ((round - 1) * repDecrement);
                                                return RoundExercise.builder()
                                                        .roundIndex(round)
                                                        .exerciseIndex(exerciseDefList.indexOf(exercise.getId()) + 1)
                                                        .exercise(exercise
                                                                .toBuilder()
                                                                .trainingVolume(trainingVolume)
                                                                .repetitionTime(trainingVolume * exercise.getRepetitionTime())
                                                                .build())
                                                        .build();
                                            })))
                            .sorted((roundExercise, roundExercise2) -> {
                                Integer compare = roundExercise.getRoundIndex().compareTo(roundExercise2.getRoundIndex());
                                return compare == 0
                                        ? roundExercise.getExerciseIndex().compareTo(roundExercise2.getExerciseIndex())
                                        : compare;

                            })
                            .reduce(workoutBuilder,
                                    new Func2<Workout.WorkoutBuilder, RoundExercise, Workout.WorkoutBuilder>() {
                                        @Override
                                        public Workout.WorkoutBuilder call(Workout.WorkoutBuilder workoutBuilder, RoundExercise roundExercise) {
                                            Workout previous = workoutBuilder.build();
                                            return workoutBuilder
                                                    .sumOfReps(0)
                                                    .sumOfTime(0)
                                                    .roundExercises(FluentIterable.concat(previous.getRoundExercises(),
                                                                    ImmutableList.of(roundExercise)).toList());
                                        }
                                    });


                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(workout -> {
                    ExerciseAdapter adapter = new ExerciseAdapter(workout.build());
                    recyclerView.setAdapter(adapter);
                });

    }

    @NonNull
    private Func1<Exercise, Boolean> withExercises(List<Exercise.ExerciseDef> exerciseDefs) {
        return exercise -> FluentIterable.from(exerciseDefs).contains(exercise.getId());
    }


    @RxLogObservable(value = RxLogObservable.Scope.EVERYTHING)
    private Observable<FormModel> buildPropertyChangeListener(final FormController formController) {
        return Observable.create(subscriber -> {
            PropertyChangeListener listener = new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onNext(formController.getModel());
                    } else {
                        formController.getModel().removePropertyChangeListener(this);
                    }
                }
            };

            formController.getModel().addPropertyChangeListener(listener);
        });

    }

    @NonNull
    @RxLogObservable(value = RxLogObservable.Scope.EVERYTHING)
    private Observable<Exercise> getFullExerciseList(ExerciseApi api) {
        return api.exercises()
                .flatMapIterable(Exercises::getExercises)
                .subscribeOn(Schedulers.io());
    }

}
