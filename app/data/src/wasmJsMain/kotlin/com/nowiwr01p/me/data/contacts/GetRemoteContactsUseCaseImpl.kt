import com.nowiwr01p.me.domain.contacts.GetContactsUseCase
import com.nowiwr01p.me.shared.ContactDomain

class GetRemoteContactsUseCaseImpl : GetContactsUseCase {

    override suspend fun execute(input: Unit): List<ContactDomain> {
        TODO()
    }
}