    if (bindingResult.hasErrors()) {
        bindingResult.getAllErrors().forEach(error -> System.out.println(error.toString()));
        return "user_create";
    }