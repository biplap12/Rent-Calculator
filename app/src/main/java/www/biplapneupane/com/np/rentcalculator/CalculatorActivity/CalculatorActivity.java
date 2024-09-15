//package www.biplapneupane.com.np.rentcalculator.CalculatorActivity;
//
//import android.annotation.SuppressLint;
//import android.app.AlertDialog;
//import android.content.ContentValues;
//import android.content.DialogInterface;
//import android.database.sqlite.SQLiteDatabase;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import www.biplapneupane.com.np.rentcalculator.R;
//
//public class CalculatorActivity extends AppCompatActivity {
//
//    private EditText roomRentEditText;
//    private EditText electricityStartEditText;
//    private EditText electricityEndEditText;
//    private EditText waterBillEditText;
//    private EditText dustChargeEditText;
//    private TextView Result;
//    private Button calculateButton;
//    private Button clearButton;
//
//    private RentCalculatorService rentCalculatorService;
//    private SQLiteDatabase database;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_calculator_rent);
//
//        roomRentEditText = findViewById(R.id.editTextRoomRent);
//        electricityStartEditText = findViewById(R.id.editTextElectricityStart);
//        electricityEndEditText = findViewById(R.id.editTextElectricityEnd);
//        waterBillEditText = findViewById(R.id.editTextWaterBill);
//        dustChargeEditText = findViewById(R.id.editTextDustCharge);
//        calculateButton = findViewById(R.id.buttonCalculate);
//        clearButton = findViewById(R.id.buttonClear);
//        Result = findViewById(R.id.textViewResult);
//
//        rentCalculatorService = new RentCalculatorService();
//        database = new RentDatabaseHelper(this).getWritableDatabase();
//
//        calculateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                performCalculation();
//            }
//        });
//
//        clearButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clearInputs();
//            }
//        });
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.calculate_rent_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//
//        if (id == R.id.navigation_save) {
//            saveData();
//            return true;
//        } else {
//            return super.onOptionsItemSelected(item);
//        }
//    }
//
//    @SuppressLint("SetTextI18n")
//    private void performCalculation() {
//        try {
//            // Validate inputs
//            if (!validateInputs()) return;
//
//            double roomRent = Double.parseDouble(roomRentEditText.getText().toString());
//            double electricityStart = Double.parseDouble(electricityStartEditText.getText().toString());
//            double electricityEnd = Double.parseDouble(electricityEndEditText.getText().toString());
//            double waterBill = Double.parseDouble(waterBillEditText.getText().toString());
//            double dustCharge = Double.parseDouble(dustChargeEditText.getText().toString());
//
//            int electricityUnits = (int) (electricityEnd - electricityStart);
//
//            // Ensure electricityEnd is greater than electricityStart
//            if (electricityUnits >= 0) {
//                double totalRent = rentCalculatorService.calculateRent(roomRent, electricityStart, electricityEnd, waterBill, dustCharge);
//                Result.setText("Total Rent: Rs. " + totalRent);
//                Toast.makeText(this,"Electricity Units: " + electricityUnits,Toast.LENGTH_LONG).show();
//            } else {
//                Result.setText("Invalid input: Electricity Units end should be greater than start.");
//            }
//
//        } catch (NumberFormatException e) {
//            Result.setText("Invalid input. Please enter valid numbers.");
//            Toast.makeText(this, "Invalid input. Please enter valid numbers.", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private boolean validateInputs() {
//        String roomRentStr = roomRentEditText.getText().toString().trim();
//        String electricityStartStr = electricityStartEditText.getText().toString().trim();
//        String electricityEndStr = electricityEndEditText.getText().toString().trim();
//        String waterBillStr = waterBillEditText.getText().toString().trim();
//        String dustChargeStr = dustChargeEditText.getText().toString().trim();
//
//        if (roomRentStr.isEmpty() || electricityStartStr.isEmpty() || electricityEndStr.isEmpty() ||
//                waterBillStr.isEmpty() || dustChargeStr.isEmpty()) {
//            Toast.makeText(this, "All fields must be filled.", Toast.LENGTH_SHORT).show();
//            Result.setText("All fields must be filled.");
//            return false;
//        }
//        return true;
//    }
//
//    private void clearInputs() {
//        roomRentEditText.setText("");
//        electricityStartEditText.setText("");
//        electricityEndEditText.setText("");
//        waterBillEditText.setText("");
//        dustChargeEditText.setText("");
//        Result.setText("");
//        roomRentEditText.requestFocus();
//    }
//
//    private void saveData() {
//        try {
//            // Validate inputs
//            if (!validateInputs()) return;
//
//            double roomRent = Double.parseDouble(roomRentEditText.getText().toString().trim());
//            double electricityStart = Double.parseDouble(electricityStartEditText.getText().toString().trim());
//            double electricityEnd = Double.parseDouble(electricityEndEditText.getText().toString().trim());
//            double waterBill = Double.parseDouble(waterBillEditText.getText().toString().trim());
//            double dustCharge = Double.parseDouble(dustChargeEditText.getText().toString().trim());
//
//            // Ensure electricityEnd is greater than electricityStart
//            if (electricityEnd < electricityStart) {
//                Toast.makeText(this, "Electricity end should be greater than start.", Toast.LENGTH_SHORT).show();
//                return;
//            }else {
//
//                // Calculate total rent
//                double totalRent = rentCalculatorService.calculateRent(roomRent, electricityStart, electricityEnd, waterBill, dustCharge);
//
//                // Insert data into database
//                ContentValues values = new ContentValues();
//                values.put("room_rent", roomRent);
//                values.put("electricity_start", electricityStart);
//                values.put("electricity_end", electricityEnd);
//                values.put("water_bill", waterBill);
//                values.put("dust_charge", dustCharge);
//                values.put("total_rent", totalRent);
//                values.put("payment_date", System.currentTimeMillis());
//
//                long newRowId = database.insert("rent_records", null, values);
//
//                if (newRowId != -1) {
//                    new AlertDialog.Builder(this)
//                            .setTitle("Success")
//                            .setMessage("Data saved successfully.")
//                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.dismiss();
//                                    clearInputs();
//                                }
//                            })
//                            .setIcon(R.drawable.success_icon)
//                            .show();
//                } else {
//                    new AlertDialog.Builder(this)
//                            .setTitle("Error")
//                            .setMessage("Failed to save data.")
//                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.dismiss();
//                                }
//                            })
//                            .setIcon(R.drawable.error_icon)
//                            .show();
//                }
//            }
//
//        } catch (NumberFormatException e) {
//            new AlertDialog.Builder(this)
//                    .setTitle("Invalid Input")
//                    .setMessage("Invalid number format. Please check your inputs.")
//                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    })
//                    .setIcon(R.drawable.error_icon)
//                    .show();
//        }
//    }
//}


