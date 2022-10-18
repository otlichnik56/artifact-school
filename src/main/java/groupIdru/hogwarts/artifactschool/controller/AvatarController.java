package groupIdru.hogwarts.artifactschool.controller;


import groupIdru.hogwarts.artifactschool.model.Avatar;
import groupIdru.hogwarts.artifactschool.service.AvatarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("avatars")
public class AvatarController {

    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @GetMapping
    public ResponseEntity<Collection<Avatar>> getAllAvatars(@RequestParam ("page") Integer pageNumber,
                                                      @RequestParam ("size") Integer pageSize) {
        Collection<Avatar> avatars = avatarService.getAllAvatar(pageNumber, pageSize);
        return ResponseEntity.ok(avatars);

    }

}
