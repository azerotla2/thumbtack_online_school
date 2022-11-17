package net.thumbtack.school.database.jdbc;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.model.Trainee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcService {

    public static void insertTrainee(Trainee trainee) throws SQLException {
        String insertQuery = "insert into trainee values (?,?,?,?)";
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, trainee.getId());
            stmt.setString(2, trainee.getFirstName());
            stmt.setString(3, trainee.getLastName());
            stmt.setInt(4, trainee.getRating());
            stmt.executeUpdate();
            try(ResultSet generatedKeys = stmt.getGeneratedKeys()){
                if(!generatedKeys.next()){
                    throw new SQLException("Don't creating user");
                }
            }
            trainee.setId(JdbcService.getId());
        } catch (SQLException ex) {
            throw new SQLException("Failed to insert trainee");
        }
    }

        public static int getId() throws SQLException {
            String selectQuery = "select last_insert_id()";
            int id = 0;
            try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(selectQuery)){
                ResultSet rs = stmt.executeQuery();
                if(rs.next())
                    id = rs.getInt(1);
            } catch (SQLException ex){
                throw new SQLException("Failed to get last insert id");
            }
            return id;
        }

        public  static void updateTrainee(Trainee trainee) throws SQLException {
            String updateQuery = "UPDATE trainee SET firstname = (?), lastname = (?) where id = (?)";
            try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(updateQuery, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, trainee.getFirstName());
                stmt.setString(2, trainee.getLastName());
                stmt.setInt(3, trainee.getId());
                stmt.executeUpdate();
            } catch (SQLException ex) {
                throw new SQLException("Failed to update trainee");
            }
        }

    public static Trainee getTraineeByIdUsingColNames(int traineeId) throws SQLException {
        String selectQuery = "select * from trainee where id in (?)";
        Trainee trainee = new Trainee();
        try(PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(selectQuery)){
            stmt.setInt(1, traineeId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                trainee.setId(rs.getInt("id"));
                trainee.setFirstName(rs.getString("firstname"));
                trainee.setLastName(rs.getString("lastname"));
                trainee.setRating(rs.getInt("rating"));
            } else
                trainee = null;
        }catch (SQLException ex){
            throw new SQLException("Failed to get trainee using names");
        }
        return trainee;
    }

    public static Trainee getTraineeByIdUsingColNumbers(int traineeId) throws SQLException {
        String selectQuery = "select * from trainee where id in (?)";
        Trainee trainee = new Trainee();
        try(PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(selectQuery)){
            stmt.setInt(1, traineeId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                trainee.setId(rs.getInt(1));
                trainee.setFirstName(rs.getString(2));
                trainee.setLastName(rs.getString(3));
                trainee.setRating(rs.getInt(4));
            } else
                trainee = null;
        }catch (SQLException ex){
            throw new SQLException("Failed to get trainee using numbers");
        }
        return trainee;
    }


    public static List<Trainee> getTraineesUsingColNames() throws SQLException {
        List<Trainee> trainees = new ArrayList<>();
        String selectQuery = "select * from trainee";
        try(PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(selectQuery)){
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                do{
                    Trainee trainee = new Trainee();
                    trainee.setId(rs.getInt("id"));
                    trainee.setFirstName(rs.getString("firstname"));
                    trainee.setLastName(rs.getString("lastname"));
                    trainee.setRating(rs.getInt("rating"));
                    trainees.add(trainee);
                } while (rs.next());
            } else
                trainees = null;
        }catch (SQLException ex){
            throw new SQLException("Failed to get list trainee using names");
        }
        return trainees;
    }

    public static List<Trainee> getTraineesUsingColNumbers() throws SQLException {
        List<Trainee> trainees = new ArrayList<>();
        String selectQuery = "select * from trainee";
        try(PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(selectQuery)){
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                do{
                    Trainee trainee = new Trainee();
                    trainee.setId(rs.getInt(1));
                    trainee.setFirstName(rs.getString(2));
                    trainee.setLastName(rs.getString(3));
                    trainee.setRating(rs.getInt(4));
                    trainees.add(trainee);
                } while(rs.next());
            } else
                trainees = null;
        }catch (SQLException ex){
            throw new SQLException("Failed to get list trainee using numbers");
        }
        return trainees;
    }

    public static void deleteTrainee(Trainee trainee) throws SQLException {
        String deleteQuery = "Delete from trainee where id = (?)";
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(deleteQuery)){
            stmt.setInt(1, trainee.getId());
            stmt.executeUpdate();
        } catch (SQLException ex){
            throw new SQLException("Failed to delete trainee");
        }
    }

    public static void insertSchool(School school) throws SQLException {
        String insertQuery = "insert into schools (name, year) values (?,?)";
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, school.getName());
                stmt.setInt(2, school.getYear());
            stmt.executeUpdate();
            try(ResultSet generatedKeys = stmt.getGeneratedKeys()){
                if(!generatedKeys.next()){
                    throw new SQLException("Don't creating school");
                }
            }
            school.setId(JdbcService.getId());
        } catch (SQLException ex) {
            throw new SQLException("Failed to insert school");
        }
    }

    public static School getSchoolByIdUsingColNames(int schoolId) throws SQLException {
        //изменить запрос сделать таблицу со школами с уникальными id
        String selectQuery = "select * from schools where id in (?)";
        School school = new School();
        try(PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(selectQuery)){
            stmt.setInt(1, schoolId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                school.setId(rs.getInt("id"));
                school.setName(rs.getString("name"));
                school.setYear(rs.getInt("year"));
                List<Group> group = new ArrayList<>();
                school.setGroups(group);
            } else
                school = null;
        }catch (SQLException ex){
            throw new SQLException("Failed to get subject using names");
        }
        return school;
    }

    public static School getSchoolByIdUsingColNumbers(int schoolId) throws SQLException {
        String selectQuery = "select * from schools where id in (?)";
        School school = new School();
        try(PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(selectQuery)){
            stmt.setInt(1, schoolId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                school.setId(rs.getInt(1));
                school.setName(rs.getString(2));
                school.setYear(rs.getInt(3));
                List<Group> group = new ArrayList<>();
                school.setGroups(group);
            } else
                school = null;
        }catch (SQLException ex){
            throw new SQLException("Failed to get school using number");
        }
        return school;
    }

    public static void insertSubject(Subject subject) throws SQLException {
        String insertQuery = "insert into subjectStudent (nameSubject) values (?)";
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, subject.getName());
            stmt.executeUpdate();
            try(ResultSet generatedKeys = stmt.getGeneratedKeys()){
                if(!generatedKeys.next()){
                    throw new SQLException("Don't creating subject");
                }
            }
            subject.setId(JdbcService.getId());
        } catch (SQLException ex) {
            throw new SQLException("Failed to insert subject");
        }
    }

    public static Subject getSubjectByIdUsingColNames(int subjectId) throws SQLException {
        String selectQuery = "select * from subjectStudent where id in (?)";
        Subject subject = new Subject();
        try(PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(selectQuery)){
            stmt.setInt(1, subjectId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                subject.setId(rs.getInt("id"));
                subject.setName(rs.getString("nameSubject"));
            } else
                subject = null;
        }catch (SQLException ex){
            throw new SQLException("Failed to get subject using names");
        }
        return subject;
    }

    public static Subject getSubjectByIdUsingColNumbers(int subjectId) throws SQLException {
        String selectQuery = "select * from subjectStudent where id in (?)";
        Subject subject = new Subject();
        try(PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(selectQuery)){
            stmt.setInt(1, subjectId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                subject.setId(rs.getInt(1));
                subject.setName(rs.getString(2));
            } else
                subject = null;
        }catch (SQLException ex){
            throw new SQLException("Failed to get subject using number");
        }
        return subject;
    }

    public static void insertGroup(School school, Group group) throws SQLException {
        String insertQuery = "insert into groupStudent value (?, ?, ?, ?)";
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, group.getId());
            stmt.setString(2, group.getName());
            stmt.setString(3, group.getRoom());
            stmt.setInt(4, school.getId());
            stmt.executeUpdate();
            try(ResultSet generatedKeys = stmt.getGeneratedKeys()){
                if(!generatedKeys.next()){
                    throw new SQLException("Don't creating group");
                }
            }
            group.setId(JdbcService.getId());
        } catch (SQLException ex) {
            throw new SQLException("Don't insert group");
        }
    }

    public static School getSchoolByIdWithGroups(int id) throws SQLException {
        String selectQuery = "select schools.id, schools.name, schools.year, " +
                "groupstudent.id, groupstudent.name, groupstudent.room " +
                "From schools JOIN groupStudent ON schoolid = schools.id where schools.id = (?)";
        School school = new School();
        try(PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(selectQuery)){
            stmt.setInt(1, id);
            List<Group> groupList = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                school.setId(rs.getInt(1));
                school.setName(rs.getString(2));
                school.setYear(rs.getInt(3));
                do{
                    Group group = new Group();
                    group.setId(rs.getInt(4));
                    group.setName(rs.getString(5));
                    group.setRoom(rs.getString(6));
                    groupList.add(group);
                    group.setTrainees(new ArrayList<>());
                    group.setSubjects(new ArrayList<>());
                } while (rs.next());
                school.setGroups(groupList);
            } else
                school = null;
        }catch (SQLException ex){
            throw new SQLException("Don't getting school with groups");
        }
        return school;
    }

    public static List<School> getSchoolsWithGroups() throws SQLException {
        String selectQuery = "select schools.id, schools.name, schools.year, " +
                "groupstudent.id, groupstudent.name, groupstudent.room " +
                "From schools JOIN groupStudent ON schoolid = schools.id";
        List<School> schools = new ArrayList<>();
        try(PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(selectQuery)){
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                do {
                    School school = new School();
                    List<Group> groupList = new ArrayList<>();
                    int idSchool = rs.getInt(1);
                    do{
                        if (idSchool != rs.getInt(1))
                            break;
                        idSchool = rs.getInt(1);
                        Group group = new Group();
                        group.setId(rs.getInt(4));
                        group.setName(rs.getString(5));
                        group.setRoom(rs.getString(6));
                        groupList.add(group);
                        group.setTrainees(new ArrayList<>());
                        group.setSubjects(new ArrayList<>());
                    }while (rs.next());
                    rs.previous();
                    school.setId(rs.getInt(1));
                    school.setName(rs.getString(2));
                    school.setYear(rs.getInt(3));
                    school.setGroups(groupList);
                    schools.add(school);
                }while (rs.next());
            } else
                schools = null;
        }catch (SQLException ex){
            throw new SQLException("Don't get list schools");
        }
        return schools;
    }


    public static void deleteTrainees() throws SQLException {
        String updateQuery = "DELETE FROM trainee";
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(updateQuery)){
            stmt.executeUpdate();
        } catch (SQLException ex){
            throw new SQLException("Don't delete table trainee");
        }
    }

    public static void deleteSchools() throws SQLException {
        String updateQuery = "DELETE FROM schools";
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(updateQuery)){
            stmt.executeUpdate();
        } catch (SQLException ex){
            throw new SQLException("Don't delete table scholls");
        }
    }

    public static void deleteSubjects() throws SQLException {
        String updateQuery = "DELETE FROM subjectStudent";
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(updateQuery)){
            stmt.executeUpdate();
        } catch (SQLException ex){
            throw new SQLException("Don't delete table subject");
        }
    }
}

