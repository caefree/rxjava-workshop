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
import com.google.common.collect.ImmutableList;
import com.pacoworks.rxcurrying.RxCurryingFunc;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import freeletics.com.workoutbuilder.api.ExerciseApi;
import freeletics.com.workoutbuilder.api.JsonExerciseApi;
import freeletics.com.workoutbuilder.model.Exercise;
import freeletics.com.workoutbuilder.model.Exercises;
import freeletics.com.workoutbuilder.ui.ExerciseAdapter;
import ix.Ix;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    public static final String FILTER_VARIANT = "filterVariant";

    @BindView(R.id.exercise_list)
    RecyclerView recyclerView;

    @BindView(R.id.form_elements_container)
    LinearLayout form;

    FormController setUpForm() {
        FormController formController = new FormController(this);
        FormSectionController section = new FormSectionController(this, "Workout Builder");

        SelectionController variantSelector = new SelectionController(this, FILTER_VARIANT, "Filter Variant", true, "Select",
                Ix.fromArray(Exercise.Variant.values()).map(Enum::name).toList(), true);

        section.addElement(variantSelector);

        formController.addSection(section);
        formController.recreateViews(form);

        formController.getModel().setValue("filterVariant", Exercise.Variant.All.name());

        return formController;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.setDebug(true);
        ButterKnife.bind(this);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(new ExerciseAdapter(ImmutableList.of()));

        buildPropertyChangeListener(setUpForm())
                .flatMap(model -> {
                    String variant = model.getValue(FILTER_VARIANT).toString();
                    return getFullExerciseList(new JsonExerciseApi(this))
                            .filter(withVariant(variant))
                            .toList();
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> recyclerView.swapAdapter(new ExerciseAdapter(list), true));
    }

    @RxLogObservable(value = RxLogObservable.Scope.EVERYTHING)
    private Observable<FormModel> buildPropertyChangeListener(final FormController formController) {
        return Observable.create(new Observable.OnSubscribe<FormModel>() {
            @Override
            public void call(Subscriber<? super FormModel> subscriber) {
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

            }
        });

    }

    @NonNull
    @RxLogObservable(value = RxLogObservable.Scope.EVERYTHING)
    private Observable<Exercise> getFullExerciseList(ExerciseApi api) {
        return api.exercises()
                .flatMapIterable(Exercises::getExercises)
                .subscribeOn(Schedulers.io());
    }

    private Func1<? super Exercise, Boolean> withVariant(String variant) {
        Exercise.Variant exerciseVariant = Exercise.Variant.valueOf(variant);
        return exercise -> exerciseVariant == Exercise.Variant.All ||
                exercise.getVariant() == exerciseVariant;
    }

}
