package com.saksoft.service;

import java.util.List;
import java.util.Map;
import com.saksoft.dto.UserDTO;

public interface UserService {
	
	public Map<String, Object> createUser(UserDTO userDTO);
	public Map<String, Object> updateUser(Long id, UserDTO userDTO);
	public Map<String, Object> getUserById(Long id);
	public Map<String, Object> deleteUserById(Long id);
	public List<UserDTO> getUserList();

}
