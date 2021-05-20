package shop.goodcasting.api.profile;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shop.goodcasting.api.article.profile.domain.ProfileDTO;
import shop.goodcasting.api.article.profile.service.ProfileService;

@SpringBootTest
public class ProfileServiceTests {

    @Autowired
    private ProfileService service;

    @Test
    public void testGetProfile() {

//        ProfileDTO dto = service.entity2Dto();
    }
}