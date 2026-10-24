/*
 * Copyright (c)2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jetpackcomposeplayground.samples.unscramble.ui.test

import com.example.jetpackcomposeplayground.samples.unscramble.data.MAX_NO_OF_WORDS
import com.example.jetpackcomposeplayground.samples.unscramble.data.SCORE_INCREASE
import com.example.jetpackcomposeplayground.samples.unscramble.data.getUnscrambledWord
import com.example.jetpackcomposeplayground.samples.unscramble.ui.GameViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GameViewModelTest {
    private val viewModel = GameViewModel()

    @Test
    fun gameViewModel_Initialization_FirstWordLoaded() {
        /**
         *  Uyarı: uiState'i bu şekilde almak, MutableStateFlow kullanıldığı için çalışmaktadır. İlerleyen
         *  ünitelerde, bir veri akışı oluşturan ve akışa tepki vermenizi gerektiren gelişmiş StateFlow
         *  kullanımlarını öğreneceksiniz. Bu tür senaryolar için farklı yöntemler/yaklaşımlar kullanarak
         *  unit testler yazacaksınız. Bu durum, bu sınıftaki tüm viewModel.uiState.value kullanımları için geçerlidir.
         **/
        val gameUiState = viewModel.uiState.value
        val unScrambledWord = getUnscrambledWord(gameUiState.currentScrambledWord)

        // Mevcut kelimenin karıştırıldığını doğrula.
        assertNotEquals(unScrambledWord, gameUiState.currentScrambledWord)
        // Mevcut kelime sayacının 1 olarak ayarlandığını doğrula.
        assertTrue(gameUiState.currentWordCount == 1)
        // Başlangıçta skorun 0 olduğunu doğrula.
        assertTrue(gameUiState.score == 0)
        // Yanlış kelime tahmin bayrağının false olduğunu doğrula.
        assertFalse(gameUiState.isGuessedWordWrong)
        // Oyunun bitmediğini doğrula.
        assertFalse(gameUiState.isGameOver)
    }

    @Test
    fun gameViewModel_IncorrectGuess_ErrorFlagSet() {
        // Girdi olarak yanlış bir kelime verildiğinde
        val incorrectPlayerWord = "and"

        viewModel.updateUserGuess(incorrectPlayerWord)
        viewModel.checkUserGuess()

        val currentGameUiState = viewModel.uiState.value
        // Skorun değişmediğini doğrula
        assertEquals(0, currentGameUiState.score)
        // checkUserGuess() metodunun isGuessedWordWrong bayrağını doğru şekilde güncellediğini doğrula
        assertTrue(currentGameUiState.isGuessedWordWrong)
    }

    @Test
    fun gameViewModel_CorrectWordGuessed_ScoreUpdatedAndErrorFlagUnset() {
        var currentGameUiState = viewModel.uiState.value
        val correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)

        viewModel.updateUserGuess(correctPlayerWord)
        viewModel.checkUserGuess()
        currentGameUiState = viewModel.uiState.value

        // checkUserGuess() metodunun isGuessedWordWrong bayrağını doğru şekilde güncellediğini doğrula.
        assertFalse(currentGameUiState.isGuessedWordWrong)
        // Skorun doğru şekilde güncellendiğini doğrula.
        assertEquals(SCORE_AFTER_FIRST_CORRECT_ANSWER, currentGameUiState.score)
    }

    @Test
    fun gameViewModel_WordSkipped_ScoreUnchangedAndWordCountIncreased() {
        var currentGameUiState = viewModel.uiState.value
        val correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)

        viewModel.updateUserGuess(correctPlayerWord)
        viewModel.checkUserGuess()
        currentGameUiState = viewModel.uiState.value
        val lastWordCount = currentGameUiState.currentWordCount

        viewModel.skipWord()
        currentGameUiState = viewModel.uiState.value
        // Kelime atlandıktan sonra skorun değişmediğini doğrula.
        assertEquals(SCORE_AFTER_FIRST_CORRECT_ANSWER, currentGameUiState.score)
        // Kelime atlandıktan sonra kelime sayacının 1 arttığını doğrula.
        assertEquals(lastWordCount + 1, currentGameUiState.currentWordCount)
    }

    @Test
    fun gameViewModel_AllWordsGuessed_UiStateUpdatedCorrectly() {
        var expectedScore = 0
        var currentGameUiState = viewModel.uiState.value
        var correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)

        repeat(MAX_NO_OF_WORDS) {
            expectedScore += SCORE_INCREASE
            viewModel.updateUserGuess(correctPlayerWord)
            viewModel.checkUserGuess()
            currentGameUiState = viewModel.uiState.value
            if (!currentGameUiState.isGameOver) {
                correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)
            }
            // Her doğru cevaptan sonra skorun doğru güncellendiğini doğrula.
            assertEquals(expectedScore, currentGameUiState.score)
        }
        // Tüm sorular yanıtlandıktan sonra güncel kelime sayacının güncel olduğunu doğrula.
        assertEquals(MAX_NO_OF_WORDS, currentGameUiState.currentWordCount)
        // 10 soru yanıtlandıktan sonra oyunun bittiğini doğrula.
        assertTrue(currentGameUiState.isGameOver)
    }

    companion object {
        private const val SCORE_AFTER_FIRST_CORRECT_ANSWER = SCORE_INCREASE
    }
}
