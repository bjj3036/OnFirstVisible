package com.bjj.onfirstvisible

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bjj.onfirstvisible.ui.theme.OnFirstVisibleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OnFirstVisibleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .verticalScroll(rememberScrollState())
                    ) {
                        repeat(100) {
                            AnimationView(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(40.dp),
                                idx = it
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AnimationView(modifier: Modifier = Modifier, idx: Int) {
    var isShown by remember {
        mutableStateOf(false)
    }
    val progress by animateFloatAsState(if (isShown) 1f else 0f)
    Row(modifier = modifier.onVisibleChanged {
        isShown = it
    }) {
        Text(text = idx.toString())
        LinearProgressIndicator(modifier = Modifier.weight(1f), progress = { progress })
    }
}
