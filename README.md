<h1 align="center">ComposeCard</h1></br>

</h1>
<p align="center">
 <img alt="material" src="https://custom-icon-badges.demolab.com/badge/material%20you-palegreen?style=for-the-badge&logoColor=black&logo=material-you"/></a>
  <img alt="API" src="https://img.shields.io/badge/Api%2021+-50f270?logo=android&logoColor=black&style=for-the-badge"/></a>
  <img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-a503fc?logo=kotlin&logoColor=white&style=for-the-badge"/></a>
  <img alt="Jetpack Compose" src="https://img.shields.io/static/v1?style=for-the-badge&message=Jetpack+Compose&color=4285F4&logo=Jetpack+Compose&logoColor=FFFFFF&label="/></a>
    <a href="https://github.com/aritra-tech/ComposeCards/stargazers"><img src="https://img.shields.io/github/stars/aritra-tech/ComposeCards?color=ffff00&style=for-the-badge"/>
  <a href="https://hits.sh/github.com/aritra-tech/ComposeCards/"><img alt="Hits" src="https://hits.sh/github.com/aritra-tech/ComposeCards.svg?style=for-the-badge&label=Views&extraCount=10&color=54856b"/></a>
  <a href="https://github.com/aritra-tech/ComposeCards/releases/latest"><img src="https://img.shields.io/github/v/release/aritra-tech/ComposeCards?color=purple&include_prereleases&logo=github&style=for-the-badge"/>
</p>
	  
<p align="center"> 💳 ComposeCards is a beautifully designed payment view library for Credit and Debit Cards. Made using Jetpack Compose 🎉. It allows you to easily integrate Payment View with much smooth UI and animations. </p>

<br>

<p align="center">
<img src="assets/Untitled.gif" width="280"/>
</p>


 ## What's included?📜
- 🗂️Automatic card type recognition that supports the following cards:
  - Visa
  - Mastercard
  - RuPay
  - American Express
  - Maestro
  - Diners Club

- 🤩Simple VisualTransformation subclasses for the following use cases:
  - Card number (with custom separators, digit masking)

## Adding the library to your project✨

[![](https://jitpack.io/v/aritra-tech/ComposeCards.svg)](https://jitpack.io/#aritra-tech/ComposeCards)

Add the following to your **root** `build.gradle` file:
```gradle
allprojects {
	repositories {
		maven { url 'https://jitpack.io' }
	}
}
```

Lastly, add the following dependency to your app's `build.gradle.kts` (Kotlin) or `build.gradle` (Groovy) file:

<details>
<summary>Kotlin (KTS)</summary>
<br>

```kotlin
dependencies {
    implementation("com.github.aritra-tech:ComposeCards:$currentVersion")
}
```
</details>

<details>
<summary>Groovy</summary>
<br>

```kotlin
dependencies {
    implementation 'com.github.aritra-tech:ComposeCards:$currentVersion'
}
```
</details>

## Usage📓
You can use the `CardDetails` by simply passing this to the screen you want to use. For example:
```Kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCardsTheme {
                CardDetails()
            }
        }
    }
}
```
## Reporting Issues and Requesting Features✨
If you encounter any issues or have feature requests, please create a new [issue](https://github.com/aritra-tech/ComposeCards/issues) in this repository.

## Supporting ComposeCards :heart:
Support it by joining __[stargazers](https://github.com/aritra-tech/ComposeCards/stargazers)__ for this repository. :star: <br>
Also __[follow](https://github.com/aritra-tech)__ me for my next creations! 🤩

## Star History ⭐ 

[![Star History Chart](https://api.star-history.com/svg?repos=aritra-tech/ComposeCards&type=Date)](https://star-history.com/#aritra-tech/ComposeCards&Date)

## License

```
Copyright 2023 aritra-tech

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
