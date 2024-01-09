package com.example.shopbackend.service.imp;

import com.example.shopbackend.dto.OrderDto;
import com.example.shopbackend.dto.UserDto;
import com.example.shopbackend.exception.DuplicatedUser;
import com.example.shopbackend.exception.InvalidUser;
import com.example.shopbackend.exception.ProductNotFound;
import com.example.shopbackend.exception.UserNotFound;
import com.example.shopbackend.model.Cart;
import com.example.shopbackend.model.Order;
import com.example.shopbackend.model.Product;
import com.example.shopbackend.model.User;
import com.example.shopbackend.repository.ProductRepository;
import com.example.shopbackend.repository.UserRepository;
import com.example.shopbackend.service.CartService;
import com.example.shopbackend.service.ProductService;
import com.example.shopbackend.service.UserService;
import com.example.shopbackend.util.Convert;
import com.example.shopbackend.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final ProductService productService;
    private final CartService cartService;
    @Autowired
    public UserServiceImp(UserRepository userRepository,
                          ProductService productService,
                          CartService cartService) {
        this.userRepository = userRepository;
        this.productService = productService;
        this.cartService = cartService;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public User createUser(UserDto userDto) throws DuplicatedUser {
        User user = Convert.dtoToUser(userDto);
        user.encryptPassword();
        user.createCart();
        checkDuplicatedUser(user);
        user.setRole(Role.USER.name());
        return userRepository.save(user);
    }

    public void checkDuplicatedUser(User user) throws DuplicatedUser {
        String username = user.getUsername();
        String email = user.getEmail();
        String phoneNumber = user.getPhoneNumber();
        long id = user.getId();

        Optional<User> userFindByUsername = findByUsername(username);
        Optional<User> userFindByEmail = findByEmail(email);
        Optional<User> userFindByPhoneNumber = findByPhoneNumber(phoneNumber);

        if (userFindByUsername.isPresent()) {
            if (id != userFindByUsername.get().getId()) {
                throw new DuplicatedUser("Duplicated username");
            }
        }
        if (userFindByEmail.isPresent()) {
            if (id != userFindByEmail.get().getId()) {
                throw new DuplicatedUser("Duplicated email");
            }
        }
        if (userFindByPhoneNumber.isPresent()) {
            if (id != userFindByPhoneNumber.get().getId()) {
                throw new DuplicatedUser("Duplicated phone number");
            }
        }
    }

    @Override
    public User updateUser(UserDto userDto) throws UserNotFound, DuplicatedUser {
        User user = Convert.dtoToUser(userDto);
        Optional<User> databaseUser = findByUsername(user.getUsername());
        if (databaseUser.isPresent()) {
            user.setPassword(databaseUser.get().getPassword());
            checkDuplicatedUser(user);
            return userRepository.save(user);
        }
        throw new UserNotFound("User not found");
    }

    @Override
    public User changeUserPassword(UserDto userDto) throws UserNotFound{
        Optional<User> userFromDatabase = findById(userDto.getId());
        if (userFromDatabase.isPresent() && isEqualWithDatabaseUser(userDto)) {
            String newPass = userDto.getPassword();
            User currentUser = userFromDatabase.get();
            currentUser.setPassword(newPass);
            currentUser.encryptPassword();
            userRepository.save(currentUser);
            return currentUser;
        }
        throw new UserNotFound("User not found");
    }

    public boolean isEqualWithDatabaseUser(UserDto userDto) throws UserNotFound {
        Optional<User> databaseUser = userRepository.findByUsername(userDto.getUsername());
        if (databaseUser.isPresent()) {
            String username = userDto.getUsername();
            String databaseUsername = databaseUser.get().getUsername();
            long userId = userDto.getId();
            long databaseUserId = databaseUser.get().getId();
            String phoneNumber = userDto.getPhoneNumber();
            String databasePhoneNumber = databaseUser.get().getPhoneNumber();

            return username.equals(databaseUsername) &&
                    userId == databaseUserId &&
                    phoneNumber.equals(databasePhoneNumber);
        } else {
            throw new UserNotFound("User Not Found");
        }
    }

    public List<OrderDto> getOrderHistory(long userid) throws UserNotFound {
        Optional<User> user = userRepository.findById(userid);
        if (user.isPresent()) {
            List<Order> orders = user.get().getOrders();
            List<OrderDto> orderDtos = new ArrayList<>();
            for (Order order : orders) {
                orderDtos.add(Convert.orderToDto(order));
            }
            return orderDtos;
        }
        throw new UserNotFound("User Not Found");
    }

    public UserDto addProductToCart(long productId, UserDto userDto) throws UserNotFound,
            ProductNotFound,
            InvalidUser {
        if (isEqualWithDatabaseUser(userDto)) {
            Optional<User> user = userRepository.findById(userDto.getId());
            if (user.isPresent()) {
                Product product = productService.getProduct(productId);
                Cart userCart = user.get().getCart();
                updateCart(userCart, product);
                List<Cart> productCart = product.getCarts();
                productCart.add(userCart);
                product.setCarts(productCart);
                List<Product> products = user.get().getCart().getProducts();
                products.add(product);
                user.get().getCart().setProducts(products);

                return Convert.userToDto(userRepository.save(user.get()));
            }
        }
        throw new InvalidUser("Invalid user");
    }

    public void updateCart(Cart cart, Product product) {
        cart.setQuantity(cart.getQuantity() + 1);
        cart.setTotalPrice(cart.getTotalPrice() + product.getPrice());
        product.setQuantity(product.getQuantity() - 1);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }
}
