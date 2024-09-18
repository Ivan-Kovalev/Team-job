package pro.sky.TeamJob.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.TeamJob.dto.ProjectInfo;

@RestController
@RequestMapping("/api/v1/management/")
@RequiredArgsConstructor
public class InformController {

    private final BuildProperties buildProperties;

    @GetMapping("info/")
    private ResponseEntity<ProjectInfo> getInfoAboutProject() {
        return ResponseEntity.ok().body(new ProjectInfo(buildProperties.getName(), buildProperties.getVersion()));
    }

}
