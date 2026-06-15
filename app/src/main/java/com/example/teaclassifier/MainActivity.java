package com.example.teaclassifier;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends Activity {

    // Reference centroid points from your results: [TDS, R]
    private static final double WATAWALA_TDS = 311.24;
    private static final double WATAWALA_R = 37.58;

    private static final double BOP_TDS = 152.04;
    private static final double BOP_R = 50.53;

    private static final double OP1_TDS = 97.91;
    private static final double OP1_R = 61.16;

    private EditText inputTds;
    private EditText inputR;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputTds = findViewById(R.id.inputTds);
        inputR = findViewById(R.id.inputR);
        resultText = findViewById(R.id.resultText);
        Button btnClassify = findViewById(R.id.btnClassify);
        Button btnClear = findViewById(R.id.btnClear);

        btnClassify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classifyTea();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputTds.setText("");
                inputR.setText("");
                resultText.setText("Result will appear here");
            }
        });
    }

    private void classifyTea() {
        String tdsText = inputTds.getText().toString().trim();
        String rText = inputR.getText().toString().trim();

        if (tdsText.isEmpty() || rText.isEmpty()) {
            Toast.makeText(this, "Please enter both TDS and R values", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double unknownTds = Double.parseDouble(tdsText);
            double unknownR = Double.parseDouble(rText);

            double dWatawala = euclideanDistance(unknownTds, unknownR, WATAWALA_TDS, WATAWALA_R);
            double dBop = euclideanDistance(unknownTds, unknownR, BOP_TDS, BOP_R);
            double dOp1 = euclideanDistance(unknownTds, unknownR, OP1_TDS, OP1_R);

            String teaName = "Watawala";
            double nearestDistance = dWatawala;

            if (dBop < nearestDistance) {
                teaName = "B.O.P.";
                nearestDistance = dBop;
            }

            if (dOp1 < nearestDistance) {
                teaName = "O.P.1";
                nearestDistance = dOp1;
            }

            String output = String.format(Locale.US,
                    "Nearest tea: %s\n\n" +
                    "Distances:\n" +
                    "Watawala: %.2f\n" +
                    "B.O.P.: %.2f\n" +
                    "O.P.1: %.2f\n\n" +
                    "Smallest distance = %.2f",
                    teaName, dWatawala, dBop, dOp1, nearestDistance);

            resultText.setText(output);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numbers only", Toast.LENGTH_SHORT).show();
        }
    }

    private double euclideanDistance(double unknownTds, double unknownR, double refTds, double refR) {
        return Math.sqrt(Math.pow(unknownTds - refTds, 2) + Math.pow(unknownR - refR, 2));
    }
}
