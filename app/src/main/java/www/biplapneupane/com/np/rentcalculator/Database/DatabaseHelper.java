//package www.biplapneupane.com.np.rentcalculator.Database;
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//public class DatabaseHelper extends SQLiteOpenHelper {
//
//    private static final String DATABASE_NAME = "app_database.db";
//    private static final int DATABASE_VERSION = 1;
//
//    // Table and column names
//    private static final String TABLE_USERS = "users";
//    private static final String COLUMN_ID = "id";
//    private static final String COLUMN_USERNAME = "username";
//    private static final String COLUMN_EMAIL = "email";
//    private static final String COLUMN_PASSWORD = "password";
//
//    // SQL statement to create table
//    private static final String TABLE_CREATE =
//            "CREATE TABLE " + TABLE_USERS + " (" +
//                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                    COLUMN_USERNAME + " TEXT NOT NULL, " +
//                    COLUMN_EMAIL + " TEXT NOT NULL, " +
//                    COLUMN_PASSWORD + " TEXT NOT NULL);";
//
//    public DatabaseHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(TABLE_CREATE);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
//        onCreate(db);
//    }
//}
