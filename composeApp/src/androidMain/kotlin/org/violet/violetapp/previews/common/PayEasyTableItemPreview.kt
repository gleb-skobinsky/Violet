package org.violet.violetapp.previews.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.violet.violetapp.common.presentation.components.VioletAppTableItem

@Preview(
    showBackground = true
)
@Composable
fun VioletAppTableItemPreview() {
    VioletAppTableItem(
        label = "Currency",
        value = "999,99",
        copyable = true
    )
}