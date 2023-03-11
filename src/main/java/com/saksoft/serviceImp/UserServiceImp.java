package com.saksoft.serviceImp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saksoft.dto.UserDTO;
import com.saksoft.model.User;
import com.saksoft.repository.UserRepository;
import com.saksoft.service.UserService;


@Service
public class UserServiceImp implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public Map<String, Object> createUser(UserDTO userDTO) {

		User user = new User();
		Map<String, Object> resultmap = new HashMap<String, Object>();

		user.setFirstname(userDTO.getFirstname());
		user.setLastname(userDTO.getLastname());
		user.setEmail(userDTO.getEmail());
		user.setAge(userDTO.getAge());
		try {
			User saveUser = userRepository.save(user);
			resultmap.put("message", "User created successfully with the id: " + saveUser.getUserId());
			resultmap.put("result", "success");
			return resultmap;
		} catch (Exception e) {
			resultmap.put("errorMessage", "User not created");
			resultmap.put("result", "failed");
			return resultmap;
		}
	}

	@Override
	public Map<String, Object> updateUser(Long id, UserDTO userDTO) {

		Map<String, Object> resultmap = new HashMap<String, Object>();
		Optional<User> checkUser = userRepository.findById(id);
		if (checkUser.isPresent()) {
			User user = checkUser.get();
			user.setFirstname(userDTO.getFirstname());
			user.setLastname(userDTO.getLastname());
			user.setEmail(userDTO.getEmail());
			user.setAge(userDTO.getAge());
			userRepository.save(user);
			resultmap.put("message", "User updated successfully");
			resultmap.put("result", "success");
			return resultmap;
		} else {
			resultmap.put("errorMessage", "User not found with the id " + id);
			resultmap.put("result", "failed");
			return resultmap;
		}
	}

	@Override
	public Map<String, Object> getUserById(Long id) {
		Optional<User> checkUser = userRepository.findById(id);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (checkUser.isPresent()) {
			UserDTO userDTO = new UserDTO();
			User user = checkUser.get();
			userDTO.setId(user.getUserId());
			userDTO.setFirstname(user.getFirstname());
			userDTO.setLastname(user.getLastname());
			userDTO.setEmail(user.getEmail());
			userDTO.setAge(user.getAge());
			resultmap.put("result", "success");
			resultmap.put("user", userDTO);
			return resultmap;
		} else {
			resultmap.put("errorMessage", "User not found with the id " + id);
			resultmap.put("result", "failed");
			return resultmap;
		}
	}

	@Override
	public Map<String, Object> deleteUserById(Long id) {

		Optional<User> checkUser = userRepository.findById(id);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (checkUser.isPresent()) {
			User user = checkUser.get();
			userRepository.delete(user);
			resultmap.put("message", "User deleted successfully");
			return resultmap;
		} else {
			resultmap.put("errorMessage", "User not found with the id " + id);
			resultmap.put("result", "failed");
			return resultmap;
		}
	}

	@Override
	public List<UserDTO> getUserList() {
		List<User> findAll = userRepository.findAll();
		List<UserDTO> userList = new ArrayList<UserDTO>();

		for (User user : findAll) {
			UserDTO dto = new UserDTO();
			dto.setId(user.getUserId());
			dto.setFirstname(user.getFirstname());
			dto.setLastname(user.getLastname());
			dto.setEmail(user.getEmail());
			dto.setAge(user.getAge());
			userList.add(dto);
		}

		return userList;
	}
	
}
