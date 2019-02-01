app.controller("myOperateCtrl",["$scope","$http",function($scope,$http){

	/**
	 * 查看详情
	 */
	$("#operate-container").delegate(".oper-r","click",function(){
		$scope.operR();
	})
	
	/**
	 *修改
	 */
	$("#operate-container").delegate(".oper-u","click",function(){
		$scope.operU();
	})
	
	/**
	 * 删除
	 */
	$("#operate-container").delegate(".oper-d","click",function(){
		$scope.operD();
	})
	
	/**
	 * 增加
	 */
	$("#operate-container").delegate(".oper-c","click",function(){
		$scope.operC();
	})
	
	/**
	 * 返回
	 */
	$("#operate-container").delegate(".oper-back","click",function(){
		history.back();
	})
}]);