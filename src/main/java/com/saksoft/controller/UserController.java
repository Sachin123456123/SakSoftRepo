package com.saksoft.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saksoft.dto.UserDTO;
import com.saksoft.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	/**
	 * @param userDTO
	 * @return
	 */
	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {

		Map<String, Object> createUser = userService.createUser(userDTO);
		if (createUser.containsKey("errorMessage")) {
			return new ResponseEntity<Object>(createUser, HttpStatus.EXPECTATION_FAILED);
		}
		return ResponseEntity.ok(createUser);
	}

	/**
	 * @param id
	 * @param userDTO
	 * @return
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {

		Map<String, Object> updateUser = userService.updateUser(id, userDTO);
		if (updateUser.containsKey("errorMessage")) {
			return new ResponseEntity<Object>(updateUser, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(updateUser);
	}

	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Long id) {

		Map<String, Object> user = userService.getUserById(id);
		if (user.containsKey("errorMessage")) {
			return new ResponseEntity<Object>(user, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(user);
	}

	/**
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> DeleteUser(@PathVariable Long id) {

		Map<String, Object> deleteUser = userService.deleteUserById(id);
		if (deleteUser.containsKey("errorMessage")) {
			return new ResponseEntity<Object>(deleteUser, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(deleteUser);
	}

	/**
	 * @return userList
	 */
	@GetMapping("/all")
	public ResponseEntity<?> getUsers() {

		List<UserDTO> userList = userService.getUserList();
		return ResponseEntity.ok(userList);
	}
}
