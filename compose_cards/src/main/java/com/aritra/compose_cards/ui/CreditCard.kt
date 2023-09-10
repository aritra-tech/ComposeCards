package com.aritra.compose_cards.ui

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import com.aritra.compose_cards.R

@Composable
fun CreditCard(
    cardNumber: TextFieldValue,
    holderName: TextFieldValue,
    expiryDate: TextFieldValue,
    cardCVV: TextFieldValue
) {

    var backSwitch by remember { mutableStateOf(false) }
    var cardType by remember { mutableStateOf(Card.None) }
    val length = if (cardNumber.text.length > 16) 16 else cardNumber.text.length
    val maskedNumber =
        remember { "*****************" }.replaceRange(0..length, cardNumber.text.take(16))

    // Switch to back side of the card depending on the cvv number
    if (cardCVV.text.length == 1 && !backSwitch) {
        backSwitch = true
    } else if (cardCVV.text.length == 2) {
        backSwitch = true
    } else if (cardCVV.text.length == 3) {
        backSwitch = false
    }

    // Show card type logo depending on the card number
    cardType = when {
        cardNumber.text.isNotEmpty() -> {
            when (cardNumber.text[0]) { // // Taking the first digits for identifying which card is it
                '4' -> Card.Visa
                '5' -> Card.Mastercard
                '6' -> Card.RuPay
                '3' -> Card.AmericanExpress
                else -> Card.None
            }
        }
        else -> Card.None
    }

    // Set Card Color according to the card type

    val animatedColor = animateColorAsState(
        targetValue =
        if (cardType == Card.Visa) {
            Color(0xFF1C478B)
        } else if (cardType == Card.Mastercard) {
            Color(0xFF1C478B)
        }
        else if (cardType == Card.RuPay) {
            Color(0xFF1C478B)
        }
        else if (cardType == Card.AmericanExpress) {
            Color(0xFF1C478B)
        }
        else {
            MaterialTheme.colors.onBackground
        },
        label = ""
    )
}

enum class Card(
    val title: String,
    @DrawableRes val image: Int
) {
    None("", R.drawable.ic_visa),
    Visa("",R.drawable.ic_visa),
    Mastercard("",R.drawable.ic_mastercard),
    RuPay("",R.drawable.rupay_logo),
    AmericanExpress("",R.drawable.amex_logo)
}