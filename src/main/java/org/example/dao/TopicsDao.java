package org.example.dao;



import org.example.db.DBManager;
import org.example.entity.Topic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TopicsDao {

    private static String getAllTopics = "SELECT * FROM topics";


    public static List<Topic> getAllTopics(){
        List<Topic> list = new ArrayList<>();
        TopicsMapper mapper = new TopicsMapper();
        try(Connection con = DBManager.getInstance().getConnection()){
            try(Statement statement = con.createStatement()){
                try (ResultSet rs = statement.executeQuery(getAllTopics)){
                    while (rs.next()){
                        Topic topic = mapper.mapRow(rs);
                        list.add(topic);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }


    private static class TopicsMapper implements EntityMapper {

        @Override
        public Topic mapRow(ResultSet rs) {
            Topic topic = new Topic();
            try{
                topic.setId(rs.getInt("id"));
                topic.setName(rs.getString("name"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return topic;
        }
    }
}
