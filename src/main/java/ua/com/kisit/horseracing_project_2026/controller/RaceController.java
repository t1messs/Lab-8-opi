package ua.com.kisit.horseracing_project_2026.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.kisit.horseracing_project_2026.service.RaceService;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class RaceController {

    private final RaceService raceService;
}
