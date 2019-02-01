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
		
		//表信息管理
		sp.state("tableManager",{
			url:'/tableManager',
			templateUrl:'views/tableinf/tableManager.html',
			controller:'tableManagerCtrl',
			resolve:{
				loadMyCtrl:["$ocLazyLoad",function($ocLazyLoad){
					return $ocLazyLoad.load({
						name:'app.tableManagerCtrl',
						files:['scripts/tableinf/tableManager.js']
					})
				}]
			}
		});
		
		//更新、查看表信息
		sp.state("tableDetailUpdate",{
			url:'/tableDetailUpdate?tableId&type',
			templateUrl:'views/tableinf/tableDetailUpdate.html',
			controller:'tableDetailUpdateCtrl',
			resolve:{
				loadMyCtrl:["$ocLazyLoad",function($ocLazyLoad){
					return $ocLazyLoad.load({
						name:'app.tableDetailUpdateCtrl',
						files:['scripts/tableinf/tableDetailUpdate.js']
					})
				}]
			}
		});
}]);



