package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.util.Optional;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.LogicManager;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.day.Weight;
import seedu.address.model.person.Height;
import seedu.address.model.person.ID;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Profile;


/**
 * Updates the profile in My Fitness Buddy
 */
public class UpdateCommand extends Command {

    public static final String COMMAND_WORD = "update";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Update the profile in the records."
            + "Parameters: "
            + PREFIX_NAME + "Name "
            + PREFIX_ID + "ID "
            + PREFIX_HEIGHT + "Height(cm) "
            + PREFIX_WEIGHT + "Target Weight "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John "
            + PREFIX_ID + "1234 "
            + PREFIX_HEIGHT + "170 "
            + PREFIX_WEIGHT + "70 ";

    public static final String MESSAGE_SUCCESS = "Profile updated!";
    public static final String MESSAGE_ERROR = "No profile found. ";
    public static final String MESSAGE_NOT_EDITED = "At least one valid field to edit must be provided.";
    public static final String MESSAGE_SAME_ID = "The ID you intending to change to belongs to someone else";
    private final UpdateProfileDescriptor updateProfileDescriptor;
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    /**
     * Creates a new {@code UpdateProfileDescriptor}.
     */
    public UpdateCommand(UpdateProfileDescriptor updateProfileDescriptor) {
        requireNonNull(updateProfileDescriptor);
        this.updateProfileDescriptor = new UpdateProfileDescriptor(updateProfileDescriptor);
    }

    /**
     * checks if the id intended to be changed is taken
     */
    public boolean isUnique(ID id, ObservableList<Person> ul) {
        return ul.size() == 0 || ul.stream().noneMatch(x -> x.getProfile().getId().value.equals(id.value));
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.isDefaultProfile()) {
            throw new CommandException(MESSAGE_ERROR);
        }

        if (updateProfileDescriptor.id != null
                && !isUnique(updateProfileDescriptor.id, model.getFilteredPersonList())) {
            throw new CommandException(MESSAGE_SAME_ID);
        }

        Profile toEdit = model.getMyFitnessBuddy().getProfile();
        Profile editedProfile = createEditedProfile(toEdit, updateProfileDescriptor);
        editedProfile.setStartingDay(toEdit.getStartDate());
        model.setProfile(editedProfile);
        logger.info("---------------[USER COMMAND][Profile updated]");
        return new CommandResult(true, String.format(MESSAGE_SUCCESS, editedProfile));
    }

    /**
     * Creates and returns a {@code Profile} with the details of {@code profileToEdit}
     * edited with {@code updateProfileDescriptor}.
     */
    private static Profile createEditedProfile(Profile profileToEdit, UpdateProfileDescriptor updateProfileDescriptor) {
        assert profileToEdit != null;

        Name updatedName = updateProfileDescriptor.getName().orElse(profileToEdit.getName());
        ID updatedID = updateProfileDescriptor.getId().orElse(profileToEdit.getId());
        Height updatedHeight = updateProfileDescriptor.getHeight().orElse(profileToEdit.getHeight());
        Weight updatedTargetWeight = updateProfileDescriptor.getWeight().orElse(profileToEdit.getStartingWeight());

        return new Profile(updatedName, updatedID, updatedHeight, updatedTargetWeight);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UpdateCommand
                && updateProfileDescriptor.equals(((UpdateCommand) other).updateProfileDescriptor));
    }


    /**
     * Stores the details to edit the profile with. Each non-empty field value will replace the
     * corresponding field value of the profile.
     */
    public static class UpdateProfileDescriptor {
        private Name name;
        private ID id;
        private Height height;
        private Weight weight;

        public UpdateProfileDescriptor() {}

        /**
         * Copy constructor.
         */
        public UpdateProfileDescriptor(UpdateProfileDescriptor toCopy) {
            setName(toCopy.name);
            setId(toCopy.id);
            setHeight(toCopy.height);
            setWeight(toCopy.weight);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, id, height, weight);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setId(ID id) {
            this.id = id;
        }

        public Optional<ID> getId() {
            return Optional.ofNullable(id);
        }

        public void setHeight(Height height) {
            this.height = height;
        }

        public Optional<Height> getHeight() {
            return Optional.ofNullable(height);
        }

        public void setWeight(Weight weight) {
            this.weight = weight;
        }

        public Optional<Weight> getWeight() {
            return Optional.ofNullable(weight);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof UpdateProfileDescriptor)) {
                return false;
            }

            // state check
            UpdateProfileDescriptor e = (UpdateProfileDescriptor) other;

            return getName().equals(e.getName())
                    && getId().equals(e.getId())
                    && getHeight().equals(e.getHeight())
                    && getWeight().equals(e.getWeight());
        }
    }
}

