package www.biplapneupane.com.np.rentcalculator.webView_for_Portfolio;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import www.biplapneupane.com.np.rentcalculator.R;

public class web_view_for_My_Portfolio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_web_view_for_my_portfolio);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.web_view_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        WebView webView = findViewById(R.id.web_view);
        webView.setWebViewClient(new WebViewClient()); // Ensures links open in the WebView
        webView.getSettings().setJavaScriptEnabled(true); // Enable JavaScript if needed

        // Get the URL from the intent
        String url = getIntent().getStringExtra("URL");
        if (url != null) {
            webView.loadUrl(url);
        }

//        // Optional: Handle back button to navigate back in WebView history
//        findViewById(R.id.back_button).setOnClickListener(v -> {
//            if (webView.canGoBack()) {
//                webView.goBack();
//            } else {
//                finish(); // Close the activity if no back history
//            }
//        });
    }
}