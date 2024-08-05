# Quizabulary

Quizabulary is a vocabulary-building Android application developed using Kotlin and Jetpack Compose. The app allows users to expand their vocabulary by adding new words, looking up definitions, and testing their knowledge through quizzes. The app supports quick word additions, categorization, and tracking of quiz results.

## Features

- **Quick Add Word**: Easily add new words to your vocabulary list, even if you don’t have time to look up the definition immediately.
- **Word Management**: View, edit, and categorize words in your vocabulary list. Track incomplete words and add definitions later.
- **Definitions and Examples**: Look up definitions, synonyms, antonyms, and example sentences for each word.
- **Quiz Mode**: Test your knowledge with quizzes that pull from your personalized vocabulary list.
- **Categorization**: Organize words into categories for focused learning (e.g., Business Vocabulary, GRE Prep).
- **Progress Tracking**: Review your quiz results and track your progress over time.

## Architecture

Quizabulary is built using a clean architecture approach, with a clear separation of concerns between the UI, data, and domain layers.

- **UI Layer**: Built with Jetpack Compose for a modern, declarative UI.
- **Data Layer**: Utilizes Room for local database storage, with entities and DAOs organized under the `data` package.
- **Dependency Injection**: Powered by Hilt for efficient dependency management.
- **Navigation**: Managed through Jetpack Navigation for a seamless user experience.

### Project Structure

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
