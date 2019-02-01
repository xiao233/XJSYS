app.controller("myPageCtrl",["$scope","$http",function($scope,$http){
	
	/*
	 * 引入分页要在对应的js初始化以下代码,统一查询方法$scope.searchInf();
	 * 
	 * 
	//分页信息
	$scope.page={
			//每页大小
			pageSize:'20',
			//总共页数
			pageTotal:1000,
			//总共数据
			dataTotal:10001,
			//当前页
			pageCurr:2
	};
	*/
	
	$scope.init = function(){
		showPageArea();
	}
	
	//首页
	$scope.firstPage = function(){
		$scope.page.pageCurr=1;
		showPageArea();
		
		$scope.searchInf();
		bulePage($scope.page.pageCurr);
	}
	//上一页
	$scope.prePage = function(){
		if($scope.page.pageCurr>1){
			$scope.page.pageCurr-=1;
		}
		
		$scope.searchInf();
		showPageArea();
		bulePage($scope.page.pageCurr);
	}
	//下一页
	$scope.nextPage = function(){
		if($scope.page.pageCurr<$scope.page.pageTotal){
			$scope.page.pageCurr+=1;
		}else{
			$scope.page.pageCurr=$scope.page.pageTotal;
		}
		
		$scope.searchInf();
		showPageArea();
		bulePage($scope.page.pageCurr);
	}
	//尾页
	$scope.lastPage = function(){
		$scope.page.pageCurr=$scope.page.pageTotal;
		
		$scope.searchInf();
		showPageArea();
		bulePage($scope.page.pageCurr);
	}
	
	//跳转指定页
	$scope.goPage = function(){
		var pageValue = $("#unique-page").val()*1;
		if(pageValue>$scope.page.pageTotal||pageValue<1){
			alert("请选择1~"+$scope.page.pageTotal+"的页数")
			return;
		}
		$scope.page.pageCurr=pageValue;
		
		$scope.searchInf();
		showPageArea();
		bulePage($scope.page.pageCurr);
	}
	
	$scope.changePageSize = function(){
		$scope.page.pageCurr=1;
		$scope.searchInf();
		showPageArea();
		bulePage($scope.page.pageCurr);
	}
	
	//选择指定页数
	$(".page-index>div").each(function(){
		$(this).click(function(){
			var pageValue = $(this).text()*1;
			$scope.page.pageCurr=pageValue;
			
			$scope.searchInf();
			bulePage($scope.page.pageCurr);
			$("#span-pageCurr").text($scope.page.pageCurr);
		});
	});
	
	
	
	//显示页数区间
	function showPageArea(){
		var showFirst = $(".page-index>div").eq(0).text()*1;
		var showLast = $(".page-index>div:last").text()*1;
		
		if($scope.page.pageCurr<showFirst){//当前页比显示第一页数小
			var index = 0;
			$(".page-index>div").each(function(){
				$(this).css("background-color","white");
				$(this).text($scope.page.pageCurr+index);
				index++;
			});
			$(".page-index>div").eq(0).css("background-color","#00BFFF");
		}else if($scope.page.pageCurr>=showFirst
				&&$scope.page.pageCurr<=showLast){
			$(".page-index>div").each(function(){
				if($(this).text()*1==$scope.page.pageCurr){
					$(this).css("background-color","#00BFFF");
				}else{
					$(this).css("background-color","white");
				}
			});
		}else{//当前页比显示最后一页数大
			var index = 0;
			$(".page-index>div").each(function(){
				$(this).css("background-color","white");
				$(this).text($scope.page.pageCurr-4+index);
				index++;
			});
			$(".page-index>div:last").css("background-color","#00BFFF");
		}
	}
	
	//跳转指定页
	function searchPage(){
		
	}
	
	//被选择的页数变蓝
	function bulePage(currPage){
		var index = 0;
		$(".page-index>div").css("background-color","white");
		$(".page-index>div").each(function(){
			if($(this).text()*1==currPage)
				$(this).css("background-color","#00BFFF");
		});
	}
	
}]);