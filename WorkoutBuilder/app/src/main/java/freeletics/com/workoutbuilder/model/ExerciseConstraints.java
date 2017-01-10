package freeletics.com.workoutbuilder.model;

import java.util.Arrays;
import java.util.List;

import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.BurpeeDeepfrogs;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.BurpeeFrogs;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.BurpeeSquatJumps;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.Burpees;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.Climbers;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.Crunches;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.Froggers;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.HandHelpLegLevers;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.HandHelpLungeWalk;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.HandHelpLunges;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.HandHelpSquats;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.HandHelpStandups;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.HighJumps;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.HighKnees;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.Jackknives;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.JumpingJacks;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.JumpingPullups;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.Jumps;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.KneePushups;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.LungeWalk;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.Lunges;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.MountainClimbers;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.Muscleups;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.OhPushups;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.Pikes;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.Pistols;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.Pullups;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.Pushups;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.Run;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.Situps;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.SplitLunges;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.SprawlFrogs;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.Sprawls;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.Squats;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.StandupJumps;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.Standups;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.StraightLegLevers;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.StrictHsPushups;
import static freeletics.com.workoutbuilder.model.Exercise.ExerciseDef.ToesToBar;

public class ExerciseConstraints {


    static final List<Exercise.ExerciseDef> TARGETING_BACK =
            Arrays.asList(Squats, Lunges, LungeWalk, HandHelpSquats, SplitLunges, SprawlFrogs,
                    BurpeeFrogs, BurpeeDeepfrogs, HandHelpLunges, HandHelpLungeWalk);

    static final List<Exercise.ExerciseDef> TARGETING_BICEPS =
            Arrays.asList(Muscleups, OhPushups, Pullups, StrictHsPushups, ToesToBar, JumpingPullups);


    static final List<Exercise.ExerciseDef> TARGETING_CHEST =
            Arrays.asList(Pushups, Pikes, ToesToBar, Burpees, Muscleups, OhPushups, StrictHsPushups,
                    MountainClimbers, Climbers, Sprawls, BurpeeDeepfrogs, BurpeeSquatJumps, BurpeeFrogs,
                    SprawlFrogs, KneePushups, Froggers);


    public static final List<Exercise.ExerciseDef> TARGETING_CORE =
            Arrays.asList(Sprawls, Squats, MountainClimbers, Burpees, Crunches, HandHelpLegLevers,
                    BurpeeSquatJumps, BurpeeDeepfrogs, BurpeeFrogs, Climbers, Froggers,
                    HandHelpSquats, HandHelpStandups, Jackknives, OhPushups, Pistols,
                    Situps, SprawlFrogs, StandupJumps, Standups, StraightLegLevers, ToesToBar);

    static final List<Exercise.ExerciseDef> TARGETING_LOWER_LEG = Arrays.asList(
            HighKnees,
            JumpingJacks,
            SplitLunges,
            SprawlFrogs,
            BurpeeSquatJumps,
            BurpeeDeepfrogs,
            BurpeeFrogs,
            Froggers,
            Jumps,
            StandupJumps,
            HighJumps);

    static final List<Exercise.ExerciseDef> TARGETING_TRICEPS = Arrays.asList(
            Pushups,
            Burpees,
            Pikes,
            StrictHsPushups,
            OhPushups,
            BurpeeFrogs,
            Muscleups,
            BurpeeSquatJumps,
            KneePushups,
            BurpeeDeepfrogs);

    static final List<Exercise.ExerciseDef> TARGETING_UPPER_LEG = Arrays.asList(
            Burpees,
            Squats,
            Lunges,
            LungeWalk,
            MountainClimbers,
            HandHelpLunges,
            HandHelpLungeWalk,
            HandHelpSquats,
            Pistols,
            SplitLunges,
            SprawlFrogs,
            Sprawls,
            ToesToBar,
            BurpeeDeepfrogs,
            BurpeeFrogs,
            BurpeeSquatJumps,
            Climbers,
            StandupJumps);

    static final List<Exercise.ExerciseDef> HARMFUL_FOR_BACK = Arrays.asList(
            BurpeeDeepfrogs,
            BurpeeFrogs,
            Burpees,
            BurpeeSquatJumps,
            Climbers,
            Froggers,
            HandHelpStandups,
            HighJumps,
            HighKnees,
            Jackknives,
            JumpingPullups,
            Jumps,
            Lunges,
            LungeWalk,
            Muscleups,
            OhPushups,
            Pikes,
            Pistols,
            Pushups,
            Situps,
            SplitLunges,
            SprawlFrogs,
            Sprawls,
            Squats,
            StandupJumps,
            Standups,
            StraightLegLevers,
            StrictHsPushups,
            ToesToBar);

