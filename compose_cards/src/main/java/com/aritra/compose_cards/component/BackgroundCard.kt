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

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aritra.compose_cards.ui.onClick

@Composable
fun BackgroundCard(
    drawableResId: Int,
    onClick: () -> Unit
) {
    Box(modifier = Modifier.onClick {
        onClick()
    }, contentAlignment = Alignment.TopEnd) {

        Column(
            modifier = Modifier
                .widthIn(min = 70.dp, 70.dp)
                .heightIn(min = 70.dp, 70.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color = Color(0xFFF9F9F9)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = drawableResId),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
        }
    }
}