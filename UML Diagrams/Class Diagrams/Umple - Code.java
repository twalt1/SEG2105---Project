class User
{
  name;

int age;
email;
telephone;
username;
password;
type;
public void createAccount(){

}
 
}

class Admin
{
    isA User;
Class[] manageClasses;
  
    1 -- * Instructor;
  1 -- * Class;
public boolean deleteClass(){

}
  
  
}

class Class
{
  name;
  description;
  type;
  Date[] dayOfWeek;
  Date[] time;
  difficulty;
  int capacity;
  Instructor[] allInstructors;
  GymMember[] allMembers;
  boolean status;
* -- 1..* Instructor;
  * -- 5..* GymMember;
}

class Instructor
{
    Class[] teachClasses;
isA User;
public boolean createClass(){

}
}

class GymMember
{
    isA User;
Class[] enrollClasses;

* -- * Instructor;
public boolean enrollClass(Class arg0){

}

}

