# Foodie

List the top 100 restaurants nearby. This demo app is to demonstrate how to strcuture Android app under clean architecture principle.

## App architecture

![architecture](https://www.evernote.com/l/ABDotxKR9kNNCqcQEfML-BBSuI_3s1RvPjQB/image.png)

## Tech stack
- Programming language: Kotlin
- Architecture pattern: Model-View-Viewmodel(MVVM)
- Asynchronous Programming: RxJava2, Kotlin Coroutines
- Dependency Injeciton: Dagger2
- Network request: Retrofit
- Android Architecture components: LiveData, Paging library

## Setup

1. Add your Zomato API key in your gradle.properties
```
zomato_api_key="<ZOMOTO_API_KEY>"
```
PS: you can also use my dev KEY for temopratry testing `4c1159fee21201ebbced7f642f3530b5`
