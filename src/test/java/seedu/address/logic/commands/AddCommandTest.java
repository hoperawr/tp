package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.day.Day;
import seedu.address.testutil.PersonBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Day validDay = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validDay).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validDay), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validDay), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Day validDay = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validDay);
        ModelStub modelStub = new ModelStubWithPerson(validDay);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Day alice = new PersonBuilder().withName("Alice").build();
        Day bob = new PersonBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different day -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addDay(Day day) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasDay(Day day) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasDay() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Day getDay() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteDay(Day target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDay(Day target, Day editedDay) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Day> getFilteredDayList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredDayList(Predicate<Day> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single day.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Day day;

        ModelStubWithPerson(Day day) {
            requireNonNull(day);
            this.day = day;
        }

        @Override
        public boolean hasDay(Day day) {
            requireNonNull(day);
            return this.day.isSamePerson(day);
        }
    }

    /**
     * A Model stub that always accept the day being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Day> personsAdded = new ArrayList<>();

        @Override
        public boolean hasDay(Day day) {
            requireNonNull(day);
            return personsAdded.stream().anyMatch(day::isSamePerson);
        }

        @Override
        public void addDay(Day day) {
            requireNonNull(day);
            personsAdded.add(day);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
