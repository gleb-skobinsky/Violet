package org.violet.violetapp.common.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import org.violet.violetapp.common.icons.ArrowDown

@Composable
fun VioletAppDropDownField(
    label: String?,
    placeholder: String = "",
    enabled: Boolean = true,
    leftIcon: ImageVector? = null,
    bottomSheetContent: @Composable ColumnScope.(sheetState: VioletAppBottomSheetState) -> Unit
) {
    val sheetState = rememberVioletAppBottomSheetState()
    Box(Modifier.clip(DefaultTextFieldShape)) {
        VioletAppTextField(
            value = label.orEmpty(),
            onValueChange = {},
            leftIcon = leftIcon,
            readOnly = true,
            placeholder = placeholder,
            rightIcon = {
                CommonIconButton(ArrowDown, enabled = enabled) {
                    sheetState.expand()
                }
            }
        )
        Spacer(
            Modifier
                .matchParentSize()
                .clickable(enabled = enabled) { sheetState.expand() }
        )
    }
    VioletAppBottomSheet(
        sheetState = sheetState,
        hideBottomSheetOnClickOutside = true,
        bottomSheetContent = {
            bottomSheetContent(sheetState)
        }
    )
}