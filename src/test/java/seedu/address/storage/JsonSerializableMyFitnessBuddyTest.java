package seedu.address.storage;

//import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

//import org.junit.jupiter.api.Test;

//import seedu.address.commons.exceptions.IllegalValueException;
//import seedu.address.commons.util.JsonUtil;


public class JsonSerializableMyFitnessBuddyTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableMyFitnessBuddyTest");
    private static final Path TYPICAL_DAYS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsAddressBook.json");
    private static final Path INVALID_DAY_FILE = TEST_DATA_FOLDER.resolve("invalidPersonAddressBook.json");
    private static final Path DUPLICATE_DAY_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonAddressBook.json");
    /*
    @Test
    public void toModelType_typicalDaysFile_success() throws Exception {
        JsonSerializableMyFitnessBuddy dataFromFile = JsonUtil.readJsonFile(TYPICAL_DAYS_FILE,
                JsonSerializableMyFitnessBuddy.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalDaysAddressBook = TypicalDays.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalDaysAddressBook);
    }

    @Test
    public void toModelType_invalidDayFile_throwsIllegalValueException() throws Exception {
        JsonSerializableMyFitnessBuddy dataFromFile = JsonUtil.readJsonFile(INVALID_DAY_FILE,
                JsonSerializableMyFitnessBuddy.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }



    @Test
    public void toModelType_duplicateDays_throwsIllegalValueException() throws Exception {
        JsonSerializableMyFitnessBuddy dataFromFile = JsonUtil.readJsonFile(DUPLICATE_DAY_FILE,
                JsonSerializableMyFitnessBuddy.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableMyFitnessBuddy.MESSAGE_DUPLICATE_DAY,
                dataFromFile::toModelType);
    }
    */
}