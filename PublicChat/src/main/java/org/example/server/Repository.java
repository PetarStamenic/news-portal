package org.example.server;

import java.util.HashMap;

public class Repository {
    private HashMap<String, Long> clientRepository;

    private static volatile Repository instance;
    private static Object mutex = new Object();
    private static Long guests;

    private Repository() {
        clientRepository = new HashMap<>();
        guests = 0L;
    }

    public static Repository getInstance() {
        Repository result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null)
                    instance = result = new Repository();
            }
        }
        return result;
    }

    public Long findIdByUsername(String username){
        Long id;
        id = clientRepository.get(username.toLowerCase());
        if(id == null)
            id = -1L;
        return id;
    }

    public synchronized Long login(String username){
        Long id = findIdByUsername(username);
        if(id == -1){
            id = clientRepository.size()+1L;
            clientRepository.put(username.toLowerCase(),id);
        }
        return id;
    }

    public synchronized boolean banClient(String username){
        if(findIdByUsername(username)==-1)
            return false;
        clientRepository.put(username.toLowerCase(),-2L);
        return true;
    }

    public synchronized String addGuest(){
        guests++;
        return guests.toString();
    }

}
