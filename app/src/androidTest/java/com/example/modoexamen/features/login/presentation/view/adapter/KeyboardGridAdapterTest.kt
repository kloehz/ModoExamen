import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import com.example.modoexamen.MainActivity
import com.example.modoexamen.R
import com.example.modoexamen.features.login.presentation.view.adapter.KeyboardGridAdapter
import com.example.modoexamen.features.login.utils.KeyboardUtils
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@RunWith(AndroidJUnit4::class)
class KeyboardGridAdapterTest {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testRecyclerViewItemClick() {
        activityScenarioRule.scenario.onActivity {

            val context = InstrumentationRegistry.getInstrumentation().targetContext

            val mockClickListener = object : KeyboardGridAdapter.OnNumberClickListener {
                override fun onNumberClick(itemPressed: String) {}
            }

            // Mock del objeto KeyboardUtils
            val mockKeyboardUtils = mock(KeyboardUtils::class.java)
            // Define el comportamiento deseado del método getBiometricIconSize()
            `when`(mockKeyboardUtils.getBiometricIconSize()).thenReturn(100)

            // Configura el adaptador con el clic de prueba y el mock de KeyboardUtils
            val adapter =
                KeyboardGridAdapter(getMockNumbersList(), mockClickListener, mockKeyboardUtils)

            // Inicializa el RecyclerView y establece el adaptador
            val recyclerView = RecyclerView(context)
            runOnUiThread {
                recyclerView.adapter = adapter
                onView(withId(R.id.grid_container))
                    .perform(
                        RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                            0,
                            click()
                        )
                    )
            }
        }
    }
    private fun getMockNumbersList(): List<Pair<String, String>> {
        return listOf(
            Pair("1", ""),
            Pair("2", ""),
            Pair("3", ""),
        )
    }
}
