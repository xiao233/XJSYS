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
		c:0,
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
		.post("/XJSYS/field/query",{"searchObj":{"exportTableName":$scope.tableInf.tableName}})
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
	
	//选中的一行变颜色
	$("table").delegate("tbody>tr","click",function(){
		$(this).siblings().removeClass("xj-content-color");
		$(this).addClass("xj-content-color");
	});
	
	//修改
	$scope.operU = function(){
		if($(".xj-content-color").length<=0){
			alert("请选择一条数据！");
			return;
		}
		$http
		.post("/XJSYS/field/update",getFieldInf($(".xj-content-color input")))
		.then(function(res){
			alert(res.data.msg);
		});
	}
	
	//删除
	$scope.operD = function(){
		if($(".xj-content-color").length<=0){
			alert("请选择一条数据！");
			return;
		}
		$http
		.post("/XJSYS/field/delete",getFieldInf($(".xj-content-color input")))
		.then(function(res){
			if(res.data.code=="D0000"){
				$scope.queryField();
			}else{
				alert(res.data.msg);
			}
		});
	}
	
	//增加
	$scope.operC = function(){
		$http
		.post("/XJSYS/field/create",getFieldInf($("#fields_inf>tr:last").find("input")))
		.then(function(res){
			alert(res.data.msg);
			if(res.data.code=="C0000"){
				$("#add_fields").text("+点此添加记录");
				$("#fields_inf>tr:last").find("input").first().prop("disabled","disabled");
				$scope.operate.c=0;
				$scope.operate.u=1;
				$scope.operate.d=1;
			}
			
		});
	}
	
	//添加字段输入框
	$scope.addStruct = function(){
		var text = $("#add_fields").text();
		if(text.trim()=="+点此添加记录"){
			var content = "<tr>";
			content+="<td><input  placeholder='请输入字段id'/></td>";
			content+="<td><input  placeholder='请输入字段名'/></td>";
			content+="<td><input  placeholder='请输入字段中文名'/></td>";
			content+="<td><input  placeholder='请输入字段是否显示'/></td>";
			content+="</tr>";
			$("#fields_inf").append(content);
			//添加按钮出现，数据入库
			$scope.operate.c=1;
			$scope.operate.u=0;
			$scope.operate.d=0;
			$("#add_fields").text("请添加数据，点击下方'增加'按钮添加");
		}
		
	}
	
	/**
	 * 获取表基本信息修改、增加对象,如果不填则保留原来的值
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
	
	/*
	 * 获取字段信息修改、增加对象
	 */
	function getFieldInf(tds){
		var searchObj = {};
		searchObj.tableId=$("#tableBase input").eq(0).val();
		searchObj.fieldId=tds.eq(0).val();
		searchObj.fieldName=tds.eq(1).val();
		searchObj.fieldNameCn=tds.eq(2).val();
		searchObj.isShow=tds.eq(3).val();
		return {"searchObj":searchObj};
	}
}]);