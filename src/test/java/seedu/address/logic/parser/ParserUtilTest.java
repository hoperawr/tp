package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_DAY;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.calorie.Exercise;
import seedu.address.model.calorie.Food;
import seedu.address.model.day.Date;
import seedu.address.model.day.Weight;
import seedu.address.model.person.Height;
import seedu.address.model.person.ID;
import seedu.address.model.person.Name;


public class ParserUtilTest {
    private static final String INVALID_DATE = "";
    private static final String INVALID_WEIGHT = "4a5";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_DATE = "2019-10-13";
    private static final String VALID_WEIGHT = "45";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_DAY, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_DAY, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDate((String) null));
    }

    @Test
    public void parseDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_DATE));
    }

    @Test
    public void parseDate_validValueWithoutWhitespace_returnsDate() throws Exception {
        Date expectedDate = new Date(VALID_DATE);
        assertEquals(expectedDate, ParserUtil.parseDate(VALID_DATE));
    }

    @Test
    public void parseDate_validValueWithWhitespace_returnsTrimmedDate() throws Exception {
        String dateWithWhitespace = WHITESPACE + VALID_DATE + WHITESPACE;
        Date expectedDate = new Date(VALID_DATE);
        Date actualDate = ParserUtil.parseDate(dateWithWhitespace);
        assertEquals(expectedDate, actualDate);
    }

    @Test
    public void parseWeight_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseWeight((String) null));
    }

    @Test
    public void parseWeight_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseWeight(INVALID_WEIGHT));
        assertThrows(ParseException.class, () -> ParserUtil.parseWeight("0")); //lower limit
        assertThrows(ParseException.class, () -> ParserUtil.parseWeight("1000")); //upper limit
        assertThrows(ParseException.class, () -> ParserUtil.parseWeight("65.1234")); //>2dp

    }

    @Test
    public void parseWeight_validValueWithoutWhitespace_returnsWeight() throws Exception {
        Weight expectedWeight = new Weight(VALID_WEIGHT);
        Weight expectedWeight2 = new Weight("60.35");
        assertEquals(expectedWeight, ParserUtil.parseWeight(VALID_WEIGHT));
        assertEquals(expectedWeight2, ParserUtil.parseWeight("60.35"));
    }

    @Test
    public void parseWeight_validValueWithWhitespace_returnsTrimmedWeight() throws Exception {
        String weightWithWhitespace = WHITESPACE + VALID_WEIGHT + WHITESPACE;
        Weight expectedWeight = new Weight(VALID_WEIGHT);
        Weight actualWeight = ParserUtil.parseWeight(weightWithWhitespace);
        assertEquals(expectedWeight, actualWeight);
    }

    @Test
    public void parseExercise_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseExercise((String) null, null));
    }

    @Test
    public void parseExercise_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseExercise(" ", true));
        assertThrows(ParseException.class, () -> ParserUtil.parseExercise("running", false));
    }

    @Test
    public void parseExercise_validValueWithoutWhitespace_returnsWeight() throws Exception {
        Exercise expectedExercise = new Exercise("running");
        assertEquals(expectedExercise, ParserUtil.parseExercise("running", true));
    }

    @Test
    public void parseFood_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseFood((String) null, null));
    }

    @Test
    public void parseFood_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseFood(" ", false));
        assertThrows(ParseException.class, () -> ParserUtil.parseFood("food", true));
    }

    @Test
    public void parseFood_validValueWithoutWhitespace_returnsWeight() throws Exception {
        Food expectedFood = new Food("food");
        assertEquals(expectedFood, ParserUtil.parseFood("food", false));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(" "));
        assertThrows(ParseException.class, () -> ParserUtil.parseName("abc@"));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsWeight() throws Exception {
        Name expectedName = new Name("John");
        Name expectedName2 = new Name("John1");
        assertEquals(expectedName, ParserUtil.parseName("John"));
        assertEquals(expectedName2, ParserUtil.parseName("John1"));
    }

    @Test
    public void parseHeight_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseHeight((String) null));
    }

    @Test
    public void parseHeight_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseHeight(" "));
        assertThrows(ParseException.class, () -> ParserUtil.parseHeight("20")); // lower limit
        assertThrows(ParseException.class, () -> ParserUtil.parseHeight("300")); // upper limit
        assertThrows(ParseException.class, () -> ParserUtil.parseHeight("165.1")); // decimal
        assertThrows(ParseException.class, () -> ParserUtil.parseHeight("-165.1")); // decimal
    }

    @Test
    public void parseHeight_validValueWithoutWhitespace_returnsWeight() throws Exception {
        Height expectedHeight = new Height("123");
        assertEquals(expectedHeight, ParserUtil.parseHeight("123"));
    }

    @Test
    public void parseID_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseID((String) null));
    }

    @Test
    public void parseID_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseID(" "));
        assertThrows(ParseException.class, () -> ParserUtil.parseID("5111")); // first digit out of range
        assertThrows(ParseException.class, () -> ParserUtil.parseID("0111"));
        assertThrows(ParseException.class, () -> ParserUtil.parseID("1511")); // second digit out of range
        assertThrows(ParseException.class, () -> ParserUtil.parseID("1011"));
        assertThrows(ParseException.class, () -> ParserUtil.parseID("1100")); // last 2 digit out of range
        assertThrows(ParseException.class, () -> ParserUtil.parseID("1121"));
    }

    @Test
    public void parseID_validValueWithoutWhitespace_returnsWeight() throws Exception {
        ID expectedID = new ID("1111");
        assertEquals(expectedID, ParserUtil.parseID("1111"));
    }

    @Test
    public void parseBmi_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseBmi((String) null));
    }

    @Test
    public void parseBmi_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseBmi(" "));
        assertThrows(ParseException.class, () -> ParserUtil.parseBmi("401")); // out of range
        assertThrows(ParseException.class, () -> ParserUtil.parseBmi("0")); // out of range
    }

    @Test
    public void parseBmi_validValueWithoutWhitespace_returnsWeight() throws Exception {
        assertEquals(23.10, ParserUtil.parseBmi("23.10"));
        assertEquals(23, ParserUtil.parseBmi("23"));
    }

}
