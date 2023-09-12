<h1 align="center">ComposeCard</h1></br>
<p align="center">
💳 ComposeCards is a beautifully designed payment view library for Credit and Debit Cards. Made using Jetpack Compose 🎉
</p>

[![](https://jitpack.io/v/aritra-tech/ComposeCards.svg)](https://jitpack.io/#aritra-tech/ComposeCards) 
 <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
 <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>

 ## What's included?📜
- 🗂️Automatic card type recognition that supports the following cards:
  - Visa
  - Mastercard
  - RuPay
  - American Express

- 🤩Simple VisualTransformation subclasses for the following use cases:
  - Card number (with custom separators, digit masking)

## Adding the library to your project✨

Add the following to your **root** `build.gradle` file:
```gradle
allprojects {
	repositories {
		maven { url 'https://jitpack.io' }
	}
}
```

Next, add the dependency below to your **module**'s `build.gradle` file:
```gradle
dependencies {
  implementation 'com.github.aritra-tech:ComposeCards:1.0.2'
}
```
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
