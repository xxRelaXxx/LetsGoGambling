# 🎰 Educational Gambling Application

> **Note:** This is an educational project designed to demonstrate the psychological risks and mechanisms of gambling. No real money is involved.

## 📄 Overview
This application simulates a casino environment to educate users on the consequences of gambling. By replicating visual and psychological triggers, it demonstrates how easily control can be lost.

A core feature is the **Auto-Simulation Mode**, a statistical tool designed to simulate thousands of games instantly, proving mathematically that "the house always wins" in the long run.

**Academic Year:** 2024-2025

## 🛠️ Tech Stack
![Java](https://img.shields.io/badge/Java-JDK_21-orange)
![JavaFX](https://img.shields.io/badge/JavaFX-UI_Toolkit-blue)
![SceneBuilder](https://img.shields.io/badge/Tools-SceneBuilder-lightgrey)
![Photoshop](https://img.shields.io/badge/Design-Adobe_Photoshop-darkblue)

## ✨ Key Features
* **Manual Mode:** First-person gaming experience to test emotional control.
* **Auto-Simulation Mode:** A powerful statistical engine that automates gameplay to generate data on long-term loss ratios.
* **Account System:** Encrypted local data storage for secure user sessions.
* **Advanced Analytics:** Real-time charting of financial trends and win/loss rates.

## 👥 The Team
* **Roman Bernatskyi** - Project Manager, Statistics & Charts, Auto-Simulation Engine, Dice Game
* **Rajiv Subramaniam** - Menu Systems, File/Account Management, Coordination
* **Alessandro Tondo** - UI/UX Graphics (Photoshop), Guess the Number, Documentation
* **Jeremias Arteaga** - UI/UX Graphics (Photoshop), Slot Machine
* **Shaeek Siraj** - Core Logic/Architecture, Coin Flip Game
* **Roberto Pisano** - Roulette, Chat Bot

## 🚀 Installation & Run
Since this project uses **JavaFX**, specific runtime configurations are required.

1.  **Prerequisites:**
    * Install **JDK 21** (or higher).
    * Download the **JavaFX SDK** matching your OS.

2.  **IDE Configuration (General):**
    * If using IntelliJ or Eclipse without a build tool (Maven/Gradle), you must add the JavaFX `lib` folder to your project's **Libraries**.

3.  **VM Options (Crucial):**
    * To run the application, you must add the following **VM Options** to your Run Configuration:
        ```bash
        --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml
        ```
    * *Replace `/path/to/...` with the actual location of your JavaFX SDK.*

4.  **Launch:**
    * Run the `Main` class.
