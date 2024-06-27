package com.aritra.compose_cards.ui

import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role

fun Modifier.onClick(
    indication: Indication? = null,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit
) = this.composed(inspectorInfo = debugInspectorInfo {
    name = "onClickModifier"
    value = enabled
}) {
    val interactionSource = remember { MutableInteractionSource() }
    clickable(
        indication = indication,
        interactionSource = interactionSource,
        enabled = enabled,
        onClickLabel = onClickLabel,
        role = role
    ) {
        onClick.invoke()
    }
}