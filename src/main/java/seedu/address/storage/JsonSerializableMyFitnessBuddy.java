package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.MyFitnessBuddy;
import seedu.address.model.ReadOnlyMyFitnessBuddy;
import seedu.address.model.person.Person;


/**
 * An Immutable My Fitness Buddy that is serializable to JSON format.
 */
@JsonRootName(value = "My Fitness Buddy")
class JsonSerializableMyFitnessBuddy {

    public static final String MESSAGE_DUPLICATE_PERSON = "Person list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableMyFitnessBuddy} with the given persons.
     */
    @JsonCreator
    public JsonSerializableMyFitnessBuddy(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyMyFitnessBuddy} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableMyFitnessBuddy}.
     */
    public JsonSerializableMyFitnessBuddy(ReadOnlyMyFitnessBuddy source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this My Fitness Buddy into the model's {@code MyFitnessBuddy} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public MyFitnessBuddy toModelType() throws IllegalValueException {
        MyFitnessBuddy modelMyFitnessBuddy = new MyFitnessBuddy();
        for (JsonAdaptedPerson person: persons) {
            Person modelPerson = person.toModelType();
            if (modelMyFitnessBuddy.hasPerson(modelPerson)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            modelMyFitnessBuddy.addPerson(modelPerson);
        }
        return modelMyFitnessBuddy;
    }

}
