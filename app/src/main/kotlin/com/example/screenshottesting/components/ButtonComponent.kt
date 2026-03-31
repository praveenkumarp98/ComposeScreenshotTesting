package com.example.screenshottesting.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.screenshottesting.ui.theme.AppTheme

/**
 * Primary filled button — high-emphasis action.
 *
 * @param leadingIcon Optional icon shown to the left of the label (follows button content colour)
 */
@Composable
fun PrimaryButton(
    label: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        if (leadingIcon != null) {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))
        }
        Text(text = label)
    }
}

/**
 * Secondary outlined button — medium-emphasis action.
 */
@Composable
fun SecondaryButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(text = label)
    }
}

@Preview(name = "PrimaryButton — Light", showBackground = true)
@Composable
private fun PrimaryButtonPreview() {
    AppTheme(darkTheme = false) {
        PrimaryButton(label = "Confirm", onClick = {})
    }
}

@Preview(name = "PrimaryButton — With Icon", showBackground = true)
@Composable
private fun PrimaryButtonWithIconPreview() {
    AppTheme(darkTheme = false) {
        PrimaryButton(label = "Add Item", onClick = {}, leadingIcon = Icons.Filled.Add)
    }
}

@Preview(name = "SecondaryButton — Light", showBackground = true)
@Composable
private fun SecondaryButtonPreview() {
    AppTheme(darkTheme = false) {
        SecondaryButton(label = "Cancel", onClick = {})
    }
}
