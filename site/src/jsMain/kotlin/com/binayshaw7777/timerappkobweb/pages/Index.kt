package com.binayshaw7777.timerappkobweb.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.Page
import com.binayshaw7777.timerappkobweb.components.layouts.PageLayout
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.silk.components.forms.Button
import kotlinx.coroutines.*
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Text


@Page
@Composable
fun HomePage() {
    val counterValue = remember { mutableStateOf(0) }
    val shouldIncrease = remember {
        mutableStateOf(true)
    }

    PageLayout("Timer App!") {
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Operate the timer using below buttons")
            Row {
                Text("Time: ${counterValue.value}")
            }
            Row(Modifier.fillMaxWidth().margin(20.px), Arrangement.SpaceAround) {
                Button(onClick = { startCounter(counterValue, shouldIncrease) }) {
                    Text("Start")
                }
                Button(onClick = { stopCounter(shouldIncrease) }) {
                    Text("Stop")
                }
                Button(onClick = { resetCounter(counterValue, shouldIncrease) }) {
                    Text("Reset")
                }
            }
        }
    }
}

private fun startCounter(
    counterValue: MutableState<Int>,
    shouldIncrease: MutableState<Boolean>
) {
    CoroutineScope(Dispatchers.Main).launch {
        shouldIncrease.value = true
        while (shouldIncrease.value) {
            delay(1_000L)
            println("Counter incrementing")
            counterValue.value += 1
        }
    }
}

private fun stopCounter(shouldIncrease: MutableState<Boolean>) {
    shouldIncrease.value = false
}

private fun resetCounter(
    counterValue: MutableState<Int>,
    shouldIncrease: MutableState<Boolean>
) {
    stopCounter(shouldIncrease)
    counterValue.value = 0
}