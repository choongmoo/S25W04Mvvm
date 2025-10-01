package kr.ac.kumoh.s20171268.s25w04mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
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
    val counterState by viewModel.counterState.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // key1에 아무 값도 주지 않아서 (Unit 지정), 최초 한 번만 실행
    LaunchedEffect(key1 = Unit) {
        viewModel.events.collectLatest { message ->
            scope.launch {
                snackbarHostState.showSnackbar(message)
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Counter(
            modifier = Modifier.padding(innerPadding),
            count = counterState.count,
            onAdd = { viewModel.addCounter() },
            onSub = { viewModel.subCounter() },
            onReset = { viewModel.resetCounter() },
        )
    }
}

