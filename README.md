# Multi Lingual Quiz Application

## Short Demo
<p align="center">
Link to demo video: https://youtu.be/gNZMxQBELYs
</p>

## Description
This Project uses fragments to display quiz questions in English/Polish with changing backgrounds, has a ProgressBar to display user's current quiz progress, and has the option to change the number of questions in the quiz using a DialogFragment, saving user's quiz completion attempts to device's file system.

## Class
[Question.java](https://github.com/Dwrulesalot/Quiz-Application-Multi-Lingual/blob/main/app/src/main/java/com/example/quiz_application_multi_lingual/Question.java) holds all data related to the question, including textID(holds the text Resource integer), answer(boolean with the correct question answer), and colorID which holds an integer for the color resource.

## Activity
This application all runs on one screen, the [MainActivity.java](https://github.com/Dwrulesalot/Quiz-Application-Multi-Lingual/blob/main/app/src/main/java/com/example/quiz_application_multi_lingual/MainActivity.java) which saves temporary progress(ie on screen rotate/onDestroy being called) to [MyApp.java](https://github.com/Dwrulesalot/Quiz-Application-Multi-Lingual/blob/main/app/src/main/java/com/example/quiz_application_multi_lingual/MyApp.java). [MainActivity.java](https://github.com/Dwrulesalot/Quiz-Application-Multi-Lingual/blob/main/app/src/main/java/com/example/quiz_application_multi_lingual/MainActivity.java) handles all functionality for the menu, buttons and progress bar. It uses the [QuestionBankManager.java](https://github.com/Dwrulesalot/Quiz-Application-Multi-Lingual/blob/main/app/src/main/java/com/example/quiz_application_multi_lingual/QuestionBankManager.java) Service to handle our [fragment question's](https://github.com/Dwrulesalot/Quiz-Application-Multi-Lingual/blob/main/app/src/main/java/com/example/quiz_application_multi_lingual/FragmentQuestion.java) update to a new question. In our menu the "change number of max questions" is handled by our [DialogFragment](https://github.com/Dwrulesalot/Quiz-Application-Multi-Lingual/blob/main/app/src/main/java/com/example/quiz_application_multi_lingual/ChangeMaxQuestionsDialogFragment.java) which uses listeners to relay info to our activity and we can give an error if needed or change the max number of questions and dismiss the fragment.

## Fragments
[FragmentQuestion.java](https://github.com/Dwrulesalot/Quiz-Application-Multi-Lingual/blob/main/app/src/main/java/com/example/quiz_application_multi_lingual/FragmentQuestion.java) handles the fragment holding the current question, has a textID and a colorID which is set on a new instance of the fragment. [ChangeMaxQuestionsDialogFragment.java](https://github.com/Dwrulesalot/Quiz-Application-Multi-Lingual/blob/main/app/src/main/java/com/example/quiz_application_multi_lingual/ChangeMaxQuestionsDialogFragment.java) handles the dialog fragment that pops up when selecting change max questions from the menu, it contains two listeners to return info to the MianActivity depending on the user's input/button click.

## Services
This application's file storage is managed by [FileStorageManager.java](https://github.com/Dwrulesalot/Quiz-Application-Multi-Lingual/blob/main/app/src/main/java/com/example/quiz_application_multi_lingual/FileStorageManager.java), where it reads and writes to the file "Average.txt" to get, update and clear the user's quiz answer. [QuestionBankManager.java](https://github.com/Dwrulesalot/Quiz-Application-Multi-Lingual/blob/main/app/src/main/java/com/example/quiz_application_multi_lingual/QuestionBankManager.java) manages all of the questions with their correct responses, has a default constructor and a constructor taking the max questions in a quiz. This service also contains the functionality to check if an answer is correct, to switch the current question variable to a new question, and to reset the question bank array.
