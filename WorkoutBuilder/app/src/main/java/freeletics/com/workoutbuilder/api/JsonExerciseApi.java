package freeletics.com.workoutbuilder.api;

import android.content.Context;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

import freeletics.com.workoutbuilder.model.Exercises;
import rx.Observable;


public class JsonExerciseApi implements ExerciseApi {

    Gson gson;
    Context context;

    public JsonExerciseApi(Context context) {
        this.gson = new Gson();
        this.context = context;

    }

    String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = context.getAssets().open("exercises.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    @Override
    public Observable<Exercises> exercises() {
        return Observable.fromCallable(() ->
                gson.fromJson(loadJSONFromAsset(), Exercises.class));

    }
}