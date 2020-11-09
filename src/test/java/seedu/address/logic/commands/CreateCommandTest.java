package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CreateCommand.MESSAGE_ERROR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPerson.getAnotherMyFitnessBuddy;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.day.Weight;
import seedu.address.model.person.Height;
import seedu.address.model.person.ID;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Profile;
import seedu.address.testutil.TypicalPerson;
import seedu.address.testutil.TypicalProfiles;

class CreateCommandTest {

    @Test
    public void constructor_nullDay_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateCommand(null));
    }

    @Test
    public void isUnique() {
        ObservableList<Person> persons = FXCollections.observableArrayList(TypicalPerson.getTypicalPersons());
        Person notUnique = TypicalPerson.PERSON1;
        CreateCommand createCommand = new CreateCommand(TypicalProfiles.PROFILE1);
        assertFalse(createCommand.isUnique(notUnique.getProfile().id, persons));
        Person unique = new Person(new Profile(new Name("asd"), new ID("1119"), new Height("123"), new Weight("123")));
        assertTrue(createCommand.isUnique(unique.getProfile().id, persons));
    }

    @Test
    public void execute_hasPerson_throwsCommandException() throws CommandException {
        Model model = new ModelManager(getAnotherMyFitnessBuddy(), new UserPrefs());
        model.setCurrentPerson(model.getMyFitnessBuddy().getPersonList().get(0));

        CreateCommand createCommand = new CreateCommand(TypicalProfiles.PROFILE1);
        assertThrows(CommandException.class, MESSAGE_ERROR, () ->
                createCommand.execute(model));
    }

    @Test
    public void execute_notUniqueId_throwsCommandException() throws CommandException {
        Model model = new ModelManager(getAnotherMyFitnessBuddy(), new UserPrefs());
        model.setCurrentPerson(model.getMyFitnessBuddy().getPersonList().get(0));
        Profile toAdd = new Profile(new Name("asdfg"), new ID("1111"), new Height("143"), new Weight("222"));
        CreateCommand createCommand = new CreateCommand(toAdd);
        assertThrows(CommandException.class, MESSAGE_ERROR, () ->
                createCommand.execute(model));
    }

    @Test
    public void execute_createCommand_successfully() throws CommandException {
        Model model = new ModelManager(getAnotherMyFitnessBuddy(), new UserPrefs());
        model.setCurrentPerson(model.getMyFitnessBuddy().getPersonList().get(0));
        Profile toAdd = new Profile(new Name("asdfg"), new ID("1119"), new Height("143"), new Weight("222"));
        CreateCommand createCommand = new CreateCommand(toAdd);
        CommandResult commandResult = createCommand.execute(model);
        assertEquals(String.format(CreateCommand.MESSAGE_SUCCESS, toAdd), commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        Profile alice = new Profile(new Name("John"), new ID("1219"), new Height("170"), new Weight("68"));
        Profile bob = new Profile(new Name("Johnny"), new ID("1318"), new Height("170"), new Weight("68"));
        CreateCommand addAliceCommand = new CreateCommand(alice);
        CreateCommand addBobCommand = new CreateCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        CreateCommand addAliceCommandCopy = new CreateCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different day -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }


}
