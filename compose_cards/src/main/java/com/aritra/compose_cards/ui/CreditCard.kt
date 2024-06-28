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


package com.aritra.compose_cards.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.aritra.compose_cards.util.Card

@Composable
fun CreditCard(
    cardNumber: String,
    holderName: String,
    expiryDate: String,
    cardCVV: String,
    selectedBackground: Int
) {
    var backSwitch by remember { mutableStateOf(false) }
    var cardType by remember { mutableStateOf(Card.None) }

    val rotation by animateFloatAsState(
        targetValue = if (backSwitch) 180f else 0f,
        animationSpec = tween(500),
        label = ""
    )

    val animateFront by animateFloatAsState(
        targetValue = if (!backSwitch) 1f else 0f,
        animationSpec = tween(500),
        label = ""
    )

    val length = if (cardNumber.length > 16) 16 else cardNumber.length
    val maskedNumber = remember { "*****************" }.replaceRange(0..length, cardNumber.take(16))

    val cvv = if (cardCVV.length > 3) 3 else cardCVV.length
    val maskedCVV = remember { "*".repeat(3) }.replaceRange(0 until cvv, cardCVV.take(3))

    if (cardCVV.length == 1 && !backSwitch) {
        backSwitch = true
    } else if (cardCVV.length == 2) {
        backSwitch = true
    } else if (cardCVV.length == 3) {
        backSwitch = false
    }

    cardType = when {
        cardNumber.isNotEmpty() -> {
            when (cardNumber.take(2)) {
                "30", "36", "38" -> Card.DinersClub
                "40" -> Card.Visa
                "50", "51", "52", "53", "54", "55" -> Card.Mastercard
                "56", "57", "58", "63", "67" -> Card.Maestro
                "60" -> Card.RuPay
                "37" -> Card.AmericanExpress
                else -> Card.None
            }
        }

        else -> Card.None
    }

    Box {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .graphicsLayer {
                    rotationY = rotation
                    cameraDistance = 8 * density
                }
                .clickable { backSwitch = !backSwitch },
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
                    .padding(10.dp)
                    .paint(
                        painter = painterResource(id = selectedBackground),
                        contentScale = ContentScale.Crop,
                    )
            ) {
                AnimatedVisibility(visible = !backSwitch) {
                    ConstraintLayout(
                        modifier = Modifier.fillMaxSize()
                            .padding(12.dp)
                    ) {
                        val (cardImage, cardName, cardHolderName, number, cardExpiry, expiry) = createRefs()

                        AnimatedVisibility(visible = cardType != Card.None,
                            modifier = Modifier
                                .padding(start = 12.dp, top = 10.dp)
                                .constrainAs(cardImage) {
                                    start.linkTo(parent.start)
                                    top.linkTo(parent.top)
                                }) {
                            Image(
                                painter = painterResource(id = cardType.image),
                                contentDescription = "Card Image"
                            )
                        }

                        Text(
                            modifier = Modifier
                                .padding(bottom = 20.dp)
                                .graphicsLayer {
                                    alpha = animateFront
                                }
                                .constrainAs(number) {
                                    linkTo(start = parent.start, end = parent.end)
                                    linkTo(top = parent.top, bottom = parent.bottom)
                                },
                            text = maskedNumber.chunked(4).joinToString(" "),
                            style = MaterialTheme.typography.headlineLarge,
                            maxLines = 1,
                            color = Color.White,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Normal
                        )

                        Text(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .graphicsLayer {
                                    alpha = animateFront
                                }
                                .constrainAs(cardHolderName) {
                                    start.linkTo(parent.start)
                                    bottom.linkTo(cardName.top)
                                },
                            text = "Card Holder",
                            color = Color.White,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            modifier = Modifier
                                .graphicsLayer {
                                    alpha = animateFront
                                }
                                .padding(start = 16.dp, bottom = 16.dp)
                                .constrainAs(cardName) {
                                    start.linkTo(parent.start)
                                    bottom.linkTo(parent.bottom)
                                },
                            text = holderName,
                            color = Color.White,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold

                        )

                        Text(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .graphicsLayer {
                                    alpha = animateFront
                                }
                                .constrainAs(expiry) {
                                    end.linkTo(parent.end)
                                    bottom.linkTo(cardExpiry.top)
                                },
                            text = "Expiry Date",
                            color = Color.White,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            modifier = Modifier
                                .padding(end = 16.dp, bottom = 16.dp)
                                .graphicsLayer {
                                    alpha = animateFront
                                }
                                .constrainAs(cardExpiry) {
                                    end.linkTo(parent.end)
                                    bottom.linkTo(parent.bottom)
                                },
                            text = expiryDate.take(4).chunked(2).joinToString(" / "),
                            color = Color.White,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                AnimatedVisibility(visible = backSwitch) {
                    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                        val (back) = createRefs()
                        Spacer(modifier = Modifier
                            .height(50.dp)
                            .background(Color.Black)
                            .fillMaxWidth()
                            .constrainAs(back) {
                                linkTo(top = parent.top, bottom = parent.bottom)
                            })
                    }
                }
            }
        }

        AnimatedVisibility(
            visible = backSwitch,
            modifier = Modifier
                .padding(end = 50.dp, bottom = 30.dp)
                .align(Alignment.BottomEnd)
        ) {
            Box(
                modifier = Modifier
                    .defaultMinSize(minWidth = 60.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.White), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = maskedCVV,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .animateContentSize(TweenSpec(300))
                        .padding(vertical = 4.dp, horizontal = 16.dp)
                )
            }
        }
    }
}

