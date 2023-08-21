package judemy.fiantso.service.VideoService;

import judemy.fiantso.models.Videos;

import java.util.List;

public interface VideoService {
    Videos createVideo(Videos video);
    List<Videos> getAllVideos();
    Videos getVideoById(Long videoId);
    Videos updateVideo(Videos video);
    void deleteVideo(Long video);
}
