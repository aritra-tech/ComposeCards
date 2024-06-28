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


package com.aritra.compose_cards.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun InputTextField(
    textFieldValue: String,
    label: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.Black),
    keyboardType: KeyboardType = KeyboardType.Text,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = keyboardType),
) {
    OutlinedTextField(
        value = textFieldValue,
        onValueChange = { onTextChanged(it) },
        keyboardOptions = keyboardOptions,
        textStyle = textStyle,
        maxLines = 1,
        singleLine = true,
        label = {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        },
        modifier = modifier,
        visualTransformation = visualTransformation
    )
}