var app = angular.module("mainApp",[]);

app.controller("addrManagerCtrl",["$scope","$http","$state",function($scope,$http,$state){
	
	//分页信息
	$scope.page={
			//每页大小
			pageSize:'10',
			//总共页数
			pageTotal:0,
			//总共数据
			dataTotal:0,
			//当前页
			pageCurr:1
	};
	
	//配置操作信息，1表示有此功能
	$scope.operate={
		//查
		r:1,
		//更新
		u:1,
		//删除
		d:1,
		//增加
		c:1,
		//返回
		back:0
	}
	
	$scope.fileTypes=[];
	
	$scope.init = function(){
		$scope.page.pageCurr=1;
		queryCountry();
	}
	
	//初始化查询国家信息
	function queryCountry(){
		$http
		.post(SERVLET_CONTEXT+"dropdown/query",{"searchObj":{dropdownName:"FILE_TYPE"}})
		.then(function(res){
			$scope.fileTypes=res.data;
		});
	}
	
	//分页查询
	$scope.searchInf = function(){
		
	}
	
	
	
}]);