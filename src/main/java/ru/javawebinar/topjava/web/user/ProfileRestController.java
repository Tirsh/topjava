package ru.javawebinar.topjava.web.user;

import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.User;

import static ru.javawebinar.topjava.web.SecurityUtil.setAuthUserId;

@Controller
public class ProfileRestController extends AbstractUserController {

    public User get() {
        return super.get(setAuthUserId());
    }

    public void delete() {
        super.delete(setAuthUserId());
    }

    public void update(User user) {
        super.update(user, setAuthUserId());
    }
}