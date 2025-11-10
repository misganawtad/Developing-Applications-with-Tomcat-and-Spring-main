package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.entity.User;
import web.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }


    @GetMapping
    public String list(@RequestParam(value = "error", required = false) String error,
                       Model model) {
        model.addAttribute("users", service.findAll());
        if ("notfound".equals(error)) {
            model.addAttribute("errorMessage", "User not found or has been removed.");
        }
        return "users";
    }

    // Create form
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("user", new User());
        return "user-form";
    }

    // Create submit
    @PostMapping
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult br) {
        if (br.hasErrors()) return "user-form";
        service.save(user);
        return "redirect:/users";
    }

    // Edit form (null guard → redirect with message)
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        User u = service.findById(id);
        if (u == null) return "redirect:/users?error=notfound";
        model.addAttribute("user", u);
        return "user-form";
    }


    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute("user") @Valid User user,
                         BindingResult br) {
        if (br.hasErrors()) return "user-form";
        if (service.findById(id) == null) return "redirect:/users?error=notfound";
        user.setId(id);
        service.save(user);
        return "redirect:/users";
    }


    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        if (service.findById(id) == null) return "redirect:/users?error=notfound";
        service.deleteById(id);
        return "redirect:/users";
    }
}
