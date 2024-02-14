package com.example.modoexamen.shared.utils
import android.view.View
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class ViewExtensionsTest {
    @Test
    fun `hide should set visibility to GONE`() {
        val mockView = mockk<View>(relaxed = true)
        mockView.hide()
        verify { mockView.visibility = View.GONE }
    }

    @Test
    fun `show should set visibility to VISIBLE`() {
        val mockView = mockk<View>(relaxed = true)
        mockView.show()
        verify { mockView.visibility = View.VISIBLE }
    }
}
