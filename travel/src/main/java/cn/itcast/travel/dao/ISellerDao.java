package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Seller;

public interface ISellerDao {
    Seller findBySid(int sid);
}
