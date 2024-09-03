package com.example.insipirahub;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam("username") String username,
                               @RequestParam("email") String email,
                               @RequestParam("name") String name,
                               @RequestParam("password") String password,
                               Model model) {

        System.out.println("Registering user: " + username + ", " + email + ", " + name);
        // Check if username or email already exists
        if (userRepository.findByUsername(username) != null) {
            model.addAttribute("error", "Username already exists.");
            return "register";
        }

        if (userRepository.findByEmail(email) != null) {
            model.addAttribute("error", "Email already exists.");
            return "register";
        }

        // Create a new user and save to the database
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password); // Remember to hash the password before saving in production
        userRepository.save(user);

        System.out.println("User registered successfully: " + user);

        return "redirect:/"; // Redirect to a success page or home page
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("email") String email,
                            @RequestParam("password") String password,
                            HttpSession session, // Inject HttpSession
                            Model model) {
    
        // Find the user by email
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            // Successful login
            session.setAttribute("userEmail", user.getEmail()); // Store email in session
            session.setAttribute("userName", user.getName()); // Store name in session
            return "redirect:/home"; // Redirect to home without passing email in URL
        } else {
            // Invalid credentials
            model.addAttribute("error", "Invalid email or password. Please try again.");
            return "login"; // Stay on the login page if login fails
        }
    }
    
    @GetMapping("/home")
    public String showHome(HttpSession session, Model model) {
        String email = (String) session.getAttribute("userEmail"); // Get email from session
        String name = (String) session.getAttribute("userName"); // Get name from session

        if (email != null) {
            model.addAttribute("name", name);
            model.addAttribute("email", email);
        } else {
            return "redirect:/login"; // Redirect to login if user is not logged in
        }
        return "home"; // Return the home page
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
