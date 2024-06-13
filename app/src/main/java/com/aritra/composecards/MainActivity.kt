package com.aritra.composecards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import com.aritra.compose_cards.CardDetails
import com.aritra.composecards.ui.theme.ComposeCardsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCardsTheme {

                var cardNumber by remember { mutableStateOf(TextFieldValue()) }
                var cardHolderName by remember { mutableStateOf(TextFieldValue()) }
                var expiryDate by remember { mutableStateOf(TextFieldValue()) }
                var cardCVV by remember { mutableStateOf(TextFieldValue()) }

                CardDetails(
                    cardNumber = cardNumber,
                    onCardNumberChange = { cardNumber = it },
                    cardHolderName = cardHolderName,
                    onCardHolderNameChange = { cardHolderName = it },
                    expiryDate = expiryDate,
                    onExpiryDateChange = { expiryDate = it },
                    cardCVV = cardCVV,
                    onCardCVVChange = { cardCVV = it }
                )
            }
        }
    }
}
