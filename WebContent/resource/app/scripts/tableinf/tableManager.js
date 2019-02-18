var app = angular.module("mainApp",[]);

app.controller("tableManagerCtrl",["$scope","$http","$state",function($scope,$http,$state){
	
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
	
	$scope.tableName="";
	//表下拉框
	$scope.tableInfsDrop=[];
	//表信息
	$scope.tableInfs=[];
	//页面初始化
	$scope.init = function(){
		$scope.page.pageCurr=1;
		queryInf();
		$scope.searchInf();
	}
	
	
	//分页查询
	 function queryInf(){
		//查询所有表信息
		$http
		.post("/XJSYS/table/query",{"searchObj":{"tableName":$scope.tableName},
			"pageCurr":$scope.page.pageCurr,
			"pageSize":"0"})
		.then(function(res){
			var data = res.data;
			var code = data.code;
			if(code!='Q0000'){
				alert(data.msg);
			}else{
				$scope.tableInfsDrop = data.result;
			}
		})
	};
	
	//查询按钮
	$scope.searchInfCon=function(){
		$scope.page.pageCurr=1;
		$scope.searchInf();
	}
	
	$scope.searchInf=function(){
		
		$http
		.post("/XJSYS/table/query",{"searchObj":{"tableName":$scope.tableName},
			"pageCurr":$scope.page.pageCurr,
			"pageSize":$scope.page.pageSize})
		.then(function(res){
			var data = res.data;
			var code = data.code;
			if(code!='Q0000'){
				alert(data.msg);
			}else{
				$scope.tableInfs = data.result;
				$scope.page.pageTotal = data.page.pageTotal;
				$scope.page.dataTotal = data.page.dataTotal;
			}
		})
	};
	
	//选中的一行变颜色
	$("table").delegate("tbody>tr","click",function(){
		$(this).siblings().removeClass("xj-content-color");
		$(this).addClass("xj-content-color");
	});
	
	//查看详情
	$scope.operR = function(){
		if($(".xj-content-color").length<=0){
			alert("请选择一条数据！");
			return;
		}
		
		var tableId = $(".xj-content-color>td").eq(0).text();
		$state.go("tableDetailUpdate",{"tableId":tableId,"type":"R"});
	}
	
	//修改
	$scope.operU = function(){
		if($(".xj-content-color").length<=0){
			alert("请选择一条数据！");
			return;
		}
		var tableId = $(".xj-content-color>td").eq(0).text();
		$state.go("tableDetailUpdate",{"tableId":tableId,"type":"U"});
	}
	
	//删除
	$scope.operD = function(){
		if($(".xj-content-color").length<=0){
			alert("请选择一条数据！");
			return;
		}
		var tableId = $(".xj-content-color>td").eq(0).text();
		$http
		.post("/XJSYS/table/delete/table",{"searchObj":{"tableId":tableId}})
		.then(function(res){
			if(res.data.code="D0000"){
				$scope.searchInf();
			}else{
				alert(res.data.msg)
			}
		})
	}
	
	//增加
	$scope.operC = function(){
		$state.go("tableDetailUpdate",{"type":"C"});
	}
	
}]);