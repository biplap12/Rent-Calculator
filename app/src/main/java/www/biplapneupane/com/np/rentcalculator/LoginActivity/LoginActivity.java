//package www.biplapneupane.com.np.rentcalculator.LoginActivity;
//
//import android.content.Intent;
//import android.database.Cursor;
//import android.database.SQLException;
//import android.database.sqlite.SQLiteDatabase;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import www.biplapneupane.com.np.rentcalculator.Database.DatabaseHelper;
//import www.biplapneupane.com.np.rentcalculator.MainActivity;
//import www.biplapneupane.com.np.rentcalculator.R;
//import www.biplapneupane.com.np.rentcalculator.RegisterActivity.RegisterActivity;
//
//public class LoginActivity extends AppCompatActivity {
//
//    private static final String TAG = "LoginActivity";
//
//    private EditText editTextUsername;
//    private EditText editTextPassword;
//    private Button buttonLogin;
//    private TextView textViewRegister;
//    private DatabaseHelper dbHelper;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        dbHelper = new DatabaseHelper(this);
//
//        editTextUsername = findViewById(R.id.editTextUsername);
//        editTextPassword = findViewById(R.id.editTextPassword);
//        buttonLogin = findViewById(R.id.buttonLogin);
//        textViewRegister = findViewById(R.id.textViewRegister);
//
//        buttonLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String username = editTextUsername.getText().toString().trim();
//                String password = editTextPassword.getText().toString().trim();
//
//                try {
//                    if (validateCredentials(username, password)) {
//                        // Proceed to main app screen
//                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                        finish();
//                    } else {
//                        // Show error message
//                        Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_LONG).show();
//                    }
//                } catch (Exception e) {
//                    Log.e(TAG, "Login failed", e);
//                    Toast.makeText(LoginActivity.this, "An error occurred. Please try again.", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//
//        textViewRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Navigate to RegisterActivity
//                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
//            }
//        });
//    }
//
//    private boolean validateCredentials(String username, String password) {
//        boolean valid = false;
//        SQLiteDatabase db = null;
//        Cursor cursor = null;
//
//        try {
//            db = dbHelper.getReadableDatabase();
//            String[] projection = { "username" };
//            String selection = "username = ? AND password = ?";
//            String[] selectionArgs = { username, password };
//
//            cursor = db.query("users", projection, selection, selectionArgs, null, null, null);
//            valid = cursor.getCount() > 0;
//        } catch (SQLException e) {
//            Log.e(TAG, "Database error", e);
//        } finally {
//            if (cursor != null) {
//                cursor.close();
//            }
//            if (db != null) {
//                db.close();
//            }
//        }
//
//        return valid;
//    }
//}
