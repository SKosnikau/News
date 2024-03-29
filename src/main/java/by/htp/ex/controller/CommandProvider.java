package by.htp.ex.controller;

import java.util.HashMap;
import java.util.Map;

import by.htp.ex.controller.impl.*;

public class CommandProvider {
    private Map<CommandName, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandName.GO_TO_BASE_PAGE, new GoToBasePage());
        commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPage());
        commands.put(CommandName.DO_SIGN_IN, new DoSignIn());
        commands.put(CommandName.DO_SIGN_OUT, new DoSignOut());
        commands.put(CommandName.GO_TO_NEWS_LIST, new GoToNewsList());
        commands.put(CommandName.GO_TO_VIEW_NEWS, new GoToViewNews());
        commands.put(CommandName.DO_REGISTRATION, new DoRegistration());
        commands.put(CommandName.GO_TO_ADD_NEWS_PAGE, new GoToAddNewsPage());
        commands.put(CommandName.ADD_NEWS, new AddNews());
    }

    public Command getCommand(String name) {
        CommandName commandName = CommandName.valueOf(name.toUpperCase());
        Command command = commands.get(commandName);
        return command;
    }
}