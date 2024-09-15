package www.biplapneupane.com.np.rentcalculator.SavedDataActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RentRecordDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "calculator.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "rent_records";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ROOM_RENT = "room_rent";
    private static final String COLUMN_ELECTRICITY_START = "electricity_start";
    private static final String COLUMN_ELECTRICITY_END = "electricity_end";
    private static final String COLUMN_WATER_BILL = "water_bill";
    private static final String COLUMN_DUST_CHARGE = "dust_charge";
    private static final String COLUMN_PAYMENT_DATE = "payment_date";

    public RentRecordDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ROOM_RENT + " REAL, " +
                COLUMN_ELECTRICITY_START + " REAL, " +
                COLUMN_ELECTRICITY_END + " REAL, " +
                COLUMN_WATER_BILL + " REAL, " +
                COLUMN_DUST_CHARGE + " REAL" +
                COLUMN_PAYMENT_DATE + "REAL)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public List<RentRecord> getAllRentRecords() {
        List<RentRecord> rentRecords = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, "DESC");
        Cursor cursor = db.query(
                TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                COLUMN_PAYMENT_DATE + " DESC"
        );
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") float roomRent = cursor.getFloat(cursor.getColumnIndex(COLUMN_ROOM_RENT));
                @SuppressLint("Range") float electricityStart = cursor.getFloat(cursor.getColumnIndex(COLUMN_ELECTRICITY_START));
                @SuppressLint("Range") float electricityEnd = cursor.getFloat(cursor.getColumnIndex(COLUMN_ELECTRICITY_END));
                @SuppressLint("Range") float waterBill = cursor.getFloat(cursor.getColumnIndex(COLUMN_WATER_BILL));
                @SuppressLint("Range") float dustCharge = cursor.getFloat(cursor.getColumnIndex(COLUMN_DUST_CHARGE));
                @SuppressLint("Range") float paymentDate = cursor.getFloat(cursor.getColumnIndex(COLUMN_PAYMENT_DATE));

                RentRecord record = new RentRecord(roomRent, electricityStart, electricityEnd, waterBill, dustCharge, (long) paymentDate);
                rentRecords.add(record);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return rentRecords;
    }
}

