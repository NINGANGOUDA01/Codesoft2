package com.example.quoteapp;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView quoteTextView;
    private Button shareButton;
    private Button nextButton;
    private Button copyButton; // Add the Copy button
    private int currentQuoteIndex = 0;
    private String[] quotes = {
            "This is your inspiring quote of the day",
            "Here is another great quote",
            "A third motivating quote for you",
            "Here's a new quote to ponder",
            "Yet another inspirational quote",
            "Keep going, success is near",
            // Add more quotes here
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteTextView = findViewById(R.id.quoteTextView);
        shareButton = findViewById(R.id.shareButton);
        nextButton = findViewById(R.id.nextButton);
        copyButton = findViewById(R.id.copyButton); // Initialize the Copy button

        displayCurrentQuote();

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quote = quoteTextView.getText().toString();
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, quote);
                startActivity(Intent.createChooser(shareIntent, "Share Quote"));
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentQuoteIndex = (currentQuoteIndex + 1) % quotes.length;
                displayCurrentQuote();
            }
        });

        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quote = quoteTextView.getText().toString();
                copyToClipboard(quote);
            }
        });
    }

    private void displayCurrentQuote() {
        if (currentQuoteIndex < quotes.length) {
            String currentQuote = quotes[currentQuoteIndex];
            quoteTextView.setText(currentQuote);
        }
    }

    private void copyToClipboard(String textToCopy) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboard != null) {
            clipboard.setText(textToCopy);
            Toast.makeText(this, "Quote copied to clipboard", Toast.LENGTH_SHORT).show();
        }
    }
}
