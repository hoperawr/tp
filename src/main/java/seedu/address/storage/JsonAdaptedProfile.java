package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.day.Date;
import seedu.address.model.day.Weight;
import seedu.address.model.person.Height;
import seedu.address.model.person.ID;
import seedu.address.model.person.Name;
import seedu.address.model.person.Profile;



/**
 * Jackson-friendly version of {@link Profile}.
 */
class JsonAdaptedProfile {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Profile's %s field is missing!";
    private final String name;
    private final String id;
    private final String height;
    private final String startingWeight;
    private final String startingDate;

    /**
     * Constructs a {@code JsonAdaptedProfile} with the given profile details.
     */
    @JsonCreator
    public JsonAdaptedProfile(@JsonProperty("name") String name, @JsonProperty("id") String id,
                          @JsonProperty("height") String height, @JsonProperty("targetWeight") String startingWeight,
                        @JsonProperty("startingDate") String startingDate) {
        this.name = name;
        this.id = id;
        this.height = height;
        this.startingWeight = startingWeight;
        this.startingDate = startingDate;
    }

    /**
     * Converts a given {@code Profile} into this class for Jackson use.
     */
    public JsonAdaptedProfile(Profile source) {
        name = source.getName().fullName;
        id = source.getId().value;
        height = source.getHeight().value;
        startingWeight = source.getStartingWeight().value;
        startingDate = source.getStartDate().value;
    }

    /**
     * Converts this Jackson-friendly adapted profile object into the model's {@code Profile} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Profile.
     */
    public Profile toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, ID.class.getSimpleName()));
        }
        if (!ID.isValidId(id)) {
            throw new IllegalValueException(ID.MESSAGE_CONSTRAINTS);
        }
        final ID modelID = new ID(id);

        if (height == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Height.class.getSimpleName()));
        }
        if (!Height.isValidHeight(height)) {
            throw new IllegalValueException(Height.MESSAGE_CONSTRAINTS);
        }
        final Height modelHeight = new Height(height);

        if (startingWeight == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Weight.class.getSimpleName()));
        }
        if (!Weight.isValidWeight(startingWeight)) {
            throw new IllegalValueException(Weight.MESSAGE_CONSTRAINTS);
        }
        final Weight modelWeight = new Weight(startingWeight);

        if (startingDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(startingDate)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date startDate = new Date(startingDate);

        return new Profile(modelName, modelID, modelHeight, modelWeight, startDate);
    }

}
