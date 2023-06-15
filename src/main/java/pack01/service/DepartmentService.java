package pack01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pack01.domain.Department;
import pack01.repository.DepartmentRepository;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) { this.departmentRepository = departmentRepository; }

    public Long save(Department department) { return departmentRepository.save(department); }
    public void update(Department department) { departmentRepository.update(department); }
    public void delete(Long departmentId) { departmentRepository.delete(departmentId); }
    public Department findById(Long departmentId) { return departmentRepository.findById(departmentId); }
    public List<Department> findByName(String departmentName) { return departmentRepository.findByName(departmentName); }
    public List<Department> findAll() { return departmentRepository.findAll(); }

}
