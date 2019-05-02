package lesson3OneToOne.service;

import lesson3OneToOne.model.Room;
import lesson3OneToOne.repository.RoomDAO;

public class RoomService {
    static RoomDAO roomDAO = new RoomDAO();

    public static void save(Room room) {
        roomDAO.save(room);
    }

    public static void delete (long id){
        try {
            roomDAO.delete(id);
        } catch (Exception e) {
            System.err.println(e.getMessage());;
        }
    }

    public static Room findById (long id){
       return roomDAO.findById(id);
    }

    public static void update (Room room){
        roomDAO.update(room);
    }
}
