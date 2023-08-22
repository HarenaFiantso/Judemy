package judemy.fiantso.judemy.ControllerTests;

import judemy.fiantso.models.Videos;
import judemy.fiantso.service.VideoService.VideoService;
import judemy.fiantso.controller.VideoController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class VideoControllerTest {

    @Mock
    private VideoService videoServiceMock;

    private MockMvc mockMvc;
    private VideoController videoController;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        videoController = new VideoController(videoServiceMock);
        mockMvc = MockMvcBuilders.standaloneSetup(videoController).build();
    }

    @Test
    public void testCreateVideo() throws Exception {
        Videos video = new Videos();
        when(videoServiceMock.createVideo(any())).thenReturn(video);

        mockMvc.perform(post("/videos/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.videoId").value(video.getVideoId()));
    }

    @Test
    public void testGetAllVideos() throws Exception {
        List<Videos> videos = new ArrayList<>();
        when(videoServiceMock.getAllVideos()).thenReturn(videos);

        mockMvc.perform(get("/videos/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(videos.size()));
    }

    @Test
    public void testDeleteVideo() throws Exception {
        Long videoId = 1L;

        mockMvc.perform(delete("/videos/{videoId}", videoId))
                .andExpect(status().isOk());

        verify(videoServiceMock, times(1)).deleteVideo(videoId);
    }
}
