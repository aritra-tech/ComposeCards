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


package com.aritra.compose_cards.util

import androidx.annotation.DrawableRes
import com.aritra.compose_cards.R

enum class Card(
    @DrawableRes val image: Int
) {
    None(R.drawable.ic_visa),
    Visa(R.drawable.ic_visa),
    Mastercard(R.drawable.ic_mastercard),
    RuPay(R.drawable.rupay_logo),
    AmericanExpress(R.drawable.amex_logo),
    Maestro(R.drawable.maestro),
    DinersClub(R.drawable.diner_clubs)
}