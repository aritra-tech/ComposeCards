package com.aritra.compose_cards.ui

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.aritra.compose_cards.R

@Composable
fun CreditCard(
    cardNumber: TextFieldValue,
    holderName: TextFieldValue,
    expiryDate: TextFieldValue,
    cardCVV: TextFieldValue
) {

    var backSwitch by remember { mutableStateOf(false) }
    var cardType by remember { mutableStateOf(Card.None) }
    val length = if (cardNumber.text.length > 16) 16 else cardNumber.text.length
    val maskedNumber =
        remember { "*****************" }.replaceRange(0..length, cardNumber.text.take(16))

    // Switch to back side of the card depending on the cvv number
    if (cardCVV.text.length == 1 && !backSwitch) {
        backSwitch = true
    } else if (cardCVV.text.length == 2) {
        backSwitch = true
    } else if (cardCVV.text.length == 3) {
        backSwitch = false
    }

    // Show card type logo depending on the card number
    cardType = when {
        cardNumber.text.isNotEmpty() -> {
            when (cardNumber.text[0]) { // // Taking the first digits for identifying which card is it
                '4' -> Card.Visa
                '5' -> Card.Mastercard
                '6' -> Card.RuPay
                '3' -> Card.AmericanExpress
                else -> Card.None
            }
        }

        else -> Card.None
    }

    // Set Card Color according to the card type

    val animatedColor = animateColorAsState(
        targetValue =
        if (cardType == Card.Visa) {
            Color(0xFF1C478B)
        } else if (cardType == Card.Mastercard) {
            Color(0xFF1C478B)
        } else if (cardType == Card.RuPay) {
            Color(0xFF1C478B)
        } else if (cardType == Card.AmericanExpress) {
            Color(0xFF1C478B)
        } else {
            MaterialTheme.colors.onBackground
        },
        label = ""
    )

    Box {
        Surface(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(200.dp)
                .graphicsLayer(
                    rotationY = animateFloatAsState(if (backSwitch) 180f else 0f).value,
                    translationY = 0f
                ).clickable {
                    backSwitch = !backSwitch
                },
            shape = RoundedCornerShape(20.dp),
            color = animatedColor.value,
            elevation = 18.dp
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                AnimatedVisibility(visible = !backSwitch) {
                    ConstraintLayout(
                        modifier = Modifier.fillMaxSize()
                    ) {

                        val (cardImage, cardName, cardHolderName, number, cardExpiry, expiry) = createRefs()

                        AnimatedVisibility(visible = cardType != Card.None,
                            modifier = Modifier
                                .padding(16.dp)
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
                            text = maskedNumber.chunked(4).joinToString(" "),
                            maxLines = 1,
                            color = Color.White,
                            modifier = Modifier
                                .animateContentSize(spring())
                                .padding(vertical = 16.dp, horizontal = 16.dp)
                                .constrainAs(number) {
                                    linkTo(
                                        start = parent.start,
                                        end = parent.end
                                    )
                                    linkTo(
                                        top = parent.top,
                                        bottom = parent.bottom
                                    )
                                }
                        )

                        Text(
                            text = "Card Holder Name",
                            color = Color.White,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .constrainAs(cardHolderName) {
                                    start.linkTo(parent.start)
                                    bottom.linkTo(cardName.top)
                                }
                        )

                        Text(
                            text = holderName.text,
                            color = Color.White,
                            modifier = Modifier
                                .animateContentSize(TweenSpec(300))
                                .padding(start = 16.dp, bottom = 16.dp)
                                .constrainAs(cardName) {
                                    start.linkTo(parent.start)
                                    bottom.linkTo(parent.bottom)
                                }
                        )

                        Text(
                            text = "Expiry Date",
                            color = Color.White,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .constrainAs(expiry) {
                                    end.linkTo(parent.end)
                                    bottom.linkTo(cardExpiry.top)
                                }
                        )

                        Text(
                            text = expiryDate.text.take(4).chunked(2).joinToString(" / "),
                            color = Color.White,
                            modifier = Modifier
                                .padding(end = 16.dp, bottom = 16.dp)
                                .constrainAs(cardExpiry) {
                                    end.linkTo(parent.end)
                                    bottom.linkTo(parent.bottom)
                                }
                        )
                    }
                }

                AnimatedVisibility(visible = backSwitch) {
                    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                        val (back) = createRefs()
                        Spacer(modifier = Modifier
                            .height(50.dp)
                            .background(
                                Color.Black
                            )
                            .fillMaxWidth()
                            .constrainAs(back) {
                                linkTo(
                                    top = parent.top,
                                    bottom = parent.bottom
                                )
                            }
                        )
                    }
                }
            }
        }

        AnimatedVisibility(
            visible = backSwitch,
            modifier = Modifier
                .padding(end = 50.dp, bottom = 50.dp)
        ) {
            Box(
                modifier = Modifier
                    .defaultMinSize(minWidth = 60.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = cardCVV.text,
                    style = MaterialTheme.typography.h6,
                    color = Color.White,
                    modifier = Modifier
                        .animateContentSize()
                        .padding(vertical = 4.dp, horizontal = 16.dp)

                )
            }

        }
    }
}

enum class Card(
    val title: String,
    @DrawableRes val image: Int
) {
    None("", R.drawable.ic_visa),
    Visa("", R.drawable.ic_visa),
    Mastercard("", R.drawable.ic_mastercard),
    RuPay("", R.drawable.rupay_logo),
    AmericanExpress("", R.drawable.amex_logo)
}

@Preview
@Composable
fun PreviewPaymentCard(){
    CreditCard(
        TextFieldValue("*****************"),
        TextFieldValue("Aritra Das"),
        TextFieldValue("0229"),
        TextFieldValue("699")
    )
}