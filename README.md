# DiscountCalculator

A simple Android application written in **Java** and **XML** that calculates the final price and total savings after applying a percentage discount. This repository demonstrates local code quality checks, automated testing, and a complete **GitHub Actions** CI workflow.

## Features

- Enter an original price and a discount percentage (`0` to `100`).
- Validates empty fields, non-numeric values, and out-of-range inputs.
- Displays the formatted final price and saved amount.

## Project Structure

- `app/src/main/java/com/example/discountcalculator/DiscountHelper.java` — Pure Java discount calculation and input validation logic.
- `app/src/main/java/com/example/discountcalculator/MainActivity.java` — Main UI controller connecting the XML layout with `DiscountHelper`.
- `app/src/main/res/layout/activity_main.xml` — Material 3 XML layout with inputs, button, and result display.
- `app/src/test/java/com/example/discountcalculator/DiscountHelperTest.java` — Local JUnit 4 unit tests covering valid calculations and edge/error cases.
- `app/src/androidTest/java/com/example/discountcalculator/MainActivityTest.java` — Espresso instrumented UI tests verifying screen elements and discount calculation output.
- `.github/workflows/ci.yml` — GitHub Actions CI pipeline.

## Code Quality & Testing

### 1. Build the Debug APK
```bash
./gradlew assembleDebug
```

### 2. Android Lint (Code Quality)
Runs built-in Android Lint checks across Java source files, XML layouts, and resources:
```bash
./gradlew lintDebug
```
The generated report is saved at `app/build/reports/lint-results-debug.html`.

### 3. Unit Tests (JUnit)
Runs fast local JVM unit tests for `DiscountHelper`:
```bash
./gradlew testDebugUnitTest
```

### 4. Instrumented UI Tests (Espresso)
Requires a connected Android device or running emulator:
```bash
./gradlew connectedDebugAndroidTest
```

## GitHub Actions Workflow

The CI workflow in [`.github/workflows/ci.yml`](.github/workflows/ci.yml) triggers automatically on `push` and `pull_request` events targeting `main` or `master` (and manually via `workflow_dispatch`).

It executes two jobs:
1. **`build_and_test`** (`Build, Lint & Unit Tests`):
   - Sets up JDK 17 (for Android SDK `sdkmanager`), Android SDK, and Gradle caching (Gradle automatically provisions JDK 25 for the daemon via `gradle-daemon-jvm.properties`).
   - Builds the debug APK (`assembleDebug`).
   - Runs Android Lint (`lintDebug`).
   - Runs JUnit unit tests (`testDebugUnitTest`).
   - Uploads Lint and unit test reports as build artifacts.
2. **`instrumented_tests`** (`Instrumented UI Tests`):
   - Runs after `build_and_test` succeeds.
   - Enables KVM hardware acceleration and launches an Android API 30 emulator using `reactivecircus/android-emulator-runner@v2`.
   - Executes Espresso tests (`connectedDebugAndroidTest`) and uploads the test report artifact.

## Requirements

- **JDK 25** (configured via `gradle/gradle-daemon-jvm.properties`)
- **Android Studio** / **Android SDK** (`compileSdk = 37`, `minSdk = 24`)
