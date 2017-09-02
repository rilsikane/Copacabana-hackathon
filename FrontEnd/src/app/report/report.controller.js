(function() {
  'use strict';

  angular
    .module('hackathonRoom')
    .controller('ReportController', ReportController);

  /** @ngInject */
  function ReportController($scope,ApiService) {
  	$scope.searchData = {};
    $scope.payment =[];

   	$scope.searchReport = function(){
	   ApiService.postService($scope.searchData,"report.getMonthlyReport.service").then(function(result){
        console.log(result);
      })
    }

    $scope.searchReport();

  }

})();