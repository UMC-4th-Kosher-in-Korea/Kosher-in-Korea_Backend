package com.kusher.kusher_in_korea.tabling.repository;

import com.kusher.kusher_in_korea.tabling.domain.Restaurant;
import com.kusher.kusher_in_korea.tabling.domain.RestaurantMenu;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    //식당메뉴
    @Query("select m "
            + "from Restaurant r "
            + "join r.restaurantMenus m "
            + "where r.id = :id")
    List<RestaurantMenu> getMenus(Pageable pageable, @Param("id") Long id);

    boolean existsById(@NotNull Long id);
}
