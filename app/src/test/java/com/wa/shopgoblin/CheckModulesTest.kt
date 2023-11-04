package com.wa.shopgoblin

import com.wa.shopgoblin.di.appModule
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.verify.verify

class CheckModulesTest : KoinTest {

    @Before
    fun startKoinForTest() {
        startKoin {
            modules(appModule)
        }
    }

    @After
    fun stopKoinAfterTest() = stopKoin()

    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun checkAllModules() {
        appModule.verify()
    }
}