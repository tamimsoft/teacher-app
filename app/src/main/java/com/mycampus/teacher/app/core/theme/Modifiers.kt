package com.mycampus.teacher.app.core.theme

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mycampus.teacher.app.common.component.ButtonSize

/// CUSTOM MODIFIER EXTENSIONS
fun Modifier.appButton(size: ButtonSize = ButtonSize.Medium) = when (size) {
    ButtonSize.Small -> this.height(40.dp)
    ButtonSize.Medium -> this.height(56.dp)
    ButtonSize.Large -> this.height(64.dp)
}
    .fillMaxWidth()

fun Modifier.appTextField() = this
    .fillMaxWidth()
    .heightIn(min = 56.dp)
