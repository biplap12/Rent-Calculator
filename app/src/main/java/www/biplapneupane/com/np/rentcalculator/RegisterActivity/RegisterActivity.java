//package www.biplapneupane.com.np.rentcalculator.RegisterActivity;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.database.sqlite.SQLiteDatabase;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//
//import www.biplapneupane.com.np.rentcalculator.Database.DatabaseHelper;
//import www.biplapneupane.com.np.rentcalculator.LoginActivity.LoginActivity;
//import www.biplapneupane.com.np.rentcalculator.MainActivity;
//import www.biplapneupane.com.np.rentcalculator.R;
//
//public class RegisterActivity extends AppCompatActivity {
//
//    private EditText editTextUsernameRegister;
//    private EditText editTextEmail;
//    private EditText editTextPasswordRegister;
//    private EditText editTextConfirmPassword;
//    private Button buttonRegister;
//    private TextView textViewLogin;
//    private DatabaseHelper dbHelper;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//
//        dbHelper = new DatabaseHelper(this);
//
//        editTextUsernameRegister = findViewById(R.id.editTextUsernameRegister);
//        editTextEmail = findViewById(R.id.editTextEmail);
//        editTextPasswordRegister = findViewById(R.id.editTextPasswordRegister);
//        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
//        buttonRegister = findViewById(R.id.buttonRegister);
//        textViewLogin = findViewById(R.id.textViewLogin);
//
//        buttonRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String username = editTextUsernameRegister.getText().toString().trim();
//                String email = editTextEmail.getText().toString().trim();
//                String password = editTextPasswordRegister.getText().toString().trim();
//                String confirmPassword = editTextConfirmPassword.getText().toString().trim();
//
//                if (validateRegistration(username, email, password, confirmPassword)) {
//                    SQLiteDatabase db = dbHelper.getWritableDatabase();
//                    ContentValues values = new ContentValues();
//                    values.put("username", username);
//                    values.put("email", email);
//                    values.put("password", password);
//
//                    long newRowId = db.insert("users", null, values);
//                    db.close();
//
//                    if (newRowId != -1) {
//                        // Save login state to SharedPreferences
//                        SharedPreferences sharedPref = getSharedPreferences("UserSession", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sharedPref.edit();
//                        editor.putBoolean("isLoggedIn", true);
//                        editor.putString("username", username); // Optional, if you need to save more data
//                        editor.apply();
//
//                        // Registration successful, go to MainActivity
//                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
//
//                    }else {
//                        // Error inserting data
//                        showAlert("Error", "Error registering user. Please try again.", R.drawable.error_icon);
//                    }
//                } else {
//                    // Show validation error
//                    showAlert("Validation Error", "Please fill all fields and ensure passwords match.", R.drawable.error_icon);
//                }
//            }
//        });
//
//        textViewLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
//                finish();
//            }
//        });
//    }
//
//    private boolean validateRegistration(String username, String email, String password, String confirmPassword) {
//        return !username.isEmpty() && !email.isEmpty() && !password.isEmpty() && password.equals(confirmPassword);
//    }
//
//    private void showAlert(String title, String message, int iconResId) {
//        new AlertDialog.Builder(this)
//                .setTitle(title)
//                .setMessage(message)
//                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        // Continue with other actions if needed
//                        finish();
//                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
//                    }
//                })
//                .setIcon(iconResId)
//                .show();
//    }
//}
