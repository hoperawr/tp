package seedu.address.logic.commands;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
<<<<<<< HEAD
=======
 * Login to a specific profile
>>>>>>> 3acc5a5225eaa68828ed4c46426e7a327b3fa4b9
 * command to switch between the users that is currently being referenced
 * Allows users to login to their profile
 */
public class LoginCommand extends Command {

    public static final String COMMAND_WORD = "login";
    private final int i;
<<<<<<< HEAD
    /**
     * Switch profile
     * @param i the index of the profile being changed to
=======

    /**
     * Constructor for login command
     * @param i index of the profile to switch t
>>>>>>> 3acc5a5225eaa68828ed4c46426e7a327b3fa4b9
     */
    public LoginCommand(Index i) {
        this.i = i.getZeroBased();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        List<Person> ul = model.getMyFitnessBuddy().getPersons();
        if (ul.size() <= i || ul.size() == 0) {
            throw new CommandException("not valid index");
        }
        Person toChange = ul.get(i);
        model.setCurrentPerson(toChange);
        model.updateDay();

        return new CommandResult(true, toChange.toString());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LoginCommand // instanceof handles nulls
                && i == (((LoginCommand) other).i)); // state check
    }
}
