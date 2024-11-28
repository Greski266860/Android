package com.example.projekt2

import android.content.Context
import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.projekt2.ui.theme.Projekt2Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        enableEdgeToEdge()
        setContent {
            Projekt2Theme {
                Aplikacja(modifier = Modifier.fillMaxSize())
            }
        }
    }


}



@Composable
fun Sensory(modifier: Modifier = Modifier){
    val sensorManager = LocalContext.current.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    var mLight: Sensor? = null
    mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    val sensorData = remember { mutableFloatStateOf(0f)
    }
    var klik1 by remember {
        mutableStateOf(true) }
    val SensorEventListener = object : SensorEventListener{
        override fun onSensorChanged(event: SensorEvent) {
            if (event != null) {
                if (event.sensor.type == Sensor.TYPE_LIGHT){
                    sensorData.floatValue = event.values[0]
                }
            }

        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

        }

    }
    sensorManager.registerListener(SensorEventListener, mLight, SensorManager.SENSOR_DELAY_NORMAL)

    Surface (
        color = MaterialTheme.colorScheme.primary
    ){
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text("Czujnik oświetlenia:")
            Text(if(!klik1)(sensorData.floatValue.toString())else (sensorData.floatValue.toString()))

        }
    }
}
@Composable
fun EkranStart(onContinueClicked: () -> Unit,
               modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text("Grzegorz Wróblewski 266860, Projekt 2")
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
    Surface(modifier){
        if (pokaz) {
            EkranStart(onContinueClicked = { pokaz = false })
        } else {
            Sensory()
        }
    }
}