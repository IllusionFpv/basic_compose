package com.example.compose_basic_codelabs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_basic_codelabs.ui.theme.BasicsCodelabsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MyScreenContent()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    BasicsCodelabsTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = Color.Cyan) {
            content()
        }
    }
}

@Composable
fun MyScreenContent(names: List<String> = List(100) { "Hello Android #$it" }) {

    val counterState = remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 16.dp)
    ) {

        NameList(
            names = names,
            modifier = Modifier.weight(1f)
        )

        Counter(
            count = counterState.value,
            updateCount = { newCount ->
                counterState.value = newCount
            }
        )
    }
}

@Composable
fun Greeting(name: String) {
    var isSelected by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(if (isSelected) Color.Red else Color.Transparent)

    Text(
        text = "Hello $name!",
        modifier = Modifier
            .padding(16.dp)
            .background(color = backgroundColor)
            .clickable(onClick = {isSelected = !isSelected}),
        style = MaterialTheme.typography.h6
    )
}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {

    Button(
        onClick = { updateCount(count + 1) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (count > 5) Color.Green else Color.Blue
        ),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text("I've been clicked $count times")
    }
}

@Composable
fun NameList(names: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = names) { name ->
            Greeting(name = name)
            Divider(color = Color.Gray, modifier = Modifier.padding(start = 16.dp, end = 16.dp))
        }
    }
}

@Preview("Text Preview")
@Composable
fun DefaultPreview() {
    MyApp {
        MyScreenContent()
    }
}
