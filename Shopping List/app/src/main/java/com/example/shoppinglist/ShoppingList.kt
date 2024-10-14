package com.example.shoppinglist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class ShoppingItem(
    val id: Int,
    var name: String,
    var quantity: Float,
    var isEditing: Boolean = false
)

@Composable
@Preview(showBackground = true)
fun ShoppingList() {

    var showDialog by remember {
        mutableStateOf(false)
    }
    var dialogName by remember {
        mutableStateOf("")
    }
    var dialogQuantity by remember {
        mutableStateOf("")
    }
    var shoppingItems by remember {
        mutableStateOf((listOf<ShoppingItem>()))
    }

    val onAddItem = {
        val floatDialogQuantity = dialogQuantity.toFloatOrNull()
        if (dialogName.isNotBlank() && dialogQuantity.isNotBlank() &&
            floatDialogQuantity != null
        ) {
            showDialog = false
            shoppingItems +=
                ShoppingItem(
                    id = shoppingItems.size + 1,
                    name = dialogName,
                    quantity = floatDialogQuantity
                )
            dialogName = ""
            dialogQuantity = ""
        }
    }

    val onDismissRequest = {
        showDialog = false
        dialogName = ""
        dialogQuantity = ""
    }

    // Wrapping content inside a Box to overlay the FAB and list
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            items(shoppingItems) { item: ShoppingItem ->
                if (item.isEditing)
                    ShoppingListItemEdit(
                        item,
                        onSaveEditedItem = { editedName, editedQuantity ->
                            shoppingItems = shoppingItems.map { it.copy(isEditing = false) }
                            val editedItem = shoppingItems.find { it.id == item.id }
                            editedItem?.apply {
                                name = editedName
                                quantity = editedQuantity
                            }
                        })
                else
                    ShoppingListItem(item = item, onEdit = {
                        // Set the editing flag to true for the clicked item
                        shoppingItems = shoppingItems.map { it.copy(isEditing = it.id == item.id) }
                    }, onDelete = {
                        shoppingItems = shoppingItems.filter { it.id != item.id }
                    })
            }
        }

        FloatingActionButton(
            onClick = {
                showDialog = true
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp) // Padding to avoid touching the screen edges
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = null)
        }
    }

    if (showDialog) {
        AddItemAlertDialog(
            showDialog = showDialog,
            dialogName = dialogName,
            dialogQuantity = dialogQuantity,
            onDialogNameChange = { dialogName = it },
            onDialogQuantityChange = { dialogQuantity = it },
            onDismissRequest = onDismissRequest,
            onAddItem = onAddItem
        )
    }
}


@Composable
fun ShoppingListItem(
    item: ShoppingItem,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(BorderStroke(2.dp, Color.Cyan), shape = RoundedCornerShape(20)),
        horizontalArrangement = Arrangement.SpaceBetween

    ) {

        Text(text = item.name, modifier = Modifier.padding(8.dp))
        Text(text = "Qyt: ${item.quantity}", modifier = Modifier.padding(8.dp))
        Row(
            modifier = Modifier.padding(8.dp),
        ) {
            IconButton(onClick = onEdit) {
                Icon(Icons.Default.Edit, null)
            }
            IconButton(onClick = onDelete) {
                Icon(imageVector = Icons.Default.Delete, null)
            }
        }
    }
}
