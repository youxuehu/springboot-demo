package nian.shop.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import nian.shop.VO.GoodsVo;
import nian.shop.dao.OrderDao;
import nian.shop.entity.OrderInfo;
import nian.shop.entity.SecondOrder;
import nian.shop.entity.SecondUser;
import nian.shop.redis.OrderKey;
import nian.shop.utils.SecondStatusEnum;

@Service
public class OrderService {
	@Autowired
	OrderDao orderDao;
	@Autowired
	RedisService redisService;
	
	public SecondOrder getSecondOrderByUserIdandGoodsId(long userId, long goodsId) {
//		return orderDao.getSecondOrderByUserIdandGoodsId(userId, goodsId);
		//查缓存
		return redisService.get(OrderKey.getMiaoshaOrderByUidGid, "" + userId + "_" + goodsId, SecondOrder.class);
	}
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT) 
	public OrderInfo createOrder(SecondUser user, GoodsVo goods) {
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setCreateDate(new Date());
		orderInfo.setDeliveryAddrId(0L);
		orderInfo.setGoodsCount(1);
		orderInfo.setGoodsId(goods.getId());
		orderInfo.setGoodsName(goods.getGoodsName());
		orderInfo.setGoodsPrice(goods.getSecondPrice());
		orderInfo.setOrderChannel(1);
		orderInfo.setStatus(SecondStatusEnum.NO_PAY.getStatus());
		orderInfo.setUserId(user.getId());
		orderDao.insert(orderInfo);
		SecondOrder secondOrder = new SecondOrder();
		secondOrder.setGoodsId(goods.getId());
		secondOrder.setOrderId(orderInfo.getId());
		secondOrder.setUserId(user.getId());
		orderDao.insertSecondOrder(secondOrder);
		//写缓存
		redisService.set(OrderKey.getMiaoshaOrderByUidGid, "" + user.getId() + "_" + goods.getId(), secondOrder);
		return orderInfo;
	}
	

	public OrderInfo getOrderById(long orderId) {
		return orderDao.getOrderById(orderId);
	}
	public void deleteOrders() {
		orderDao.deleteOrders();
		orderDao.deleteMiaoshaOrders();
	}

}
