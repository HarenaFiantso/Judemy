package judemy.fiantso.repository.VideoRepository;

import judemy.fiantso.models.Videos;
import judemy.fiantso.repository.JudemyRepository;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Repository
public class VideoRepositoryImplementation implements JudemyRepository<Videos> {
    private static final Logger logger = LoggerFactory.getLogger(VideoRepositoryImplementation.class);

    private final Connection connection;
    private static final String INSERT_QUERY = "INSERT INTO videos (lesson_id, title, url, duration) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM videos";
    private static final String UPDATE_QUERY = "UPDATE videos SET lesson_id = ?, title = ?, url = ?, duration = ? WHERE video_id = ?";
    private static final String DELETE_QUERY = "DELETE FROM videos WHERE video_id = ?";

    public VideoRepositoryImplementation(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Videos create(Videos video) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
            statement.setInt(1, video.getLessonId());
            statement.setString(2, video.getTitle());
            statement.setString(3, video.getUrl());
            statement.setInt(4, video.getDuration());

            statement.executeUpdate();
            logger.info("Data insert query executed successfully!");
        } catch (SQLException e) {
            logger.error("Error executing insert query: {}", e.getMessage());
        }
        return video;
    }

    @Override
    public Videos getById(Long id) {
        String selectByIdQuery = "SELECT * FROM videos WHERE video_id = ?";
        Videos video = new Videos();

        try (PreparedStatement statement = connection.prepareStatement(selectByIdQuery)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                video = new Videos(
                        resultSet.getLong("video_id"),
                        resultSet.getInt("lesson_id"),
                        resultSet.getString("title"),
                        resultSet.getString("url"),
                        resultSet.getInt("duration")
                );
            }
            logger.info("Data select by id query executed successfully!");
        } catch (SQLException e) {
            logger.error("Error executing select by id query: {}", e.getMessage());
        }

        return video;
    }

    @Override
    public List<Videos> getAll() {
        List<Videos> videos = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                videos.add(new Videos(
                        resultSet.getLong("video_id"),
                        resultSet.getInt("lesson_id"),
                        resultSet.getString("title"),
                        resultSet.getString("url"),
                        resultSet.getInt("duration")
                ));
            }
            logger.info("Data select query executed successfully!");
        } catch (SQLException e) {
            logger.error("Error executing select query: {}", e.getMessage());
        }

        return videos;
    }

    @Override
    public Videos update(Videos video) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setInt(1, video.getLessonId());
            statement.setString(2, video.getTitle());
            statement.setString(3, video.getUrl());
            statement.setInt(4, video.getDuration());
            statement.setLong(5, video.getVideoId());

            statement.executeUpdate();
            logger.info("Data update query executed successfully!");
        } catch (SQLException e) {
            logger.error("Error executing update query: {}", e.getMessage());
        }

        return video;
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            logger.info("Data delete query executed successfully!");
        } catch (SQLException e) {
            logger.error("Error executing delete query: {}", e.getMessage());
        }
    }
}
