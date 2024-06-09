package simsek.ali.VeterinaryManagementProject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import simsek.ali.VeterinaryManagementProject.entity.Vaccination;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VaccinationRepository extends JpaRepository<Vaccination, Long> {

    List<Vaccination> findByNameAndCodeAndAnimalIdAndProtectionFinishDateGreaterThanEqual(String name, String code, Long id, LocalDate protectionStartDate);

    @Query(value = "Select v from Vaccination v where  v.animal.name= :name")
    Page<Vaccination> findByAnimalName(String name, Pageable pageable);

    @Query(value = "select v from Vaccination v where :startDate >= v.protectionStartDate  and :endDate <= v.protectionFinishDate")
    Page<Vaccination> findByDate(LocalDate startDate, LocalDate endDate, Pageable pageable);

    Page<Vaccination> findByName(String name, Pageable pageable);

    Page<Vaccination> findByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
}
