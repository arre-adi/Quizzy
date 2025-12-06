# Quizzy - Student Dashboard App

An Android application built with Kotlin and Jetpack Compose that provides students with a comprehensive dashboard to track their quiz activity, performance metrics, and progress over time.

## Test Credentials

- **Email:** `test@quizzy.com`
- **Password:** `12345678`

## Overview

Quizzy is a modern Android app that enables students to log in securely using Firebase Authentication and access a personalized dashboard. The dashboard displays quiz activity, availability status, accuracy rates, and weekly progress tracking. Built with clean MVVM architecture, the app fetches dashboard data from a REST API using Retrofit.

## Features

### Authentication
- Secure login with email and password via Firebase Authentication
- Comprehensive error handling for invalid credentials
- Logout functionality available in Settings

### Home Dashboard
- Personalized greeting with student name and class
- Status cards displaying availability, quiz attempts, and accuracy percentage
- Today's Summary section for quick insights
- Weekly overview showing quiz streak and accuracy trends

### Notifications & Settings
- Color-coded notification list for easy scanning
- Settings screen with options for:
  - Switch Child account
  - Language preferences
  - Logout

## Tech Stack

- **Language:** Kotlin
- **UI Framework:** Jetpack Compose
- **Design System:** Material 3
- **Authentication:** Firebase Authentication
- **Networking:** Retrofit + Gson
- **Async Operations:** Coroutines + Flow
- **Navigation:** Navigation Compose

## Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/a84a/quizzy/
│   │   │   ├── core/
│   │   │   │   ├── theme/
│   │   │   │   └── util/
│   │   │   ├── data/
│   │   │   │   └── auth/              # Firebase login logic
│   │   │   ├── ui/
│   │   │   │   ├── login/             # LoginScreen + ViewModel
│   │   │   │   ├── home/              # Home screen + components
│   │   │   │   ├── notification/      # Notification + settings UI
│   │   │   │   ├── components/        # Shared UI components
│   │   │   │   ├── navigation/        # NavGraph setup
│   │   │   │   └── theme/             # App theming
│   │   │   └── MainActivity.kt
│   │   └── res/
│   │       ├── drawable/
│   │       ├── font/
│   │       └── values/
└── gradle/
    └── wrapper/
```

## Requirements

- Android Studio (latest version)
- JDK 11 or higher
- Minimum SDK: 24
- Target SDK: 34

## Setup Instructions

### 1. Clone the Repository

```bash
git clone YOUR_REPO_URL quizzy
cd quizzy
```

### 2. Firebase Configuration

1. Navigate to [Firebase Console](https://console.firebase.google.com/)
2. Create a new project or select an existing one
3. Register your Android app with package name: `com.a84a.quizzy`
4. Download the `google-services.json` file
5. Place the file in: `app/google-services.json`
6. Enable **Email/Password** authentication method in Firebase Console
7. Add a test user with the following credentials:
   - Email: `test@quizzy.com`
   - Password: `12345678`

### 3. Build and Run

1. Open the project in Android Studio
2. Sync Gradle files
3. Connect an Android device or start an emulator
4. Click Run or press `Shift + F10`

## API Integration

The app fetches dashboard data from given assignment

**API Response includes:**
- Student name
- Class name
- Accuracy percentage
- Streak values
- Summary text

This data is used to populate the Home screen UI dynamically.

## App Flow

1. User enters credentials and logs in
2. Firebase validates authentication
3. Upon successful login, user is redirected to Home screen
4. Dashboard loads and displays data from API
5. User can access Notifications via the bell icon
6. Settings screen provides account management options
7. Logout clears the session and returns to login screen

## Building APK
To generate a debug APK:

1. Go to **Build** → **Build Bundle(s) / APK(s)** → **Build APK(s)**
2. Find the generated APK at: `app/build/outputs/apk/debug/`

For release builds:

```bash
./gradlew assembleRelease
```

