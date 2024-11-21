package com.example.projekt1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projekt1.ui.theme.Projekt1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Projekt1Theme {

                Aplikacja(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun Aktywnosci(modifier: Modifier = Modifier) {
    var klik1 by remember { mutableStateOf(true) }
    var klik2 by remember { mutableStateOf(true) }
    var liczba by remember {( mutableIntStateOf(0))
    }
    Surface (
        color = MaterialTheme.colorScheme.primary
    ){
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(if (!klik1 or !klik2) (liczba.toString()) else liczba.toString())
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = {
                klik1 = !klik1
                liczba = (0..100).random()
            }
        ) {
            Text("Losuj")
        }
        Button(
            modifier = Modifier.padding(vertical = 8.dp),
            onClick = {
                klik2 = !klik2
                liczba = liczba * 2
            }) {
            Text("Pomnóż")
        }
    }

    }
}
@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun AktywnosciPreview() {
    Projekt1Theme {
        Aktywnosci()
    }
}
@Composable
fun EkranStart(onContinueClicked: () -> Unit,
               modifier: Modifier = Modifier){
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text("Grzegorz Wróblewski 266860, Projekt 1")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text("Przejdź dalej")
        }
    }
}

@Composable
fun Aplikacja(modifier: Modifier = Modifier){
    var pokaz by remember { mutableStateOf(true) }

    Surface(modifier) {
        if (pokaz) {
            EkranStart(onContinueClicked = { pokaz = false })
        } else {
            Aktywnosci()
        }
    }
}

