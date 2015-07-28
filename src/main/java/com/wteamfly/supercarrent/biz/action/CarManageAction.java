package com.wteamfly.supercarrent.biz.action;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.wteamfly.supercarrent.biz.service.CarManageService;
import com.wteamfly.supercarrent.biz.service.ResultMessage;
import com.wteamfly.supercarrent.biz.service.SuperService;
import com.wteamfly.supercarrent.entity.po.CarInfoPo;
import com.wteamfly.supercarrent.entity.po.PaginationPo;
import com.wteamfly.supercarrent.entity.po.UserPo;
import com.wteamfly.supercarrent.entity.vo.CarInfoVo;
import com.wteamfly.supercarrent.entity.vo.PaginationVo;
import com.wteamfly.supercarrent.entity.vo.UserVo;
import com.wteamfly.supercarrent.helper.ConstantVar;
/**
 * 汽车管理类Action.
 * @author Imissyou
 *
 */
public class CarManageAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2754960463051409372L;
	/**
	 * log4的实体对象.
	 */
	private static Logger logger = LogManager.getLogger(CarManageAction.class);

	/**
	 * 汽车信息实体.
	 */
	private CarInfoVo carInfo;
	
	/**
	 * 当前用户实体.
	 */
	private UserVo currentUser;
	
	/**
	 * 返回信息实体.
	 */
	private ResultMessage resultMessage;
	
	 /**
     * 分页信息.
     */
    private PaginationVo page;
	
	
    /**
	 * 获取carIfo的方法.
	 * @return  carIfo
	 */
	public final CarInfoVo getCarInfo() {
		return carInfo;
	}
	/**
     * 设置CarInfo的方法.
     * 
     * @param carIfo
     *            赋值给CarInfo的值
     */
	public final void setCarInfo(final CarInfoVo carInfo) {
		this.carInfo = carInfo;
	}

	/**
	 * 获取currentUser的方法.
	 * @return  currentUser
	 */
	public final UserVo getCurrentUser() {
		return currentUser;
	}
	
	/**
     * 设置currentUser的方法.
     * 
     * @param currentUser
     *            赋值给currentUser的值
     */
	public final void setCurrentUser(final UserVo currentUser) {
		this.currentUser = currentUser;
	}
	
	/**
	 * 获取resultMessage的方法.
	 * @return resultMessage
	 */
	public final ResultMessage getResultMessage() {
		return resultMessage;
	}
	
	/**
     * 设置resultMessage的方法.
     * 
     * @param resultMessage
     *            赋值给resultMessage的值
     */
	public final void setResultMessage(final ResultMessage resultMessage) {
		this.resultMessage = resultMessage;
	} 
	
	/**
	 * 获取分页信息.
	 * @return page 
	 */
	public final PaginationVo getPage() {
		return page;
	}
    /**
     * 设置分页信息.
     * @param page
     */
	public final void setPage(final PaginationVo page) {
		this.page = page;
	}
	
	/**
	 * 增加汽车.
	 */
	public final void addCar() {
        logger.debug("进入addCar方法");
        final CarInfoPo carInfoPo = carInfo.voToPo(CarInfoPo.class);
        final UserPo currentUserPo = currentUser.voToPo(UserPo.class);
        final SuperService service = SuperService.getInstance();
        carInfoPo.setCarState(ConstantVar.STATEWITE);
        resultMessage = service.addEntity(carInfoPo, currentUserPo);
        resultMessage.setUserToken(true);
        logger.debug("退出addCar方法");
	}
	
	 /**
     * 逻辑删除汽车.
     */
    public final void deleteCar() {
        logger.debug("进入deleteCar方法");
        final CarInfoPo carInfoPo = carInfo.voToPo(CarInfoPo.class);
        final UserPo currentUserPo = currentUser.voToPo(UserPo.class);
        final SuperService service = SuperService.getInstance();
        resultMessage = service.logicDeleteEntity(carInfoPo, currentUserPo);
        resultMessage.setUserToken(true);
        logger.debug("退出deleteCar方法");
    }

    /**
     * 编辑汽车.
     */
    public final void editCar() {
        logger.debug("进入editCar方法");
        final CarInfoPo carInfoPo = carInfo.voToPo(CarInfoPo.class);
        final UserPo currentUserPo = currentUser.voToPo(UserPo.class);
        final SuperService service = SuperService.getInstance();
        resultMessage = service.editEntity(carInfoPo, currentUserPo);
        resultMessage.setUserToken(true);
        logger.debug("退出editCar方法");
    }

    /**
     * 查询汽车分页列表.
     */
    public final void pageCar() {
        logger.debug("进入pageCar方法");
        final PaginationPo paginationPo = page.voToPo(PaginationPo.class);
        CarInfoPo carInfoPo = null;
        if (carInfo != null) {
            carInfoPo = carInfo.voToPo(CarInfoPo.class);
        } else {
        	carInfoPo = new CarInfoPo();
        }
        carInfoPo.setCarState(ConstantVar.STATEWITE);
        final SuperService service = SuperService.getInstance();
        resultMessage = service.pageEntity(paginationPo, carInfoPo);
        resultMessage.setUserToken(true);
        logger.debug("退出pageCar方法");
    }
    
    /**
     * 查询汽车列表.
     */
    public final void getAllCars() {
        logger.debug("进入getAllCars方法");
        final PaginationPo paginationPo = page.voToPo(PaginationPo.class);
        CarInfoPo carInfoPo = null;
        
        final CarManageService service = CarManageService.getInstance();
        resultMessage = service.queryALLCarList(paginationPo);
        resultMessage.setUserToken(true);
        logger.debug("退出getAllCars方法");
    }
    
    /**
     * 根据品牌来找车.
     */
    public final void getCarsByBrand() {
    	logger.debug("进入getCarsByBrand方法");
    	final PaginationPo paginationPo = page.voToPo(PaginationPo.class);
    	CarInfoPo carInfoPo = null;
    	if(carInfo != null) {
    		carInfoPo = carInfo.voToPo(CarInfoPo.class);
    	} else {
    		carInfoPo = new CarInfoPo();
    	}
    	carInfoPo.setCarState(ConstantVar.STATEWITE);
    	final CarManageService carManageService = CarManageService.getInstance();
    	resultMessage = carManageService.findCarsByType(paginationPo,carInfoPo);
    	resultMessage.setUserToken(true);
    	
    	logger.debug("退出getCarsByBrand");
    }
    
    /**
     * 根据价格来对车辆进行排序.
     */
    public final void orderCarsByMoney() {
    	logger.debug("进入orderCarsByMoney方法");
    	final PaginationPo paginationPo = page.voToPo(PaginationPo.class);
    	
    	final CarManageService carManageService = CarManageService.getInstance();
    	
    	resultMessage = carManageService.orderCarsByMoney(paginationPo);
    	resultMessage.setUserToken(true);
    	
    	logger.debug("退出orderCarsByMoney");
    }
    
    /**
     * 根据类型来找车.
     */
    public final void getCarsByType() {
    	logger.debug("进入getCarsByType方法");
    	final PaginationPo paginationPo = page.voToPo(PaginationPo.class);
    	CarInfoPo carInfoPo = null;
    	if(carInfo != null) {
    		carInfoPo = carInfo.voToPo(CarInfoPo.class);
    	} else {
    		carInfoPo = new CarInfoPo();
    	}
    	carInfoPo.setCarState(ConstantVar.STATEWITE);
    	final CarManageService carManageService = CarManageService.getInstance();
    	resultMessage = carManageService.findCarsByType(paginationPo,carInfoPo);
    	resultMessage.setUserToken(true);
    	
    	logger.debug("退出getCarsByType");
    }
    
    /**
     * 根据根据Carid查必须的订单明细.
     */
    public final void getOrderDetailByCarId() {
    	logger.debug("进入Action 层的getOrderDetailByCarId方法");
    	CarInfoPo carInfoPo = null;
    	//开始逻辑处理
    	if(carInfo.getCarInfoId() != null ) {
    		carInfoPo = carInfo.voToPo(CarInfoPo.class);
    		final CarManageService carManageService = CarManageService.getInstance();
    		resultMessage = carManageService.findOrderDetailByCarId(carInfoPo);
    		resultMessage.setUserToken(true);
    	} else {
    		resultMessage.setServiceResult(false);
    		resultMessage.setResultInfo("查询车辆服务不存在");
    		resultMessage.setUserToken(false);
    	}
    	logger.debug("进入Action 层的getOrderDetailByCarId方法");
    }
    
    /**
     * 根据根据Carid查必须的所有的订单明细.
     */
    public final void getAllOrderDetailByCarId() {
    	logger.debug("进入getAllOrderDetailByCarId方法");
    	CarInfoPo carInfoPo = null;
    	//开始逻辑处理
    	if(carInfo.getCarInfoId() != null ) {
    		carInfoPo = carInfo.voToPo(CarInfoPo.class);
    		final CarManageService carManageService = CarManageService.getInstance();
    		resultMessage = carManageService.findAllOrderDetailByCarId(carInfoPo);
    		resultMessage.setUserToken(true);
    	} else {
    		resultMessage.setServiceResult(false);
    		resultMessage.setResultInfo("查询车辆服务不存在");
    		resultMessage.setUserToken(true);
    	}
    	resultMessage.setUserToken(true);
    	logger.debug("进入getAllOrderDetailByCarId方法");
    }
}
