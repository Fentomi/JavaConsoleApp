public class CurrentUser {
    private static User user;
    private static Person person;
    private static Role role;

    // Basic Setters
    public static void setUserId(Integer newUserId) { user._userId = newUserId; }
    public static void setUserLogin(String newUserLogin) { user._userLogin = newUserLogin; }
    public static void setUserPassword(String newUserPassword) { user._userPassword = newUserPassword; }

    public static void setPersonId(Integer newPersonId) { person._personId = newPersonId; }
    public static void setPersonName(String newPersonName) { person._personName = newPersonName; }
    public static void setPersonSurname(String newPersonSurname) { person._personSurname = newPersonSurname; }
    public static void setPersonLastname(String newPersonLastname) { person._personLastname = newPersonLastname; }

    public static void setRoleId(Integer newRoleId) { role._roleId = newRoleId; }
    public static void setRoleName(String newRoleName) { role._roleName = newRoleName; }

    // Basic Getters
    public static Integer getUserId() { return user._userId; }
    public static String getUserLogin() { return user._userLogin; }
    public static String getUserPassword() { return user._userPassword; }

    public static Integer getPersonId() { return person._personId; }
    public static String getPersonName() { return person._personName; }
    public static String getPersonSurname() { return person._personSurname; }
    public static String getPersonLastname() { return person._personLastname; }

    public static Integer getRoleId() { return role._roleId; }
    public static String getRoleName() { return role._roleName; }
}

// Data Structures
class User {
    public Integer _userId;
    public String _userLogin;
    public String _userPassword;
}
class Person {
    public Integer _personId;
    public String _personName;
    public String _personSurname;
    public String _personLastname;
}
class Role {
    public Integer _roleId;
    public String _roleName;
}