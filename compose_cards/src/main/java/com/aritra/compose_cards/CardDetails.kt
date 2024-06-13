package com.aritra.compose_cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.aritra.compose_cards.component.CardNumberFilter
import com.aritra.compose_cards.component.InputTextField
import com.aritra.compose_cards.ui.CreditCard

@Composable
fun CardDetails(
    cardNumber: TextFieldValue,
    onCardNumberChange: (TextFieldValue) -> Unit,
    cardHolderName: TextFieldValue,
    onCardHolderNameChange: (TextFieldValue) -> Unit,
    expiryDate: TextFieldValue,
    onExpiryDateChange: (TextFieldValue) -> Unit,
    cardCVV: TextFieldValue,
    onCardCVVChange: (TextFieldValue) -> Unit
) {

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
                textFieldValue = cardNumber,
                label = stringResource(R.string.your_card_number),
                keyboardType = KeyboardType.Number,
                onTextChanged = onCardNumberChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                visualTransformation = CardNumberFilter
            )

            InputTextField(
                textFieldValue = cardHolderName,
                label = stringResource(R.string.card_holder_name),
                keyboardType = KeyboardType.Text,
                onTextChanged = onCardHolderNameChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                InputTextField(
                    textFieldValue = expiryDate,
                    label = stringResource(R.string.expiry_date),
                    keyboardType = KeyboardType.Number,
                    onTextChanged = onExpiryDateChange,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )
                InputTextField(
                    textFieldValue = cardCVV,
                    label = stringResource(R.string.cvv),
                    keyboardType = KeyboardType.Number,
                    onTextChanged = onCardCVVChange,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { },
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