//*******************************

package www.biplapneupane.com.np.rentcalculator.CalculatorActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import www.biplapneupane.com.np.rentcalculator.R;

public class CalculatorActivity extends AppCompatActivity {

    private EditText roomRentEditText;
    private EditText electricityStartEditText;
    private EditText electricityEndEditText;
    private EditText waterBillEditText;
    private EditText dustChargeEditText;
    private TextView resultTextView;
    private Button calculateButton;
    private Button clearButton;

    private RentCalculatorService rentCalculatorService;
    private SQLiteDatabase database;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_rent);

        roomRentEditText = findViewById(R.id.editTextRoomRent);
        electricityStartEditText = findViewById(R.id.editTextElectricityStart);
        electricityEndEditText = findViewById(R.id.editTextElectricityEnd);
        waterBillEditText = findViewById(R.id.editTextWaterBill);
        dustChargeEditText = findViewById(R.id.editTextDustCharge);
        calculateButton = findViewById(R.id.buttonCalculate);
        clearButton = findViewById(R.id.buttonClear);
        resultTextView = findViewById(R.id.textViewResult);

        // Initialize SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        retrieveAndDisplayElectricityStart();

        rentCalculatorService = new RentCalculatorService();
        database = new RentDatabaseHelper(this).getWritableDatabase();

        calculateButton.setOnClickListener(v -> performCalculation());
        clearButton.setOnClickListener(v -> clearInputs());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.calculate_rent_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.navigation_save) {
            saveData();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @SuppressLint("SetTextI18n")
    private void performCalculation() {
        try {
            if (!validateInputs()) return;

            double roomRent = Double.parseDouble(roomRentEditText.getText().toString().trim());
            double electricityStart = Double.parseDouble(electricityStartEditText.getText().toString().trim());
            double electricityEnd = Double.parseDouble(electricityEndEditText.getText().toString().trim());
            double waterBill = Double.parseDouble(waterBillEditText.getText().toString().trim());
            double dustCharge = Double.parseDouble(dustChargeEditText.getText().toString().trim());

            int electricityUnits = (int) (electricityEnd - electricityStart);

            if (electricityUnits >= 0) {
                double totalRent = rentCalculatorService.calculateRent(roomRent, electricityStart, electricityEnd, waterBill, dustCharge);
                resultTextView.setText("Total Rent: Rs. " + totalRent);
                Toast.makeText(this, "Electricity Units: " + electricityUnits, Toast.LENGTH_LONG).show();
            } else {
                resultTextView.setText("Invalid input: Electricity end should be greater than start.");
            }
        } catch (NumberFormatException e) {
            resultTextView.setText("Invalid input. Please enter valid numbers.");
            Toast.makeText(this, "Invalid input. Please enter valid numbers.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateInputs() {
        String roomRentStr = roomRentEditText.getText().toString().trim();
        String electricityStartStr = electricityStartEditText.getText().toString().trim();
        String electricityEndStr = electricityEndEditText.getText().toString().trim();
        String waterBillStr = waterBillEditText.getText().toString().trim();
        String dustChargeStr = dustChargeEditText.getText().toString().trim();

        if (roomRentStr.isEmpty() || electricityStartStr.isEmpty() || electricityEndStr.isEmpty() ||
                waterBillStr.isEmpty() || dustChargeStr.isEmpty()) {
            Toast.makeText(this, "All fields must be filled.", Toast.LENGTH_SHORT).show();
            resultTextView.setText("All fields must be filled.");
            return false;
        }
        return true;
    }

    private void clearInputs() {
        roomRentEditText.setText("");
        electricityStartEditText.setText("");
        electricityEndEditText.setText("");
        waterBillEditText.setText("");
        dustChargeEditText.setText("");
        resultTextView.setText("");
        roomRentEditText.requestFocus();
    }

    private void saveData() {
        try {
            if (!validateInputs()) return;

            double roomRent = Double.parseDouble(roomRentEditText.getText().toString().trim());
            double electricityStart = Double.parseDouble(electricityStartEditText.getText().toString().trim());
            double electricityEnd = Double.parseDouble(electricityEndEditText.getText().toString().trim());
            double waterBill = Double.parseDouble(waterBillEditText.getText().toString().trim());
            double dustCharge = Double.parseDouble(dustChargeEditText.getText().toString().trim());

            if (electricityEnd < electricityStart) {
                Toast.makeText(this, "Electricity end should be greater than start.", Toast.LENGTH_SHORT).show();
                return;
            }

            double totalRent = rentCalculatorService.calculateRent(roomRent, electricityStart, electricityEnd, waterBill, dustCharge);

            ContentValues values = new ContentValues();
            values.put("room_rent", roomRent);
            values.put("electricity_start", electricityStart);
            values.put("electricity_end", electricityEnd);
            values.put("water_bill", waterBill);
            values.put("dust_charge", dustCharge);
            values.put("total_rent", totalRent);
            values.put("payment_date", System.currentTimeMillis());

            long newRowId = database.insert("rent_records", null, values);
            Log.d("CalculatorActivity", "Row ID: " + newRowId);

            if (newRowId != -1) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putFloat("electricity_end", (float) electricityEnd);
                editor.apply();

                new AlertDialog.Builder(this)
                        .setTitle("Success")
                        .setMessage("Data saved successfully.")
                        .setPositiveButton("OK", (dialog, which) -> {
                            dialog.dismiss();
                            clearInputs();
                        })
                        .setIcon(R.drawable.success_icon)
                        .show();
            } else {
                showErrorDialog("Failed to save data.");
            }
        } catch (NumberFormatException e) {
            showErrorDialog("Invalid number format. Please check your inputs.");
        }
    }

    private void showErrorDialog(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .setIcon(R.drawable.error_icon)
                .show();
    }

    private void retrieveAndDisplayElectricityStart() {
        float savedElectricityEnd = sharedPreferences.getFloat("electricity_end", 0);
        if (savedElectricityEnd != 0) {
            electricityStartEditText.setText(String.valueOf(savedElectricityEnd));
        }
    }
}
