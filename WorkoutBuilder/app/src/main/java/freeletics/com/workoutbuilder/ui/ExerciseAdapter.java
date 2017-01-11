package freeletics.com.workoutbuilder.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import freeletics.com.workoutbuilder.R;
import freeletics.com.workoutbuilder.model.RoundExercise;
import freeletics.com.workoutbuilder.model.Workout;
import ix.Ix;

public class ExerciseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int VIEW_HEADER = -1;
    private int VIEW_FOOOTER = +1;

    private final Workout workout;

    public ExerciseAdapter(Workout workout) {
        this.workout = workout;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_item, parent, false);
            return new VHHeader(v);
        } else if (viewType == VIEW_FOOOTER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_item, parent, false);
            return new VHFooter(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            return new VHItem(v);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VHHeader) {
            VHHeader vhHeader = (VHHeader) holder;
            vhHeader.workoutName.setText(workout.getName());
            vhHeader.roundCount.setText(
                    Ix.from(workout.getRoundExercises())
                            .map(RoundExercise::getRoundIndex)
                            .max()
                            .map(String::valueOf)
                            .first());
        } else if (holder instanceof VHFooter) {
            VHFooter vhFooter = (VHFooter) holder;
            vhFooter.repCount.setText("0");
            vhFooter.timeCount.setText("0");
        } else {
            RoundExercise currentItem = getItem(position - 1);
            VHItem VHitem = (VHItem) holder;
            VHitem.exerciseName.setText(currentItem.getExercise().getId().name());
            VHitem.exerciseVariant.setText(currentItem.getExercise().getVariant().name());
            VHitem.exerciseReps.setText(String.valueOf(currentItem.getExercise().getTrainingVolume()));
        }
    }

    private RoundExercise getItem(int position) {
        return workout.getRoundExercises().get(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_HEADER;
        } else if (position == getItemCount() - 1) {
            return VIEW_FOOOTER;
        } else {
            return 0;
        }
    }

    @Override
    public int getItemCount() {
        return workout.getRoundExercises().size() + 2;
    }


    class VHFooter extends RecyclerView.ViewHolder {

        @BindView(R.id.timeCount)
        TextView timeCount;

        @BindView(R.id.repCount)
        TextView repCount;

        VHFooter(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class VHHeader extends RecyclerView.ViewHolder {

        @BindView(R.id.workoutName)
        TextView workoutName;

        @BindView(R.id.roundCount)
        TextView roundCount;

        VHHeader(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class VHItem extends RecyclerView.ViewHolder {

        @BindView(R.id.exercise_name)
        TextView exerciseName;

        @BindView(R.id.exercise_variant)
        TextView exerciseVariant;

        @BindView(R.id.exercise_reps)
        TextView exerciseReps;

        VHItem(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
