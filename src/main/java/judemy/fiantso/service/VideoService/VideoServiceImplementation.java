package judemy.fiantso.service.VideoService;

import judemy.fiantso.models.Videos;
import judemy.fiantso.repository.JudemyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImplementation implements VideoService {
    private final JudemyRepository<Videos> videoRepository;

    public VideoServiceImplementation(JudemyRepository<Videos> videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Override
    public Videos createVideo(Videos video) {
        return videoRepository.create(video);
    }

    @Override
    public List<Videos> getAllVideos() {
        return videoRepository.getAll();
    }

    @Override
    public Videos getVideoById(Long videoId) {
        return videoRepository.getById(videoId);
    }

    @Override
    public Videos updateVideo(Videos video) {
        return videoRepository.update(video);
    }

    @Override
    public void deleteVideo(Long video) {
        this.videoRepository.delete(video);
    }
}
