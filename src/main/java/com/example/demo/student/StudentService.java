package com.example.demo.student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public List<Student> getStudents(){
		return studentRepository.findAll();
	}

    public void addNewStudent(Student student) {
		Optional<Student> studentByEmail = studentRepository.findByMail(student.getMail());
		if(studentByEmail.isPresent())
			throw new IllegalStateException("email taken");
		
		studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
		boolean exist = studentRepository.existsById(id);
		if(!exist){
			throw new IllegalStateException("student with id "+id+" doesn't exist");
		}
		studentRepository.deleteById(id);
    }

	@Transactional
    public void updateStudent(Long studentId, String name, String email) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new IllegalStateException("student with id:"+studentId+"not exist"));
		
		if(name != null && name.length() > 0 && student.getName()!=name)
			student.setName(name);
		
		if(email != null && email.length() > 0 && student.getMail()!=email){
			Optional<Student> optionalStudent = studentRepository.findByMail(email);

			if(optionalStudent.isPresent())
				throw new IllegalStateException("email taken");
			
			student.setMail(email);
		}
    }
}
