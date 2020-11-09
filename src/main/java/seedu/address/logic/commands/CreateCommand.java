package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.LogicManager;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.ID;
import seedu.address.model.person.Person;
import seedu.address.model.person.Profile;


/**
 * Creates a profile for the new Person and add it into My Fitness Buddy.
 */
public class CreateCommand extends Command {

    public static final String COMMAND_WORD = "create";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a profile in the records. "
            + "Parameters: "
            + PREFIX_NAME + "Name "
            + PREFIX_ID + "ID "
            + PREFIX_HEIGHT + "Height(cm) "
            + PREFIX_WEIGHT + "Target Weight "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John "
            + PREFIX_ID + "4312 "
            + PREFIX_HEIGHT + "170 "
            + PREFIX_WEIGHT + "70 ";

    public static final String MESSAGE_NO_PROFILE = "Create a profile before adding a day. \n" + MESSAGE_USAGE;
    public static final String MESSAGE_SAME_ID = "There exists a profile with the same ID ";
    public static final String MESSAGE_SUCCESS = "New profile created: %1$s";
    public static final String MESSAGE_ERROR = "There exists a profile. ";
    private final Profile profile;
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    /**
     * Creates a new {@code profile}.
     */
    public CreateCommand(Profile profile) {
        requireNonNull(profile);
        this.profile = profile;
    }

    /**
     * Creates a new {@code profile}.
     */
    public boolean isUnique(ID id, ObservableList<Person> ul) {
        return ul.size() == 0 || ul.stream().noneMatch(x -> x.getProfile().getId().value.equals(id.value));
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person newPerson = new Person(profile);
        if (model.hasPerson(newPerson)) {
            throw new CommandException(MESSAGE_ERROR);
        }
        model.addPerson(newPerson);
        model.updateDay();
        logger.info("---------------[USER COMMAND][Profile" + profile.toString() + " created]");
        return new CommandResult(true, String.format(MESSAGE_SUCCESS, profile));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CreateCommand
                && profile.equals(((CreateCommand) other).profile));
    }
}
