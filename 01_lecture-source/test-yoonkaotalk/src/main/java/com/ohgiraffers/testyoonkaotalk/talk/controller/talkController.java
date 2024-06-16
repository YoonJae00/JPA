package com.ohgiraffers.testyoonkaotalk.talk.controller;

import com.ohgiraffers.testyoonkaotalk.talk.dto.UserDTO;
import com.ohgiraffers.testyoonkaotalk.talk.entity.User;
import com.ohgiraffers.testyoonkaotalk.talk.service.TalkService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class talkController {

    @Autowired
    private TalkService talkService;

    @GetMapping("/regist")
    public String register() {
        return "user/regist";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserDTO userDTO) {

        talkService.regist(userDTO);

        return "redirect:/main";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<UserDTO> userDTO = talkService.getList();

        System.out.println("userDTO = " + userDTO);
        model.addAttribute("user", userDTO);
        return "user/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int userCode) {
        System.out.println("userCode = " + userCode);
        talkService.delete(userCode);

        return "redirect:/list";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam int userCode, Model model) {

        User user = talkService.findbyId(userCode);

        model.addAttribute("user", user);
        System.out.println("user = " + user);
        return "user/detail";
    }

    @GetMapping("/chat")
    public String chatTest() {

        return "user/websocket";
    }
}
