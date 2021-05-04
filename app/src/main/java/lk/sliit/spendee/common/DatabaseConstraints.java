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
    public static final String GOAL_TABLE_NAME = "goal";

    /**
     * column names
     */
    public static final String ID_COLUMN = "id";
    public static final String AMOUNT_COLUMN = "amount";
    public static final String DATE_COLUMN = "date";
    public static final String DESCRIPTION_COLUMN = "description";

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


    };

}
