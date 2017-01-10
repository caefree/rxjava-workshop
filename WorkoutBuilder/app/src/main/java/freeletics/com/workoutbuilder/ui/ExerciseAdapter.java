package freeletics.com.workoutbuilder.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.common.collect.ImmutableList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import freeletics.com.workoutbuilder.model.Exercise;
import freeletics.com.workoutbuilder.R;
import freeletics.com.workoutbuilder.model.RoundExercise;

public class ExerciseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private ImmutableList<RoundExercise> exercises;


    public ExerciseAdapter(List<RoundExercise> exerciseDefs) {
        this.exercises = ImmutableList.copyOf(exerciseDefs);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_HEADER)
        {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_item, parent, false);
            return  new VHHeader(v);
        }
        else if(viewType == TYPE_ITEM)
        {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            return new VHItem(v);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof VHHeader)
        {
            VHHeader VHheader = (VHHeader)holder;
            VHheader.roundNumber.setText("1");
        }
        else if(holder instanceof VHItem)
        {
            RoundExercise currentItem = getItem(position-1);
            VHItem VHitem = (VHItem)holder;
            VHitem.exerciseName.setText(currentItem.getExercise().getId().name());
            VHitem.exerciseVariant.setText(currentItem.getExercise().getVariant().name());
            VHitem.exerciseReps.setText(String.valueOf(currentItem.getExercise().getTrainingVolume()));
        }

    }

    private RoundExercise getItem(int position)
    {
        return exercises.get(position);
    }


    @Override
    public int getItemViewType(int position) {
        if(isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position)
    {
        return position == 0;
    }

    //increasing getItemcount to 1. This will be the row of header.
    @Override
    public int getItemCount() {
        return exercises.size()+1;
    }


    class VHHeader extends RecyclerView.ViewHolder{

        @BindView(R.id.roundNumber)
        TextView roundNumber;

        VHHeader(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class VHItem extends RecyclerView.ViewHolder{

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
