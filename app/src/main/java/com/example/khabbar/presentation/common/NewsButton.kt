package com.example.khabbar.presentation.common

import androidx.compose.foundation.layout.padding // Added for preview spacing if needed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button // Material 3 Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier // Added for preview
import androidx.compose.ui.graphics.Color // Added for Color.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview // Added for Preview
import androidx.compose.ui.unit.dp


@Composable
fun NewsButton(
    text: String,
    onClick: () -> Unit // onClick parameter name is conventional
) {
    Button( // Make sure this is androidx.compose.material3.Button
        onClick = onClick, // Corrected: parameter name is 'onClick'
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White // For text and icon color inside the button
        ),
        shape = RoundedCornerShape(size = 6.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
        )
    }
}

@Composable
fun NewsTextButton(
    text: String,
    onClick: () -> Unit
) {
    TextButton(onClick = onClick) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun NewsButtonPreview() {
    // It's good practice to wrap previews in your app's theme
    // to ensure they render with the correct styling.
    // Replace KhabbarTheme with your actual app theme if you have one.
    MaterialTheme { // Or your custom KhabbarTheme { ... }
        NewsButton(
            text = "Next",
            onClick = {
                // This lambda is for the preview,
                // you can add a log or leave it empty.
                // Log.d("Preview", "NewsButton clicked")
            }
        )
    }
}

// If you want to see another variant, e.g., a longer text:
@Preview(showBackground = true)
@Composable
fun NewsButtonLongTextPreview() {
    MaterialTheme { // Or your custom KhabbarTheme { ... }
        NewsButton(
            text = "Click Here To Read More",
            onClick = {}
        )
    }
}