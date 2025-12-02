package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.example.dto.UserRequestDTO;
import org.example.dto.UserResponseDTO;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;



    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })


    @PostMapping
    public UserResponseDTO createUser(@Valid @RequestBody UserRequestDTO request) {

        User user = new User(request.getName());
        User saved = userService.createUser(user);

        return new UserResponseDTO(saved.getId(), saved.getName());
    }




    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetched all users")
    })

    @GetMapping
    public List<UserResponseDTO> getAll() {
        return userService.getAllUsers()
                .stream()
                .map(user -> new UserResponseDTO(user.getId(), user.getName()))
                .toList();
    }


    @Operation(summary = "Update a user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })

    // UPDATE USER
    @PutMapping("/{id}")
    public UserResponseDTO update(
            @PathVariable Long id,
            @Valid @RequestBody UserRequestDTO request) {

        User updated = userService.updateUser(id, new User(request.getName()));

        return new UserResponseDTO(updated.getId(), updated.getName());
    }


    @Operation(summary = "Delete a user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })


    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted";
    }
}
