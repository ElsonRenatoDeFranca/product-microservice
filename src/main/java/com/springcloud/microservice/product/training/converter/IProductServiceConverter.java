package com.springcloud.microservice.product.training.converter;

import com.hotelreservation.microservices.entity.Room;
import com.hotelreservation.microservices.vo.RoomVO;

/**
 * Created by e068635 on 6/7/2019.
 */
public interface IProductServiceConverter {

    /**
     *
     * @param room
     * @return
     */
    RoomVO convertEntityToVO(Room room);

    /**
     *
     * @param roomVO
     * @return
     */
    Room convertVOToEntity(RoomVO roomVO);
}
