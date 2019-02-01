var app = angular.module("mainApp",[]);

app.controller("tableDetailUpdateCtrl",["$scope","$http","$stateParams",function(
		$scope,$http,$stateParams){
	
	$scope.type=$stateParams.type;
	
	//配置操作信息，1表示有此功能
	$scope.operate={
		//查
		r:0,
		//更新
		u:1,
		//删除
		d:1,
		//增加
		c:1,
		//返回
		back:1
	}
	
	//表信息
	$scope.tableInf={};
	
	//字段信息
	$scope.fieldInfs=[];
	
	$scope.init = function(){
		if($scope.type=='R'){
			changeOperateStatu();
		}
		if($scope.type!='C'){
			queryDetail();
		}
	}
	
	//查看时不显示删除、修改、增加按钮
	function changeOperateStatu(){
		$scope.operate.u=0;
		$scope.operate.d=0;
		$scope.operate.c=0;
	}
	
	//查看表信息详情
	function queryDetail(){
		$http
		.post("/XJSYS/table/detail",{"searchObj":{"tableId":$stateParams.tableId}})
		.then(function(res){
			var data = res.data;
			var code = data.code;
			if(code!='Q0000'){
				alert(data.msg);
			}else{
				$scope.tableInf = data.result[0];
				$scope.queryField();
			}
		})
	}
	
	//查看表字段信息
	$scope.queryField = function(){
		$http
		.post("/XJSYS/table/field",{"searchObj":{"exportTableName":$scope.tableInf.tableName}})
		.then(function(res){
			$scope.fieldInfs = res.data;
		})
	}
	
	/**
	 * 修改表基本信息
	 */
	$scope.updateBaseInf = function(){
		$http
		.post("/XJSYS/table/update/table",getTableBase())
		.then(function(res){
			if(res.data.code=="U0000"){
				$scope.tableInf = res.data.result[0];
			}else{
				alert(res.data.msg);
			}
		})
	}
	
	/**
	 * 添加表基本信息
	 */
	$scope.createBaseInf = function(){
		$http
		.post("/XJSYS/table/create/table",getTableBase())
		.then(function(res){
			if(res.data.code=="C0000"){
				$scope.tableInf = res.data.result[0];
			}else{
				alert(res.data.msg);
			}
		})
	}
	
	/**
	 * 获取表基本信息修改对象,如果不填则保留原来的值
	 */
	function getTableBase(){
		var searchObj = {};
		var inputs = $("#tableBase input");
		searchObj.tableId=inputs.eq(0).val();
		searchObj.tableName=inputs.eq(1).val();
		searchObj.tableNameCn=inputs.eq(2).val();
		searchObj.tableDescription=inputs.eq(3).val();
		return {"searchObj":searchObj};
	}
}]);