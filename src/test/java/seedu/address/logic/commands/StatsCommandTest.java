package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.StatsCommand.SHOWING_ALL_STATS_MESSAGE;
import static seedu.address.logic.commands.StatsCommand.SHOWING_CALORIE_STATS_MESSAGE;
import static seedu.address.logic.commands.StatsCommand.SHOWING_COMMANDER_STATS_MESSAGE;
import static seedu.address.logic.commands.StatsCommand.SHOWING_WEIGHT_STATS_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class StatsCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_viewBothStats_success() {
        CommandResult expectedCommandResult =
                new CommandResult(SHOWING_ALL_STATS_MESSAGE, false, false, true,
                        false, false, false, false, 0,
                        false, false, 0, false);
        assertCommandSuccess(new StatsCommand(true, true, false), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_viewBothStatsOverloadedConstructor_success() {
        CommandResult expectedCommandResult =
                new CommandResult(SHOWING_ALL_STATS_MESSAGE, true, false, false, false);
        assertCommandSuccess(new StatsCommand(true, true, false), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_viewCalorieStats_success() {
        CommandResult expectedCommandResult =
                new CommandResult(SHOWING_CALORIE_STATS_MESSAGE, false, false, false,
                        true, false, false, false, 0, false, false, 0, false);
        assertCommandSuccess(new StatsCommand(true, false, false), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_viewCalorieStatsOverloadedConstructor_success() {
        CommandResult expectedCommandResult =
                new CommandResult(SHOWING_CALORIE_STATS_MESSAGE, false, true, false, false);
        assertCommandSuccess(new StatsCommand(true, false, false), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_viewWeightStats_success() {
        CommandResult expectedCommandResult =
                new CommandResult(SHOWING_WEIGHT_STATS_MESSAGE, false, false, false,
                        false, true, false, false, 0, false, false, 0, false);
        assertCommandSuccess(new StatsCommand(false, true, false), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_viewWeightStatsOverloadedConstructor_success() {
        CommandResult expectedCommandResult =
                new CommandResult(SHOWING_WEIGHT_STATS_MESSAGE, false, false, true, false);
        assertCommandSuccess(new StatsCommand(false, true, false), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_viewCommanderStats_success() {
        CommandResult expectedCommandResult =
                new CommandResult(SHOWING_COMMANDER_STATS_MESSAGE, false, false, false,
                        false, false, false, false, 0, false, false, 0, true);
        assertCommandSuccess(new StatsCommand(false, false, true), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_viewCommanderStatsOverloadedConstructor_success() {
        CommandResult expectedCommandResult =
                new CommandResult(SHOWING_COMMANDER_STATS_MESSAGE, false, false, false, true);
        assertCommandSuccess(new StatsCommand(false, false, true), model,
                expectedCommandResult, expectedModel);
    }

}
