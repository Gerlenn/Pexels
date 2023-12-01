# Pexels
An application that allows users to discover, save, and share images of their interests in various categories such as fashion, travel, art, and more. It has a user-friendly interface that displays images in a grid layout with infinite scrolling and smooth transitions. Also there are such features as search, bookmark and share to enhance user engagement and interaction [Pexels API](https://www.pexels.com/). 
<div align="left">
    <img src="./app/src/main/ic_launcher-playstore.png" width="64"/>
</div>

## Technologies
- Android SDK
- Kotlin
- Jetpack Compose
- Clean Architecture
- MVVM
- Coroutines
- Flow
- Dagger2
- Retrofit
- okHttp
- GSON
- Encrypted shared preferences
- ViewModel
- Work manager
- Paging3

## Android development
Pexels is an app that attempts to use the latest libraries and tools. As a summary:

 * Entirely written in [Kotlin](https://kotlinlang.org/).
 * UI completely written in [Jetpack Compose](https://developer.android.com/jetpack/compose).
 * Uses [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines/coroutines-guide.html) throughout.
 * [Dagger2](https://dagger.dev/dev-guide/) for dependency injection
 * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) encapsulates related business logic
 * [Retrofit](http://square.github.io/retrofit/) + [Gson](https://github.com/google/gson) + [OkHttp](https://square.github.io/okhttp/) - RESTful API and networking client.
 * [Flow](https://developer.android.com/kotlin/flow) stream of value that returns from suspend function.
 * [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager) for working in the background 

## Features
- Viewing popular photos
- Search for the desired photo category
- Adding a photo you like to bookmarks using encrypted SharedPreferences
- Downloading photos
- Cache photos for offline viewing
- Zoom in photos with two fingers
- Onboarding with three screens using Lottie animation
- Handling Internet access errors and others
- Light/Dark theme
- Page-by-page uploading of photos
- Clearing the image cache using Work Manager

## Screenshots
<div align="center">
  <img src="https://github.com/Gerlenn/Pexels/assets/114432313/cdd3cf80-05c9-461b-b7ac-651ec09e7d63" width="200">  
  <img src="https://github.com/Gerlenn/Pexels/assets/114432313/59ca1840-3784-48a9-98f0-98c41f7188f0" width="200">  
  <img src="https://github.com/Gerlenn/Pexels/assets/114432313/80475f44-f781-4744-9ba9-2e4a6ef2544e" width="200">  
  <img src="https://github.com/Gerlenn/Pexels/assets/114432313/ba4db567-5a48-4268-aac5-bc212f3eef23" width="200"> 
  <img src="https://github.com/Gerlenn/Pexels/assets/114432313/181b5ce8-e5a5-477e-b9c5-c0fdeee802f2" width="200"> 
  <img src="https://github.com/Gerlenn/Pexels/assets/114432313/96e9ddb9-6b58-4f01-ae06-e527d112f227" width="200">
  <img src="https://github.com/Gerlenn/Pexels/assets/114432313/d0124555-647c-4c72-a977-072bb6cfbcd0" width="200">
  <img src="https://github.com/Gerlenn/Pexels/assets/114432313/5f61a30d-139f-44f6-ae96-4d32e78bf805" width="200">
</div>

#### How to run the app
The app is targeted to Android. You will need Pexels API key to get this app up and running. You can get the api key from here https://www.pexels.com/api/. Once you get the api key, place it in your local.properties and you should be good to run the app on your Android studio.
