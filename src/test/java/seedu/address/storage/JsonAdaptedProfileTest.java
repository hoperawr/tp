package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedProfile.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.day.Date;
import seedu.address.model.day.Weight;
import seedu.address.model.person.Height;
import seedu.address.model.person.ID;
import seedu.address.model.person.Name;
import seedu.address.model.person.Profile;

public class JsonAdaptedProfileTest {

    private static final String VALID_NAME = "Sample Name";
    private static final String VALID_ID = "1111";
    private static final String VALID_HEIGHT = "170";
    private static final String VALID_STARTING_WEIGHT = "70";
    private static final String VALID_STARTING_DATE = "2020-01-01";
    private static final Profile VALID_PROFILE = new Profile(new Name(VALID_NAME), new ID(VALID_ID),
            new Height(VALID_HEIGHT), new Weight(VALID_STARTING_WEIGHT), new Date(VALID_STARTING_DATE));

    private static final String INVALID_NAME = "";
    private static final String INVALID_NAME_2 = "&%*-";
    private static final String INVALID_ID = "0101";
    private static final String INVALID_ID_2 = "5001";
    private static final String INVALID_ID_3 = "1001";
    private static final String INVALID_ID_4 = "1501";
    private static final String INVALID_ID_5 = "1100";
    private static final String INVALID_ID_6 = "1122";
    private static final String INVALID_HEIGHT = "49";
    private static final String INVALID_HEIGHT_2 = "273";
    private static final String INVALID_STARTING_WEIGHT = "Test.";
    private static final String INVALID_STARTING_WEIGHT_2 = "70.000";
    private static final String INVALID_STARTING_DATE = "";



    @Test
    public void toModelType_validProfileDetails_returnsProfile() throws Exception {
        JsonAdaptedProfile profile = new JsonAdaptedProfile(VALID_PROFILE);
        assertEquals(VALID_PROFILE, profile.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedProfile profile = new JsonAdaptedProfile(INVALID_NAME, VALID_ID, VALID_HEIGHT,
                VALID_STARTING_WEIGHT, VALID_STARTING_DATE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, profile::toModelType);
    }

    @Test
    public void toModelType_invalidName2_throwsIllegalValueException() {
        JsonAdaptedProfile profile = new JsonAdaptedProfile(INVALID_NAME_2, VALID_ID, VALID_HEIGHT,
                VALID_STARTING_WEIGHT, VALID_STARTING_DATE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, profile::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedProfile profile = new JsonAdaptedProfile(null, VALID_ID, VALID_HEIGHT,
                VALID_STARTING_WEIGHT, VALID_STARTING_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, profile::toModelType);
    }

    @Test
    public void toModelType_invalidId_throwsIllegalValueException() {
        JsonAdaptedProfile profile = new JsonAdaptedProfile(VALID_NAME, INVALID_ID, VALID_HEIGHT,
                VALID_STARTING_WEIGHT, VALID_STARTING_DATE);
        String expectedMessage = ID.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, profile::toModelType);
    }

    @Test
    public void toModelType_invalidId2_throwsIllegalValueException() {
        JsonAdaptedProfile profile = new JsonAdaptedProfile(VALID_NAME, INVALID_ID_2, VALID_HEIGHT,
                VALID_STARTING_WEIGHT, VALID_STARTING_DATE);
        String expectedMessage = ID.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, profile::toModelType);
    }

    @Test
    public void toModelType_invalidId3_throwsIllegalValueException() {
        JsonAdaptedProfile profile = new JsonAdaptedProfile(VALID_NAME, INVALID_ID_3, VALID_HEIGHT,
                VALID_STARTING_WEIGHT, VALID_STARTING_DATE);
        String expectedMessage = ID.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, profile::toModelType);
    }

    @Test
    public void toModelType_invalidId4_throwsIllegalValueException() {
        JsonAdaptedProfile profile = new JsonAdaptedProfile(VALID_NAME, INVALID_ID_4, VALID_HEIGHT,
                VALID_STARTING_WEIGHT, VALID_STARTING_DATE);
        String expectedMessage = ID.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, profile::toModelType);
    }

    @Test
    public void toModelType_invalidId5_throwsIllegalValueException() {
        JsonAdaptedProfile profile = new JsonAdaptedProfile(VALID_NAME, INVALID_ID_5, VALID_HEIGHT,
                VALID_STARTING_WEIGHT, VALID_STARTING_DATE);
        String expectedMessage = ID.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, profile::toModelType);
    }

    @Test
    public void toModelType_invalidId6_throwsIllegalValueException() {
        JsonAdaptedProfile profile = new JsonAdaptedProfile(VALID_NAME, INVALID_ID_6, VALID_HEIGHT,
                VALID_STARTING_WEIGHT, VALID_STARTING_DATE);
        String expectedMessage = ID.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, profile::toModelType);
    }

    @Test
    public void toModelType_nullId_throwsIllegalValueException() {
        JsonAdaptedProfile profile = new JsonAdaptedProfile(VALID_NAME, null, VALID_HEIGHT,
                VALID_STARTING_WEIGHT, VALID_STARTING_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ID.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, profile::toModelType);
    }

    @Test
    public void toModelType_invalidHeight_throwsIllegalValueException() {
        JsonAdaptedProfile profile = new JsonAdaptedProfile(VALID_NAME, VALID_ID, INVALID_HEIGHT,
                VALID_STARTING_WEIGHT, VALID_STARTING_DATE);
        String expectedMessage = Height.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, profile::toModelType);
    }

    @Test
    public void toModelType_invalidHeight2_throwsIllegalValueException() {
        JsonAdaptedProfile profile = new JsonAdaptedProfile(VALID_NAME, VALID_ID, INVALID_HEIGHT_2,
                VALID_STARTING_WEIGHT, VALID_STARTING_DATE);
        String expectedMessage = Height.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, profile::toModelType);
    }

    @Test
    public void toModelType_nullHeight_throwsIllegalValueException() {
        JsonAdaptedProfile profile = new JsonAdaptedProfile(VALID_NAME, VALID_ID, null,
                VALID_STARTING_WEIGHT, VALID_STARTING_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Height.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, profile::toModelType);
    }

    @Test
    public void toModelType_invalidWeight_throwsIllegalValueException() {
        JsonAdaptedProfile profile = new JsonAdaptedProfile(VALID_NAME, VALID_ID, VALID_HEIGHT,
                INVALID_STARTING_WEIGHT, VALID_STARTING_DATE);
        String expectedMessage = Weight.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, profile::toModelType);
    }

    @Test
    public void toModelType_invalidWeight2_throwsIllegalValueException() {
        JsonAdaptedProfile profile = new JsonAdaptedProfile(VALID_NAME, VALID_ID, VALID_HEIGHT,
                INVALID_STARTING_WEIGHT_2, VALID_STARTING_DATE);
        String expectedMessage = Weight.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, profile::toModelType);
    }

    @Test
    public void toModelType_nullWeight_throwsIllegalValueException() {
        JsonAdaptedProfile profile = new JsonAdaptedProfile(VALID_NAME, VALID_ID, VALID_HEIGHT,
                null, VALID_STARTING_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Weight.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, profile::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedProfile profile = new JsonAdaptedProfile(VALID_NAME, VALID_ID, VALID_HEIGHT,
                VALID_STARTING_WEIGHT, INVALID_STARTING_DATE);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, profile::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedProfile profile = new JsonAdaptedProfile(VALID_NAME, VALID_ID, VALID_HEIGHT,
                VALID_STARTING_WEIGHT, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, profile::toModelType);
    }
}
