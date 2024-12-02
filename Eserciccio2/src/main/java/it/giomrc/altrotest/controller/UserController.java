package it.giomrc.altrotest.controller;


import it.giomrc.altrotest.service.UserService;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Metodo per gestire la logica delle richieste utente
}