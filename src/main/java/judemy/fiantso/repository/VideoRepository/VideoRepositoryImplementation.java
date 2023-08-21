package judemy.fiantso.repository.VideoRepository;

import judemy.fiantso.models.Videos;
import judemy.fiantso.repository.JudemyRepository;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Repository
public class VideoRepositoryImplementation implements JudemyRepository<Videos> {
    private final Connection connection;

    public VideoRepositoryImplementation(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Videos create(Videos video) {
        String insertQuery = "INSERT INTO videos (lesson_id, title, url, duration) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setInt(1, video.getLessonId());
            statement.setString(2, video.getTitle());
            statement.setString(3, video.getTitle());
            statement.setInt(4, video.getDuration());

            statement.executeUpdate();
            System.out.println("The data insert query is executed successfully ! ");
        } catch (SQLException e) {
            System.out.println("There is an error while executing the insert query : " + e.getMessage());
        }
        return video;
    }

    @Override
    public Videos getById(Long id) {
        String selectQuery = "SELECT * FROM videos WHERE video_id = ?";
        Videos video = new Videos();

        try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            video = new Videos(
                    resultSet.getLong("video_id"),
                    resultSet.getInt("lesson_id"),
                    resultSet.getString("title"),
                    resultSet.getString("url"),
                    resultSet.getInt("duration")
            );
            System.out.println("The data select id query is executed successfully !");
        } catch (SQLException e) {
            System.out.println("There is an error while executing the select by id query : " + e.getMessage());
        }

        return video;
    }

    @Override
    public List<Videos> getAll() {
        String selectQuery = "SELECT * FROM videos";
        List<Videos> videos = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                videos.add(new Videos(
                        resultSet.getLong("video_id"),
                        resultSet.getInt("lesson_id"),
                        resultSet.getString("title"),
                        resultSet.getString("url"),
                        resultSet.getInt("duration")
                ));
            }
            System.out.println("The data select query is executed successfully !");
        } catch (SQLException e) {
            System.out.println("There is an error while executing the select query : " + e.getMessage());
        }

        return videos;
    }

    @Override
    public Videos update(Videos video) {
        String updateQuery = "UPDATE videos SET lesson_id = ?, title = ?, url = ?, duration = ? WHERE video_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setInt(1, video.getLessonId());
            statement.setString(2, video.getTitle());
            statement.setString(3, video.getUrl());
            statement.setInt(4, video.getDuration());
            statement.setLong(5, video.getVideoId());

            statement.executeUpdate();
            System.out.println("The data update query is executed successfully !");
        } catch (SQLException e) {
            System.out.println("There is an error while executing the update query : " + e.getMessage());
        }

        return video;
    }

    @Override
    public void delete(Long id) {
        String deleteQuery = "DELETE FROM videos WHERE video_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("The data delete query is executed successfully !");
        } catch (SQLException e) {
            System.out.println("There is an error while executing the delete query : " + e.getMessage());
        }
    }
}
