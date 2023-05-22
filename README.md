## Drink App

This is an Android application written in Kotlin that displays a list of drinks and provides detailed information about each drink. The project follows the MVVM (Model-View-ViewModel) architectural pattern with clean architecture principles. It incorporates various libraries and frameworks, including Kotlin coroutines, Gradle conventional plugins, version catalog, Kotlin serialization for JSON parsing, Retrofit for network calls, Koin for dependency injection, and Jetpack Compose as the view framework. The project is modularized, allowing for independent feature development and reuse.

## Features

The Drink App offers the following features:

1. Display a list of drinks: The application presents a list of drinks retrieved from a remote server.
2. Drink detail page: Users can view detailed information about each drink by selecting an item from the list.
3. Modularity: The project is divided into multiple modules, enabling isolation and reusability of features.
4. Composition root: The composition root module is responsible for orchestrating all other modules. It implements providers, delegates, and dependencies required by each feature module.
5. API integration: The composition root module handles API calls, response models, and mappers, allowing feature modules to operate independently and potentially be utilized in other applications.
6. Navigation: All navigation within the application is managed through delegates declared in the composition root. Feature modules cannot have dependencies on other feature modules. In case of shared models, a separate module can be created to accommodate them, or a module should be declared for each feature, which the composition root can parse.
7. Dependencies

## The project relies on the following dependencies:

- Kotlin coroutines: A library for managing asynchronous programming and concurrency.
- Gradle conventional plugins: Plugins that enforce conventional project structure and best practices.
- Version catalog: A mechanism for centralized version management of dependencies.
- Kotlin serialization: A library for parsing JSON responses.
- Retrofit: A type-safe HTTP client for making API calls.
- Koin: A lightweight dependency injection framework for Kotlin.
- Jetpack Compose: A modern UI toolkit for building native Android interfaces.