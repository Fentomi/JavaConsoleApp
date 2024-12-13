public class CurrentUser {
    private static Integer _userId;
    private static String _userLogin;
    private static String _userPassword;

    private static Integer _personId;
    private static String _personName;
    private static String _personSurname;
    private static String _personLastname;

    private static Integer _roleId;
    private static String _roleName;

    // Basic Getters
    public static Integer getUserId() { return _userId; }
    public static String getUserLogin() { return _userLogin; }
    public static String getUserPassword() { return _userPassword; }

    public static Integer getPersonId() { return _personId; }
    public static String getPersonName() { return _personName; }
    public static String getPersonSurname() { return _personSurname; }
    public static String getPersonLastname() { return _personLastname; }

    public static Integer getRoleId() { return _roleId; }
    public static String getRoleName() { return _roleName; }

    // Basic Setters
    public static void setUserId(Integer newUserId) { _userId = newUserId; }
    public static void setUserLogin(String newUserLogin) { _userLogin = newUserLogin; }
    public static void setUserPassword(String newUserPassword) { _userPassword = newUserPassword; }

    public static void setPersonId(Integer newPersonId) { _personId = newPersonId; }
    public static void setPersonName(String newPersonName) { _personName = newPersonName; }
    public static void setPersonSurname(String newPersonSurname) { _personSurname = newPersonSurname; }
    public static void setPersonLastname(String newPersonLastname) { _personLastname = newPersonLastname; }

    public static void setRoleId(Integer newRoleId) { _roleId = newRoleId; }
    public static void setRoleName(String newRoleName) { _roleName = newRoleName; }
}
