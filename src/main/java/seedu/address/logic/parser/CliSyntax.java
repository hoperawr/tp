package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions*/
    /* Prefixes for day commands */
    public static final Prefix PREFIX_DATE = new Prefix("d/");
    public static final Prefix PREFIX_WEIGHT = new Prefix("w/");

    /* Prefixes for calorie commands */
    public static final Prefix PREFIX_CALORIE_TYPE = new Prefix("tp/");
    public static final Prefix PREFIX_CALORIE_COUNT = new Prefix("c/");
    public static final Prefix PREFIX_TIME = new Prefix("t/");
    public static final Prefix PREFIX_EXERCISE = new Prefix("e/");
    public static final Prefix PREFIX_FOOD = new Prefix("f/");
    public static final Prefix PREFIX_INDEX = new Prefix("i/");

    /* Prefixes for profile commands */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_ID = new Prefix("id/");
    public static final Prefix PREFIX_HEIGHT = new Prefix("h/");
    public static final Prefix PREFIX_BMI = new Prefix("bmi/");

    /* Prefix for stats command */
    public static final Prefix PREFIX_VIEW_STATS = new Prefix("v/");
}
