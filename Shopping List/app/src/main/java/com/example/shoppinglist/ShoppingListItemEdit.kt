package com.example.shoppinglist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun ShoppingListItemEdit(item: ShoppingItem, onSaveEditedItem: (String, Float) -> Unit) {
    var editedName by remember {
        mutableStateOf(item.name)
    }
    var editedQuantity by remember {
        mutableStateOf(item.quantity.toString())
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(BorderStroke(2.dp, Color.Black), RoundedCornerShape(10)),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column {
            BasicTextField(
                value = editedName,
                onValueChange = { editedName = it },
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color.Gray),
                singleLine = true
            )
            BasicTextField(
                value = editedQuantity,
                onValueChange = { editedQuantity = it },
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color.Gray),
                singleLine = true
            )
        }
        Button(onClick = {
            onSaveEditedItem(editedName, editedQuantity.toFloatOrNull() ?: 1.0f)
        }, modifier = Modifier.padding(8.dp)) {
            Text(text = "Save")
        }
    }
}