package com.example.modoexamen.features.login.presentation.utils

import android.content.Context
import android.content.res.Resources
import org.junit.Test
import org.mockito.Mockito.`when`
import com.example.modoexamen.R
import com.example.modoexamen.features.login.utils.KeyboardUtils
import org.junit.Assert.assertEquals
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class KeyboardUtilsTest {
    @Mock
    lateinit var mockContext: Context
    @Mock
    lateinit var mockResources: Resources

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `getBiometricIconSize should return correct size`() {
        `when`(mockContext.resources).thenReturn(mockResources)
        `when`(mockContext.resources.getDimensionPixelOffset(R.dimen.default_icon_size)).thenReturn(100)
        val keyboardUtils = KeyboardUtils(mockContext)
        var result = keyboardUtils.getBiometricIconSize()

        assertEquals(100, result)
    }
}