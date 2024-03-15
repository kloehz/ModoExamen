import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.modoexamen.features.home.presentation.view.HomeFragment
import com.example.modoexamen.shared.components.NavBar.NavBarAdapter
import com.example.modoexamen.shared.components.NavBar.tabs
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class NavBarAdapterTest {
    @Mock
    private lateinit var mockFragmentManager: FragmentManager

    @Mock
    private lateinit var mockLifecycle: Lifecycle

    private lateinit var navBarAdapter: NavBarAdapter

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        navBarAdapter = NavBarAdapter(mockFragmentManager, mockLifecycle)
    }

    @Test
    fun testCreateFragment() {
        val fragment = navBarAdapter.createFragment(0)
        assertEquals(HomeFragment::class.java, fragment::class.java)
    }

    @Test
    fun testItemCount() {
        assertEquals(tabs.size, navBarAdapter.itemCount)
    }
}
