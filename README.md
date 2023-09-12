<h1 align="center">ComposeCard</h1></br>
<p align="center">
💳 ComposeCards is a beautifully designed payment view library for Credit and Debit Cards. Made using Jetpack Compose 🎉
</p>

<p align="center">
<img src="assets/Untitled.gif" width="280"/>
</p>


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
## Reporting Issues and Requesting Features✨
If you encounter any issues or have feature requests, please create a new [issue](https://github.com/aritra-tech/ComposeCards/issues) in this repository.

## Supporting ComposeCards :heart:
Support it by joining __[stargazers](https://github.com/aritra-tech/ComposeCards/stargazers)__ for this repository. :star: <br>
Also __[follow](https://github.com/aritra-tech)__ me for my next creations! 🤩

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
