package com.castom;

public class HelperClass {//класс для пользователей

    String number;
   // String login;
    String password;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    String idUser;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

   /* public String getLogin(){
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }*/

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public HelperClass(String number, String password, String idUser) {
        this.idUser = idUser;
        this.number = number;
        //this.login = login;
        this.password = password;

    }

}
