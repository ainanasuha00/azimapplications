package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.ExplorerRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

  @Test
  fun readStringFromContext() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("The Explorer's Map", appName)
  }

  @Test
  fun explorerRepositoryHasRequiredData() {
    assertEquals(8, ExplorerRepository.townPlaces.size)
    assertEquals(6, ExplorerRepository.directionWords.size)
    assertEquals(3, ExplorerRepository.listeningQuestions.size)
    assertTrue(ExplorerRepository.whereAmIClues.isNotEmpty())
    assertTrue(ExplorerRepository.rescueMissions.isNotEmpty())
  }
}
