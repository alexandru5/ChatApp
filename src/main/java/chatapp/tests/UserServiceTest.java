package chatapp.tests;

import chatapp.dao.repositories.UserRepoInterface;
import chatapp.dao.services.UserService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class UserServiceTest {

    @Mock
    UserRepoInterface repoMock;

    @InjectMocks
    UserService servMock;

//    @Before
//    public void setUp() throws Exception {
//
//        MockitoAnnotations.initMocks(this);
//    }
}
