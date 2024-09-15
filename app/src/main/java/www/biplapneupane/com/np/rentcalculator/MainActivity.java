package www.biplapneupane.com.np.rentcalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

import www.biplapneupane.com.np.rentcalculator.CalculatorActivity.CalculatorActivity;
import www.biplapneupane.com.np.rentcalculator.SavedDataActivity.SavedDataActivity;
import www.biplapneupane.com.np.rentcalculator.webView_for_Portfolio.web_view_for_My_Portfolio;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView footerText = findViewById(R.id.footer_text);
        CardView calculateRent = findViewById(R.id.card_calculate_rent);
        CardView viewSavedData = findViewById(R.id.card_view_data);

        //calculate rent
        calculateRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
                startActivity(intent);
            }
        });

//        view saved data
        viewSavedData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SavedDataActivity.class);
                startActivity(intent);
            }
        });


//dynamic value in footer
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        String footerTextString = "© " + currentYear + " Rent Calculator By Biplap Neupane.";
        footerText.setText(footerTextString);


//        redirect the web view
            footerText.setOnClickListener(v -> {
            String url = "https://www.biplapneupane.com.np";
            Intent intent = new Intent(MainActivity.this, web_view_for_My_Portfolio.class);
            intent.putExtra("URL", url);
            startActivity(intent);
        });
    }
    //menu logout
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_button, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//
//        if (id == R.id.navigation_save) {
//            logout();
//            return true;
//        } else {
//            return super.onOptionsItemSelected(item);
//        }
//    }

//    private void logout() {
//        SharedPreferences sharedPreferences = getSharedPreferences("UserSession", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.clear();
//        editor.apply();
//
//        // Navigate back to login screen
//        startActivity(new Intent(MainActivity.this, LoginActivity.class));
//        finish();
//    }
}