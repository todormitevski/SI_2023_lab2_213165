package mk.ukim.finki;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {
    RuntimeException ex;

    @Test
    public void EveryBranchTests(){
        //1. user=null, allUsers=anything
        ex = assertThrows(RuntimeException.class, () -> SILab2.function(null, new ArrayList<>()));
        assertTrue(ex.getMessage().contains("Mandatory information missing!"));

        //2. user=(username=null,password=777,email=user1@gmail.com), allUsers.size()=1=user(username=user1@gmail.com,password=777,email=user1@gmail.com)
        List<User> list1 = new ArrayList<>();
        list1.add(new User("user1@gmail.com","777","user1@gmail.com"));
        assertFalse(SILab2.function(new User(null,"777","user1@gmail.com"), list1));

        //3. user=(username=John,password=ZZZMMMS!S,email=johndoe@gmail.com), allUsers.size()=1=(username=Jane,password=SSSMMMZZZ,email=janedoe@gmail.com)
        List<User> list2 = new ArrayList<>();
        list2.add(new User("Jane","SSSMMMZZZ","janedoe@gmail.com"));
        assertTrue(SILab2.function(new User("John","ZZZMMMS!S","johndoe@gmail.com"),list2));

        //4. user=(username=Pyke,password=XXX FFFOOO,email=pykemail), allUsers.size()=1=rand values != user values
        assertFalse(SILab2.function(new User("Pyke","XXX FFFOOO","pykemail"),new ArrayList<>()));

        //5. user=(username=Anakin,password=THEFORCE,mail=skywalker), allUsers.size()=1=rand values != user values
        assertFalse(SILab2.function(new User("Anakin","THEFORCE","skywalker"),new ArrayList<>()));
    }

    @Test
    public void MultipleConditionTests(){
        //if (user==null || user.getPassword()==null || user.getEmail()==null)

        ex = assertThrows(RuntimeException.class, () -> SILab2.function(
                null, new ArrayList<>()
        ));
        assertTrue(ex.getMessage().contains("Mandatory information missing!"));

        ex = assertThrows(RuntimeException.class, () -> SILab2.function(
                new User("something","something",null), new ArrayList<>()
        ));
        assertTrue(ex.getMessage().contains("Mandatory information missing!"));

        ex = assertThrows(RuntimeException.class, () -> SILab2.function(
                new User("something", null, "something"), new ArrayList<>()
        ));
        assertTrue(ex.getMessage().contains("Mandatory information missing!"));

        ex = assertThrows(RuntimeException.class, () -> SILab2.function(
                new User("something",null,null),new ArrayList<>()
        ));
        assertTrue(ex.getMessage().contains("Mandatory information missing!"));
    }
}
