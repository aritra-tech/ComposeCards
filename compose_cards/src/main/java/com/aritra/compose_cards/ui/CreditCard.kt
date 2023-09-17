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
import com.aritra.compose_cards.util.Card

/**
 * Composable function to display a Credit Card view with animated flip functionality.
 *
 * @param cardNumber The text input for the credit card number.
 * @param holderName The text input for the cardholder's name.
 * @param expiryDate The text input for the card's expiry date.
 * @param cardCVV The text input for the card's CVV (Card Verification Value) number.
 */


@Composable
fun CreditCard(
    cardNumber: TextFieldValue,
    holderName: TextFieldValue,
    expiryDate: TextFieldValue,
    cardCVV: TextFieldValue
) {

    // Mutable state to track the flip state of the card
    var backSwitch by remember { mutableStateOf(false) }

    // Mutable state to track the detected card type (Visa, Mastercard, etc.)
    var cardType by remember { mutableStateOf(Card.None) }

    // Calculate the length of the card number and mask it for display
    val length = if (cardNumber.text.length > 16) 16 else cardNumber.text.length
    val maskedNumber = remember { "*****************" }.replaceRange(0..length, cardNumber.text.take(16))


    val cvv = if (cardCVV.text.length > 3) 3 else cardCVV.text.length
    val maskedCVV = remember { "*".repeat(3) }.replaceRange(0 until cvv, cardCVV.text.take(3))

    // Determine whether to switch to the back side of the card based on CVV length
    if (cardCVV.text.length == 1 && !backSwitch) {
        backSwitch = true
    } else if (cardCVV.text.length == 2) {
        backSwitch = true
    } else if (cardCVV.text.length == 3) {
        backSwitch = false
    }

    // Detect and set the card type logo based on the card number's first digit
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

    // Set the card's background color based on its type
    val animatedColor = animateColorAsState(
        targetValue =
        if (cardType == Card.Visa) {
            Color(0xFF1C478B)
        } else if (cardType == Card.Mastercard) {
            Color(0xFF3BB9A1)
        } else if (cardType == Card.RuPay) {
            Color(0xFFB2B1FD)
        } else if (cardType == Card.AmericanExpress) {
            Color(0xFFA671FC)
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
                    rotationY = animateFloatAsState(if (backSwitch) 180f else 0f, label = "").value,
                    translationY = 0f
                )
                .clickable {
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
                                .padding(start = 12.dp,bottom = 10.dp)
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
                            style = MaterialTheme.typography.h5,
                            maxLines = 1,
                            color = Color.White,
                            modifier = Modifier
                                .animateContentSize(spring())
                                .padding(bottom = 20.dp)
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
                                .padding(top = 10.dp, start = 16.dp, bottom = 16.dp)
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
                                .padding(top = 10.dp, end = 16.dp, bottom = 16.dp)
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
                .align(Alignment.BottomEnd)
        ) {
            Box(
                modifier = Modifier
                    .defaultMinSize(minWidth = 60.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = maskedCVV,
                    style = MaterialTheme.typography.h6,
                    color = Color.White,
                    modifier = Modifier
                        .animateContentSize(TweenSpec(300))
                        .padding(vertical = 4.dp, horizontal = 16.dp)

                )
            }

        }
    }
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