package pl.coderslab.charity.donation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query(value = "SELECT SUM(quantity) from donation;", nativeQuery = true)
    Integer findTotalAmountOfDonatedBags();

    @Query("select count(d) from Donation d")
    Integer findTotalAmountOfDonations();

}
