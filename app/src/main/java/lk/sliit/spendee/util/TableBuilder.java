package lk.sliit.spendee.util;

/**
 * author: Lasith Hansana
 * date: 3/19/2021
 * time: 4:15 PM
 */
public  class TableBuilder {

    public static class Builder {
        private final StringBuilder columnsBuilder = new StringBuilder();

        public Builder(String tableName) {
            columnsBuilder.append("CREATE TABLE IF  NOT EXISTS " + tableName + " ( ");
        }

        public Builder integerColumn(String name) {
            columnsBuilder.append(name + " INTEGER ");
            return this;
        }

        public Builder textColumn(String name) {
            columnsBuilder.append(name + " TEXT ");
            return this;
        }

        public Builder floatColumn(String name) {
            columnsBuilder.append(name + " FLOAT ");
            return this;
        }

        public Builder defineColumnHasPrimaryKey() {
            columnsBuilder.append("PRIMARY KEY ");
            return this;
        }

        public Builder setAutoincrement() {
            columnsBuilder.append("AUTOINCREMENT ");
            return this;
        }

        public Builder and() {
            columnsBuilder.append(",");
            return this;
        }

        public String build() {
            columnsBuilder.append(" )");
            return columnsBuilder.toString();
        }
    }
}
