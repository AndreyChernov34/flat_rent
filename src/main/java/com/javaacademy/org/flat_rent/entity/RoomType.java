package com.javaacademy.org.flat_rent.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RoomType {
    ROOM("комната"),
    ONE_ROOM_FLAT("однокомнатная квартира"),
    TWO_ROOM_FLAT("двухкомнатная квартира"),
    THREE_ROOM_FLAT("трехкомнатная квартира"),
    FOUR_ROOM_FLAT("четырех и более комнатная квартира");

    private final String description;
}
