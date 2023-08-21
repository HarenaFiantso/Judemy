package judemy.fiantso.controller;

import judemy.fiantso.models.Videos;
import judemy.fiantso.service.VideoService.VideoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {
    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping("/create")
    public ResponseEntity<Videos> createVideo(@RequestBody Videos video) {
        Videos createdVideo = videoService.createVideo(video);
        if (createdVideo != null) {
            return ResponseEntity.ok(createdVideo);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Videos>> getAllVideos() {
        List<Videos> videos = videoService.getAllVideos();
        return ResponseEntity.ok(videos);
    }

    @PutMapping("/{videoId}")
    public Videos updateVideo(@PathVariable Long videoId, @RequestBody Videos video) {
        video.setVideoId(videoId);
        return this.videoService.updateVideo(video);
    }

    @DeleteMapping("/{videoId}")
    public void deleteVideo(@PathVariable Long videoId) {
        this.videoService.deleteVideo(videoId);
    }
}