    static final List<Exercise.ExerciseDef> HARMFUL_FOR_BICEPS = Arrays.asList(
            BurpeeDeepfrogs,
            BurpeeFrogs,
            Burpees,
            BurpeeSquatJumps,
            Climbers,
            Froggers,
            JumpingPullups,
            KneePushups,
            MountainClimbers,
            Muscleups,
            OhPushups,
            Pikes,
            Pullups,
            Pushups,
            SprawlFrogs,
            Sprawls,
            StrictHsPushups,
            ToesToBar);

    static final List<Exercise.ExerciseDef> HARMFUL_FOR_CHEST = Arrays.asList(
            BurpeeDeepfrogs,
            BurpeeFrogs,
            Burpees,
            BurpeeSquatJumps,
            Climbers,
            Froggers,
            HandHelpStandups,
            JumpingPullups,
            KneePushups,
            MountainClimbers,
            Muscleups,
            OhPushups,
            Pikes,
            Pullups,
            Pushups,
            SprawlFrogs,
            Sprawls,
            StrictHsPushups,
            ToesToBar);

    static final List<Exercise.ExerciseDef> HARMFUL_FOR_CORE = Arrays.asList(
            BurpeeDeepfrogs,
            BurpeeFrogs,
            Burpees,
            BurpeeSquatJumps,
            Climbers,
            Crunches,
            Froggers,
            HandHelpLegLevers,
            HandHelpSquats,
            HandHelpStandups,
            HighJumps,
            HighKnees,
            Jackknives,
            JumpingPullups,
            Jumps,
            KneePushups,
            MountainClimbers,
            Muscleups,
            OhPushups,
            Pikes,
            Pistols,
            Pullups,
            Pushups,
            Situps,
            SplitLunges,
            SprawlFrogs,
            Sprawls,
            Squats,
            StandupJumps,
            Standups,
            StraightLegLevers,
            StrictHsPushups,
            ToesToBar);

    static final List<Exercise.ExerciseDef> HARMFUL_FOR_LOWER_LEG = Arrays.asList(
            BurpeeDeepfrogs,
            BurpeeFrogs,
            Burpees,
            BurpeeSquatJumps,
            Climbers,
            Froggers,
            HandHelpLunges,
            HandHelpLungeWalk,
            HandHelpStandups,
            HighJumps,
            HighKnees,
            JumpingJacks,
            JumpingPullups,
            Jumps,
            Lunges,
            LungeWalk,
            MountainClimbers,
            OhPushups,
            Pikes,
            Pistols,
            Pushups,
            Run,
            SplitLunges,
            SprawlFrogs,
            Sprawls,
            StandupJumps,
            Standups);

    static final List<Exercise.ExerciseDef> HARMFUL_FOR_TRICEPS = Arrays.asList(
            BurpeeDeepfrogs,
            BurpeeFrogs,
            Burpees,
            BurpeeSquatJumps,
            Climbers,
            Froggers,
            KneePushups,
            MountainClimbers,
            Muscleups,
            OhPushups,
            Pikes,
            Pushups,
            SprawlFrogs,
            Sprawls,
            StrictHsPushups,
            ToesToBar);

    static final List<Exercise.ExerciseDef> HARMFUL_FOR_UPPER_LEG = Arrays.asList(
            BurpeeDeepfrogs,
            BurpeeFrogs,
            Burpees,
            BurpeeSquatJumps,
            Climbers,
            Froggers,
            HandHelpLegLevers,
            HandHelpLunges,
            HandHelpLungeWalk,
            HandHelpSquats,
            HandHelpStandups,
            HighJumps,
            HighKnees,
            Jackknives,
            JumpingJacks,
            JumpingPullups,
            Jumps,
            KneePushups,
            Lunges,
            LungeWalk,
            MountainClimbers,
            OhPushups,
            Pikes,
            Pistols,
            Pushups,
            Run,
            SplitLunges,
            SprawlFrogs,
            Sprawls,
            Squats,
            StandupJumps,
            Standups,
            StraightLegLevers,
            ToesToBar);

}
