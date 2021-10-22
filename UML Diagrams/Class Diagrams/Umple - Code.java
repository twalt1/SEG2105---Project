class UserAccount
{
  age;
email;
telephone;
username;
password;
public void createAccount(){

}
  
}

class Admin
{
    isA UserAccount;
1 -- * Instructor;
  1 -- * Class;
  1 -- 1 DBAdmin;
}

class Class
{
  description;
  difficulty;
  int capacity;
  title;
date;
time;
* -- 1..* Instructor;
  * -- 5..* GymMember;
  * -- * DBAdmin;
}

class Instructor
{
    isA UserAccount;
}

class GymMember
{
    isA UserAccount;
* -- * Instructor;
}class DBAdmin
{
  DATABASE_NAME;
  TABLE_NAME;
  COL_1;
  COL_2;
  COL_3;
  COL_4;
  COL_5;
  COL_6;
  Class newClass;
public void insertData(){

}
public int deleteData(){

}
public boolean updateName(){

}
public boolean updateDescription(){

}
public void onCreate(){

}
public void onUpgrade(){

}
}

class DBInstructor
{
  DATABASE_NAME;
  TABLE_NAME;
  COL_1;
  COL_2;
  COL_3;
  COL_4;
  COL_5;
  COL_6;
  1 -- 1..* Instructor;
public void onCreate(){

}
public void onCreate(){

}
public void onUpgrade(){

}
public boolean insertData(){

}
public boolean checkusername(){

}
public boolean checkusernameandpassword(){

}
public int deleteData(){

}
}

class DBUser
{
  DATABASE_NAME;
  TABLE_NAME;
  COL_1;
  COL_2;
  COL_3;
  COL_4;
  COL_5;
  COL_6;
  * -- 1..* GymMember;
public void onCreate(){

}
public void onCreate(){

}
public void onUpgrade(){

}
public boolean insertData(){

}
public boolean checkusername(){

}
public boolean checkusernameandpassword(){

}
public int deleteData(){

}
}

