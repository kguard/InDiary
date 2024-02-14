package com.kguard.indiary.core.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kguard.indiary.core.designsystem.R
import com.kguard.indiary.core.designsystem.theme.IndiaryTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IndiaryTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    labelId: Int,
    placeholder: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    singleLine: Boolean = true,
    isError: Boolean = false,
    readOnly: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        textStyle = MaterialTheme.typography.bodyMedium,
        label = {
            Text(
                text = stringResource(id = labelId),
                style = MaterialTheme.typography.labelMedium
            )
        },
        placeholder = placeholder,
        supportingText = supportingText,
        isError = isError,
        singleLine = singleLine,
        readOnly = readOnly,
        colors = if (readOnly)
            ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                cursorColor = MaterialTheme.colorScheme.onSurface,
                focusedBorderColor = MaterialTheme.colorScheme.onSurface,
                unfocusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
                errorBorderColor = MaterialTheme.colorScheme.error,
            )
        else
            OutlinedTextFieldDefaults.colors(
                cursorColor = MaterialTheme.colorScheme.onSurface,
                focusedBorderColor = MaterialTheme.colorScheme.onSurface,
                unfocusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
                errorBorderColor = MaterialTheme.colorScheme.error,
                errorSupportingTextColor = MaterialTheme.colorScheme.error,
            ),

        trailingIcon = trailingIcon,
    )
}


@Composable
fun IndiaryNumField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    labelId: Int,
    placeholder: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        textStyle = MaterialTheme.typography.bodyMedium,
        label = {
            Text(
                text = stringResource(id = labelId),
                style = MaterialTheme.typography.labelMedium
            )
        },
        singleLine = true,
        placeholder = placeholder,
        supportingText = supportingText,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        isError = isError,
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = MaterialTheme.colorScheme.onSurface,
            focusedBorderColor = MaterialTheme.colorScheme.onSurface,
            unfocusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
            errorBorderColor = MaterialTheme.colorScheme.error,
            errorSupportingTextColor = MaterialTheme.colorScheme.error,
        )
    )
}

//@Composable
//fun IndiaryMultilineTextField(
//    value: String,
//    onValueChange: (String) -> Unit,
//    modifier: Modifier = Modifier,
//    placeholder: String = "",
//    textStyle: TextStyle = MaterialTheme.typography.labelMedium,
//    maxLine: Int = 4
//) {
//    BasicTextField(
//        value = value,
//        onValueChange = onValueChange,
//        textStyle = textStyle,
//        maxLines = maxLine,
//        modifier = modifier ,
//        decorationBox = { innerTextField ->
//            Box(modifier = Modifier.padding(8.dp)) {
//                if (value.isEmpty()) {
//                    Text(
//                        text = placeholder,
//                        style = textStyle,
//                        color = MaterialTheme.colorScheme.onSurfaceVariant
//                    )
//                }
//                innerTextField()
//            }
//        },
//         modifier = Modifier.onFocusChanged { it.isFocused }
//    )
//
//}
@Composable
fun IndiaryMultilineTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    labelId: Int,
    placeholder: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.height(160.dp),
        textStyle = MaterialTheme.typography.bodyMedium,
        label = {
            Text(
                text = stringResource(id = labelId),
                style = MaterialTheme.typography.labelMedium
            )
        },
        placeholder = placeholder,
        supportingText = supportingText,
        isError = isError,
        singleLine = false,
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = MaterialTheme.colorScheme.onSurface,
            focusedBorderColor = MaterialTheme.colorScheme.onSurface,
            unfocusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
            errorBorderColor = MaterialTheme.colorScheme.error,
            errorSupportingTextColor = MaterialTheme.colorScheme.error,
        ),
        maxLines = 7,
    )
}


@Preview(showSystemUi = true)
@Composable
fun IndiaryTextFieldPrev() {
    IndiaryTheme {
        var a by remember { mutableStateOf("") }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IndiaryTextField(
                value = a, onValueChange = { new -> a = new },
                labelId = R.string.Name,
                placeholder = { Text(text = ("123123")) },
                supportingText = { Text("sup") },
                singleLine = true,
                readOnly = true
            )
            IndiaryTextField(
                value = a, onValueChange = { new -> a = new },
                labelId = R.string.Gender,
                placeholder = { Text(text = ("123123")) },
                supportingText = { Text("sup") },
                readOnly = false
            )
            IndiaryMultilineTextField(
                value = a, onValueChange = { new -> a = new },
                labelId = R.string.Memo,
                placeholder = { Text(text = ("123123")) },
                supportingText = { Text("sup") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            IndiaryNumField(
                value = a, onValueChange = { new -> a = new },
                labelId = R.string.Birth,
                placeholder = { Text(text = ("123123")) },
                supportingText = { Text("sup") },
            )
        }
    }
}