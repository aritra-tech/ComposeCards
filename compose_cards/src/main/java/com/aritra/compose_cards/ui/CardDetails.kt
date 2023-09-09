package com.aritra.compose_cards.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.aritra.compose_cards.R
import com.aritra.compose_cards.component.InputTextField

@Composable
fun CardDetails() {
    var cardNumber by remember { mutableStateOf(TextFieldValue()) }
    var cardHolderName by remember { mutableStateOf(TextFieldValue()) }
    var expiryDate by remember { mutableStateOf(TextFieldValue()) }
    var cardCVV by remember { mutableStateOf(TextFieldValue()) }

    Column (modifier = Modifier.fillMaxSize()) {

        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            item {
                InputTextField(
                    textFieldValue = cardNumber,
                    label = stringResource(R.string.your_card_number),
                    onTextChanged = { cardNumber = it}
                )
            }

            item {
                InputTextField(
                    textFieldValue = cardHolderName,
                    label = "Card Holder Name",
                    onTextChanged = { cardHolderName = it }
                )
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    InputTextField(
                        textFieldValue = expiryDate,
                        label = stringResource(R.string.expiry_date),
                        keyboardType = KeyboardType.Number,
                        onTextChanged = { expiryDate = it },
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                    )
                    InputTextField(
                        textFieldValue = cardCVV,
                        label = stringResource(R.string.cvv),
                        keyboardType = KeyboardType.Number,
                        onTextChanged = { cardCVV = it },
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp)
                    )
                }
            }

            item {
                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.save),
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier.padding(horizontal = 30.dp, vertical = 8.dp)
                    )
                }
            }
        }
    }
}

