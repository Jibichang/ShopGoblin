package com.wa.shopgoblin.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wa.shopgoblin.domain.UserStateHolder
import com.wa.shopgoblin.ui.theme.ShopGoblinTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShopGoblinTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ViewModelDatabase()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ShopGoblinTheme {
        FactoryInject("Android")
    }
}

@Composable
fun ViewModelInject(userName: String, viewModel: UserViewModel = koinViewModel()) {
    Text(text = viewModel.sayHello(userName), modifier = Modifier.padding(8.dp))
}

@Composable
fun ViewModelDatabase(viewModel: UserViewModel = koinViewModel()) {
    val helloText by viewModel.helloText.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.addData()
    }

    Text(text = helloText, modifier = Modifier.padding(8.dp))
}

@Composable
fun FactoryInject(userName : String, presenter: UserStateHolder = koinInject()){
    Text(text = presenter.sayHello(userName), modifier = Modifier.padding(8.dp))
}