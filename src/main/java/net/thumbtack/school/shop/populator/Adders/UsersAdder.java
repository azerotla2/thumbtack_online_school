package net.thumbtack.school.shop.populator.Adders;

import net.thumbtack.school.shop.daoImpl.UserDaoImpl;
import net.thumbtack.school.shop.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UsersAdder {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersAdder.class);
    private final UserDaoImpl userDao;
    private Random random = new Random();

    public UsersAdder(UserDaoImpl userDao){
        this.userDao = userDao;
    }

    private void createUser(String id, int yearBirth, String gender,
                            int numberSaleCard, boolean haveChild,
                            String familyStatus, String education, String locality){
        User user = new User(id, yearBirth, gender, numberSaleCard, haveChild, familyStatus, education, locality);
        userDao.insert(user);
    }

    public void addAllUser(){
        for(int id = 0; id < 8; id++){
            createUser(String.valueOf(id),
                randomYearBirth(),
                randomGender(),
                randomNumberCard(),
                randomBoolean(),
                randomFamilyStatus(),
                randomEducation(),
                randomLocality()
            );
        }
        LOGGER.info("All user is added");
    }

    private int randomYearBirth(){
        return (int) (1950 + Math.random()*60);
    }

    private String randomGender(){
        if(randomBoolean())
            return "male";
        else
            return "female";
    }

    private int randomNumberCard(){
        return (int)(1000000 + Math.random()*1000000);
    }

    private String randomFamilyStatus(){
        if(randomBoolean())
            return "married";
        else
            return "single";
    }

    private String randomEducation(){
        String[] list = {"Higher" , "Middle", "School"};
        return randomStringOnList(list);
    }

    private String randomLocality(){
        String[] list = {"Omsk" , "Krasnodar", "Moscow", "Novosibirsk"};
        return randomStringOnList(list);
    }

    private String randomStringOnList(String[] list){
        return list[random.nextInt(list.length)];
    }

    private boolean randomBoolean(){
        return random.nextBoolean();
    }
}
