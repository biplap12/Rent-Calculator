package www.biplapneupane.com.np.rentcalculator.SavedDataActivity;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import www.biplapneupane.com.np.rentcalculator.R;

public class SavedDataActivity extends AppCompatActivity {

    private DataAdapter dataAdapter;
    private List<RentRecord> rentRecordList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_saved_data);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter
        dataAdapter = new DataAdapter(rentRecordList);
        recyclerView.setAdapter(dataAdapter);

        // Load data from SQLite Database
        loadDataFromDatabase();
    }

    private void loadDataFromDatabase() {
        // Load data from SQLite database and update the adapter
        RentRecordDatabase db = new RentRecordDatabase(this);
        rentRecordList = db.getAllRentRecords();

        if (rentRecordList == null || rentRecordList.isEmpty()) {
            // Show toast message if no data is found
            Toast.makeText(this, "No Data Found!!", Toast.LENGTH_LONG).show();
        }

        dataAdapter.updateData(rentRecordList);
    }
}
