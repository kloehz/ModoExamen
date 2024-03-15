import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.modoexamen.features.home.domain.repository.HomeRepository
import com.example.modoexamen.features.home.presentation.viewmodel.HomeViewModel
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AccountFragmentTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: HomeViewModel
    private lateinit var repository: HomeRepository

    @Before
    fun setup() {
        repository = mockk()
        viewModel = HomeViewModel(repository)
    }

    @Test
    fun `account test`() = runBlocking {
        viewModel.getMe()
    }

}
