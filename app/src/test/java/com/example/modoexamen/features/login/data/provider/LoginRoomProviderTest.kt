import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.modoexamen.features.login.data.provider.LoginRoomProvider
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LoginRoomProviderTest {
    private lateinit var provider: LoginRoomProvider

    @Before
    fun setup() {
        provider = LoginRoomProvider()
    }

    @Test
    fun `initialize should create LoggedUserDatabase with correct name`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        provider.initialize(context)
        assertEquals("logged_user_database", LoginRoomProvider.instance.openHelper.databaseName)
    }
}
