package com.playground.tinderswipe.repository.entity;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "favorite")
@CompoundIndexes({
        @CompoundIndex(name = "swiperswipee", unique = true, def = "{'swiper' : 1, 'swipee': 1}")
})
public class FavoriteEntity {
    @Id
    private ObjectId id;

    @Indexed
    private String swiper;

    @Indexed
    private String swipee;

    @LastModifiedDate
    private DateTime ts;

    public FavoriteEntity() {}

    public FavoriteEntity(String swiper, String swipee) {
        this.swiper = swiper;
        this.swipee = swipee;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getSwiper() {
        return swiper;
    }

    public void setSwiper(String swiper) {
        this.swiper = swiper;
    }

    public String getSwipee() {
        return swipee;
    }

    public void setSwipee(String swipee) {
        this.swipee = swipee;
    }

    public DateTime getTs() {
        return ts;
    }

    public void setTs(DateTime ts) {
        this.ts = ts;
    }

    @Override
    public String toString() {
        return "FavoriteEntity{" +
                "swiper=" + swiper +
                ", swipee=" + swipee +
                ", ts=" + ts +
                '}';
    }
}
