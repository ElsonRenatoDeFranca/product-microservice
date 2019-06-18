package com.springcloud.microservice.product.training.converter;


import com.hotelreservation.microservices.entity.Room;
import com.hotelreservation.microservices.vo.RoomVO;
import org.springframework.stereotype.Service;

/**
 * Created by e068635 on 6/7/2019.
 */
@Service
public class ProductServiceConverterImpl implements IProductServiceConverter {


    @Override
    public RoomVO convertEntityToVO(Room room) {
        RoomVO roomVO = new RoomVO();
        roomVO.setRoomNumber(room.getRoomNumber());
        roomVO.setName(room.getName());
        roomVO.setBedInfo(room.getBedInfo());

        return roomVO;
    }

    @Override
    public Room convertVOToEntity(RoomVO roomVO) {
        Room room = new Room();
        room.setBedInfo(roomVO.getBedInfo());
        room.setName(roomVO.getName());
        room.setRoomNumber(roomVO.getRoomNumber());
        return room;
    }
}
