package com.example.shoppinglist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddItemAlertDialog(
    showDialog: Boolean,
    dialogName: String,
    dialogQuantity: String,
    onDialogNameChange: (String) -> Unit,
    onDialogQuantityChange: (String) -> Unit,
    onDismissRequest: () -> Unit,
    onAddItem: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismissRequest,
            title = { Text(text = "Add Shopping Item") },
            text = {
                Column(
                    modifier = Modifier.padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        value = dialogName,
                        onValueChange = onDialogNameChange,
                        label = { Text(text = "Item name") },
                        singleLine = true
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        value = dialogQuantity,
                        onValueChange = onDialogQuantityChange,
                        label = { Text(text = "Item quantity") },
                        singleLine = true
                    )
                }
            },
            confirmButton = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = onDismissRequest) {
                        Text(text = "Close")
                    }
                    Button(onClick = onAddItem) {
                        Text(text = "Add")
                    }
                }
            }
        )
    }
}