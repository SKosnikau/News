package by.htp.ex.service;

import by.htp.ex.service.impl.AdminServiceImpl;
import by.htp.ex.service.impl.UserServiceImpl;

public class ServiceProvider {

    private static final ServiceProvider instance = new ServiceProvider();

    private ServiceProvider() {
    }

    public static ServiceProvider getInstance() {
        return instance;
    }

    private final UserService userService = new UserServiceImpl();
    private final AdminService adminService = new AdminServiceImpl();

    public UserService getUserService() {
        return userService;
    }

    public AdminService adminService() {
        return adminService;
    }
}
