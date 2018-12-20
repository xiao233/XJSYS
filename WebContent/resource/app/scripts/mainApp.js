var app = angular.module("mainApp",["ui.router","oc.lazyLoad"]);

app.config(["$stateProvider","$ocLazyLoadProvider","$urlRouterProvider",
	function($stateProvider,$ocLazyLoadProvider,$urlRouterProvider){
		var sp = $stateProvider;
		var urp = $urlRouterProvider;
		
		/*urp.when("/qaq",{template:'<p1>你怎么就这么牛呢</p1>'}).otherwise("/first");*/
		
		
		//欢迎页
		sp.state("welcome",{
			 url:'/welcome',
			 templateUrl:'welcome.html'
		});
		
		//找不到页
		sp.state("error",{
			 url:'/error',
			 templateUrl:'error.html'
		});
		
		//导入导出
		sp.state("exImport",{
			 url:'/exImport',
             templateUrl:'views/ex_import/exImport.html',
             controller:'exImportCtrl',
             resolve:{
            	 loadMyCtrl:["$ocLazyLoad",function($ocLazyLoad){
            		 return $ocLazyLoad.load({
            			 name:'app.exImportCtrl',
            			 files:['scripts/ex_import/exImport.js']});
            	 }]
             }
		});
		
		//二维码
		sp.state("enDecode",{
			 url:'/enDecode',
            templateUrl:'views/en_decode/enDecode.html',
            controller:'enDecodeCtrl',
            resolve:{
           	 loadMyCtrl:["$ocLazyLoad",function($ocLazyLoad){
           		 return $ocLazyLoad.load({
           			 name:'app.enDecodeCtrl',
           			 files:['scripts/en_decode/enDecode.js']});
           	 }]
            }
		});
}]);



app.controller("mainCtrl",["$scope",function($scope){
	$scope.data="oooo";
}]);