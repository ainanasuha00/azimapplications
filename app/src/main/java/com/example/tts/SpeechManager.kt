package com.example.tts

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import java.util.Locale

class SpeechManager(context: Context) {
    private var tts: TextToSpeech? = null
    var isInitialized by mutableStateOf(false)
        private set
    var isSpeaking by mutableStateOf(false)
        private set
    var statusMessage by mutableStateOf("Ready to listen")
        private set

    init {
        tts = TextToSpeech(context.applicationContext) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = tts?.setLanguage(Locale.UK)
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    // Fallback to English
                    tts?.setLanguage(Locale.ENGLISH)
                }
                tts?.setSpeechRate(0.85f) // Gentle paced for primary school learners
                tts?.setPitch(1.05f)

                tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                    override fun onStart(utteranceId: String?) {
                        isSpeaking = true
                        statusMessage = "Speaking... 🎧"
                    }

                    override fun onDone(utteranceId: String?) {
                        isSpeaking = false
                        statusMessage = "Finished! Tap to play again."
                    }

                    @Deprecated("Deprecated in Java")
                    override fun onError(utteranceId: String?) {
                        isSpeaking = false
                        statusMessage = "Audio playback error."
                    }
                })

                isInitialized = true
                statusMessage = "Press play to hear the directions."
            } else {
                Log.e("SpeechManager", "TTS initialization failed.")
                statusMessage = "Audio not supported on this device."
            }
        }
    }

    fun speak(text: String) {
        if (!isInitialized) return
        tts?.stop()
        val utteranceId = "utterance_${System.currentTimeMillis()}"
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId)
    }

    fun stop() {
        tts?.stop()
        isSpeaking = false
        statusMessage = "Audio stopped."
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
        tts = null
    }
}

@Composable
fun rememberSpeechManager(): SpeechManager {
    val context = LocalContext.current
    val speechManager = remember { SpeechManager(context) }
    DisposableEffect(speechManager) {
        onDispose {
            speechManager.shutdown()
        }
    }
    return speechManager
}
