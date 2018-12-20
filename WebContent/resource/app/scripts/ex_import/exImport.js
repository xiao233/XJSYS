var app = angular.module("mainApp",[]);
app.controller("exImportCtrl",["$scope","$http",function($scope,$http){
	
	//表信息
	$scope.tableInfs=[];
	
	var status=['操作前','已选择表','已经选择文件'];
	//文件导入状态
	$scope.importStatu=status[0];
	
	$scope.init=function(){
		searchTableInf();
	}
	
	//获取导入的表下拉框
	function searchTableInf(){
		$http
			.post("/XJSYS/table/query")
			.then(function(res){
				var data = res.data;
				var code = data.code;
				if(code!='Q0000'){
					alert(data.msg);
				}else{
					$scope.tableInfs = data.result;
				}
			});
	}
	
	
	//点击导入文件
	$scope.importFile = function(){
		$http
			.post("/XJSYS/table/import/"+$scope.importTableName)
			.then(function(res){
				alert(res.data.code)
			});
		
		
	};
	
	
	$("#fileName").bind("change",function(){
		$scope.importStatu=status[2];
	});
	
	$("#importTableName").bind("change",function(){
		if($(this).val()==null||$(this).val()==""){
			$scope.importStatu=status[0];
		}else{
			$scope.importStatu=status[1];
		}
	});
}]);
