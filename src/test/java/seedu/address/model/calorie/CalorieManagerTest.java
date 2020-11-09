package seedu.address.model.calorie;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCalories.INPUT_A;
import static seedu.address.testutil.TypicalCalories.INPUT_B;
import static seedu.address.testutil.TypicalCalories.INPUT_C;
import static seedu.address.testutil.TypicalCalories.INPUT_D;
import static seedu.address.testutil.TypicalCalories.OUTPUT_A;
import static seedu.address.testutil.TypicalCalories.OUTPUT_B;
import static seedu.address.testutil.TypicalCalories.OUTPUT_C;
import static seedu.address.testutil.TypicalCalories.OUTPUT_D;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.testutil.TypicalCalories;




public class CalorieManagerTest {


    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CalorieManager(null, null));
    }

    @Test
    public void getTotalInputCalorie() {
        CalorieManager calorieManager = new CalorieManager();

        try {
            calorieManager.addCalorieInput(INPUT_C);
            calorieManager.addCalorieInput(INPUT_A);
            calorieManager.addCalorieInput(INPUT_B);
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }

        assertEquals(calorieManager.getTotalInputCalorie(), 603);
    }

    @Test
    public void getTotalOutputCalorie() {
        CalorieManager calorieManager = new CalorieManager();
        try {
            calorieManager.addCalorieOutput(OUTPUT_A);
            calorieManager.addCalorieOutput(OUTPUT_B);
            calorieManager.addCalorieOutput(OUTPUT_C);
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }


        assertEquals(calorieManager.getTotalOutputCalorie(), 666);
    }

    @Test
    public void updateTotalCalorieCounts() {
        CalorieManager calorieManager = new CalorieManager();

        //testing null parameters
        assertThrows(NullPointerException.class, ()->
                calorieManager.updateTotalCalorieCounts(null, null));
        assertThrows(NullPointerException.class, ()->
                calorieManager.updateTotalCalorieCounts(new ArrayList<>(), null));


        //adding empty list
        calorieManager.updateTotalCalorieCounts(new ArrayList<>(),
                new ArrayList<>());
        assertEquals(calorieManager.getTotalInputCalorie(), 0);
        assertEquals(calorieManager.getTotalInputCalorie(), 0);

        //adding non empty list
        calorieManager.updateTotalCalorieCounts(TypicalCalories.getTypicalInputList(),
                TypicalCalories.getTypicalOutputList());
        assertEquals(calorieManager.getTotalInputCalorie(), 603);
        assertEquals(calorieManager.getTotalOutputCalorie(), 666);
    }

    @Test
    public void addTotalCalorieOutput() {

        CalorieManager calorieManager = new CalorieManager();

        //testing null parameter
        assertThrows(NullPointerException.class, ()->
                calorieManager.addTotalCalorieOut(null));

        //testing with non empty calories
        try {
            calorieManager.addTotalCalorieOut(OUTPUT_A.getCalorieCount());
            calorieManager.addTotalCalorieOut(OUTPUT_B.getCalorieCount());
            calorieManager.addTotalCalorieOut(OUTPUT_C.getCalorieCount());
            assertEquals(666, calorieManager.getTotalOutputCalorie());
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }

        //testing with adding calories which will result in an overflow
        assertThrows(IllegalValueException.class, ()->
                    calorieManager.addTotalCalorieOut(new CalorieCount("2147483647")));
    }

    @Test
    public void addTotalCalorieInput() {
        CalorieManager calorieManager = new CalorieManager();

        //testing null parameter
        assertThrows(NullPointerException.class, ()->
                calorieManager.addTotalCalorieOut(null));

        //testing with non empty calories
        try {
            calorieManager.addTotalCalorieInput(INPUT_A.getCalorieCount());
            calorieManager.addTotalCalorieInput(INPUT_B.getCalorieCount());
            calorieManager.addTotalCalorieInput(INPUT_C.getCalorieCount());
            assertEquals(603, calorieManager.getTotalInputCalorie());
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }

        //testing with adding calories which will result in an overflow
        assertThrows(IllegalValueException.class, ()->
                calorieManager.addTotalCalorieInput(new CalorieCount("2147483647")));
    }

    @Test
    public void deleteTotalCalorieOutput() {
        CalorieManager calorieManager = new CalorieManager();
        //testing null parameter
        Integer testNull = null;
        assertThrows(NullPointerException.class, ()->
                calorieManager.deleteTotalCalorieInput(testNull));
        //testing normal range
        try {
            calorieManager.addCalorieOutput(OUTPUT_A);
            calorieManager.addCalorieOutput(OUTPUT_B);
            calorieManager.addCalorieOutput(OUTPUT_C);
            calorieManager.deleteTotalCalorieOutput(1);
            assertEquals(444, calorieManager.getTotalOutputCalorie());
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }
        //testing index out of range
        assertThrows(IllegalValueException.class, ()->
                calorieManager.deleteTotalCalorieOutput(-1));
        int outputListSize = calorieManager.getListSize(true);
        assertThrows(IllegalValueException.class, ()->
                calorieManager.deleteTotalCalorieOutput(outputListSize + 1));
        assertThrows(IllegalValueException.class, ()->
                calorieManager.deleteTotalCalorieOutput(-1));

    }

    @Test
    public void deleteTotalCalorieInput() {
        CalorieManager calorieManager = new CalorieManager();
        //testing null parameter
        Integer testNull = null;
        assertThrows(NullPointerException.class, ()->
                calorieManager.deleteTotalCalorieInput(testNull));
        //testing normal input
        try {
            calorieManager.addCalorieInput(INPUT_A);
            calorieManager.addCalorieInput(INPUT_B);
            calorieManager.addCalorieInput(INPUT_C);
            calorieManager.deleteTotalCalorieInput(1);
            assertEquals(402, calorieManager.getTotalInputCalorie());
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }
        //testing index out of range
        assertThrows(IllegalValueException.class, ()->
                calorieManager.deleteTotalCalorieInput(-1));
        int inputListSize = calorieManager.getListSize(false);
        assertThrows(IllegalValueException.class, ()->
                calorieManager.deleteTotalCalorieInput(inputListSize + 1));
        assertThrows(IllegalValueException.class, ()->
                calorieManager.deleteTotalCalorieInput(-1));
    }


    @Test
    public void getCalorieInputList() {
        CalorieManager calorieManager = new CalorieManager();

        assertEquals(calorieManager.getCalorieInputList(), new ArrayList<Input>());
        assertTrue(calorieManager.getCalorieInputList().isEmpty());

        try {
            calorieManager.addCalorieInput(INPUT_A);
            calorieManager.addCalorieInput(INPUT_B);
            calorieManager.addCalorieInput(INPUT_C);
            assertEquals(TypicalCalories.getTypicalInputList(), calorieManager.getCalorieInputList());
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getCalorieOutputList() {
        CalorieManager calorieManager = new CalorieManager();

        assertEquals(calorieManager.getCalorieOutputList(), new ArrayList<Output>());
        assertTrue(calorieManager.getCalorieOutputList().isEmpty());
        try {
            calorieManager.addCalorieOutput(OUTPUT_A);
            calorieManager.addCalorieOutput(OUTPUT_B);
            calorieManager.addCalorieOutput(OUTPUT_C);
            assertEquals(calorieManager.getCalorieOutputList(), TypicalCalories.getTypicalOutputList());
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addCalorieInput() {
        CalorieManager calorieManager = new CalorieManager();

        assertThrows(NullPointerException.class, ()->calorieManager.addCalorieInput(null));

        ArrayList<Input> arr = new ArrayList<>(Arrays.asList(INPUT_A, INPUT_B, INPUT_C));
        ArrayList<Input> arr1 = new ArrayList<>(Arrays.asList(INPUT_C, INPUT_B, INPUT_A));

        //checks for the sorting
        try {
            calorieManager.addCalorieInput(INPUT_C);
            calorieManager.addCalorieInput(INPUT_B);
            calorieManager.addCalorieInput(INPUT_A);
            assertNotEquals(calorieManager.getCalorieInputList(), arr1);
            assertEquals(calorieManager.getCalorieInputList(), arr);
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addCalorieOutput() {
        CalorieManager calorieManager = new CalorieManager();

        assertThrows(NullPointerException.class, ()->calorieManager.addCalorieInput(null));

        ArrayList<Output> arr = new ArrayList<>(Arrays.asList(OUTPUT_A, OUTPUT_B, OUTPUT_C));
        ArrayList<Output> arr1 = new ArrayList<>(Arrays.asList(OUTPUT_C, OUTPUT_B, OUTPUT_A));

        //checks for the sorting
        try {
            calorieManager.addCalorieOutput(OUTPUT_C);
            calorieManager.addCalorieOutput(OUTPUT_B);
            calorieManager.addCalorieOutput(OUTPUT_A);
            assertNotEquals(calorieManager.getCalorieOutputList(), arr1);
            assertEquals(calorieManager.getCalorieOutputList(), arr);
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void removeCalorie() {
        CalorieManager calorieManager = new CalorieManager();

        assertThrows(NullPointerException.class, ()->calorieManager.removeCalorie(null, null));

        try {
            calorieManager.addCalorieOutput(OUTPUT_A);
            calorieManager.addCalorieOutput(OUTPUT_B);
            calorieManager.addCalorieOutput(OUTPUT_C);
            calorieManager.removeCalorie(true, Index.fromOneBased(2));
            assertEquals(444, calorieManager.getTotalOutputCalorie());
            assertEquals(Arrays.asList(OUTPUT_A, OUTPUT_C), calorieManager.getCalorieOutputList());

            calorieManager.addCalorieInput(INPUT_A);
            calorieManager.addCalorieInput(INPUT_B);
            calorieManager.addCalorieInput(INPUT_C);
            calorieManager.removeCalorie(false, Index.fromOneBased(2));
            assertEquals(402, calorieManager.getTotalInputCalorie());
            assertEquals(Arrays.asList(INPUT_A, INPUT_C), calorieManager.getCalorieInputList());
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getListSize() {
        CalorieManager calorieManager = new CalorieManager();
        assertThrows(NullPointerException.class, ()->calorieManager.getListSize(null));
        try {
            calorieManager.addCalorieOutput(OUTPUT_A);
            calorieManager.addCalorieOutput(OUTPUT_B);
            calorieManager.addCalorieOutput(OUTPUT_C);
            calorieManager.removeCalorie(true, Index.fromOneBased(2));
            assertEquals(2, calorieManager.getListSize(true));

            calorieManager.addCalorieInput(INPUT_A);
            calorieManager.addCalorieInput(INPUT_B);
            calorieManager.addCalorieInput(INPUT_C);
            calorieManager.removeCalorie(false, Index.fromOneBased(2));
            assertEquals(2, calorieManager.getListSize(false));
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getCalorie() {
        CalorieManager calorieManager = new CalorieManager();
        assertThrows(NullPointerException.class, ()->calorieManager.getCalorie(null, null));
        try {
            calorieManager.addCalorieOutput(OUTPUT_A);
            calorieManager.addCalorieOutput(OUTPUT_B);
            calorieManager.addCalorieOutput(OUTPUT_C);

            assertThrows(IllegalValueException.class, ()->calorieManager.getCalorie(true, Index.fromOneBased(5)));
            assertEquals(calorieManager.getCalorie(true, Index.fromOneBased(2)), OUTPUT_B);

            calorieManager.addCalorieInput(INPUT_A);
            calorieManager.addCalorieInput(INPUT_B);
            calorieManager.addCalorieInput(INPUT_C);

            assertThrows(IllegalValueException.class, ()->calorieManager.getCalorie(false, Index.fromOneBased(5)));
            assertEquals(calorieManager.getCalorie(false, Index.fromOneBased(2)), INPUT_B);
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void setCalorie() {
        CalorieManager calorieManager = new CalorieManager();
        assertThrows(NullPointerException.class, ()->calorieManager.contains(null, null));

        try {
            calorieManager.addCalorieOutput(OUTPUT_A);
            calorieManager.addCalorieOutput(OUTPUT_B);
            calorieManager.addCalorieOutput(OUTPUT_C);

            assertTrue(calorieManager.contains(OUTPUT_B, true));
            assertFalse(calorieManager.contains(OUTPUT_D, true));
            calorieManager.setCalorie(Index.fromOneBased(2), true, OUTPUT_D);
            assertTrue(calorieManager.contains(OUTPUT_D, true));
            assertFalse(calorieManager.contains(OUTPUT_B, true));

            calorieManager.addCalorieInput(INPUT_A);
            calorieManager.addCalorieInput(INPUT_B);
            calorieManager.addCalorieInput(INPUT_C);

            assertTrue(calorieManager.contains(INPUT_B, false));
            assertFalse(calorieManager.contains(INPUT_D, false));
            calorieManager.setCalorie(Index.fromOneBased(2), false, INPUT_D);
            assertTrue(calorieManager.contains(INPUT_D, false));
            assertFalse(calorieManager.contains(INPUT_B, false));
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void contains() {
        CalorieManager calorieManager = new CalorieManager();
        assertThrows(NullPointerException.class, ()->calorieManager.contains(null, null));

        try {
            calorieManager.addCalorieOutput(OUTPUT_A);
            calorieManager.addCalorieOutput(OUTPUT_B);
            calorieManager.addCalorieOutput(OUTPUT_C);

            assertTrue(calorieManager.contains(OUTPUT_B, true));
            assertFalse(calorieManager.contains(OUTPUT_D, true));
            assertFalse(calorieManager.contains(INPUT_A, true));

            calorieManager.addCalorieInput(INPUT_A);
            calorieManager.addCalorieInput(INPUT_B);
            calorieManager.addCalorieInput(INPUT_C);
            assertTrue(calorieManager.contains(INPUT_C, false));
            assertFalse(calorieManager.contains(INPUT_D, false));
            assertFalse(calorieManager.contains(OUTPUT_A, false));
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }
    }
}
