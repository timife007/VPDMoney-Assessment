<p align="center">  
This Fintech App demonstrates modern Android development with Hilt, Coroutines, Flow, Jetpack (Room, ViewModel), and Material Design based on MVVM architecture.

<p align="center">


<img src="https://github.com/timife007/VPDMoney-Assessment/assets/59525449/75407b84-04ea-4195-99f3-dd0a453dc778.png" width="400" height="800"/>

<img src="https://github.com/timife007/VPDMoney-Assessment/assets/59525449/f7a0f0dc-5b42-40fc-83de-85e041ffc80f.png" width="400" height="800"/>

<img src="https://github.com/timife007/VPDMoney-Assessment/assets/59525449/bf1b04d9-d719-4ff3-937c-aaf0a823f744.png" width="400" height="800"/>

<img src="https://github.com/timife007/VPDMoney-Assessment/assets/59525449/ace89afb-617f-4257-b59c-3452797a477e.png" width="400" height="800"/>

<img src="https://github.com/timife007/VPDMoney-Assessment/assets/59525449/59fce6e3-f302-49c1-805b-43815e21ab2c.png" width="400" height="800"/>

<img src="https://github.com/timife007/VPDMoney-Assessment/assets/59525449/faa0fcd9-6c7c-447f-ac3e-aa1ec2062361.png" width="400" height="800"/>

</p>

## Tech stack & Open-source libraries
- Minimum SDK level 24
- [Kotlin](https://kotlinlang.org/) based, LiveData, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- Jetpack
  - Lifecycle: Observe Android lifecycles and handle UI states upon the lifecycle changes.
  - ViewModel: Manages UI-related data holder and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
  - LiveData: An observable to keep data and state monitored and up to date in a lifecycle aware manner
  - DataBinding: Binds UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
  - Room: Constructs Database by providing an abstraction layer over SQLite to allow fluent database access.
  - [Hilt](https://dagger.dev/hilt/): for dependency injection.
  - XML for UI design layout.
- Architecture
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
- [ksp](https://github.com/google/ksp): Kotlin Symbol Processing API.
- [Turbine](https://github.com/cashapp/turbine): A small testing library for kotlinx.coroutines Flow.
- [Material-Components](https://github.com/material-components/material-components-android): Material design components for building ripple animation, and CardView.

## Architecture
This app is based on the MVVM architecture and the Repository pattern, which follows the [Google's official architecture guidance](https://developer.android.com/topic/architecture).

The overall architecture here is composed of three layers; the presentation layer, the domain layer, the data layer according to the Clean Architecture paradigm. Each layer has dedicated components and they have each different responsibilities, as defined below:

### Presentation Layer
The presentation layer consists of the ui components as well as the view models to control the lifecyle of the application.

### Data Layer
The data Layer consists of repositories, which include business logic, such as querying data from the local database. It is implemented as an offline-first source of business logic and follows the [single source of truth](https://en.wikipedia.org/wiki/Single_source_of_truth) principle.<br>

### Domain
The domain layer consists mainly of repostory interfaces and some business logic as followed by the clean architecure principles.

## Prerequisites
To build this project, you require:

- Android Studio jelly fish
- Gradle or AGP 8.4.2
- Kotlin version 1.9.24


