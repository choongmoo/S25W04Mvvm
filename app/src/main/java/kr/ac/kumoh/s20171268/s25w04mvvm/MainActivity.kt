package kr.ac.kumoh.s20171268.s25w04mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import kr.ac.kumoh.s20171268.s25w04mvvm.ui.theme.S25W04MvvmTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            S25W04MvvmTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(
    viewModel: CounterViewModel = viewModel()
) {
    //var count by rememberSaveable { mutableIntStateOf(0) }
    val counterState = viewModel.counterState.value

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Counter(
            modifier = Modifier.padding(innerPadding),
            count = counterState.count,
            onAdd = { viewModel.addCounter() },
            onSub = { viewModel.subCounter() },
            onReset = { viewModel.resetCounter() },
        )
    }
}

