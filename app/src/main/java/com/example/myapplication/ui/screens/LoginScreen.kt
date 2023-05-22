package com.example.myapplication.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.*

@Composable
fun LoginScreen() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(text = "Username") },
            modifier = Modifier.fillMaxWidth().background(Color.White),

        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))

        GradientTextButton("Log in", modifier = Modifier, onClick = {})
    }
}
@Composable
fun GradientTextButton(
    buttonText: String,
    modifier: Modifier = Modifier,
    contentDescription: String = buttonText,
    enabled: Boolean = true,
    shape: Shape = buttonRoundShape,
    onClick: () -> Unit
) {
    OvalButton(
        bgBrush = if (enabled) gradient1 else disabledGradient1,
        onClick = onClick,
        modifier = modifier.wrapContentHeight(),
        enabled = enabled,
        shape = shape,
    ) {
        Text(
            modifier = Modifier
                .semantics { this.contentDescription = contentDescription }
                .padding(all = 16.dp),
            text = buttonText,

            color = if (enabled) MaterialTheme.colorScheme.onPrimary else Gray2
        )
    }
}

@Composable
fun OvalButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    bgBrush: Brush? = null,
    shape: Shape = MaterialTheme.shapes.small,
    enabled: Boolean = true,
    border: BorderStroke? = null,
    content: @Composable RowScope.() -> Unit,
) {
    Row(
        content = content,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .defaultMinSize(
                minWidth = ButtonDefaults.MinWidth,
                minHeight = ButtonDefaults.MinHeight,
            )
            .shadow(4.dp, shape, clip = false)
            .background(
                brush = bgBrush ?: SolidColor(Color.Gray),
                shape = shape,
            )
            .clip(shape = shape)
            .then(if (border != null) Modifier.border(border, shape) else Modifier)
            .clickable(
                enabled = enabled,
                role = Role.Button,
                onClick = onClick,
            ),
    )
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    MyApplicationTheme {
        LoginScreen()
    }
}
