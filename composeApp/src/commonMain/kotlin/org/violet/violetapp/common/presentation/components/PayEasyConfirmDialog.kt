package org.violet.violetapp.common.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import org.violet.uiKit.theme.LocalVioletTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.jetbrains.compose.resources.stringResource
import org.violet.violetapp.resources.AppRes
import org.violet.violetapp.resources.button_cancel
import org.violet.violetapp.resources.button_confirm

class ConfirmDialogState(
    initialState: Boolean
) {
    var isOpen by mutableStateOf(initialState)
        private set

    fun open() {
        isOpen = true
    }

    fun close() {
        isOpen = false
    }

    companion object {
        val Saver = listSaver(
            save = { listOf(it.isOpen) },
            restore = { ConfirmDialogState(it[0]) }
        )
    }
}

@Composable
fun rememberConfirmDialogState(isOpen: Boolean = false): ConfirmDialogState {
    return rememberSaveable(
        saver = ConfirmDialogState.Saver
    ) {
        ConfirmDialogState(isOpen)
    }
}

@Composable
fun VioletAppConfirmDialog(
    descriptionText: String,
    state: ConfirmDialogState = rememberConfirmDialogState(),
    dismissOnClickOutside: Boolean = true,
    onCancel: () -> Unit = { state.close() },
    labelConfirm: String = stringResource(AppRes.string.button_confirm),
    labelCancel: String = stringResource(AppRes.string.button_cancel),
    confirmLoading: Boolean = false,
    onConfirm: () -> Unit
) {
    if (state.isOpen) {
        Dialog(
            onDismissRequest = {
                if (dismissOnClickOutside) state.close()
            }
        ) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = LocalVioletTheme.colors.surface
            ) {
                Column(
                    Modifier.fillMaxWidth().height(250.dp).padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = descriptionText,
                        modifier = Modifier.fillMaxWidth(0.7f),
                        color = LocalVioletTheme.colors.primaryContainer,
                        style = LocalVioletTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                    48.dp.VerticalSpacer()
                    Row(Modifier.fillMaxWidth()) {
                        VioletAppButton(
                            isLoading = confirmLoading,
                            label = labelConfirm,
                            type = VioletAppButtonType.Danger,
                            modifier = Modifier.weight(1f),
                            rightIcon = null,
                            onClick = onConfirm
                        )
                        16.dp.HorizontalSpacer()
                        VioletAppButton(
                            label = labelCancel,
                            modifier = Modifier.weight(1f),
                            rightIcon = null,
                            onClick = onCancel
                        )
                    }
                }
            }
        }
    }
}