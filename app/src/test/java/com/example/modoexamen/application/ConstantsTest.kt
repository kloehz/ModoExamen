import com.example.modoexamen.application.Constants
import com.example.modoexamen.application.FAKE_LOGIN_DATA
import com.example.modoexamen.features.login.data.model.LoginRequest
import org.junit.Assert.assertEquals
import org.junit.Test

class ConstantsTest {

    @Test
    fun `check FAKE_LOGIN_DATA values`() {
        val expectedLoginData = LoginRequest(
            "39549621",
            "kloehzmu@gmail.com",
            "+5492213057172",
            "M",
            "",
            "Password"
        )

        assertEquals(expectedLoginData, FAKE_LOGIN_DATA)
    }
}
