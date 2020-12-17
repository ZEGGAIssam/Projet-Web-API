package com.apiweb.model;

import com.apiweb.controller.GenerateId;


import java.util.Calendar;

import java.util.HashMap;


public class Authentification {

    static public HashMap<String, Authentification> authArray = new HashMap<String, Authentification>();
    static private Integer  delai = 1;
    private User user;
    private Calendar dlc;
    private String id;

    public Authentification(String id, User user)
    {
        this.user = user;
        this.id = id;
        this.dlc = Calendar.getInstance();
        System.out.println(dlc.toString());
        this.dlc.add(Calendar.HOUR_OF_DAY, delai);

    }

    static public String generateToken(User user)
    {
        String authId = GenerateId.generateID();
        String oldAuthId = "";
        for (Authentification authentification : authArray.values()) {
            if (authentification.user.equals(user))
            {
              oldAuthId = authentification.id;
            }
        }
        if(oldAuthId != ""){
            authArray.remove(oldAuthId);
        }
        authArray.put(authId, new Authentification(authId, user));
        return authId;
    }

    static public User getByToken(String token)
    {
        Authentification auth = authArray.get(token);
        if (auth != null && auth.isValid()) {
            return auth.getUser();
        }
        else if (auth != null && !auth.isValid())
        {
            authArray.remove(token);
            return null;
        }
        else
        {
            return null;
        }

    }

    public static boolean isValid(String token)
    {
        return authArray.containsKey(token)  && authArray.get(token).isValid();
    }


    public boolean isValid()
    {
        return this.dlc.compareTo(Calendar.getInstance()) > 0;
    }

    public User getUser() {
        return user;
    }

    public Calendar getDlc() {
        return dlc;
    }
}
