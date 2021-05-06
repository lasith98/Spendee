package lk.sliit.spendee.common;


import lk.sliit.spendee.util.TableBuilder;

/**
 * author: Lasith Hansana
 * date: 3/19/2021
 * time: 2:27 PM
 */
public class DatabaseConstraints {
    public static final String DATABASE_NAME = "spendee.db";
    public static final String EXPENSES_TABLE_NAME ="expenses";

    /**
     * Table names
     */
    public static final String INCOME_TABLE_NAME = "income";
    public static final String INVESTMENT_TABLE_NAME = "investment";
    public static final String SETTING_TABLE_NAME = "setting";
    public static final String GOAL_TABLE_NAME = "goal";
    public static final String SAVING_TABLE_NAME = "saving";
    public static final String ACCOUNT_TABLE_NAME = "account";

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
    public static final String PIN_COLUMN = "pin";

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

            // create account table
            new TableBuilder.Builder(ACCOUNT_TABLE_NAME)
                    .integerColumn(ID_COLUMN)
                    .defineColumnHasPrimaryKey()
                    .setAutoincrement().and()
                    .integerColumn(PIN_COLUMN).build(),
    };

}
