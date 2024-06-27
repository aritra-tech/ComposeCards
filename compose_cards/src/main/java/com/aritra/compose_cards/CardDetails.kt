package com.aritra.compose_cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
    var selectedColor by remember { mutableIntStateOf(R.drawable.violet_bg) }

    Column(modifier = Modifier.fillMaxSize()) {

        CreditCard(
            cardNumber = cardNumber,
            holderName = cardHolderName,
            expiryDate = expiryDate,
            cardCVV = cardCVV,
            selectedBackground = selectedColor
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            BackgroundButton(drawableResId = R.drawable.bg1) { selectedColor = R.drawable.bg1 }
            BackgroundButton(drawableResId = R.drawable.bg3) { selectedColor = R.drawable.bg3 }
            BackgroundButton(drawableResId = R.drawable.red_bg) { selectedColor = R.drawable.red_bg }
            BackgroundButton(drawableResId = R.drawable.circle_bg) { selectedColor = R.drawable.circle_bg }
        }

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
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onBackground)
            ) {
                Text(
                    text = stringResource(id = R.string.save),
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(horizontal = 30.dp, vertical = 8.dp)
                )
            }

        }
    }
}

@Composable
fun BackgroundButton(drawableResId: Int, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .background(Color.Transparent, CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = drawableResId),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
    }
}


