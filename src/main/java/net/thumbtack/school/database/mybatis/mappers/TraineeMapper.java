package net.thumbtack.school.database.mybatis.mappers;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.Trainee;
import org.apache.ibatis.annotations.*;


import java.util.List;
import java.util.Set;

public interface TraineeMapper {

    @Insert("INSERT INTO trainee ( firstname, lastname, rating, groupid) VALUES "
            + "( #{trainee.firstname}, #{trainee.lastname}, #{trainee.rating}, #{group.id} )")
    @Options(useGeneratedKeys = true, keyProperty = "trainee.id")
    Integer insert(@Param("trainee") Trainee trainee, @Param("group") Group group);

    @Select("SELECT id, firstname, lastname, rating FROM trainee WHERE id = #{id}")
    @Results({ @Result(property = "id", column = "id") })
    Trainee getById(int id);

    @Update("UPDATE trainee SET firstname = #{firstname}, lastname = #{lastname}, rating = #{rating} WHERE id = #{id} ")
    void update(Trainee trainee);

    @Delete("DELETE FROM trainee WHERE id = #{trainee.id}")
    int delete(@Param("trainee") Trainee trainee);

    @Delete("DELETE FROM trainee")
    void deleteAll();

    @Select("SELECT id, firstname, lastname, rating FROM trainee")
    @Results({ @Result(property = "id", column = "id") })
    List<Trainee> getAll();

    @Select({"<script>",
            "SELECT id, firstname, lastname, rating FROM trainee",
            "<where>" ,
            "<if test='firstname != null'> AND firstname like #{firstname}",
            "</if>",
            "<if test='lastname != null'> AND lastname like #{lastname}",
            "</if>",
            "<if test='rating != null'> AND rating = #{rating}",
            "</if>",
            "</where>" ,
            "</script>"})
    @Results({
            @Result(property = "id", column = "id"),
          })
    List<Trainee> getAllWithParams(@Param("firstname") String firstName, @Param("lastname") String lastName, @Param("rating") Integer rating);

    @Insert({"<script>",
            "INSERT INTO trainee (firstname, lastname, rating) VALUES",
            "<foreach item='item' collection='list' separator=','>",
            "( #{item.firstname}, #{item.lastname}, #{item.rating} )",
            "</foreach>",
            "</script>"})
    @Options(useGeneratedKeys = true, keyProperty = "list.id")
    void batchInsert(@Param("list") List<Trainee> listTrainees);

    @Select("SELECT id, firstname, lastname, rating from trainee WHERE groupid= #{group.id}")
    @Results({
            @Result(property = "id", column = "id") })
    Set<Trainee> getByGroup(Group group);

}
