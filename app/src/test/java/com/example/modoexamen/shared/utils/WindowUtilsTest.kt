package com.example.modoexamen.shared.utils

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class WindowUtilsTest {
    @Mock
    lateinit var mockContext: Context

    @Mock
    lateinit var mockResources: Resources

    @Mock
    lateinit var mockDisplayMetrics: DisplayMetrics

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `WindowUtils should be return if is small size device`(){
        Mockito.`when`(mockContext.resources).thenReturn(mockResources)
        // Mock Pixel XL
        mockDisplayMetrics.widthPixels = 1440
        mockDisplayMetrics.xdpi = 560f
        mockDisplayMetrics.heightPixels = 2476
        mockDisplayMetrics.ydpi = 560f
        Mockito.`when`(mockContext.resources.displayMetrics).thenReturn(mockDisplayMetrics)
        val isSmall = isSmallScreen(mockContext)
        assertEquals(true, isSmall)
    }


    @Test
    fun `WindowUtils should be return if is NOT small size device`(){
        Mockito.`when`(mockContext.resources).thenReturn(mockResources)
        // Mock Pixel 7 Pro
        mockDisplayMetrics.widthPixels = 1440
        mockDisplayMetrics.xdpi = 560f
        mockDisplayMetrics.heightPixels = 3076
        mockDisplayMetrics.ydpi = 560f
        Mockito.`when`(mockContext.resources.displayMetrics).thenReturn(mockDisplayMetrics)
        val isSmall = isSmallScreen(mockContext)
        assertEquals(false, isSmall)
    }
}