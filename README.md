# Sorting Visualizer

JavaFX application that visualizes sorting algorithms with animated bar representations.

## Technologies Used

- **Java 25**
- **JavaFX 21.0.6** - GUI framework
- **Maven** - Build and dependency management
- **Custom ArrayList Implementation** - Custom data structure for sorting

## Requirements

Before running this application, ensure you have:

- **Java Development Kit (JDK) 25** or higher
- **Maven** 3.6 or higher
- **macOS/Linux/Windows** operating system

### Verify Java Installation

```bash
java -version
javac -version
```

### Verify Maven Installation

```bash
mvn -version
```

## Project Structure

```
sorting/
├── src/
│   ├── main/
│   │   ├── java/com/ds/sorting/
│   │   │   ├── SortApplication.java      # Main application entry point
│   │   │   ├── SortController.java       # FXML controller for UI logic
│   │   │   ├── structure/
│   │   │   │   └── ArrayList.java        # Custom ArrayList implementation
│   │   │   └── utils/
│   │   │       └── Sort.java             # Sorting algorithms (Bubble, Insertion, Quick)
│   │   └── resources/com/ds/sorting/
│   │       ├── app-view.fxml             # JavaFX FXML layout
│   │       └── data/                     # CSV datasets directory
│   │           ├── aleatorio_100.csv     # Random data (100 values)
│   │           ├── aleatorio_1000.csv    # Random data (1000 values)
│   │           ├── aleatorio_10000.csv   # Random data (10000 values)
│   │           ├── crescente_100.csv     # Ascending data (100 values)
│   │           ├── crescente_1000.csv    # Ascending data (1000 values)
│   │           ├── crescente_10000.csv   # Ascending data (10000 values)
│   │           ├── decrescente_100.csv   # Descending data (100 values)
│   │           ├── decrescente_1000.csv  # Descending data (1000 values)
│   │           └── decrescente_10000.csv # Descending data (10000 values)
│   └── test/                             # Test files
├── target/                               # Compiled files (generated)
└── pom.xml                              # Maven configuration
```

## How to Run

1. **Navigate to the project directory:**
   ```bash
   cd sorting
   ```

2. **Clean and compile the project:**
   ```bash
   mvn clean compile
   ```

3. **Run the application:**
   ```bash
   mvn javafx:run
   ```

## Usage

1. **Select a CSV File** - Choose from 9 datasets (random, ascending, or descending; 100, 1000, or 10000 values)
2. **Select an Algorithm** - Choose from: Bubble Sort, Insertion Sort, or Quick Sort
3. **Click "Sort"** - The sorting algorithm will execute and visualize the process
4. **Click "Reset"** - Restore the data to its original state from the CSV file
5. **Switch CSV Files** - Change datasets to see different sorting behaviors

## Features

- ✅ **Multiple CSV Datasets** - 9 different sample datasets (random, ascending, descending)
- ✅ **Three Sorting Algorithms** - Bubble, Insertion, and Quick Sort
- ✅ **Visual Representation** - Bars represent array values, scaled proportionally
- ✅ **Background Sorting** - Non-blocking UI during sorting operations
- ✅ **Reset Functionality** - Restore to original data state instantly
- ✅ **Status Updates** - Real-time feedback on operations

## CSV File Format

CSV files should contain:
- First line: Header (e.g., "Value")
- Subsequent lines: Integer values, one per line

Example:
```csv
Value
52341
23512
91234
12345
```

## Notes

- The application automatically loads the first CSV file on startup
- Bar heights are scaled relative to the maximum value in each dataset
- Sorting happens in a background thread to keep the UI responsive
- All sample datasets are located in `src/main/resources/com/ds/sorting/data/`

## UI screenshot
![ui-screenshot.png](src/main/resources/com/ds/ui-screenshot.png)
