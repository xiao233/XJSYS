var app = angular.module("mainApp",[]);
app.controller("exImportCtrl",["$scope","$http",function($scope,$http){
	
	//表信息
	$scope.tableInfs=[];
	//表字段信息
	$scope.tableFieldInfs=[];
	//文件类型
	$scope.fileTypes=[];
	
	var status=['操作前','已选择表','已经选择文件'];
	//文件导入状态
	$scope.importStatu=status[0];
	//导出的表名
	$scope.exportTableName="";
	//导出的文件名
	$scope.exportFileName="";
	//导出的文件类型
	$scope.exportFileType="";
	
	$scope.init=function(){
		searchTableInf();
		searchFileTypes();
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
	
	//获取文件类型
	function searchFileTypes(){
		$http
		.post("/XJSYS/dropdown/query",{"searchObj":{dropdownName:"FILE_TYPE"}})
		.then(function(res){
			$scope.fileTypes=res.data;
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
	
	//获取已经选择表所有能导出的字段信息
	$scope.searchFieldInf = function(){
		var fileName = getFileName();
		$http
			.post("/XJSYS/table/field",{"searchObj":{"exportTableName":$scope.exportTableName}})
			.then(function(res){
				$scope.tableFieldInfs=res.data;
			})
	}
	
	
	//通过选择的表名和当前时间戳生成导出的文件名
	function getFileName(){
		var date = new Date();
		var fileName = $scope.exportTableName+"_"+date.getTime();
		$scope.exportFileName = fileName;
	}
	
	
	
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