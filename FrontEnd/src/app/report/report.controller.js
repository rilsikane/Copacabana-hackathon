(function() {
  'use strict';

  angular
    .module('hackathonRoom')
    .controller('ReportController', ReportController);

  /** @ngInject */
  function ReportController($scope,ApiService,$rootScope) {
    $scope.payment =[];
    $scope.total = 0;

   	$scope.searchReport = function(){
<<<<<<< HEAD
	   ApiService.postService($scope.searchData,"report.getMonthlyReport.service").then(function(result){
=======
   		var param = {};
         param.userNo = "12";
      // param.userNo = $rootScope.user.userNo;
	ApiService.postService(param,"report.getMonthlyReport.service").then(function(result){
>>>>>>> da1dab42723e0dbb075ea5735f1c4ceffc6b55a0
        console.log(result);
        $scope.payment = result;
        for(var i = 0; i <result.length; i++){
        
        $scope.total += result[i].income;
        console.log($scope.total);
    }
      })
    }

    

    $scope.searchReport();

  }

})();