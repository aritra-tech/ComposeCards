package com.aritra.composecards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.aritra.compose_cards.CardDetails
import com.aritra.composecards.ui.theme.ComposeCardsTheme

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
@Preview(showBackground = true)
@Composable
fun Card() {
    CardDetails()
}