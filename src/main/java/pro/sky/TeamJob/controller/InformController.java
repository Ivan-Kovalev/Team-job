package pro.sky.TeamJob.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/management/")
@RequiredArgsConstructor
public class InformController {

    @GetMapping("info/")
    private void getInfoAboutProject() {

    }

}
