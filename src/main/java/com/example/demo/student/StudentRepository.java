package com.example.demo.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{ //Long è il tipo della primary key
    //viene convertito in un select con condizione di where
    Optional<Student> findByMail(String mail);
    Optional<Student> findById(Long Id);
}
