package simsek.ali.VeterinaryManagementProject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import simsek.ali.VeterinaryManagementProject.entity.Appointment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Optional<Appointment> findByAppointmentDateAndDoctorIdAndAnimalId(LocalDateTime date, Long id, Long id1);

    Optional<Appointment> findByAppointmentDateAndDoctorId(LocalDateTime date, Long id);


    @Query(value = "select  a from Appointment a where a.appointmentDate= :appointmentDate")
    Page<Appointment> findByAppointmentDate(LocalDateTime appointmentDate, Pageable pageable);

    @Query(value = "select  a from Appointment a where a.appointmentDate= :appointmentDate and a.doctor.id = :doctorId")
    Page<Appointment> findByDoctorIdAndAppointmentDate(Long doctorId, LocalDateTime appointmentDate, Pageable pageable);

    @Query(value = "select  a from Appointment a where a.appointmentDate= :appointmentDate and a.animal.id = :animalId")
    Page<Appointment> findByAnimalIdAndAppointmentDateBetween(Long animalId, LocalDateTime appointmentDate, Pageable pageable);
}
