package by.htp.ex.controller;

import by.htp.ex.controller.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private Map<CommandName, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPage());
        commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPage());
        commands.put(CommandName.GO_TO_AUTHORIZATION_PAGE, new GoToAuthorizationPage());
        commands.put(CommandName.DO_REGISTRATION, new DoRegistration());
        commands.put(CommandName.DO_AUTHORIZATION, new DoAuthorization());
    }

    public Command getCommand(String name) {
        CommandName commandName = CommandName.valueOf(name.toUpperCase());
        Command command = commands.get(commandName);
        return command;
    }
}