public class CurrentUser {
    // Basic Getters
    public static Integer getUserId() { return User._userId; }
    public static String getUserLogin() { return User._userLogin; }
    public static String getUserPassword() { return User._userPassword; }

    public static Integer getPersonId() { return Person._personId; }
    public static String getPersonName() { return Person._personName; }
    public static String getPersonSurname() { return Person._personSurname; }
    public static String getPersonLastname() { return Person._personLastname; }

    public static Integer getRoleId() { return Role._roleId; }
    public static String getRoleName() { return Role._roleName; }

    // Basic Setters
    public static void setUserId(Integer newUserId) { User._userId = newUserId; }
    public static void setUserLogin(String newUserLogin) { User._userLogin = newUserLogin; }
    public static void setUserPassword(String newUserPassword) { User._userPassword = newUserPassword; }

    public static void setPersonId(Integer newPersonId) { Person._personId = newPersonId; }
    public static void setPersonName(String newPersonName) { Person._personName = newPersonName; }
    public static void setPersonSurname(String newPersonSurname) { Person._personSurname = newPersonSurname; }
    public static void setPersonLastname(String newPersonLastname) { Person._personLastname = newPersonLastname; }

    public static void setRoleId(Integer newRoleId) { Role._roleId = newRoleId; }
    public static void setRoleName(String newRoleName) { Role._roleName = newRoleName; }
}

// Структуры данных
class User {
    public static Integer _userId;
    public static String _userLogin;
    public static String _userPassword;
}
class Person {
    public static Integer _personId;
    public static String _personName;
    public static String _personSurname;
    public static String _personLastname;
}
class Role {
    public static Integer _roleId;
    public static String _roleName;
}