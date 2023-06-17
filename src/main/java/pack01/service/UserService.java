package pack01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pack01.domain.Department;
import pack01.domain.User;
import pack01.domain.type.RoleType;
import pack01.dto.user.response.UserDepartmentResponse;
import pack01.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final DepartmentService departmentService;

    @Autowired
    public UserService(UserRepository userRepository, DepartmentService departmentService) {
        this.userRepository = userRepository;
        this.departmentService = departmentService;
    }

    public Long save(User user) {
        User findUser = userRepository.findByEmail(user.getEmail());
        if (findUser == null) {
            if (user.getDepartmentId() == null) {
                List<Department> departments = departmentService.findByName("무소속");
                if (departments.size() > 0) {
                    user.setDepartmentId(departments.get(0).getDepartmentId());
                } else {
                    Long deptId = departmentService.save(new Department("무소속", "0", "0", "0", 0, 0));
                    user.setDepartmentId(deptId);
                }
            }
            return userRepository.save(user);
        }
        return null;
    }

    public User findById(Long userId) {
        try {
            return userRepository.findById(userId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public User findByEmailAndPassword(String email, String password) {
        try {
            return userRepository.findByEmailAndPassword(email, password);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void update(User user) {
        userRepository.update(user);
    }

    public void delete(Long userId) {
        userRepository.delete(userId);
    }

    public void updateDepartmentByUserId(Long applicantId, Long departmentId) {
        userRepository.updateDepartmentByUserId(applicantId, departmentId);
    }

    public List<UserDepartmentResponse> findByRole(RoleType roleType, Long departmentId) {
        return userRepository.findByRole(roleType, departmentId);
    }
}