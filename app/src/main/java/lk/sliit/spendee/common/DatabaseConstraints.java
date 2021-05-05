package lk.sliit.spendee.common;


import lk.sliit.spendee.util.TableBuilder;

/**
 * author: Lasith Hansana
 * date: 3/19/2021
 * time: 2:27 PM
 */
public class DatabaseConstraints {
    public static final String DATABASE_NAME = "spendee.db";

    /**
     * Table names
     */
    public static final String INCOME_TABLE_NAME = "income";
    public static final String INVESTMENT_TABLE_NAME = "investment";
    public static final String EXPENSES_TABLE_NAME = "expenses";
    public static final String SETTING_TABLE_NAME = "setting";
    public static final String GOAL_TABLE_NAME = "goal";
    public static final String SAVING_TABLE_NAME = "saving";
    public static final String REMAINS_TABLE_NAME = "remains";

    /**
     * column names
     */
    public static final String ID_COLUMN = "id";
    public static final String AMOUNT_COLUMN = "amount";
    public static final String DATE_COLUMN = "date";
    public static final String DESCRIPTION_COLUMN = "description";
    public static final String SAVING_RATE_COLUMN = "savingRate";
    public static final String INVESTMENT_COLUMN = "investmentRate";
    public static final String EXPENSE_COLUMN = "expenseRate";
    public static final String GOAL_COLUMN = "goalRate";

    public static final String INVESTMENT_REMAINS_COLUMN = "investment";
    public static final String GOAL_REMAINS_COLUMN = "goal";
    public static final String SAVING_REMAINS_COLUMN = "saving";
    public static final String EXPENSES_REMAINS_COLUMN = "expenses";

    /**
     * column index
     */
    public static final int COLUMN_ONE = 1;

    public static final String[] DATABASE_TABLES = {
            // create income table
            new TableBuilder.Builder(INCOME_TABLE_NAME)
                    .integerColumn(ID_COLUMN)
                    .defineColumnHasPrimaryKey()
                    .setAutoincrement().and()
                    .floatColumn(AMOUNT_COLUMN).and()
                    .textColumn(DATE_COLUMN).and()
                    .textColumn(DESCRIPTION_COLUMN).build(),
            // create goal table
            new TableBuilder.Builder(GOAL_TABLE_NAME)
                    .integerColumn(ID_COLUMN)
                    .defineColumnHasPrimaryKey()
                    .setAutoincrement().and()
                    .floatColumn(AMOUNT_COLUMN).and()
                    .textColumn(DATE_COLUMN).and()
                    .textColumn(DESCRIPTION_COLUMN).build(),

            // create expenses table
            new TableBuilder.Builder(EXPENSES_TABLE_NAME)
                    .integerColumn(ID_COLUMN)
                    .defineColumnHasPrimaryKey()
                    .setAutoincrement().and()
                    .floatColumn(AMOUNT_COLUMN).and()
                    .textColumn(DATE_COLUMN).and()
                    .textColumn(DESCRIPTION_COLUMN).build(),

            // create investment table
            new TableBuilder.Builder(INVESTMENT_TABLE_NAME)
                    .integerColumn(ID_COLUMN)
                    .defineColumnHasPrimaryKey()
                    .setAutoincrement().and()
                    .floatColumn(AMOUNT_COLUMN).and()
                    .textColumn(DATE_COLUMN).and()
                    .textColumn(DESCRIPTION_COLUMN).build(),

            //create setting table
            new TableBuilder.Builder(SETTING_TABLE_NAME)
                    .integerColumn(ID_COLUMN)
                    .defineColumnHasPrimaryKey()
                    .setAutoincrement().and()
                    .floatColumn(SAVING_RATE_COLUMN).and()
                    .floatColumn(INVESTMENT_COLUMN).and()
                    .floatColumn(EXPENSE_COLUMN).and()
                    .floatColumn(GOAL_COLUMN).build(),


            // create saving table
            new TableBuilder.Builder(SAVING_TABLE_NAME)
                    .integerColumn(ID_COLUMN)
                    .defineColumnHasPrimaryKey()
                    .setAutoincrement().and()
                    .floatColumn(AMOUNT_COLUMN).and()
                    .textColumn(DATE_COLUMN).and()
                    .textColumn(DESCRIPTION_COLUMN).build(),
            // create remains table
            new TableBuilder.Builder(REMAINS_TABLE_NAME)
                    .integerColumn(ID_COLUMN)
                    .defineColumnHasPrimaryKey()
                    .setAutoincrement().and()
                    .floatColumn(INVESTMENT_REMAINS_COLUMN).and()
                    .floatColumn(EXPENSES_REMAINS_COLUMN).and()
                    .floatColumn(SAVING_REMAINS_COLUMN).and()
                    .floatColumn(GOAL_REMAINS_COLUMN).build()


    };

}
