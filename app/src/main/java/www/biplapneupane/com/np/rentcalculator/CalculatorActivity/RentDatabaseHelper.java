package www.biplapneupane.com.np.rentcalculator.CalculatorActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RentDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "calculator.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_RENT_RECORDS = "rent_records";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_ROOM_RENT = "room_rent";
    private static final String COLUMN_ELECTRICITY_START = "electricity_start";
    private static final String COLUMN_ELECTRICITY_END = "electricity_end";
    private static final String COLUMN_WATER_BILL = "water_bill";
    private static final String COLUMN_DUST_CHARGE = "dust_charge";
    private static final String COLUMN_TOTAL_RENT = "total_rent";
    private static final String COLUMN_PAYMENT_DATE = "payment_date";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_RENT_RECORDS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_ROOM_RENT + " REAL, " +
                    COLUMN_ELECTRICITY_START + " REAL, " +
                    COLUMN_ELECTRICITY_END + " REAL, " +
                    COLUMN_WATER_BILL + " REAL, " +
                    COLUMN_DUST_CHARGE + " REAL, " +
                    COLUMN_TOTAL_RENT + " REAL, " +
                    COLUMN_PAYMENT_DATE + " INTEGER);";

    public RentDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RENT_RECORDS);
        onCreate(db);
    }
}
