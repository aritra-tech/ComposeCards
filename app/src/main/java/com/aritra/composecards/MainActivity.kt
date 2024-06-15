package com.aritra.composecards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.aritra.compose_cards.CardDetails
import com.aritra.composecards.ui.theme.ComposeCardsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCardsTheme {

                val cardNumber by remember { mutableStateOf("") }
                val cardHolderName by remember { mutableStateOf("") }
                val expiryDate by remember { mutableStateOf("") }
                val cardCVV by remember { mutableStateOf("") }

                CardDetails(
                    creditCardNumber = cardNumber,
                    creditCardHolderName = cardHolderName,
                    creditCardExpiryDate = expiryDate,
                    creditCardCVV = cardCVV,
                    onSave = {}
                )
            }
        }
    }
}

