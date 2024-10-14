package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                Surface {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    val artWorks by rememberSaveable { mutableStateOf(ArtWork.getArtWorkList()) }

    var currentArtWorkIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        ArtWorkImageAndInfo(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .shadow(2.dp),
            artWorks, currentArtWorkIndex
        )
        NextAndPreviousButtons(modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
            onPreviousClick = {
                if (currentArtWorkIndex == 0)
                    currentArtWorkIndex = artWorks.size - 1
                else
                    currentArtWorkIndex--
            }, onNextClick = {
                if (currentArtWorkIndex == artWorks.size - 1)
                    currentArtWorkIndex = 0
                else
                    currentArtWorkIndex++
            })


    }
}

@Composable
fun NextAndPreviousButtons(
    modifier: Modifier = Modifier,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            modifier = Modifier.size(height = 40.dp, width = 128.dp),
            onClick = onPreviousClick
        ) {
            Text(
                text = stringResource(R.string.previous)
            )
        }
        Button(modifier = Modifier.size(height = 40.dp, width = 128.dp), onClick = onNextClick) {
            Text(
                text = stringResource(R.string.next)
            )
        }
    }
}

@Composable
fun ArtWorkImageAndInfo(
    modifier: Modifier = Modifier,
    artWorks: List<ArtWork>,
    currentArtWorkIndex: Int
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center

    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            painter = painterResource(
                artWorks[currentArtWorkIndex].artWorkImage
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.LightGray)
    )
    {
        Text(
            modifier = Modifier
                .wrapContentSize()
                .padding(vertical = 8.dp, horizontal = 16.dp),
            text = artWorks[currentArtWorkIndex].artWorkTitle,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            modifier = Modifier
                .wrapContentSize()
                .padding(vertical = 8.dp, horizontal = 16.dp),
            text = "${artWorks[currentArtWorkIndex].artWorkArtist} (${
                artWorks[currentArtWorkIndex].artWorkYear
            })",
            style = MaterialTheme.typography.titleMedium
        )
    }
}

//small phones 5.7 inches
@Preview(showBackground = true, device = "id:pixel_4")
@Composable
fun ArtSpaceAppPreviewPixel4() {
    ArtSpaceApp()
}
//medium phones 6 inches
@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun ArtSpaceAppPreviewPixel5() {
    ArtSpaceApp()
}
//large phones 6.71 inches
@Preview(showBackground = true, device = "id:pixel_6_pro")
@Composable
fun ArtSpaceAppPreviewPixel6Pro() {
    ArtSpaceApp()
}
//foldable 7.6 inches
@Preview(showBackground = true, device = "spec:width=768dp,height=2208dp")
@Composable
fun ArtSpaceAppPreviewGalaxyZFold3() {
    ArtSpaceApp()
}
//small tablet 10.2 inches 4:3 square
@Preview(showBackground = true, device = "id:pixel_c")
@Composable
fun ArtSpaceAppPreviewPixelC() {
    ArtSpaceApp()
}
//large tablet 10.1 inches 16:9  rectangle (wide)
@Preview(showBackground = true, device = "id:Nexus 10")
@Composable
fun ArtSpaceAppPreviewPixelNexus10() {
    ArtSpaceApp()
}
