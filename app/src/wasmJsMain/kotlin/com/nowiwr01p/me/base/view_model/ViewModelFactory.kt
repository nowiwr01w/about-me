import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.nowiwr01p.me.base.view_model.BaseViewModel
import org.koin.compose.getKoin
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

private typealias BaseVM = BaseViewModel<*, *, *>

@Composable
inline fun <reified VM : BaseVM> rememberViewModel(vararg params: Any): VM {
    val scope = rememberCoroutineScope()
    val viewModel = koinInject<VM> { parametersOf(scope, *params) }
    return remember { viewModel }
}