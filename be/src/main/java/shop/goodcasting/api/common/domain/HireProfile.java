package shop.goodcasting.api.common.domain;

import lombok.Data;
import shop.goodcasting.api.article.hire.domain.Hire;
import shop.goodcasting.api.article.profile.domain.Profile;

import javax.persistence.*;

@Table(name = "hire_profiles")
@Entity
public class HireProfile extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hire_profile_id")
    private long hireProfileId;

    @ManyToOne
    @JoinColumn(name = "hire_id")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Hire hire;
}

