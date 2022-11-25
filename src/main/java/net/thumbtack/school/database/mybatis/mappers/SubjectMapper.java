package net.thumbtack.school.database.mybatis.mappers;

import net.thumbtack.mybatis.model.Author;
import net.thumbtack.mybatis.model.Book;
import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Subject;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Set;

public interface SubjectMapper {

    @Insert("INSERT INTO `subject` ( name ) VALUES "
            + "( #{subject.name} )")
    @Options(useGeneratedKeys = true, keyProperty = "subject.id")
    Integer insert(@Param("subject") Subject subject);

    @Select("SELECT id, name FROM `subject` WHERE id = #{id}")
    @Results({ @Result(property = "id", column = "id") })
    Subject getById(int id);

    @Update("UPDATE `subject` SET name = #{name} WHERE id = #{id} ")
    void update(Subject subject);

    @Delete("DELETE FROM `subject` WHERE id = #{subject.id}")
    void delete(@Param("subject") Subject subject);

    @Delete("DELETE FROM `subject`")
    void deleteAll();

    @Select("SELECT id, name FROM `subject` ")
    @Results({ @Result(property = "id", column = "id") })
    List<Subject> getAll();

  //  @Select("SELECT * from group_subject WHERE groupid = #{group.id}")
    @Select("SELECT id, name FROM subject WHERE id IN (SELECT subjectid FROM group_subject WHERE groupid = #{group.id})")
    Set<Subject> getByGroup(Group group);
}
