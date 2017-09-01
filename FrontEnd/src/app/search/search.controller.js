(function() {
  'use strict';

  angular
    .module('hackathonRoom')
    .controller('SearchController', SearchController);

  /** @ngInject */
  function SearchController($scope,ApiService) {
    $scope.priceRageList = [];
    $scope.searchData = {};



    $scope.init = function(){
      $scope.priceRageList = [{id:"1",value:"0-1,000"},
      {id:"2",value:"1,001-3,000"},{id:"3",value:"3,001-6,000"}
      ,{id:"4",value:"6,001-10,000"},{id:"5",value:"มากกว่า 10,000"}];
      $scope.searchData.priceRange = "1";
    }
    $scope.searchRoom = function(){
      
ApiService.postService($scope.searchData,"room.searchAvailRoom.service").then(function(result){
        console.log(result);
      })
    }

    $scope.init();

  }

})();
