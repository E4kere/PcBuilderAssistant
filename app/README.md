# PC Builder Assistant (Android)

Android application that works as an intelligent assistant for selecting an optimal PC configuration based on user preferences.

## 📌 Overview
Instead of manually choosing hardware components, the user specifies:
- budget
- usage purpose (Gaming / Work / 3D Modeling)
- priority (Cheap / Balanced / Performance)

The system analyzes the workload profile and automatically generates a balanced computer build.

This project represents a rule-based recommendation expert system implemented on Android using Kotlin and Jetpack Compose.

---

## 🧠 Features
- Automatic PC configuration generation
- Budget-based component selection
- Hardware compatibility validation
- Power consumption estimation
- Bottleneck detection
- Explanation of chosen configuration

---

## 🏗 Architecture
The application follows a layered architecture:

- **ui** — user interface (Jetpack Compose)
- **domain** — business logic and recommendation algorithms
- **data** — hardware dataset

---

## ⚙️ Recommendation Logic
The system distributes the budget depending on workload:

| Purpose | GPU | CPU |
|------|------|------|
| Gaming | High priority | Medium |
| Work | Medium | High |
| 3D Modeling | Balanced | Balanced |

Then it:
1. Selects components within budget
2. Checks compatibility
3. Calculates required power
4. Detects bottlenecks
5. Explains the decision

---

## 🛠 Tech Stack
- Kotlin
- Jetpack Compose
- Material 3
- MVVM architecture principles
- Rule-based recommendation algorithm

---

## 🎯 Project Goal
To develop a mobile expert system that helps users without technical knowledge choose a balanced and efficient PC configuration.

---

## 🚀 How to run
1. Clone repository
2. Open in Android Stu
