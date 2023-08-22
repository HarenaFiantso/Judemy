package judemy.fiantso.judemy.ServiceTests;

import judemy.fiantso.models.Videos;
import judemy.fiantso.repository.JudemyRepository;
import judemy.fiantso.service.VideoService.VideoService;
import judemy.fiantso.service.VideoService.VideoServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class VideoServiceImplementationTest {

    @Mock
    private JudemyRepository<Videos> videoRepositoryMock;

    private VideoService videoService;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        videoService = new VideoServiceImplementation(videoRepositoryMock);
    }

    @Test
    public void testCreateVideo() {
        Videos video = new Videos();
        when(videoRepositoryMock.create(video)).thenReturn(video);

        Videos result = videoService.createVideo(video);

        assertEquals(video, result);
        verify(videoRepositoryMock, times(1)).create(video);
    }

    @Test
    public void testGetAllVideos() {
        List<Videos> videos = new ArrayList<>();
        when(videoRepositoryMock.getAll()).thenReturn(videos);

        List<Videos> result = videoService.getAllVideos();

        assertEquals(videos, result);
        verify(videoRepositoryMock, times(1)).getAll();
    }

    @Test
    public void testGetVideoById() {
        Long videoId = 1L;
        Videos video = new Videos();
        when(videoRepositoryMock.getById(videoId)).thenReturn(video);

        Videos result = videoService.getVideoById(videoId);

        assertEquals(video, result);
        verify(videoRepositoryMock, times(1)).getById(videoId);
    }

    @Test
    public void testUpdateVideo() {
        Videos videoToUpdate = new Videos();
        Videos updatedVideo = new Videos();
        when(videoRepositoryMock.update(videoToUpdate)).thenReturn(updatedVideo);

        Videos result = videoService.updateVideo(videoToUpdate);

        assertEquals(updatedVideo, result);
        verify(videoRepositoryMock, times(1)).update(videoToUpdate);
    }

    @Test
    public void testDeleteVideo() {
        Long videoId = 1L;

        videoService.deleteVideo(videoId);

        verify(videoRepositoryMock, times(1)).delete(videoId);
    }
}
