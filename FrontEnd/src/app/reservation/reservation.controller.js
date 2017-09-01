(function() {
  'use strict';

  angular
    .module('hackathonRoom')
    .controller('ReservationController', ReservationController);

  /** @ngInject */
  function ReservationController($scope,$stateParams,ApiService) {
  	console.log($stateParams.room);
  	$scope.slides = [];
  	ApiService.postService($stateParams.room,"attach.searchRoomAttach.service").then(function(result){
	    
  		for(var i=0;i<result.length;i++){
  			var attach = result[i];
  			$scope.slides.push("http://174.138.25.181:8407"+attach.attachPath);
  		}
	    console.log($scope.slides);
	})
  }
    
   })();