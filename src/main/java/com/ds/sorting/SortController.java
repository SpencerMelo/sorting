package com.ds.sorting;

import com.ds.sorting.structure.ArrayList;
import com.ds.sorting.utils.Sort;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SortController implements Initializable {

    @FXML
    private ComboBox<String> csvComboBox;

    @FXML
    private ComboBox<String> algorithmComboBox;

    @FXML
    private Button startButton;

    @FXML
    private Button resetButton;

    @FXML
    private Label statusLabel;

    @FXML
    private AnchorPane barsContainer;

    private ArrayList<Integer> data;
    private ArrayList<Integer> originalData;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        algorithmComboBox
            .getItems()
            .addAll("Bubble Sort", "Insertion Sort", "Quick Sort");

        loadCSVFiles();

        if (!csvComboBox.getItems().isEmpty()) {
            csvComboBox.getSelectionModel().select(0);
            loadSelectedCSV();
        }

        csvComboBox.setOnAction(e -> loadSelectedCSV());
        resetButton.setOnAction(e -> resetData());
        startButton.setOnAction(e -> startSorting());
    }

    private void loadCSVFiles() {
        csvComboBox
            .getItems()
            .addAll(
                "data/aleatorio_100.csv",
                "data/aleatorio_1000.csv",
                "data/aleatorio_10000.csv",
                "data/crescente_100.csv",
                "data/crescente_1000.csv",
                "data/crescente_10000.csv",
                "data/decrescente_100.csv",
                "data/decrescente_1000.csv",
                "data/decrescente_10000.csv"
            );
    }

    private void loadSelectedCSV() {
        String selectedCSV = csvComboBox.getValue();
        if (selectedCSV != null) {
            loadDataFromCSV("/com/ds/sorting/" + selectedCSV);
        }
    }

    private void loadDataFromCSV(String csvPath) {
        try {
            InputStream input = getClass().getResourceAsStream(csvPath);
            if (input != null) {
                data = loadCSV(input);
                originalData = cloneArray(data);
                displayBars();
                String fileName = csvPath.substring(
                    csvPath.lastIndexOf("/") + 1
                );
                statusLabel.setText(
                    "Loaded '" + data.size() + "' items from " + fileName
                );
            }
        } catch (Exception e) {
            statusLabel.setText("Error loading CSV - Generated random data");
        }
    }

    private ArrayList<Integer> loadCSV(InputStream input) throws IOException {
        ArrayList<Integer> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(input)
        );

        String line;
        boolean isFirstLine = true;
        while ((line = reader.readLine()) != null) {
            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }

            String[] values = line.split(",");
            for (String value : values) {
                try {
                    String trimmed = value.trim();
                    if (!trimmed.isEmpty()) {
                        list.add(Integer.parseInt(trimmed));
                    }
                } catch (NumberFormatException e) {
                    // Ignore non-numeric values
                }
            }
        }
        return list;
    }

    private void displayBars() {
        barsContainer.getChildren().clear();

        // Find max value for scaling
        int maxValue = 1;
        for (int i = 0; i < data.size(); i++) {
            Integer value = data.get(i);
            maxValue = Math.max(maxValue, value);
        }

        // Calculate bar dimensions and spacing
        double maxHeight = 400;
        double barWidth = Math.max(5, (800 - 50) / Math.max(data.size(), 1));
        double totalWidth = barWidth * data.size();
        double startX = (800 - totalWidth) / 2; // Center the bars

        for (int i = 0; i < data.size(); i++) {
            Rectangle bar = new Rectangle();
            Integer value = data.get(i);

            // Calculate bar height relative to max value
            double barHeight = (value.doubleValue() / maxValue) * maxHeight;

            bar.setWidth(barWidth);
            bar.setHeight(barHeight);
            bar.setFill(Color.SKYBLUE);
            bar.setStroke(Color.BLACK);

            // Position the bar using layout coordinates
            double x = startX + (i * barWidth);
            double y = maxHeight - barHeight;

            bar.setLayoutX(x);
            bar.setLayoutY(y);

            // Store value as user data for animation
            bar.setUserData(value);

            barsContainer.getChildren().add(bar);
        }
    }

    private void startSorting() {
        String algorithm = algorithmComboBox.getValue();
        if (algorithm == null) {
            statusLabel.setText("Please select an algorithm first");
            return;
        }

        statusLabel.setText("Sorting with '" + algorithm + "'...");

        ArrayList<Integer> workingData = cloneArray(data);

        new Thread(() -> {
            long startTime = System.currentTimeMillis();

            switch (algorithm) {
                case "Bubble Sort":
                    Sort.bubbleSort(workingData);
                    break;
                case "Insertion Sort":
                    Sort.insertionSort(workingData);
                    break;
                case "Quick Sort":
                    Sort.quickSort(workingData);
                    break;
            }

            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            updateBenchmark(csvComboBox.getValue(), algorithm, String.valueOf(duration));

            Platform.runLater(() -> {
                data = workingData;
                displayBars();
                statusLabel.setText(
                    "Sorting complete with '" +
                        algorithm +
                        "' (" +
                        duration +
                        "ms)"
                );
            });
        })
            .start();
    }

    private void updateBenchmark(
        String file,
        String algorithm,
        String duration
    ) {
        try {
            String fileName = file.substring(file.lastIndexOf("/") + 1);
            String row = algorithm + ", " + fileName + ", " + duration + "\n";
            try (FileWriter writer = new FileWriter("result.log", true)) {
                writer.write(row);
            }
        } catch (Exception e) {
            System.err.println("Failed to update results: " + e.getMessage());
        }
    }

    private void resetData() {
        data = cloneArray(originalData);
        displayBars();
        statusLabel.setText("Data reset to original state.");
    }

    private ArrayList<Integer> cloneArray(ArrayList<Integer> original) {
        ArrayList<Integer> copy = new ArrayList<>(original.size());
        for (int i = 0; i < original.size(); i++) {
            copy.add(original.get(i));
        }
        return copy;
    }
}
