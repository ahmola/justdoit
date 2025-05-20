package com.example.jpa.repository;

import com.example.jpa.model.User;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.hibernate.WrongClassException;
import org.junit.jupiter.api.Test;
import org.mockito.exceptions.misusing.WrongTypeOfReturnValue;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.data.util.Streamable;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class FindUsersUsingQueriesTest extends UserRepositoryTest{

    @Test
    void testFindAll(){
        List<User> users = userRepository.findAll();

        if(users.size() == 3)
            System.out.println(users.toString());
        else
            throw new WrongTypeOfReturnValue("Size is different");

    }

    @Test
    void testFindUser() throws ClassNotFoundException {
        User john = userRepository.findByUsername("john").get(0);
        System.out.println(john.toString());
        if(!john.getUsername().equals("john"))
            throw new ClassNotFoundException("there is no john");
    }

    @Test
    void testFindByRegistrationDateBetween() throws Exception{
        LocalDate before = LocalDate.of(2023, Month.DECEMBER, 5);
        LocalDate after = LocalDate.of(2023, Month.DECEMBER, 1);

        List<User> users = userRepository.findByRegistrationDateBetween(after, before);

        if(users.isEmpty())
            throw new WrongClassException("No data found", 0, users.toString());

        System.out.println(users.toString());
    }

    @Test
    void testOrder() throws Exception{
        User user1 = userRepository.findFirstByOrderByUsernameAsc();
        User user2 = userRepository.findTopByOrderByRegistrationDateDesc();
        Page<User> userPage = userRepository.findAll(PageRequest.of(0, 3));
        List<User> users = userRepository.findFirst2ByLevel(2,
                Sort.by("registrationDate"));

        if(!user1.getUsername().equals("abigail"))
            throw new WrongClassException("there's no abigail", user1.getId(), user1.getUsername());
        if(users.size() != 2)
            throw new WrongClassException("size is different", users.size(), users.toString());
        if(userPage.getSize() != 3)
            throw new WrongClassException("size is different", userPage.getSize(), userPage.toString());
        if(!users.get(1).getUsername().equals("arthur"))
            throw new WrongClassException("where is arthur?", users.get(1).getId(), users.get(1).getUsername());

        System.out.println("user1: " + user1.toString());
        System.out.println("user2: " + user2.toString());
        System.out.println("users: " + users.toString());
        System.out.println("userPage: " + userPage.getContent());
    }

    @Test
    void testFindByLevel() throws Exception{
        Sort.TypedSort<User> userTypedSort = Sort.sort(User.class);

        List<User> users = userRepository.findByLevel(2,
                userTypedSort.by(User::getUsername).descending());

        if(users.size() != 2)
            throw new SizeLimitExceededException("Size is different", users.size(), 2);
        if(!users.get(0).getUsername().equals("john"))
            throw new WrongClassException("where is john?", users.get(0).getId(), users.get(0).getUsername());

        System.out.println(users.toString());
        System.out.println(userTypedSort.toString());
    }

    @Test
    void testFindByActive() throws Exception{
        List<User> users = userRepository.findByActive(true, PageRequest.of(0,
                3, Sort.by("level").descending()));
        if(users.size() != users.size())
            throw new SizeLimitExceededException("Size is different", users.size(), 3);
        if(!users.get(0).getUsername().equals("dutch"))
            throw new WrongClassException("where is dutch?",
                    users.get(0).getId(), users.get(0).getUsername());
        System.out.println(users.toString());
    }

    @Test
    void testStreamable() throws Exception{
        try{
            Streamable<User> result =
                    userRepository.findByEmailContaining("rdr")
                            .and(userRepository.findByLevel(2));
            Stream<User> stream = result.stream();
            if(stream.count() != 7L)
                throw new SizeLimitExceededException("different size", stream.count(), 7L);

            Stream<User> printStream = result.stream().distinct();
            System.out.println(printStream.toList().toString());
        }catch (Exception e){
            System.out.println("try again  " + e.toString());
            throw e;
        }
    }

    @Test
    void testCustomQuery(){
        List<Object[]> userList1 =
                userRepository.findByAsArrayAndSort("j",
                        Sort.by("username"));
        List<Object[]> userList2 =
                userRepository.findByAsArrayAndSort("t",
                        Sort.by("email_length").descending());
        List<Object[]> userList3 =
                userRepository.findByAsArrayAndSort("a",
                        JpaSort.unsafe("LENGTH(u.email)"));

        System.out.println("List1");
        userList1.stream().forEach(u -> System.out.println(Arrays.stream(u).toList()));
        System.out.println("List2");
        userList2.stream().forEach(u -> System.out.println(Arrays.stream(u).toList()));
        System.out.println("List3");
        userList3.stream().forEach(u -> System.out.println(Arrays.stream(u).toList()));
    }

//    @Test
//    void testProjectionUsername() throws Exception {
//        List<Projection.UsernameOnly> usernameOnlyList =
//                userRepository.findByUsername("john", Projection.UsernameOnly.class);
//
//        if(usernameOnlyList.size() != 1 || !usernameOnlyList.get(0).getUsername().equals("john"))
//            throw new ClassNotFoundException("something's wrong...");
//    }

    @Test
    void testModifyLevel() throws Exception{
        int updated = userRepository.updateLevel(2, 3);
        List<User> users = userRepository.findByLevel(3, Sort.by("username"));

        if(users.size() != 3)
            throw new ClassNotFoundException();

        System.out.println(users.toString());
    }

    @Test
    void testDeleteByLevel() throws Exception{
        int delete = userRepository.deleteByLevel(2);
        List<User> users = userRepository.findByLevel(2, Sort.by("username"));

        if(users.size() == 0)
            System.out.println(users.toString());
    }

    @Test
    void testEmailWithQueryByExample(){
        User user = new User();
        user.setEmail("@rdrmail.com");
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("level", "active")
                .withMatcher("email", match -> match.endsWith());
        Example<User> example = Example.of(user, matcher);
        List<User> users = userRepository.findAll(example);
        System.out.println(users.toString());
    }

    @Test
    void testUsernameWithQueryByExample(){
        User user = new User();
        user.setUsername("j");

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("level", "active")
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING)
                .withIgnoreCase();

        Example<User> example = Example.of(user, matcher);

        List<User> users = userRepository.findAll(example);

        System.out.println(users.toString());
    }
}
