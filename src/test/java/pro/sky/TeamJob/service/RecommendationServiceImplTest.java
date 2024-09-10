package pro.sky.TeamJob.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.TeamJob.model.User;
import pro.sky.TeamJob.repository.ProductRepository;
import pro.sky.TeamJob.repository.UserRepository;
import pro.sky.TeamJob.service.impl.RecommendationServiceImpl;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class RecommendationServiceImplTest {

    RecommendationServiceImpl recommendationService;

    @Mock
    private UserRepository userRepository;

    private List<User> usersThatAppropriateToProductInvest;
    private List<User> usersThatAppropriateToProductSaving;
    private List<User> usersThatAppropriateToProductSimpleCredit;

    @BeforeEach
    public void setUp() {
        recommendationService = new RecommendationServiceImpl(userRepository);
    }

    @BeforeEach
    public void reposInit() {

    }
}
