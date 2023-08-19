package judemy.fiantso.controller;

import judemy.fiantso.models.Users;
import judemy.fiantso.service.userService.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public List<Users> getUsers() {
        return this.userService.getUsers();
    }

    @GetMapping("/{id}")
    public Users getUser(@PathVariable int id) {
        return this.userService.getUser(id);
    }

    @PostMapping("")
    public Users insert(@RequestBody Users u) {
        return this.userService.addUser(u);
    }

    @PutMapping("/{id}")
    public Users updateUser(@PathVariable int id, @RequestBody Users u) {
        u.setUser_id(id);
        return this.userService.updateUser(u);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        this.userService.deleteUser(id);
    }
}
