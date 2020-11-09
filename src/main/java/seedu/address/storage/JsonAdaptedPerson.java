package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.day.Day;
import seedu.address.model.person.Person;
import seedu.address.model.person.Profile;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MESSAGE_DUPLICATE_DAY = "Day list contains duplicate days(s).";
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final JsonAdaptedProfile profile;
    private final List<JsonAdaptedDay> days = new ArrayList<>();


    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("profile") JsonAdaptedProfile profile,
                             @JsonProperty("days") List<JsonAdaptedDay> days) {
        this.profile = profile;
        if (days != null) {
            this.days.addAll(days);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        profile = new JsonAdaptedProfile(source.getProfile());
        days.addAll(source.getDayList().stream().map(JsonAdaptedDay::new).collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Person.
     */
    public Person toModelType() throws IllegalValueException {
        if (profile == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Profile.class.getSimpleName()));
        }
        Profile modelProfile = profile.toModelType();
        Person modelPerson = new Person();
        modelPerson.setProfile(modelProfile);

        for (JsonAdaptedDay jsonAdaptedDay : days) {
            Day day = jsonAdaptedDay.toModelType();
            if (modelPerson.hasDay(day)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_DAY);
            }
            modelPerson.addDay(day);
        }
        return modelPerson;
    }

}
