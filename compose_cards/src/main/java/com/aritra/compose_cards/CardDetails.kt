package com.aritra.compose_cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.aritra.compose_cards.component.CardNumberFilter
import com.aritra.compose_cards.component.InputTextField
import com.aritra.compose_cards.ui.CreditCard

@Composable
fun CardDetails(
    creditCardNumber: String,
    creditCardHolderName: String,
    creditCardExpiryDate: String,
    creditCardCVV: String,
    onSave: () -> Unit
) {

    var cardNumber by remember { mutableStateOf(creditCardNumber) }
    var cardHolderName by remember { mutableStateOf(creditCardHolderName) }
    var expiryDate by remember { mutableStateOf(creditCardExpiryDate) }
    var cardCVV by remember { mutableStateOf(creditCardCVV) }

    Column(modifier = Modifier.fillMaxSize()) {

        CreditCard(
            cardNumber = cardNumber,
            holderName = cardHolderName,
            expiryDate = expiryDate,
            cardCVV = cardCVV
        )

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            InputTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                textFieldValue = cardNumber,
                label = stringResource(R.string.your_card_number),
                keyboardType = KeyboardType.Number,
                onTextChanged = {
                    cardNumber = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                visualTransformation = CardNumberFilter
            )

            InputTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                textFieldValue = cardHolderName,
                label = stringResource(R.string.card_holder_name),
                keyboardType = KeyboardType.Text,
                onTextChanged = {
                    cardHolderName = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                InputTextField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    textFieldValue = expiryDate,
                    label = stringResource(R.string.expiry_date),
                    keyboardType = KeyboardType.Number,
                    onTextChanged = {
                        expiryDate = it
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                )
                InputTextField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    textFieldValue = cardCVV,
                    label = stringResource(R.string.cvv),
                    keyboardType = KeyboardType.Number,
                    onTextChanged = {
                        cardCVV = it
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { onSave() },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colors.onBackground)
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

