package cn.zhang.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService {

	@Autowired
	private BookshopDao bookShopDao;

	/**
	 * 1.使用propagation指定事务的传播行为  即当前事务方法被另一个事物方法调用时
	 * 如何使用事务  默认取值是REQUIRED 即使用调用方法的事务
	 * REQUIRES_NEW:使用自己的事务  调用的事务方法的事务就会被挂起
	 * 2.使用isolation指定事务的隔离级别  最常用的取值是 READ_COMMITTED
	 * 3.默认情况下Spring的声明式事务对所有的运行时异常进行回滚  也可以通过对应的属性进行设置
	 * 包含[noRollbackFor、noRollbackForClassName、rollbackFor、rollbackForClassName]
	 * 通常情况下去默认值即可  一般不对其进行设置
	 * 4.使用readOnly指定事务是否为只读  表示这个事务只读取数据但不进行数据的更新  这样可以帮助数据库引擎优化事务
	 * 若真的是一个只读取数据库值的方法 应当设置只读属性
	 * 5.使用timeout指定事务在强制回滚之前可以保持多久. 这样可以防止长期运行的事务占用资源.单位是秒
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW,
			isolation = Isolation.READ_COMMITTED)
	@Override
	public void buy(String username, String isbn) {
		int price = bookShopDao.findPriceByIsbn(isbn);
		bookShopDao.updateBookStock(isbn);
		bookShopDao.updateUserBalance(username, price);
	}

}
