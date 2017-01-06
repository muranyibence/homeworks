package com.muranyibence.webshop;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Bence
 */
public class UserEntityTest {

    private static ValidatorFactory vf;
    private static Validator validator;

    private UserEntity userEntity;
    private UserEntity userEntity2;

    @BeforeClass
    public static void init() {
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }

    @AfterClass
    public static void close() {
        vf.close();
    }

    public Date getRelativeDate(int days) {
        Calendar beforeDays = Calendar.getInstance();
        beforeDays.add(Calendar.DAY_OF_YEAR, days);
        return beforeDays.getTime();
    }

    @Test
    public void positiveTestUserNameNotNull() {

        Date registrationDate = getRelativeDate(-1);
        Date dateOfBirth = getRelativeDate(-2);

        UserEntity userEntity = new UserEntity.Builder()
                .username("username")
                .password("Password1+")
                .firstname("user")
                .lastname("User1")
                .address("1111 Budapest Első utca 1.")
                .phone("+36305555555").email("user1@gmail.com")
                .sex(Sex.MALE)
                .registrationDate(registrationDate)
                .lastModifiedDate(registrationDate)
                .dateOfBirth(dateOfBirth)
                .admin(true)
                .build();
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(userEntity);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void NegativeTestUserNameNotNull() {

        Date registrationDate = getRelativeDate(-1);
        Date dateOfBirth = getRelativeDate(-2);

        UserEntity userEntity = new UserEntity.Builder()
                .username(null)
                .password("Password1+")
                .firstname("user")
                .lastname("User1")
                .address("1111 Budapest Első utca 1.")
                .phone("+36305555555").email("user1@gmail.com")
                .sex(Sex.MALE)
                .registrationDate(registrationDate)
                .lastModifiedDate(registrationDate)
                .dateOfBirth(dateOfBirth)
                .admin(true)
                .build();
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(userEntity);

        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("{javax.validation.constraints.NotNull.message}", violations.iterator().next().getMessageTemplate());

    }

    @Test
    public void positiveTestUserNameLeastSixChars() {

        Date registrationDate = getRelativeDate(-1);
        Date dateOfBirth = getRelativeDate(-2);

        UserEntity userEntity = new UserEntity.Builder()
                .username("userna")
                .password("Password1+")
                .firstname("user")
                .lastname("User1")
                .address("1111 Budapest Első utca 1.")
                .phone("+36305555555").email("user1@gmail.com")
                .sex(Sex.MALE)
                .registrationDate(registrationDate)
                .lastModifiedDate(registrationDate)
                .dateOfBirth(dateOfBirth)
                .admin(true)
                .build();
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(userEntity);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void negativeTestUserNameLeastSixChars() {

        Date registrationDate = getRelativeDate(-1);
        Date dateOfBirth = getRelativeDate(-2);

        UserEntity userEntity = new UserEntity.Builder()
                .username("usern")
                .password("Password1+")
                .firstname("user")
                .lastname("User1")
                .address("1111 Budapest Első utca 1.")
                .phone("+36305555555").email("user1@gmail.com")
                .sex(Sex.MALE)
                .registrationDate(registrationDate)
                .lastModifiedDate(registrationDate)
                .dateOfBirth(dateOfBirth)
                .admin(true)
                .build();
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(userEntity);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("{javax.validation.constraints.Size.message}", violations.iterator().next().getMessageTemplate());

    }

    @Test
    public void positiveTestPasswordNotNull() {

        Date registrationDate = getRelativeDate(-1);
        Date dateOfBirth = getRelativeDate(-2);

        UserEntity userEntity = new UserEntity.Builder()
                .username("username")
                .password("Password1+")
                .firstname("user")
                .lastname("User1")
                .address("1111 Budapest Első utca 1.")
                .phone("+36305555555").email("user1@gmail.com")
                .sex(Sex.MALE)
                .registrationDate(registrationDate)
                .lastModifiedDate(registrationDate)
                .dateOfBirth(dateOfBirth)
                .admin(true)
                .build();
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(userEntity);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void NegativePasswordNotNull() {

        Date registrationDate = getRelativeDate(-1);
        Date dateOfBirth = getRelativeDate(-2);

        UserEntity userEntity = new UserEntity.Builder()
                .username("username")
                .password(null)
                .firstname("user")
                .lastname("User1")
                .address("1111 Budapest Első utca 1.")
                .phone("+36305555555").email("user1@gmail.com")
                .sex(Sex.MALE)
                .registrationDate(registrationDate)
                .lastModifiedDate(registrationDate)
                .dateOfBirth(dateOfBirth)
                .admin(true)
                .build();
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(userEntity);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("{javax.validation.constraints.NotNull.message}", violations.iterator().next().getMessageTemplate());

    }

    @Test
    public void positiveTestPasswordLeastSixChars() {

        Date registrationDate = getRelativeDate(-1);
        Date dateOfBirth = getRelativeDate(-2);

        UserEntity userEntity = new UserEntity.Builder()
                .username("username")
                .password("Password1+")
                .firstname("user")
                .lastname("User1")
                .address("1111 Budapest Első utca 1.")
                .phone("+36305555555").email("user1@gmail.com")
                .sex(Sex.MALE)
                .registrationDate(registrationDate)
                .lastModifiedDate(registrationDate)
                .dateOfBirth(dateOfBirth)
                .admin(true)
                .build();
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(userEntity);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void negativeTestPasswordLeastSixChars() {

        Date registrationDate = getRelativeDate(-1);
        Date dateOfBirth = getRelativeDate(-2);

        UserEntity userEntity = new UserEntity.Builder()
                .username("username")
                .password("P1sw+")
                .firstname("user")
                .lastname("User1")
                .address("1111 Budapest Első utca 1.")
                .phone("+36305555555").email("user1@gmail.com")
                .sex(Sex.MALE)
                .registrationDate(registrationDate)
                .lastModifiedDate(registrationDate)
                .dateOfBirth(dateOfBirth)
                .admin(true)
                .build();
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(userEntity);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("{javax.validation.constraints.Size.message}", violations.iterator().next().getMessageTemplate());

    }

    @Test
    public void positiveTestPasswordShouldContainsCapitalLettersSpecialCharactersAndNumbers() {

        Date registrationDate = getRelativeDate(-1);
        Date dateOfBirth = getRelativeDate(-2);

        UserEntity userEntity = new UserEntity.Builder()
                .username("username")
                .password("Password1+")
                .firstname("user")
                .lastname("User1")
                .address("1111 Budapest Első utca 1.")
                .phone("+36305555555").email("user1@gmail.com")
                .sex(Sex.MALE)
                .registrationDate(registrationDate)
                .lastModifiedDate(registrationDate)
                .dateOfBirth(dateOfBirth)
                .admin(true)
                .build();
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(userEntity);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void negativeTestPasswordShouldContainsCapitalLettersSpecialCharactersAndNumbers() {

        Date registrationDate = getRelativeDate(-1);
        Date dateOfBirth = getRelativeDate(-2);

        UserEntity userEntity = new UserEntity.Builder()
                .username("username")
                .password("password")
                .firstname("user")
                .lastname("User1")
                .address("1111 Budapest Első utca 1.")
                .phone("+36305555555").email("user1@gmail.com")
                .sex(Sex.MALE)
                .registrationDate(registrationDate)
                .lastModifiedDate(registrationDate)
                .dateOfBirth(dateOfBirth)
                .admin(true)
                .build();
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(userEntity);
        Assert.assertEquals(3, violations.size());
    }

    @Test
    public void positiveTestNotNullAddressShouldStartWithFourNumber() {

        Date registrationDate = getRelativeDate(-1);
        Date dateOfBirth = getRelativeDate(-2);

        UserEntity userEntity = new UserEntity.Builder()
                .username("username")
                .password("Password1+")
                .firstname("user")
                .lastname("User1")
                .address("1111 Budapest Első utca 1.")
                .phone("+36305555555").email("user1@gmail.com")
                .sex(Sex.MALE)
                .registrationDate(registrationDate)
                .lastModifiedDate(registrationDate)
                .dateOfBirth(dateOfBirth)
                .admin(true)
                .build();
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(userEntity);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void negativeTestNotNullAddressShouldStartWithFourNumber() {

        Date registrationDate = getRelativeDate(-1);
        Date dateOfBirth = getRelativeDate(-2);

        UserEntity userEntity = new UserEntity.Builder()
                .username("username")
                .password("Password1+")
                .firstname("user")
                .lastname("User1")
                .address(" Budapest Első utca 1.")
                .phone("+36305555555").email("user1@gmail.com")
                .sex(Sex.MALE)
                .registrationDate(registrationDate)
                .lastModifiedDate(registrationDate)
                .dateOfBirth(dateOfBirth)
                .admin(true)
                .build();
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(userEntity);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("{Address.message}", violations.iterator().next().getMessageTemplate());

    }

    @Test
    public void positiveTestPhoneNumber() {

        Date registrationDate = getRelativeDate(-1);
        Date dateOfBirth = getRelativeDate(-2);

        UserEntity userEntity = new UserEntity.Builder()
                .username("username")
                .password("Password1+")
                .firstname("user")
                .lastname("User1")
                .address("1111 Budapest Első utca 1.")
                .phone("+36305555555").email("user1@gmail.com")
                .sex(Sex.MALE)
                .registrationDate(registrationDate)
                .lastModifiedDate(registrationDate)
                .dateOfBirth(dateOfBirth)
                .admin(true)
                .build();
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(userEntity);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void negativeTestPhoneNumber() {

        Date registrationDate = getRelativeDate(-1);
        Date dateOfBirth = getRelativeDate(-2);

        UserEntity userEntity = new UserEntity.Builder()
                .username("username")
                .password("Password1+")
                .firstname("user")
                .lastname("User1")
                .address("1111 Budapest Első utca 1.")
                .phone("36305555555").email("user1@gmail.com")
                .sex(Sex.MALE)
                .registrationDate(registrationDate)
                .lastModifiedDate(registrationDate)
                .dateOfBirth(dateOfBirth)
                .admin(true)
                .build();
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(userEntity);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("Phone should start with +36 or 06, then followed by 9 number", violations.iterator().next().getMessageTemplate());

    }

    @Test
    public void positiveTestEmailFormat() {

        Date registrationDate = getRelativeDate(-1);
        Date dateOfBirth = getRelativeDate(-2);

        UserEntity userEntity = new UserEntity.Builder()
                .username("username")
                .password("Password1+")
                .firstname("user")
                .lastname("User1")
                .address("1111 Budapest Első utca 1.")
                .phone("+36305555555").email("user1@gmail.com")
                .sex(Sex.MALE)
                .registrationDate(registrationDate)
                .lastModifiedDate(registrationDate)
                .dateOfBirth(dateOfBirth)
                .admin(true)
                .build();
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(userEntity);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void negativeTestEmailFormat() {

        Date registrationDate = getRelativeDate(-1);
        Date dateOfBirth = getRelativeDate(-2);

        UserEntity userEntity = new UserEntity.Builder()
                .username("username")
                .password("Password1+")
                .firstname("user")
                .lastname("User1")
                .address("1111 Budapest Első utca 1.")
                .phone("+36305555555").email("user1ATgmail.com")
                .sex(Sex.MALE)
                .registrationDate(registrationDate)
                .lastModifiedDate(registrationDate)
                .dateOfBirth(dateOfBirth)
                .admin(true)
                .build();
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(userEntity);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("email format", violations.iterator().next().getMessageTemplate());

    }

    @Test
    public void positiveRegDateInPast() {

        Date registrationDate = getRelativeDate(-1);
        Date lastModifyDate = getRelativeDate(-1);
        Date dateOfBirth = getRelativeDate(-2);

        UserEntity userEntity = new UserEntity.Builder()
                .username("username")
                .password("Password1+")
                .firstname("user")
                .lastname("User1")
                .address("1111 Budapest Első utca 1.")
                .phone("+36305555555").email("user1@gmail.com")
                .sex(Sex.MALE)
                .registrationDate(registrationDate)
                .lastModifiedDate(lastModifyDate)
                .dateOfBirth(dateOfBirth)
                .admin(true)
                .build();
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(userEntity);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void negativeRegDateInPast() {

        Date registrationDate = getRelativeDate(1);
        Date lastModifyDate = getRelativeDate(-1);
        Date dateOfBirth = getRelativeDate(-5);

        UserEntity userEntity = new UserEntity.Builder()
                .username("username")
                .password("Password1+")
                .firstname("user")
                .lastname("User1")
                .address("1111 Budapest Első utca 1.")
                .phone("+36305555555").email("user1@gmail.com")
                .sex(Sex.MALE)
                .registrationDate(registrationDate)
                .lastModifiedDate(lastModifyDate)
                .dateOfBirth(dateOfBirth)
                .admin(true)
                .build();
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(userEntity);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("{javax.validation.constraints.Past.message}", violations.iterator().next().getMessageTemplate());

    }

    @Test
    public void positiveLastModifyDateInPast() {

        Date registrationDate = getRelativeDate(-1);
        Date lastModifyDate = getRelativeDate(-1);
        Date dateOfBirth = getRelativeDate(-2);

        UserEntity userEntity = new UserEntity.Builder()
                .username("username")
                .password("Password1+")
                .firstname("user")
                .lastname("User1")
                .address("1111 Budapest Első utca 1.")
                .phone("+36305555555").email("user1@gmail.com")
                .sex(Sex.MALE)
                .registrationDate(registrationDate)
                .lastModifiedDate(lastModifyDate)
                .dateOfBirth(dateOfBirth)
                .admin(true)
                .build();
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(userEntity);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void negativeLastModifyDateInPast() {

        Date registrationDate = getRelativeDate(-1);
        Date lastModifyDate = getRelativeDate(1);
        Date dateOfBirth = getRelativeDate(-5);

        UserEntity userEntity = new UserEntity.Builder()
                .username("username")
                .password("Password1+")
                .firstname("user")
                .lastname("User1")
                .address("1111 Budapest Első utca 1.")
                .phone("+36305555555").email("user1@gmail.com")
                .sex(Sex.MALE)
                .registrationDate(registrationDate)
                .lastModifiedDate(lastModifyDate)
                .dateOfBirth(dateOfBirth)
                .admin(true)
                .build();
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(userEntity);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("{javax.validation.constraints.Past.message}", violations.iterator().next().getMessageTemplate());

    }

}
