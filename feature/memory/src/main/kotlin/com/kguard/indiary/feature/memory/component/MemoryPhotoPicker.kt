package com.kguard.indiary.feature.memory.component

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kguard.indiary.core.designsystem.theme.IndiaryTheme
import com.kguard.indiary.core.designsystem.R

@Composable
fun MemoryPhotoPicker(
    onPhotoSelected: (String?) -> Unit,
) {
    val context = LocalContext.current
    val singlePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
    )
    { uri ->
        if (uri != null) {
            context.contentResolver.takePersistableUriPermission(
                uri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
            onPhotoSelected(uri.toString())
        }

    }
    IconButton(onClick = {
        singlePhotoPicker.launch(
            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
        )
    }) {
        Image(
            painter = painterResource(id = R.drawable.bg_add_photo2),
            contentDescription = "add Photo"
        )
    }
}

@Composable
@Preview(showSystemUi = true)
internal fun MemoryPhotoPickerPrev() {
    val contextForToast = LocalContext.current.applicationContext
    IndiaryTheme {
        val photo = remember {
            mutableStateOf(listOf<String?>())
        }
        val a = mutableListOf<String?>()
        Row {
            MemoryPhotoPicker(onPhotoSelected = {
                a.add(it)
                photo.value = a
                Toast.makeText(contextForToast, it.toString(), Toast.LENGTH_SHORT).show()
            })
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            {
                items(items = photo.value) {
                    AsyncImage(
                        model = it, modifier = Modifier
                            .height(80.dp)
                            .width(60.dp)
                            .clip(
                                RoundedCornerShape(5.dp)
                            ), contentDescription = null
                    )
                }
            }

        }
    }
}