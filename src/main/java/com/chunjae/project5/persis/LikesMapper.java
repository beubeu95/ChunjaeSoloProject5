package com.chunjae.project5.persis;

import com.chunjae.project5.entity.Likes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LikesMapper {

    int checkLikedMar(Likes likes);
    int checkLikedReq(Likes likes);
    void removeLikeMar(Likes likes);
    void removeLikeReq(Likes likes);
    void addLikeMar(Likes likes);
    void addLikeReq(Likes likes);
    List<Likes> marketLikeList(String loginId);
    List<Likes> requestLikeList(String loginId);

}
