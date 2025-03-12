# 🌟 Finnsathii

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-0095D5?style=for-the-badge&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpack-compose&logoColor=white)
![Firebase](https://img.shields.io/badge/Firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=black)

> A modern Android application built with Jetpack Compose and Material Design 3, featuring seamless Firebase integration and a beautiful, responsive UI.

<div align="center">
  <img src="screenshots/app_banner.png" alt="Finnsathii Banner" width="800px"/>
</div>

## ✨ Features

- 🎨 **Modern UI/UX** - Built with Jetpack Compose and Material Design 3
- 🔐 **Authentication** - Secure user authentication with Firebase
- 🌓 **Dark/Light Theme** - Dynamic theming with Material You support
- 🚀 **Real-time Updates** - Powered by Firebase Realtime Database
- 📱 **Responsive Design** - Perfect adaptation across all screen sizes
- 🔄 **State Management** - Clean architecture with ViewModels and Kotlin Flow
- 🎯 **Dependency Injection** - Powered by Hilt for clean and testable code
- 🔔 **Push Notifications** - Firebase Cloud Messaging integration

## 🛠️ Tech Stack

### Frontend
```kotlin
implementation {
    // UI Framework
    androidx.compose.material3
    androidx.compose.ui
    androidx.compose.runtime
    
    // Architecture Components
    androidx.lifecycle
    androidx.navigation
    
    // Dependency Injection
    dagger.hilt.android
    
    // Image Loading
    coil-compose
}
```

### Backend
```kotlin
implementation {
    // Firebase
    firebase-auth-ktx
    firebase-firestore-ktx
    firebase-messaging-ktx
    
    // Networking
    retrofit2
    okhttp3
    
    // Async
    kotlinx.coroutines
}
```

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog or later
- JDK 11+
- Android SDK 24+
- Kotlin 1.9+
- Google Services JSON file from Firebase Console

### Installation

1. Clone the repository
```bash
git clone https://github.com/yourusername/finnsathii.git
```

2. Add your `google-services.json` to the app directory

3. Build and run
```bash
./gradlew build
```

## 📱 Architecture

The app follows Clean Architecture principles with MVVM pattern:

```
finnsathii/
├── data/
│   ├── remote/
│   │   └── FirebaseDataSource
│   ├── repository/
│   │   └── AuthRepository
│   └── models/
├── di/
│   ├── AppModule
│   ├── FirebaseModule
│   └── NetworkModule
├── ui/
│   ├── components/
│   ├── screens/
│   ├── theme/
│   └── viewmodels/
└── utils/
```

## 🎨 UI Components

### Material Design 3 Implementation
- Dynamic Color Support
- Elegant Typography
- Custom Components
  - FinnSathiiButton
  - FinnSathiiTopBar
  - Custom Input Fields
- Smooth Animations
- Responsive Layouts

### Theme Customization
```kotlin
val LightColorScheme = lightColorScheme(
    primary = Primary,
    secondary = Secondary,
    background = Background,
    surface = Surface
    // ... more color definitions
)
```

## 🔒 Security Features

- Firebase Authentication
- Secure Data Storage
- Network Security
- Input Validation
- ProGuard Rules
- SSL Pinning

## 🎯 Future Roadmap

- [ ] Biometric Authentication
- [ ] Offline Support
- [ ] Multi-language Support
- [ ] Analytics Dashboard
- [ ] Advanced Search Features
- [ ] Social Media Integration
- [ ] Cloud Backup

## 🤝 Contributing

We welcome contributions! Please follow these steps:

1. Fork the repository
2. Create your feature branch
```bash
git checkout -b feature/amazing-feature
```
3. Commit your changes
```bash
git commit -m 'Add amazing feature'
```
4. Push to the branch
```bash
git push origin feature/amazing-feature
```
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Material Design 3](https://m3.material.io/)
- [Firebase](https://firebase.google.com/)
- [Hilt](https://dagger.dev/hilt/)

---

<div align="center">
  <p>Made with ❤️ by Rohit</p>
  <p>
    <a href="https://github.com/ysauo72">GitHub</a> •
    <a href="https://twitter.com/finnsathii">Twitter</a> •
    <a href="https://linkedin.com/company/rohit-singh-47b9a0302/">LinkedIn</a>
  </p>
</div> 
