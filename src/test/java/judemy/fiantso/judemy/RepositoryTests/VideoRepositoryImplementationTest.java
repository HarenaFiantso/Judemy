package judemy.fiantso.judemy.RepositoryTests;

import judemy.fiantso.models.Videos;
import judemy.fiantso.repository.VideoRepository.VideoRepositoryImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class VideoRepositoryImplementationTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    private VideoRepositoryImplementation videoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        videoRepository = new VideoRepositoryImplementation(connection);
    }

    @Test
    public void testCreateVideo() throws Exception {
        Videos video = new Videos();
        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        Videos createdVideo = videoRepository.create(video);

        assertNotNull(createdVideo);
        assertEquals(video, createdVideo);
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testGetVideoById() throws Exception {
        Videos video = new Videos();
        video.setVideoId(1L);
        video.setLessonId(1);
        video.setTitle("Video tsotsotra");
        video.setUrl("https://example.com/sample.mp4");
        video.setDuration(300);

        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getLong("video_id")).thenReturn(video.getVideoId());
        when(resultSet.getInt("lesson_id")).thenReturn(video.getLessonId());
        when(resultSet.getString("title")).thenReturn(video.getTitle());
        when(resultSet.getString("url")).thenReturn(video.getUrl());
        when(resultSet.getInt("duration")).thenReturn(video.getDuration());

        Videos result = videoRepository.getById(1L);

        assertEquals(video.getVideoId(), result.getVideoId());
        assertEquals(video.getLessonId(), result.getLessonId());
        assertEquals(video.getTitle(), result.getTitle());
        assertEquals(video.getUrl(), result.getUrl());
        assertEquals(video.getDuration(), result.getDuration());

        verify(preparedStatement, times(1)).executeQuery();
    }

    @Test
    public void testUpdateVideo() throws Exception {
        Videos video = new Videos();
        video.setVideoId(1L);

        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        Videos updatedVideo = videoRepository.update(video);

        assertNotNull(updatedVideo);
        assertEquals(video, updatedVideo);
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testGetAllVideos() throws Exception {
        List<Videos> videos = new ArrayList<>();
        Videos video1 = new Videos();
        video1.setVideoId(1L);
        video1.setLessonId(1);
        video1.setTitle("Video 1");
        video1.setUrl("https://example.com/video1.mp4");
        video1.setDuration(300);

        Videos video2 = new Videos();
        video2.setVideoId(2L);
        video2.setLessonId(2);
        video2.setTitle("Video 2");
        video2.setUrl("https://example.com/video2.mp4");
        video2.setDuration(400);

        videos.add(video1);
        videos.add(video2);

        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getLong("video_id")).thenReturn(1L).thenReturn(2L);
        when(resultSet.getInt("lesson_id")).thenReturn(1).thenReturn(2);
        when(resultSet.getString("title")).thenReturn("Video 1").thenReturn("Video 2");
        when(resultSet.getString("url")).thenReturn("https://example.com/video1.mp4").thenReturn("https://example.com/video2.mp4");
        when(resultSet.getInt("duration")).thenReturn(300).thenReturn(400);

        List<Videos> result = videoRepository.getAll();

        assertEquals(2, result.size());
        assertEquals(video1.getVideoId(), result.get(0).getVideoId());
        assertEquals(video2.getVideoId(), result.get(1).getVideoId());
        assertEquals(video1.getLessonId(), result.get(0).getLessonId());
        assertEquals(video2.getLessonId(), result.get(1).getLessonId());
        assertEquals(video1.getTitle(), result.get(0).getTitle());
        assertEquals(video2.getTitle(), result.get(1).getTitle());
        assertEquals(video1.getUrl(), result.get(0).getUrl());
        assertEquals(video2.getUrl(), result.get(1).getUrl());
        assertEquals(video1.getDuration(), result.get(0).getDuration());
        assertEquals(video2.getDuration(), result.get(1).getDuration());

        verify(preparedStatement, times(1)).executeQuery();
    }

    @Test
    public void testDeleteVideo() throws Exception {
        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        videoRepository.delete(1L);

        verify(preparedStatement, times(1)).executeUpdate();
    }

}
