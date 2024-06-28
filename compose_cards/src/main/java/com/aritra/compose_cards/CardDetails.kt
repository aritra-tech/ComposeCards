/**
 * Copyright 2023 aritra-tech
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Please contact Aritra Das, if you need additional information or have any
 * questions or directly reach out to me via mail: aritrarick2002@gmail.com
 *
 * @author Aritra Das
 *
 */


package com.aritra.compose_cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.aritra.compose_cards.component.BackgroundCard
import com.aritra.compose_cards.component.CardNumberFilter
import com.aritra.compose_cards.component.InputTextField
import com.aritra.compose_cards.ui.CreditCard

/**
 * Renders the CardDetails composable, displaying and handling credit card details input.
 *
 * @param creditCardNumber The card number input by the user.
 * @param creditCardHolderName The card holder's name input by the user.
 * @param creditCardExpiryDate The card's expiry date input by the user.
 * @param creditCardCVV The card's CVV input by the user.
 * @param onSave Callback function to handle save action.
 */


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
            BackgroundCard(drawableResId = R.drawable.bg4) { selectedColor = R.drawable.bg4 }
            BackgroundCard(drawableResId = R.drawable.bg3) { selectedColor = R.drawable.bg3 }
            BackgroundCard(drawableResId = R.drawable.red_bg) { selectedColor = R.drawable.red_bg }
            BackgroundCard(drawableResId = R.drawable.circle_bg) { selectedColor = R.drawable.circle_bg }
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




