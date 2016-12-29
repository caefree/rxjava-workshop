package freeletics.com.workoutbuilder.model;

import com.google.gson.annotations.SerializedName;

import lombok.Value;

@Value
public class Exercise {

    ExerciseDef id;

    Variant variant;

    @SerializedName("training_volume")
    int trainingVolume;

    @SerializedName("rep_time")
    float repetitionTime;

    public enum Variant {
        Endurance,
        Strength,
        Standard,
        All
    }

    public enum ExerciseDef {
        @SerializedName("burpee-deepfrogs")
        BurpeeDeepfrogs,

        @SerializedName("burpee-frogs")
        BurpeeFrogs,

        @SerializedName("burpees")
        Burpees,

        @SerializedName("burpee-squat-jumps")
        BurpeeSquatJumps,

        @SerializedName("climbers")
        Climbers,

        @SerializedName("crunches")
        Crunches,

        @SerializedName("froggers")
        Froggers,

        @SerializedName("hand-help-leg-levers")
        HandHelpLegLevers,

        @SerializedName("hand-help-lunges")
        HandHelpLunges,

        @SerializedName("hand-help-lunge-walk")
        HandHelpLungeWalk,

        @SerializedName("hand-help-squats")
        HandHelpSquats,

        @SerializedName("hand-help-standups")
        HandHelpStandups,

        @SerializedName("high-jumps")
        HighJumps,

        @SerializedName("high-knees")
        HighKnees,

        @SerializedName("jackknives")
        Jackknives,

        @SerializedName("jumping-jacks")
        JumpingJacks,

        @SerializedName("jumping-pullups")
        JumpingPullups,

        @SerializedName("jumps")
        Jumps,

        @SerializedName("knee-pushups")
        KneePushups,

        @SerializedName("lunges")
        Lunges,

        @SerializedName("lunge-walk")
        LungeWalk,

        @SerializedName("mountain-climbers")
        MountainClimbers,

        @SerializedName("muscleups")
        Muscleups,

        @SerializedName("oh-pushups")
        OhPushups,

        @SerializedName("pikes")
        Pikes,

        @SerializedName("pistols")
        Pistols,

        @SerializedName("pullups")
        Pullups,

        @SerializedName("pushups")
        Pushups,

        @SerializedName("run")
        Run,  // is not part of the targeted muscle groups!

        @SerializedName("situps")
        Situps,

        @SerializedName("split-lunges")
        SplitLunges,

        @SerializedName("sprawl-frogs")
        SprawlFrogs,

        @SerializedName("sprawls")
        Sprawls,

        @SerializedName("squats")
        Squats,

        @SerializedName("standup-jumps")
        StandupJumps,

        @SerializedName("standups")
        Standups,

        @SerializedName("straight-leg-levers")
        StraightLegLevers,

        @SerializedName("strict-hs-pushups")
        StrictHsPushups,

        @SerializedName("toes-to-bar")
        ToesToBar,
    }

}
