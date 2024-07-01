package com.examplechallengebinarc4.challengebinarc4.Service.impl;

import com.examplechallengebinarc4.challengebinarc4.Entity.Product;
import com.examplechallengebinarc4.challengebinarc4.Entity.User;
import com.examplechallengebinarc4.challengebinarc4.Repository.ProductRepository;
import com.examplechallengebinarc4.challengebinarc4.Repository.UserRepository;
import com.examplechallengebinarc4.challengebinarc4.Service.UserService;
import com.examplechallengebinarc4.challengebinarc4.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.*;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private Response response;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Map addUser(User user) {
        try {
            if (response.checkNull(user.getUsername())) {
                return response.eror("username is required.", 402);
            }
            if (StringUtils.isEmpty(user.getUsername())) {
                return response.eror("Username is required.", 402);
            }
            if (response.checkNull(user.getEmailAddress())) {
                return response.eror("Email is required.", 402);
            }
            if (StringUtils.isEmpty(user.getEmailAddress())) {
                return response.eror("Email is required.", 402);
            }
            if (response.checkNull(user.getPassword())) {
                return response.eror("Pasword is required.", 402);
            }
            if (StringUtils.isEmpty(user.getPassword())) {
                return response.eror("Password is required.", 402);
            }
            return response.sucsess(userRepository.save(user));
        } catch (Exception e) {
            return response.eror("An error occurred while saving Product.", 500);
        }
    }


    @Override
    public Map updateUser(UUID userId, User user)  {
        try {
            Optional<User> existingUser = Optional.ofNullable(userRepository.getByIdUser(userId));
            if (!response.checkNull(user.getId())) {
                if (existingUser.isPresent()) {
                    User edit = existingUser.get();
                    edit.setUsername(user.getUsername());
                    edit.setEmailAddress(user.getEmailAddress());
                    if (user.getPassword() != null) {
                        edit.setPassword(user.getPassword());
                    }
                    return response.sucsess(userRepository.save(edit));
                } else {
                    return response.eror("User not found", 404);
                }
            } else {
                return response.eror("Id is required.", 402);
            }
        } catch (Exception e) {
            return response.eror(e.getMessage(), 500);
        }
    }

    @Override
    public Map deleteUser(UUID userId) {
        try {
            Optional<User> findUserOptional = Optional.ofNullable(userRepository.getByIdUser(userId));

            if (findUserOptional.isPresent()) {
                User user = findUserOptional.get();
                userRepository.delete(user);
                return response.sucsess("data has been deleted");

            } else {
                return response.eror("id not found", 404);
            }
        } catch (DataAccessException e) {
            return response.eror("An error occurred while deleting order", 500);
        }
    }

    @Override
    public Map getUserById(UUID id) {
        Map map = new HashMap();
        Optional<User> getId = userRepository.findById(id);
        if (!getId.isPresent()) {
            return response.eror("id not found", 404);
        }
        return response.sucsess(getId.get());
    }
    @Override
    public Map pagination(int page, int size)  {
        Pageable show_data = PageRequest.of(page,size, Sort.by("id").descending());
        Page<User> list = userRepository.getAllDataPage(show_data);
        return response.sucsess(list);
    }
    @Override
    public Optional<List<User>> getAllUser() {
        List<User> userList = userRepository.findAll();
        return Optional.ofNullable(userList.isEmpty()? null : userList);
    }

}
