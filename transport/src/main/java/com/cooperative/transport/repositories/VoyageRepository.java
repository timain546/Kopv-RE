    package com.cooperative.transport.repositories;

    import org.springframework.stereotype.Repository;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    @Repository
    public class VoyageRepository {
        public interface VoyageRepository extends JpaRepository<Voyage, Long> {
            @Query("SELECT v FROM Voyage v")
            List<Voyage> findAllVoyages();
        }
    }