package com.example.discountcalculator;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText etPrice;
    private TextInputEditText etDiscount;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_main);
        android.view.View rootView = findViewById(R.id.main);
        final int baseLeft = rootView.getPaddingLeft();
        final int baseTop = rootView.getPaddingTop();
        final int baseRight = rootView.getPaddingRight();
        final int baseBottom = rootView.getPaddingBottom();
        ViewCompat.setOnApplyWindowInsetsListener(rootView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(
                    baseLeft + systemBars.left,
                    baseTop + systemBars.top,
                    baseRight + systemBars.right,
                    baseBottom + systemBars.bottom
            );
            return insets;
        });

        etPrice = findViewById(R.id.etPrice);
        etDiscount = findViewById(R.id.etDiscount);
        Button btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);

        btnCalculate.setOnClickListener(v -> calculateAndDisplay());
    }

    private void calculateAndDisplay() {
        String priceText = etPrice.getText() != null ? etPrice.getText().toString().trim() : "";
        String discountText = etDiscount.getText() != null ? etDiscount.getText().toString().trim() : "";

        if (TextUtils.isEmpty(priceText) || TextUtils.isEmpty(discountText)) {
            tvResult.setText(R.string.error_empty_fields);
            return;
        }

        try {
            double price = Double.parseDouble(priceText);
            double discount = Double.parseDouble(discountText);
            double finalPrice = DiscountHelper.calculateFinalPrice(price, discount);
            double savings = DiscountHelper.calculateSavings(price, discount);

            String formattedFinal = String.format(Locale.US, "%.2f", finalPrice);
            String formattedSavings = String.format(Locale.US, "%.2f", savings);
            tvResult.setText(getString(R.string.result_format, formattedFinal, formattedSavings));
        } catch (NumberFormatException e) {
            tvResult.setText(R.string.error_invalid_number);
        } catch (IllegalArgumentException e) {
            tvResult.setText(R.string.error_invalid_range);
        }
    }
}
